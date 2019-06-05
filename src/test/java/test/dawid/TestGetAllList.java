package test.dawid;

import com.dawid.Application;
import com.dawid.Repository;
import com.dawid.ToDoListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TestGetAllList {

    @Autowired
    ToDoListService toDoListService;
    @Autowired
    Repository repository;

    @Before
    public void setUp(){
        repository.deleteAll();
    }

    @Test
    public void shouldReturnToDoList() {

        toDoListService.createList("todo");
        Assert.assertEquals(1, toDoListService.getAllList().size());


    }

    @Test
    public void testGetAllListWhenListIsEmpty() {

        Assert.assertTrue(toDoListService.getAllList().size() == 0);
    }

}
