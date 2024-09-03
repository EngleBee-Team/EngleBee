package com.beelinkers.englebee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component    //--------------Spring <bean>  JPA:@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

  private String name;
  private String regdate;


}
