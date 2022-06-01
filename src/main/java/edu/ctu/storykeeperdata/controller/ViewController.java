package edu.ctu.storykeeperdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {


    @Autowired
    public ViewController() {
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

}


