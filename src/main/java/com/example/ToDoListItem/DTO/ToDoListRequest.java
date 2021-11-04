package com.example.ToDoListItem.DTO;

public class ToDoListRequest {
    private Integer id;
    private String text;
    private Boolean done;

    public ToDoListRequest() {
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


