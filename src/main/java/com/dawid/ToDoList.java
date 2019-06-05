package com.dawid;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.*;
import java.util.List;

@Entity
public class ToDoList{

 @Id
 private String name;

    @OneToMany(cascade =CascadeType.ALL ,orphanRemoval =true )
   private List<Tasks> listTasks ;
    public ToDoList() {
    }
    public ToDoList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tasks> getListTasks() {
        return listTasks;
    }

    public void setListTasks(List<Tasks> listTasks) {
        this.listTasks = listTasks;
    }
}
