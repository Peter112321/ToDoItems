package com.example.ToDoListItem.Mapper;

import com.example.ToDoListItem.DTO.ToDoListRequest;
import com.example.ToDoListItem.DTO.ToDoListResponse;
import com.example.ToDoListItem.Todolist.Todo;
import org.springframework.stereotype.Component;

@Component
public class ToDoListMapper {
    public Todo toEntity(ToDoListRequest toDoListRequest){
        Todo toDoList=new Todo();

        toDoList.setText(toDoListRequest.getText());
        toDoList.setDone(toDoListRequest.isDone());

        return toDoList;
    }

    public ToDoListResponse toResponse(Todo todo){
        ToDoListResponse toDoListResponse =new ToDoListResponse();

        toDoListResponse.setId(todo.getId());
        toDoListResponse.setText(todo.getText());
        toDoListResponse.setDone(todo.isDone());

        return  toDoListResponse;
    }

}
