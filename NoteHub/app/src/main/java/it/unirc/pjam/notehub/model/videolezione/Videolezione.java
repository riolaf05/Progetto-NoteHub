package it.unirc.pjam.notehub.model.videolezione;

/**
 * Created by USER on 14/06/2017.
 */

public class Videolezione {
    private VideolezioneId id;
    private String url;

    public Videolezione() {
    }

    public Videolezione(VideolezioneId id, String url) {
        this.id = id;
        this.url = url;
    }

    public VideolezioneId getId() {
        return id;
    }

    public void setId(VideolezioneId id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Videolezione{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
