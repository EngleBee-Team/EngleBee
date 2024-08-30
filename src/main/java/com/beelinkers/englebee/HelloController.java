package com.beelinkers.englebee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String sayHello(){
    return "Hello, I am EngleBee!";
  }

}
