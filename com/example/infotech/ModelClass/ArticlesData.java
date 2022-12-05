package com.example.infotech.ModelClass;

public class ArticlesData {

    private String title;
    private String urlToImage;
    private String description;
    private String content;
    private String url;


    public ArticlesData(String title, String urlToImage, String description, String content, String url) {
        this.title = title;
        this.urlToImage = urlToImage;
        this.description = description;
        this.content = content;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
