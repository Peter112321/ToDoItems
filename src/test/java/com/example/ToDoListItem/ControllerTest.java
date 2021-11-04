package com.example.ToDoListItem;

import com.example.ToDoListItem.ToDoListRepository.ToDoListRepository;
import com.example.ToDoListItem.Todolist.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(statements = "alter table todo alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ToDoListRepository toDoListRepository;

    @BeforeEach
    void setup() {
        toDoListRepository.deleteAll();
    }

    @Test
    void should_return_todolist_when_get_todolist() throws Exception {
        //given
        Todo item1 = new Todo("1", false);
        Todo item2 = new Todo("2", false);
        toDoListRepository.save(item1);
        toDoListRepository.save(item2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/todos"));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(item1.getId()))
                .andExpect(jsonPath("$[0].text").value(item1.getText()))
                .andExpect(jsonPath("$[0].done").value(item1.isDone()))
                .andExpect(jsonPath("$[1].id").value(item2.getId()))
                .andExpect(jsonPath("$[1].text").value(item2.getText()))
                .andExpect(jsonPath("$[1].done").value(item2.isDone()));
    }

    @Test
    void should_update_item_when_update_given_udpated_item() throws Exception {
        Todo unsaved = new Todo("KFC Dinner", false);
        Todo updated = toDoListRepository.save(unsaved);
        updated.setText("Go Home");
        updated.setDone(true);

        ResultActions result = mockMvc.perform(
                put("/todos/" + updated.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated))
        );

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updated.getId()))
                .andExpect(jsonPath("$.text").value(updated.getText()))
                .andExpect(jsonPath("$.done").value(updated.isDone()))
        ;
    }

    @Test
    void Should_add_item_when_add_item_given_item_info() throws Exception {
        //given

        Todo add = new Todo("NewName", false);

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(add)));
        //when
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").value("NewName"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_get_deleted_success_message_when_delete_item_given_item_id() throws Exception {
        //given
        Todo employee = new Todo("a", false);
        toDoListRepository.save(employee);

        //when
        ResultActions resultActions = mockMvc
                .perform(delete("/todos/1"));

        //then
        resultActions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.text").doesNotExist());
    }


}