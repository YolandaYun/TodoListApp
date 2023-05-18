package com.springboot.todo.todo;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo("userA","walk dog and clean poop",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo("userA","listen to the birds sing",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo("userA","visit John at sesame street 10:00",
                LocalDate.now().plusYears(3), false ));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        todos.add(new Todo(username, description, targetDate, done));
    }
    public void deleteTodo(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }
    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public void updateTodo(Todo todo) {
        deleteTodo(todo.getId());
        todos.add(todo);
    }
}
