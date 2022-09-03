package ru.shornikov.restapi.controller.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shornikov.restapi.enity.xmlservice.Request;
import ru.shornikov.restapi.enity.xmlservice.Response;

@RestController
@RequestMapping("/xml")
public class TestXmlService {


    @RequestMapping(method = RequestMethod.POST,  produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response> get(@RequestBody Request request)
    {
        /*
        EntityModel model = new EntityModel();
        model.setID("1");
        model.setNAME("Darshan.G.Pawar");
        model.setDOB("05-09-2001");
        model.setPINCODE("422 009");

        HttpHeaders hearders = new HttpHeaders();
        ResponseEntity<EntityModel> entityModel
                = new ResponseEntity<>(model, hearders,
                HttpStatus.CREATED);

        return entityModel;*/

        Response resp = new Response("123");


        HttpHeaders hearders = new HttpHeaders();
        return new ResponseEntity<>(resp, hearders, HttpStatus.CREATED);
    }
}
