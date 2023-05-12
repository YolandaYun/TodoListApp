package com.springboot.todo.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;



@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list-todos")
    public String listTodos(ModelMap model) {
        // @RestController = @Controller + @ResponseBody
        // @Controller (without @ResponseBody): default is return a view
        // @RestController (with @ResponseBody): complete HTTP response (json and code)
        List<Todo> todos = todoService.findByUsername("cat");
        model.put("todos", todos);
        return "listTodos";
    }

    @GetMapping("/add-todos")
    public String addTodos(ModelMap model) {
        model.put("todo", new Todo((String)model.get("name"),"DEFAULT",
                LocalDate.now().plusYears(1), false ));
        return "addTodos";
    }

    @PostMapping("/add-todos")
    public String postTodos(@Valid Todo todo, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "addTodos";
        }
        // redirect:url
        String name = (String)model.get("name");
        LocalDate date = LocalDate.now();
        todoService.addTodos(name, todo.getDescription(), date, false);

        return "redirect:list-todos";
    }
}
