package com.llisovichok.raw_mappers;

import com.llisovichok.lessons.clinic.Pet;
import com.llisovichok.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KUDIN ALEKSANDR on 15.10.2017.
 */
public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        Pet pet = new Pet();
        pet.setKind(rs.getString("kind"));
        pet.setName(rs.getString("nickname"));
        pet.setAge(rs.getInt("age"));
        user.setId(rs.getInt("uid"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAddress(rs.getString("address"));
        user.setPhoneNumber(rs.getLong("phone"));
        user.setPet(pet);
        return user;
    }
}
