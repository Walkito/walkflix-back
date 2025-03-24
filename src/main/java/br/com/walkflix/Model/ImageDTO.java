package br.com.walkflix.Model;

public class ImageDTO {
    private String imageB64;

    private String fileName;

    public ImageDTO() {
    }

    public String getImageB64() {
        return imageB64;
    }

    public void setImageB64(String imageB64) {
        this.imageB64 = imageB64;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
