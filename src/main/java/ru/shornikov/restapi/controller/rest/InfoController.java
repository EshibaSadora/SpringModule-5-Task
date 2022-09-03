package ru.shornikov.restapi.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnError;

@RestController
@RequestMapping()
public class InfoController {
    //info
    @GetMapping(path = "/info")
    public String info(){
        return "Hello";
    }

    @GetMapping(path = "/status")
    public String status(){
        return "Status";
    }

    @ExceptionHandler
    String CatchException(Exception e){
        return "Возникла ошибка: " + e.getMessage();
    }


}
