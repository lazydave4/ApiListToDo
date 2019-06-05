package test.dawid;

import com.dawid.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TestDeleteTasks {
    @Autowired
    ToDoListService toDoListService;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Repository repository;

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenListNameIsNull() {
        toDoListService.removeTask(null, 1);
    }

    @Test(expected = OwnException.class)
    public void shouldThrowExceptionWhenListNameIsEmpty() {
        toDoListService.removeTask("", 1);

    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenListDotContainTask() {
        toDoListService.removeTask("toDO", 23);

    }

    /*
    Nie działa
     */

    @Test
    public void RemoveTest() {
        ToDoList toDoList = repository.save(new ToDoList("toDo"));
        Tasks tasks = taskRepository.save(new Tasks("shopping"));
        toDoListService.addTask(toDoList.getName(), tasks.getDescription());
        List<Tasks> list = new ArrayList<>();
        taskRepository.findAll().forEach(list::add);
        Assert.assertTrue(list.size() == 1);
        toDoListService.removeTask(toDoList.getName(), tasks.getId());
    }
}