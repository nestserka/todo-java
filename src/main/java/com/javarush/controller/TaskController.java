package com.javarush.controller;

import com.javarush.dao.TaskInfo;
import com.javarush.domain.Status;
import com.javarush.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

import java.util.Locale;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "redirect:/todo";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String tasks (@ModelAttribute("model") ModelMap model,
                             @RequestParam(value ="page", required = false, defaultValue = "1") int page,
                             @RequestParam(value ="limit", required = false, defaultValue = "40") int limit){
        model.addAttribute("tasks", taskService.getAll((page - 1) * limit,limit));
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("info") TaskInfo info){
        taskService.create(info.getDescription(), info.getStatus());
        return "redirect:/todo";
    }
    @RequestMapping(value = "/todo/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable ("id") Integer id,
                     @RequestBody TaskInfo info){
        if (isNull(id) || id <= 0){
            throw new RuntimeException("Invalid id");
        }
        taskService.edit(id, info.getDescription(), info.getStatus());
        return "redirect:/todo";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(Model model, @PathVariable ("id") Integer id){
        if (isNull(id) || id <= 0){
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
    }

}
