package com.example.ToDoListItem.Controller;

import com.example.ToDoListItem.DTO.ToDoListRequest;
import com.example.ToDoListItem.DTO.ToDoListResponse;
import com.example.ToDoListItem.Mapper.ToDoListMapper;
import com.example.ToDoListItem.Service.ToDoListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class ToDoListController {
    private final ToDoListService toDoListService;
    private final ToDoListMapper toDoListMapper;

    public ToDoListController(ToDoListService toDoListService, ToDoListMapper toDoListMapper) {
        this.toDoListService = toDoListService;
        this.toDoListMapper = toDoListMapper;
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping
    public List<ToDoListResponse> findAll() {
        return this.toDoListService.findAll().stream()
                .map(todo -> toDoListMapper.toResponse(todo))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ToDoListResponse save(@RequestBody ToDoListRequest toDoListRequest) {
        return toDoListMapper.toResponse(toDoListService.save(toDoListMapper.toEntity(toDoListRequest)));
    }

    @PutMapping("/{id}")
    public ToDoListResponse update(@PathVariable("id") Integer id, @RequestBody ToDoListRequest toDoListRequest) {
        return toDoListMapper.toResponse(toDoListService.update(id, toDoListMapper.toEntity(toDoListRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean deleteById(@PathVariable Integer id) {
        return this.toDoListService.deleteById(id);
    }
}