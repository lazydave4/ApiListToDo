package test.dawid;

import com.dawid.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Application.class)
public class TestUpdateTasks {
@Autowired
    TaskRepository taskRepository;

@Autowired
    ToDoListService toDoListService;

@Before
    public void setUp(){
        taskRepository.deleteAll();
    }

@Test(expected = NoSuchElementException.class)
    public void testMissingTaskDataBase(){
      toDoListService.updateTask(0,"NewUpdate");

}
@Test
   public void testChangeDescriptionOfTask(){
    Tasks taskDateBase =taskRepository.save(new Tasks("aaaa"));
    toDoListService.updateTask(taskDateBase.getId(),"bbb");
    Assert.assertEquals("bbb",taskRepository.findById(taskDateBase.getId()).get().getDescription());

   }
@Test(expected = NullPointerException.class)
   public void testNullInDescriptionOfTask(){
    toDoListService.updateTask(1,null);


   }
@Test(expected = OwnException.class)
   public void testEmptyValueInDescriptionOfTask(){
    toDoListService.updateTask(1,"");
   }
}
