package com.beelinkers.englebee;

import com.beelinkers.englebee.auth.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DBConnectionTestController {

  private final MemberRepository memberRepository;

  @GetMapping("/db/conn")
  public String testDB(){
    if(memberRepository.findById(1L).isEmpty()){
      return "no item!";
    }
    return "yes item!";
  }

}
