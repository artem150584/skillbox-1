package org.example.web.dto;

public class RemovedBook {

    private String authorPattern;
    private String titlePattern;
    private String sizePattern;

    public String getAuthorPattern() {
        return authorPattern;
    }

    public void setAuthorPattern(String authorPattern) {
        this.authorPattern = authorPattern;
    }

    public String getTitlePattern() {
        return titlePattern;
    }

    public void setTitlePattern(String titlePattern) {
        this.titlePattern = titlePattern;
    }

    public String getSizePattern() {
        return sizePattern;
    }

    public void setSizePattern(String sizePattern) {
        this.sizePattern = sizePattern;
    }

    @Override
    public String toString() {
        return "RemovedBook{" +
                "authorRegExp='" + authorPattern + '\'' +
                ", titleRegExp='" + titlePattern + '\'' +
                ", sizeRegExp='" + sizePattern + '\'' +
                '}';
    }
}
