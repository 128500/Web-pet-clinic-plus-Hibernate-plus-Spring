package com.llisovichok.storages;

import com.llisovichok.lessons.clinic.Pet;
import com.llisovichok.models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by KUDIN ALEKSANDR on 16.10.2017.
 */
public class SpringJdbcStorageTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    private final SpringJdbcStorage storage = (SpringJdbcStorage)context.getBean("springJdbcStorage");

    @Before public void initialize() {
        storage.dropExistingTables();
        storage.createNewTables();
    }

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
        assertEquals(Long.valueOf(647834783), example1.getPhoneNumber() );
        assertEquals("Test", example1.getPet().getName() );
        assertEquals("test", example1.getPet().getKind() );
        assertEquals(Integer.valueOf(2), example1.getPet().getAge() );

        assertEquals("Opra", example2.getFirstName() );
        assertEquals("Jenkins", example2.getLastName() );
        assertEquals("Greedy st., 458", example2.getAddress() );
        assertEquals(Long.valueOf(58235419), example2.getPhoneNumber() );
        assertEquals("Dodik", example2.getPet().getName() );
        assertEquals("snake", example2.getPet().getKind() );
        assertEquals(Integer.valueOf(4), example2.getPet().getAge() );
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
        assertEquals(Long.valueOf(647834783), addedUser.getPhoneNumber() );
        assertEquals("Test", addedUser.getPet().getName() );
        assertEquals("test", addedUser.getPet().getKind() );
        assertEquals(Integer.valueOf(2), addedUser.getPet().getAge() );
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
        assertEquals(Long.valueOf(58235419), alterEgo.getPhoneNumber() );
        assertEquals("Dodik", alterEgo.getPet().getName() );
        assertEquals("snake", alterEgo.getPet().getKind() );
        assertEquals(Integer.valueOf(4), alterEgo.getPet().getAge() );
    }


    @Test(expected = NoSuchElementException.class)
    public void removeUser() throws Exception {
        User user = new User("Test", "Test", "Test", 103, new Pet("Test", "test", 100));
        int id = storage.addUser(user);
        assertTrue(id > 0);
        storage.removeUser(id);
        storage.getUser(id);
    }

    @Test
    public void findUsers() throws Exception {

        User user = new User("Test", "Test", "Test", 103, new Pet("TEST", "test", 100));
        User user2 = new User("Pest", "Test", "Test", 103, new Pet("TEST", "test", 100));
        User user3 = new User("Dest", "Test", "Test", 103, new Pet("TEST", "test", 100));
        int id = storage.addUser(user);
        int id2 = storage.addUser(user2);
        int id3 = storage.addUser(user3);

        try {
            assertTrue(id > 0);
            assertTrue(id2 > 0);
            assertTrue(id3 > 0);

            Collection<User> collection = storage.findUsers("est", true, false, false);
            ArrayList<User> result = new ArrayList<>(collection);


            assertTrue(result.size() == 3);

            User user_obtained1 = result.get(0);
            User user_obtained2 = result.get(1);
            User user_obtained3 = result.get(2);

            assertEquals("Test", user_obtained1.getFirstName());
            assertEquals("Pest", user_obtained2.getFirstName());
            assertEquals("Dest", user_obtained3.getFirstName());


            Collection<User> collection2 = storage.findUsers("Pe", true, false, false);
            ArrayList<User> result2 = new ArrayList<>(collection2);

            assertTrue(result2.size() == 1);

            User user_obtained4 = result2.get(0);

            assertEquals("Pest", user_obtained4.getFirstName());


            Collection<User> collection3 = storage.findUsers("Test", true, true, false);
            ArrayList<User> result3 = new ArrayList<>(collection3);

            assertTrue(result3.size() == 3);

           User user_obtained5 = result3.get(0);
            User user_obtained6 = result3.get(1);
            User user_obtained7 = result3.get(2);

            assertEquals("Test", user_obtained5.getLastName());
            assertEquals("Test", user_obtained6.getLastName());
            assertEquals("Test", user_obtained7.getLastName());


            Collection<User> collection4 = storage.findUsers("TEST", false, false, true);
            ArrayList<User> result4 = new ArrayList<>(collection4);

            assertTrue(result4.size() == 3);

            User user_obtained8 = result4.get(0);
            User user_obtained9 = result4.get(1);
            User user_obtained10 = result4.get(2);

            assertEquals("TEST", user_obtained8.getPet().getName());
            assertEquals("TEST", user_obtained9.getPet().getName());
            assertEquals("TEST", user_obtained10.getPet().getName());


            Collection<User> collection5 = storage.findUsers("T", false, true, false);
            ArrayList<User> result5 = new ArrayList<>(collection5);

            assertTrue(result5.size() == 3);

            User user_obtained11 = result5.get(0);
            User user_obtained12 = result5.get(1);
            User user_obtained13 = result5.get(2);

            assertEquals("Test", user_obtained11.getLastName());
            assertEquals("Test", user_obtained12.getLastName());
            assertEquals("Test", user_obtained13.getLastName());
        }

        finally{
            storage.removeUser(id);
            storage.removeUser(id2);
            storage.removeUser(id3);
        }
    }
}