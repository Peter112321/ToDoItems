package com.example.ToDoListItem.DTO;

public class ToDoListResponse {
    private Integer id;
    private String text;
    private Boolean done;

    public ToDoListResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}


