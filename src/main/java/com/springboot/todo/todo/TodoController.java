package com.springboot.todo.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String listTodos(ModelMap model) {
        // @RestController = @Controller + @ResponseBody
        // @Controller (without @ResponseBody): default is return a view
        // @RestController (with @ResponseBody): complete HTTP response (json and code)
        List<Todo> todos = todoService.findByUsername("cat");
        model.put("todos", todos);
        return "listTodos";
    }

    @GetMapping("/add-todo")
    public String addTodos(ModelMap model) {
        model.put("todo", new Todo(0,(String)model.get("name"),"DEFAULT added todo",
                LocalDate.now().plusYears(1), false ));
        return "addTodo";
    }

    @PostMapping("/add-todo")
    public String postTodos(@Valid Todo todo, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "addTodo";
        }
        // redirect:url
        String name = (String)model.get("name");
        LocalDate date = LocalDate.now();
        todoService.addTodo(0, name, todo.getDescription(), date, false);

        return "redirect:list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodos(@RequestParam int id) {
        // Clicking a link on a html page results in a http GET request.
        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @PostMapping(value="/update-todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()) {
            return "addTodo";
        }

        String username = (String)model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

}
