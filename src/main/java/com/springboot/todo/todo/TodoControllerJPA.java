package com.springboot.todo.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


// work with TodoRepo instead of TodoService
@Controller
@SessionAttributes("name")
public class TodoControllerJPA {

    private TodoRepository todoRepository;

    private String getLoginUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public TodoControllerJPA(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping("/welcome")
    public String home(ModelMap model) {
        return "welcome";
    }

    @RequestMapping("/list-todos")
    public String listTodos(ModelMap model) {
        // @RestController = @Controller + @ResponseBody
        // @Controller (without @ResponseBody): default is return a view
        // @RestController (with @ResponseBody): complete HTTP response (json and code)
        List<Todo> todos = todoRepository.findByUsername(getLoginUsername());
        model.put("todos", todos);
        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String addTodos(ModelMap model) {
        model.put("todo", new Todo(getLoginUsername(),"DEFAULT added todo",
                LocalDate.now().plusYears(1), false ));
        return "addTodo";
    }

    @PostMapping("/add-todo")
    public String postTodos(@Valid Todo todo, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "addTodo";
        }
        // redirect:url
        todo.setUsername(getLoginUsername());
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodos(@RequestParam int id) {
        // Clicking a link on a html page results in a http GET request.
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @PostMapping(value="/update-todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()) {
            return "addTodo";
        }

        todo.setUsername(getLoginUsername());
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

}
