//package TemaCurs2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class Grupa {

    private final static int DIMENSIUNE_INITIALA = 2;
    private MainStudenti[] studenteList;
    private int count;

    private static Grupa singleton;

    private Grupa() {
        studenteList = new MainStudenti[DIMENSIUNE_INITIALA];
    }

    public static Grupa getInstance() {
        if (singleton == null) {
            singleton = new Grupa();
        }
        return singleton;
    }

    public boolean addStudent(MainStudenti student) {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            String addStudent = "INSERT INTO StudentsTable values (null, '" + student.getPrenume() +
                    "', '" + student.getNume() + "', '" + student.getCnp() + "');";
            statement.executeUpdate(addStudent);
            getStudentList();
        } catch (SQLException ex) {
ex.printStackTrace();

        }
        return true;
    }

    public boolean adaugaStudent(MainStudenti a) {
        if (a == null) return false;

        // TODO: scrie mai jos o secventa de cod care sa asigure faptul ca animalul "a" dat prin parametru
        //       NU va fi adaugat in array-ul "animale" daca este EGAL cu un animal deja adaugat. (caz in care
        //       se va returna valoarea false)
        for (int i = 0; i < count; i++) {
            if (studenteList[i].equals(a)) {
                return false;
            }
        }
//            System.out.println(studenteList.length+ "cat e");
//            System.out.println(getStudenteList().length + "e mai scurta");
        if (count == studenteList.length) {
            MainStudenti[] aux = new MainStudenti[studenteList.length * 2];
            //  MainStudenti [] aux = new MainStudenti[studenteList.length ];
            System.arraycopy(studenteList, 0, aux, 0, studenteList.length);
            studenteList = aux;
        }

        studenteList[count++] = a;
        return true;
    }

    public MainStudenti stergeStudent(int id) {
        MainStudenti aux = null;
        int index = -1;

        for (int i = 0; i < count; i++) {
            if (studenteList[i].getId() == id) {
                aux = studenteList[i];
                index = i;
                break;
            }
        }

        if (index == -1) return null;

        for (int i = index; i < count - 1; i++) {
            studenteList[i] = studenteList[i + 1];
        }

        count--;
        return aux;
    }

    public void deleteStudent(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            String delete = "delete from StudentsTable where id=" + id + ";";
            statement.execute(delete);
            getStudentList();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public MainStudenti[] getStudenteList() {
        return Utils.copy(studenteList);

    }

    public List<MainStudenti> getStudentList() {
        List<MainStudenti> result = new ArrayList();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            String getStudents = "select * from StudentsTable;";
            ResultSet rs = statement.executeQuery(getStudents);
            while (rs.next()) {
                MainStudenti student = new MainStudenti(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                result.add(student);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
