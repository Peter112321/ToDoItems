package com.example.ToDoListItem.ToDoListRepository;

import com.example.ToDoListItem.Todolist.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<Todo, Integer> {
}