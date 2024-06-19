package com.github.peco2282.webapp;

import com.github.peco2282.webapp.entity.User;
import com.github.peco2282.webapp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"/com/github/peco2282/webapp/database/schema.sql", "/com/github/peco2282/webapp/database/data.sql"})
@SpringBootTest
public class WebappTest {
  public @Autowired UserMapper mapper;

  @Test
  void contextLoads() {
    User user = mapper.selectById(1);
    System.out.println(user);
  }
}
