package ru.shornikov.restapi.repository;

import org.apache.tomcat.util.log.SystemLogHandler;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import ru.shornikov.restapi.enity.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Repository
public class UserRepository {

    private Map<UUID, User> data = new HashMap<>();

    public User create(User newUser){
        if(newUser.getId()!=null){
            User existuser = data.get(newUser.getId());
            if(existuser==null){
                LoggerFactory.getLogger(this.getClass()).warn("Create error: id exist UUID:" + newUser.getId());
                new RuntimeException("Create error: id exist UUID:" + newUser.getId());
            }
        }else {
            newUser.setId(UUID.randomUUID());
        }

        newUser.setCreatedate(new Date());
        newUser.setChangedate(new Date());
        newUser.setSecurelevel(-1);
        //data.put(newUser.getId(), newUser);
        return newUser;
    }

    public User update(User newUser){
        User existuser = data.get(newUser.getId());
        if(existuser!=null){
            newUser.setCreatedate(existuser.getCreatedate());
            newUser.setChangedate(new Date());
            //data.put(newUser.getId(), newUser);
        }else {
            LoggerFactory.getLogger(this.getClass()).warn("Update error: no object UUID:" + newUser.getId());
            new RuntimeException("Update error: no object UUID:" + newUser.getId());
        }
        return newUser;
    }

    public User save(UUID id, User newUser){
        User existuser = data.get(id);
        if(existuser!=null) {
            newUser.setId(id);
            newUser = update(newUser);
        }else {
            newUser = create(newUser);
        }

        data.put(newUser.getId(), newUser);
        return newUser;
    }

    public User save(User newUser){
        return save(newUser.getId(), newUser);
    }

    public List<User> findAll() {
        return List.copyOf(data.values());
    }

    public User findUserById(UUID id) {
        return data.get(id);
    }

    public User deleteById(UUID id) {
        return data.remove(id);
    }

    public User delete(User user) {
        User existuser = data.get(user.getId());
        if(existuser==null) {
            String msg = "Delete error user: no object UUID:" + user.getId();
            LoggerFactory.getLogger(this.getClass()).warn(msg);
            new RuntimeException(msg);
        }
        data.remove(user.getId());
        return user;
    }
}
