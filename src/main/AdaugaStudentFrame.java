//package TemaCurs2;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

public class AdaugaStudentFrame extends JFrame {
    private Grupa grupa;
    private Subject subject;

    public AdaugaStudentFrame(Grupa grupa, Subject subject, int height, int x, int y) {
        super("Adauga un student");
        initComponents();
        this.grupa = grupa;
        this.subject = subject;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                subject.notifyWindowClosed();
            }
        });

        setSize(200, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(x, y);
    }

    private void adaugaStudent() {
        try {
            String prenume = prenumeField.getText();
            String nume = numeField.getText();
            String cnp = cnpField.getText();

            // new AnimalTerestru(nume, varsta, greutate, regiuneField.getText())
            MainStudenti a = new MainStudenti(prenume, nume, cnp);
            List<MainStudenti> listaCuCnp = grupa.getStudentList().stream().filter(st -> cnp.equals(st.getCnp())).collect(Collectors.toList());
            if (listaCuCnp.size() > 0) {
                JOptionPane.showMessageDialog(null, "CNP deja folosit");
                System.out.println("CNP deja introdus");
                return;
            }
            //   grupa.adaugaStudent(a);
            grupa.addStudent(a);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numar introdus invalid");
            numeField.setText("");
            cnpField.setText("");
            return;
        }

        numeField.setText("");
        prenumeField.setText("");
        cnpField.setText("");
        regiuneField.setText("");
        subject.notifyListeners();
    }

    private void initComponents() {
        prenumeLabel = new JLabel("Prenume ");
        numeLabel = new JLabel("Nume ");
        cnpLabel = new JLabel("Cnp ");
        prenumeField = new JTextField();
        numeField = new JTextField();
        cnpField = new JTextField();
        acvaticButton = new JRadioButton("Studenti", true);
//        terestruButton = new JRadioButton("Terestru", false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(acvaticButton);
//        bg.add(terestruButton);
        regiuneLabel = new JLabel("Regiune");
        regiuneField = new JTextField();
        adaugaButton = new JButton("Adauga");
        anuleazaButton = new JButton("Inchide");

        adaugaButton.addActionListener(ev -> adaugaStudent());
        anuleazaButton.addActionListener(ev -> {
            dispose();
            subject.notifyWindowClosed();
        });

        numeField.setSize(numeField.getWidth(), 5);

        regiuneLabel.setVisible(false);
        regiuneField.setVisible(false);

        acvaticButton.addItemListener(ie -> {
            if (acvaticButton.isSelected()) {
                regiuneLabel.setVisible(false);
                regiuneField.setVisible(false);
            } else {
                regiuneLabel.setVisible(true);
                regiuneField.setVisible(true);
            }
        });

        add(prenumeLabel);
        add(prenumeField);
        add(numeLabel);
        add(numeField);
        add(cnpLabel);
        add(cnpField);
        add(acvaticButton);
//        add(terestruButton);
        add(regiuneLabel);
        add(regiuneField);
        add(adaugaButton);
        add(anuleazaButton);

        setLayout(new GridLayout(6, 2));
    }

    private JLabel prenumeLabel;
    private JTextField prenumeField;
    private JLabel cnpLabel;
    private JTextField cnpField;
    private JLabel numeLabel;
    private JTextField numeField;
    private JRadioButton acvaticButton;
    //    private JRadioButton terestruButton;
    private JLabel regiuneLabel;
    private JTextField regiuneField;
    private JButton adaugaButton;
    private JButton anuleazaButton;

}
