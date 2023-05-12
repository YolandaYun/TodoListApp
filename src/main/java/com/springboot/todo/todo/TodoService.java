package com.springboot.todo.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo("in28minutes","Learn AWS",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo("in28minutes","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo("in28minutes","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }

    public List<Todo> findByUsername(String username){
        return todos;
    }

    public void addTodos(String username, String description, LocalDate targetDate, boolean done){
        todos.add(new Todo(username, description, targetDate, done));
    }
}
