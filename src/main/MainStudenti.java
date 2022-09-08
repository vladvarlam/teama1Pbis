//package TemaCurs2;

public class MainStudenti {

    private static int idGenerator = 0;
    private String prenume;
    private String nume;
    private String cnp;
    private final int id;


    public MainStudenti(String prenume, String nume, String cnp) {
        this.prenume = prenume;
        this.nume = nume;
        this.cnp = cnp;
        id = idGenerator++;
    }

    public MainStudenti(int id, String prenume, String nume, String cnp) {
        this.prenume = prenume;
        this.nume = nume;
        this.cnp = cnp;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MainStudenti{" +
                "prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        //if (this == o) return true;
        if (!(other instanceof MainStudenti)) {
            return false;
        }

        final MainStudenti a = (MainStudenti) other;

        return this.cnp.equals(a.cnp);
    }


    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        MainStudenti.idGenerator = idGenerator;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getId() {
        return id;
    }
}
