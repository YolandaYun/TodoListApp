package com.springboot.todo.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo("userA","walk mingye and clean poop",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo("userA","listen to the birds sing",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo("userA","visit John at sesame street 10:00",
                LocalDate.now().plusYears(3), false ));
    }

    public List<Todo> findByUsername(String username){
        return todos;
    }

    public void addTodos(String username, String description, LocalDate targetDate, boolean done){
        todos.add(new Todo(username, description, targetDate, done));
    }
}
