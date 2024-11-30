package com.mythovac.demo.entity;

/**
 * Message数据表的实体类
 * */
public class Message {
    private int id;
    private String username;
    private String title;
    private String content;
    private String time;

    public Message(){}
    public Message(String username,String title, String content, String time) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
