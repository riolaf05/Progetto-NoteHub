package it.unirc.pjam.notehub.model.post;


public class Post {
    private String titolo;
    private String contenuto;

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }


    public Post(String titolo, String contenuto) {
        this.titolo = titolo;
        this.contenuto = contenuto;
    }

    @Override
    public String toString() {
        return  titolo + ": " + contenuto;
    }
}
