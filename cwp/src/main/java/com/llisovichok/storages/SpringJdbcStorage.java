package com.llisovichok.storages;

import com.llisovichok.models.User;
import com.llisovichok.raw_mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KUDIN ALEKSANDR on 15.10.2017.
 */
@Repository
public class SpringJdbcStorage implements SpringJdbcTemplateStorage {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public void setNamedTemplate(NamedParameterJdbcTemplate namedTemplate) {
        this.namedTemplate = namedTemplate;
    }

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<User> values() {
        String sql = "SELECT * FROM clients, pets WHERE clients.uid = pets.client_id ORDER BY clients.uid";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public int addUser(User user) {
        Map<String, Object> userMap  = createUserMap(user);

        String insertUserSql = "INSERT INTO clients(first_name, last_name, address, phone) VALUES (:firstName, :lastName, :address, :phone) RETURNING clients.uid";
        int userId = namedTemplate.query(insertUserSql, userMap, rs -> {
                rs.next();
                return rs.getInt(1);
        });

        Map<String, Object> petMap  = new HashMap<>();
        petMap.put("clientId", userId);
        petMap.putAll(createPetMap(user));
        String insertPetSql = "INSERT INTO pets(client_id, nickname, kind, age) VALUES (:clientId, :nickname, :kind, :age)";
        namedTemplate.execute(insertPetSql, petMap, preparedStatement -> preparedStatement.executeUpdate());
        return userId;
    }

    @Override
    public boolean editUser(Integer id, User user) {
        Map<String, Object> userMap = createUserMap(user);
        userMap.put("uid", id);
        String userUpdateSql = "UPDATE clients SET first_name=:firstName, last_name=:lastName, address=:address, phone=:phone WHERE public.clients.uid=:uid";
        int userMark = namedTemplate.update(userUpdateSql, userMap);

        Map<String,Object> petMap = createPetMap(user);
        petMap.put("client_id", id);
        String petUpdateSql = "UPDATE pets SET nickname=:nickname, kind=:kind, age=:age WHERE public.pets.client_id=:client_id";
        int petMark = namedTemplate.update(petUpdateSql, petMap);
        return petMark > 0 && userMark > 0;
    }

    @Override
    public User getUser(Integer id) {
        String sql = "SELECT * FROM clients INNER JOIN pets on clients.uid=client_id WHERE clients.uid = :id";
        Map<String, Object> map  = new HashMap<>();
        map.put("id", id);
        return (User) namedTemplate.query(sql, map, new UserMapper()).iterator().next();
    }

    @Override
    public void removeUser(Integer userId) {

    }

    @Override
    public Collection<User> findUsers(String input, boolean lookInFirstName, boolean lookInLastName, boolean lookInPetName) {
        return null;
    }

    private Map<String, Object> createUserMap(User user){
        Map<String, Object> userMap  = new HashMap<>();
        userMap.put("firstName", user.getFirstName());
        userMap.put("lastName", user.getLastName());
        userMap.put("address", user.getAddress());
        userMap.put("phone", user.getPhoneNumber());
        return userMap;
    }

    private Map<String, Object> createPetMap(User user){
        Map<String, Object> petMap  = new HashMap<>();
        petMap.put("nickname", user.getPet().getName());
        petMap.put("kind", user.getPet().getKind());
        petMap.put("age", user.getPet().getAge());
        return petMap;
    }


}
