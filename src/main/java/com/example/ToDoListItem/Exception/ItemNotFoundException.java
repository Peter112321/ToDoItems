package com.example.ToDoListItem.Exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(){
        super("Item Not Found");
    }
}
