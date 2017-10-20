package com.llisovichok.tools;

import com.llisovichok.lessons.clinic.Pet;
import com.llisovichok.models.Role;
import com.llisovichok.models.User;
import com.llisovichok.storages.Storages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by KUDIN ALEKSANDR on 23.09.2017.
 */
public class DbTool {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storages storages = context.getBean(Storages.class);

        User user = new User("Mark",
                "Timmerberg",
                "Glase st., 258-4",
                125477447448l,
                new Pet("Hippo", "iguana", 2));
        user.setRole(new Role("user"));
        int idJdbc = storages.springJdbcStorage.addUser(user);
        user.setId(1);
        storages.memoryStorage.addUser(user);
        int hiberNumber = storages.shHiberStorage.addUser(user);
        ArrayList<User> users = (ArrayList<User>)storages.shHiberStorage.values();

        System.out.println(users.iterator().next().toString());
        System.out.println(storages.springJdbcStorage.getUser(idJdbc).toString());
        System.out.println(storages.memoryStorage.getUser(1).toString());

        User changedUser = new User("Danni",
                "Groover",
                "Princess Diana st., 19",
                582369258410L,
                new Pet("Fuco", "dog", 5));
        changedUser.setRole(new Role("admin"));

        storages.shHiberStorage.editUser(hiberNumber, changedUser);
        users = (ArrayList<User>)storages.shHiberStorage.values();
        System.out.println(users.iterator().next().toString());

        User u = storages.shHiberStorage.getUser(hiberNumber);
        System.out.println(u.toString());

        byte[] photoBytes = getImageBytes("http://www.avajava.com/images/avajavalogo.jpg");
        boolean resultOfAddingPetPhoto = storages.shHiberStorage.addPhotoWithHibernate(u.getPet().getId(), photoBytes);
        System.out.println("Saving photo: " + resultOfAddingPetPhoto);

        storages.shHiberStorage.addMessage(u.getId(), "First message");

        storages.shHiberStorage.removeUser(hiberNumber);
    }

    private static byte[] getImageBytes(final String urlPath){
        InputStream is = null;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            URL url = new URL(urlPath);
            is  = url.openStream();
            byte[] data = new byte[1024];
            int nRead = 0;

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                baos.write(data, 0, nRead);
            }

            baos.flush();
            int byteArraySize = baos.size();

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(is != null) is.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        throw new IllegalStateException("Couldn't  get image bytes");
    }
}
