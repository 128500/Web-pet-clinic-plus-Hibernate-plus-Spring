package com.llisovichok.storages;

import com.llisovichok.lessons.clinic.Pet;
import com.llisovichok.models.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by KUDIN ALEKSANDR on 16.10.2017.
 */
public class SpringJdbcStorageTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    private final SpringJdbcStorage storage = (SpringJdbcStorage)context.getBean("springJdbcStorage");

    @Test
    public void values() throws Exception {
        User test1 = new User("Test", "Testing", "Test", 647834783, new Pet("Test", "test", 2));
        User test2 = new User("Opra", "Jenkins", "Greedy st., 458", 58235419, new Pet("Dodik", "snake", 4));

        int id1, id2;
        id1 = storage.addUser(test1);
        id2 = storage.addUser(test2);

        assertTrue(id1 > 0 && id2 > 0);

        Collection<User> users1 = storage.values();

        ArrayList<User> usersList = new ArrayList<>(users1);
        User example1 = null;
        User example2 = null;

        for(User u : usersList){
            if(u.getId() == id1) example1 = u;
            if(u.getId() == id2) example2 = u;
        }

        assertEquals("Test", example1.getFirstName() );
        assertEquals("Testing", example1.getLastName() );
        assertEquals("Test", example1.getAddress() );
        assertEquals(647834783, example1.getPhoneNumber() );
        assertEquals("Test", example1.getPet().getName() );
        assertEquals("test", example1.getPet().getKind() );
        assertEquals(2, example1.getPet().getAge() );

        assertEquals("Opra", example2.getFirstName() );
        assertEquals("Jenkins", example2.getLastName() );
        assertEquals("Greedy st., 458", example2.getAddress() );
        assertEquals(58235419, example2.getPhoneNumber() );
        assertEquals("Dodik", example2.getPet().getName() );
        assertEquals("snake", example2.getPet().getKind() );
        assertEquals(4, example2.getPet().getAge() );
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("Test", "Testing", "Test", 647834783, new Pet("Test", "test", 2));
        int id;
        id = storage.addUser(user);
        assertTrue(id > 0);

        User addedUser = storage.getUser(id);
        assertEquals("Test", addedUser.getFirstName() );
        assertEquals("Testing", addedUser.getLastName() );
        assertEquals("Test", addedUser.getAddress() );
        assertEquals(647834783, addedUser.getPhoneNumber() );
        assertEquals("Test", addedUser.getPet().getName() );
        assertEquals("test", addedUser.getPet().getKind() );
        assertEquals(2, addedUser.getPet().getAge() );
    }

    @Test
    public void editUser() throws Exception{
        User user = new User("Gal", "Hulio", "Hepa st., 47", 647834783, new Pet("Joric", "dog", 2));
        User alteredUser = new User("Opra", "Jenkins", "Greedy st., 458", 58235419, new Pet("Dodik", "snake", 4));
        int id;
        id = storage.addUser(user);
        assertTrue(id >0);
        storage.editUser(id, alteredUser);
        User alterEgo = storage.getUser(id);
        assertEquals("Opra", alterEgo.getFirstName() );
        assertEquals("Jenkins", alterEgo.getLastName() );
        assertEquals("Greedy st., 458", alterEgo.getAddress() );
        assertEquals(58235419, alterEgo.getPhoneNumber() );
        assertEquals("Dodik", alterEgo.getPet().getName() );
        assertEquals("snake", alterEgo.getPet().getKind() );
        assertEquals(4, alterEgo.getPet().getAge() );
    }
}