package com.example.ToDoListItem.Service;


import com.example.ToDoListItem.Exception.ItemNotFoundException;
import com.example.ToDoListItem.ToDoListRepository.ToDoListRepository;
import com.example.ToDoListItem.Todolist.Todo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ToDoListService {

    private ToDoListRepository toDoListRepository;

    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public List<Todo> findAll() {
        return toDoListRepository.findAll();
    }

    public Todo save(Todo todo) {
        return toDoListRepository.save(todo);
    }

    public Todo update (Integer id, Todo update) {
        Todo todoItem = toDoListRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        if (update.getText() != null){
            todoItem.setText(update.getText());
        }
        if (update.isDone() != null){
            todoItem.setDone(update.isDone());
        }
        return toDoListRepository.save(todoItem);
    }

    public Boolean deleteById(Integer id) {
        this.toDoListRepository.deleteById(id);
        return true;
    }
}

