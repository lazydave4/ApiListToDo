package com.dawid;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
    @RestController
    @RequestMapping("/toDoList")
    public class Controller {

        @Autowired
        ToDoListService toDoListService;


        @RequestMapping("/createList")
        public String createList(@RequestParam(value = "listName") String listName) {
            return toDoListService.createList(listName);
        }

        @RequestMapping("/allList")
        public List<ToDoList> getAllList() {
            return toDoListService.getAllList();
        }

        @RequestMapping("/addTask/{id}")
        public String addTask(@PathVariable("id") String listName, @RequestParam(value = "task") String taskDescription) {
            return toDoListService.addTask(listName, taskDescription);

        }

        @RequestMapping("/remove/{listName}")
        public String removeTask(@PathVariable("listName") String listName, @RequestParam(value = "id") long id) {
            return toDoListService.removeTask(listName, id);

        }

        @RequestMapping("/updateTask/{id}")
        public String updateTask(@PathVariable("id") long id, @RequestParam(value = "taskToEdit") String taskToEdit) {
            return toDoListService.updateTask(id, taskToEdit);

        }
        @RequestMapping("/allTask")
        public List<Tasks> getAllTask() {
            return toDoListService.getTaskList();
        }

    }

