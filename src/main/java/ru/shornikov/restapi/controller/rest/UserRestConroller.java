package ru.shornikov.restapi.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shornikov.restapi.enity.User;
import ru.shornikov.restapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserRestConroller
{
    @Autowired
    UserRepository users;


    //createUser
    @PostMapping
    public User Create(@RequestBody User user){
        return users.save(users.create(user));
    }

    //deleteUser
    @DeleteMapping(path = "/{id}")
    public UUID DeleteById(@PathVariable(name="id") UUID id){
        return users.deleteById(id).getId();
    }

    //getUserById
    @GetMapping(path = "/{id}")
    public User GetById(@PathVariable(name="id") UUID id){
        return users.findUserById(id);
    }


    //listAllUsers
    @GetMapping
    public List<User> GetAll(){
        return users.findAll();
    }


    //updateUser
    @PutMapping
    public User Update(@RequestBody User user){
        return users.save(users.update(user));
    }


    @DeleteMapping
    public User Delete(@RequestBody User user){
        return users.delete(user);
    }


    @PutMapping(path = "/update")
    public List<User> Update(@RequestBody List<User> user){
        List<User> newuser = new ArrayList<>();
        user.forEach(usr -> {
            newuser.add(users.save(users.update(usr)));
        });

        return newuser;
    }

    @ExceptionHandler
    String CatchException(Exception e){
        return "Возникла ошибка: " + e.getMessage();
    }
}
