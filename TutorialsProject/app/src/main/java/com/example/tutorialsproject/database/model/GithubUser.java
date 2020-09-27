package com.example.tutorialsproject.database.model;

public class GithubUser {
    String login;
    int id;
    String html_url;
    double score;
    String avatar_url;

    public GithubUser(String login, int id, String html_url, double score, String avatar_url) {
        this.login = login;
        this.id = id;
        this.html_url = html_url;
        this.score = score;
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
