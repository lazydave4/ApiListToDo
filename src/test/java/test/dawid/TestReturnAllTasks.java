package test.dawid;

import com.dawid.Application;
import com.dawid.Controller;
import com.dawid.TaskRepository;
import com.dawid.Tasks;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.SecondaryTable;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Application.class)
public class TestReturnAllTasks {
 @Autowired
    Controller controller;
 @Autowired
    TaskRepository taskRepository;


 @Test
 public void testAllTasksWhenDataBaseIsEmpty()   {

     Assert.assertTrue(controller.getAllTask().size()==0 );



 }
 @Test
 public void testAllTasksWhenDataBaseHaveTasks(){

     List<Tasks> listTasks = new ArrayList<>();
     taskRepository.save(new Tasks("aaaa"));
     taskRepository.save(new Tasks("aaaa"));
     Assert.assertEquals(2,controller.getAllTask().size());
 }
 @Before
 public void setUp(){
     taskRepository.deleteAll();
 }
}
