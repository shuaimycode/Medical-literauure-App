package com.he.yiliao.bean;


public class LiteratureObj {
    private int id;
    private String topic;    // 关于,话题
    private String author;      //作者
    private String time;    //发表时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LiteratureObj(String topic, String author, String time) {
        this.topic = topic;
        this.author = author;
        this.time = time;
    }

    public LiteratureObj() {
    }

    @Override
    public String toString() {
        return "Literature{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

