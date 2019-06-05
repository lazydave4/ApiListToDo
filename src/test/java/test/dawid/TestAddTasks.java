package test.dawid;
import com.dawid.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TestAddTasks {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ToDoListService toDoListService;
    @Autowired
    Repository repository;


    @Before
    public void setUp(){
        taskRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void testWhenDataBankIsEmpty(){
        List<Tasks> list = new ArrayList();
        taskRepository.findAll().forEach(list::add);
        Assert.assertTrue(list.size()==0);
    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDescriptionIsNull(){
        toDoListService.addTask("toDo",null);


    }
    @Test(expected = OwnException.class)
    public void shouldThrowExceptionWhenDescriptionIsEmpty(){
        toDoListService.addTask("toDo","");

    }
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenListNameIsNull(){
        toDoListService.addTask(null,"shopping");

    }
    @Test(expected = OwnException.class)
    public void shouldThrowExceptionWhenListNameIsEmpty(){
        toDoListService.addTask("","shopping");

    }

    /*
   Nie działa
    */

    @Test
    public void testAddTask(){
        List<Tasks>list=new ArrayList<>();
        ToDoList toDoList=repository.save(new ToDoList("make"));
        toDoListService.addTask(toDoList.getName(),"shopping");
        taskRepository.findAll().forEach(list::add);
        Assert.assertTrue(list.size()==1);

    }

    /*
   Nie działa
    */
    @Test
    public void testAddTaskListShouldTwoTaskContains (){
        List<Tasks>list=new ArrayList<>();
        ToDoList toDoList=repository.save(new ToDoList("make"));
        toDoListService.addTask(toDoList.getName(),"shopping");
        toDoListService.addTask(toDoList.getName(),"houseWork");
        taskRepository.findAll().forEach(list::add);
        Assert.assertTrue(list.size()==1);


    }

}
