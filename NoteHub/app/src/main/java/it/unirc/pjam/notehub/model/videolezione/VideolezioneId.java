package it.unirc.pjam.notehub.model.videolezione;

/**
 * Created by USER on 14/06/2017.
 */

public class VideolezioneId implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String titolo;
    private String argomento;

    public VideolezioneId() {
    }

    public VideolezioneId(String titolo, String argomento) {
        this.titolo = titolo;
        this.argomento = argomento;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }
}
