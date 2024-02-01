package com.javarush.controller;

import com.javarush.dao.TaskInfo;
import com.javarush.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String tasks (@ModelAttribute("model") ModelMap model,
                             @RequestParam(value ="page", required = false, defaultValue = "1") int page,
                             @RequestParam(value ="limit", required = false, defaultValue = "10") int limit){
        model.addAttribute("tasks", taskService.getAll((page - 1) * limit,limit));
        return "index";
    }

    @PostMapping("/{id}")
    public void edit(Model model, @PathVariable Integer id,
                     @RequestBody TaskInfo info){
        if (isNull(id) || id <= 0){
            throw new RuntimeException("Invalid id");
        }
        taskService.edit(id, info.getDescription(), info.getStatus());
    }

    @PostMapping("/")
    public void add(Model model, @RequestBody TaskInfo info){
        taskService.create(info.getDescription(), info.getStatus());
    }

    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable Integer id){
        if (isNull(id) || id <= 0){
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
        return "index";
    }

}
