package test.dawid;

import com.dawid.Application;
import com.dawid.OwnException;
import com.dawid.Repository;
import com.dawid.ToDoListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TestCreateList {
    @Autowired
    ToDoListService toDoListService;
    @Autowired
    Repository repository;

    @Before
    public void setUp() {
        repository.deleteAll();


    }
    @Test
    public void testWhenDataBaseIsEmpty(){
        Assert.assertTrue(toDoListService.getAllList().size()==0);

    }
    @Test
    public void testWhenDataBaseHasOneToDoList(){
        toDoListService.createList("toDo");
        Assert.assertTrue(toDoListService.getAllList().size()==1);
    }
    @Test
    public void testWhenDataBaseHasTwoToDoList(){
        toDoListService.createList("toDo");
        toDoListService.createList("shopping");
        Assert.assertTrue(toDoListService.getAllList().size()==2);
    }
    @Test
    public void testWhenDataBaseHastTwoToDoList(){
        toDoListService.createList("toDo");
        toDoListService.createList("shopping");
        Assert.assertFalse(toDoListService.getAllList().size()==1);
    }

    @Test(expected = OwnException.class)
    public void shouldThrowExceptionWhenDescriptionIsNull(){
        toDoListService.createList(null);

    }
    @Test(expected = OwnException.class)
    public void shouldThrowExceptionWhenDescriptionIsEmpty(){
        toDoListService.createList("");


    }
}
