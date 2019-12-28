package guru.springframework.domain;

public class graphe {
    private String nomDoc;
    private int nombre;

    public graphe(String nomDoc, int nombre) {
        this.nomDoc = nomDoc;
        this.nombre = nombre;
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
