package com.example.training.util;

public class TagCount {
    private String tagName;
    private int news;
    private int blog;
    private int forum;
    private int social;
    private int comment;
    private int qa;
    private int video;
    private int type;

    public TagCount() {
    }

    public TagCount(String tagName, int news, int blog, int forum, int social, int comment, int qa, int video, int type) {
        this.tagName = tagName;
        this.news = news;
        this.blog = blog;
        this.forum = forum;
        this.social = social;
        this.comment = comment;
        this.qa = qa;
        this.video = video;
        this.type = type;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }

    public int getBlog() {
        return blog;
    }

    public void setBlog(int blog) {
        this.blog = blog;
    }

    public int getForum() {
        return forum;
    }

    public void setForum(int forum) {
        this.forum = forum;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getQa() {
        return qa;
    }

    public void setQa(int qa) {
        this.qa = qa;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}