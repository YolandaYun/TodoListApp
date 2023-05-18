package com.springboot.todo.todo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Todo {
    // Entity (~ database table)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // @Id indicate the Primary Key of this entity (~ database table)
    private String username;
    @Size(min=10, max=30)
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public Todo() {

    }
    // for TodoService + TodoController, updated for TodoRepository + TodoControllerJPA
//    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
//        this.id = id;
//        this.username = username;
//        this.description = description;
//        this.targetDate = targetDate;
//        this.done = done;
//    }

    public Todo(String username, String description, LocalDate targetDate, boolean done) {
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
