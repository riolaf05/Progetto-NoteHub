package it.unirc.pjam.notehub.model.appunto;

public class Appunto {


    private String fileFileName;


    public Appunto(String fileFileName) {

        this.fileFileName=fileFileName;


    }


    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    @Override
    public String toString() {
        return fileFileName;
    }
}
