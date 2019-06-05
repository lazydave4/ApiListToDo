package com.dawid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToDoListService {

    @Autowired
    Repository repository;
    @Autowired
    TaskRepository taskRepository;


    public String createList(String listName) {
        if (listName == null || listName.length() == 0) {
            throw new OwnException(new ExceptionAlert("Podaj nazwe Listy"));
        }
        ToDoList toDoList = new ToDoList(listName);
        repository.save(toDoList);
        return "lista zrobiona: " + listName;
    }

    public List<ToDoList> getAllList() {
        List<ToDoList> lists = new ArrayList<>();
        repository.findAll().forEach(lists::add);
        return lists;
    }

    public String addTask(String listName, String taskDescription) {
        if (listName.equals(null) || taskDescription.equals(null) || listName.length() == 0 || taskDescription.length() == 0) {
            throw new OwnException(new ExceptionAlert("Wprowadz zadanie "));

        }
        ToDoList toDoList = repository.findById(listName).get();
        Tasks tasks = taskRepository.save(new Tasks(taskDescription));
        toDoList.getListTasks().add(tasks);
        repository.save(toDoList);

        return "Zadanie dodane do listy: " + listName;

    }

    public String updateTask(long id, String taskDescription) {
        if (taskDescription.equals(null) || taskDescription.length() == 0) {
            throw new OwnException(new ExceptionAlert("podaj treść zadania"));
        }
        taskRepository.findById(id).map(tasks ->
        {
            tasks.setDescription(taskDescription);
            return taskRepository.save(tasks);
        }).orElseThrow(() -> new NoSuchElementException());

        return "zadanie zostało zmienione";

    }

    public String removeTask(String listName, long id) {
        if(listName.equals(null)|| listName.length()==0){
            throw new OwnException(new ExceptionAlert("wybierz liste"));
        }
        if(taskRepository.existsById(id)){
            throw  new NoSuchElementException();
        }
        ToDoList toDoList = repository.findById(listName).get();
        Tasks tasks = taskRepository.findById(id).get();
        toDoList.getListTasks().remove(tasks);
        repository.save(toDoList);
        taskRepository.delete(tasks);

        return "zadanie zostało usunięte";


    }
    public List<Tasks> getTaskList() {
        List<Tasks> list = new ArrayList<>();
        taskRepository.findAll().forEach(list::add);
        return list;
    }

}