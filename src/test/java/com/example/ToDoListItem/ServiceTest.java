package com.example.ToDoListItem;

import com.example.ToDoListItem.Service.ToDoListService;
import com.example.ToDoListItem.ToDoListRepository.ToDoListRepository;
import com.example.ToDoListItem.Todolist.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ServiceTest {
    @Mock
    ToDoListRepository toDoListRepository;
    @InjectMocks
    ToDoListService toDoListService;

    @Test
    void should_return_all_Companies_when_get_Company_list_given_3_Companies() {
        //given
        List<Todo> todoList = Arrays.asList(
                new Todo("a", false),
                new Todo("b", false),
                new Todo("c", false)
        );
        when(toDoListRepository.findAll()).thenReturn(todoList);

        //when
        List<Todo> actual = toDoListService.findAll();
        //then
        assertEquals(actual, todoList);
    }

    @Test
    void should_return_company_when_add_Company_given_companyInfo() {
        //given
        Todo newItem = new Todo("d",false);
        when(toDoListRepository.save(newItem))
                .thenReturn(newItem);
        //when
        Todo actual = toDoListService.save(newItem);
        //then
        assertEquals(actual, newItem);
    }

    @Test
    void shouldUpdateTodoWhenGivenAnUpdatedTodo() {
        Todo updated = new Todo("Play Tennis", false);
        updated.setId(13);
        given(toDoListRepository.findById(13)).willReturn(Optional.of(updated));
        given(toDoListRepository.save(any(Todo.class))).willReturn(updated);

        Todo actual = toDoListService.update(updated.getId(), updated);

        assertEquals(updated.getId(), actual.getId());
        assertEquals(updated.getText(), actual.getText());
        assertEquals(updated.isDone(), actual.isDone());
    }
    @Test
    void should_return_delete_message_when_delete_employee_given_id() {
        //given
        doNothing().when(toDoListRepository).deleteById(1);
        //when
        toDoListService.deleteById(1);
        //then
        verify(toDoListRepository, times(1)).deleteById(1);


    }
}
