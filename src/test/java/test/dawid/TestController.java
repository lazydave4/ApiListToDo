package test.dawid;

import com.dawid.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Application.class)
public class TestController {
 @Autowired
    Controller  controller;
 @Autowired
    Repository repository;
 @Autowired
    TaskRepository taskRepository;
 @Test
 public void testCreateListName_correct(){
       String list = controller.createList("listName");
       Assert.assertEquals( "lista zadan: listName",list);
 }
    @Test
    public void testCreateListSize_correct(){
        //given
        List<ToDoList> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        Assert.assertTrue(list.size() == 0);
        //when
        controller.createList("listName");
        repository.findAll().forEach(list::add);
        //than
        Assert.assertTrue(list.size() == 1);
    }
    @Test
    public void testReturnAllList_correct() {
        //given
        Assert.assertTrue(controller.getAllTask().size() == 0);
        //when
        controller.createList("listName");
        //than
        Assert.assertTrue(controller.getAllTask().size() == 1);
    }



}
