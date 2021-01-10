package org.example.web.dto;

public class RemovedBook {

    private Integer id;
    private String authorRegExp;
    private String titleRegExp;
    private String sizeRegExp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorRegExp() {
        return authorRegExp;
    }

    public void setAuthorRegExp(String authorRegExp) {
        this.authorRegExp = authorRegExp;
    }

    public String getTitleRegExp() {
        return titleRegExp;
    }

    public void setTitleRegExp(String titleRegExp) {
        this.titleRegExp = titleRegExp;
    }

    public String getSizeRegExp() {
        return sizeRegExp;
    }

    public void setSizeRegExp(String sizeRegExp) {
        this.sizeRegExp = sizeRegExp;
    }

    @Override
    public String toString() {
        return "RemovedBook{" +
                "id=" + id +
                ", authorRegExp='" + authorRegExp + '\'' +
                ", titleRegExp='" + titleRegExp + '\'' +
                ", sizeRegExp='" + sizeRegExp + '\'' +
                '}';
    }
}
