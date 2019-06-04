package com.kingname.springbootoutconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest(properties = "kingname.name = limgeun2")
//@TestPropertySource(properties = "kingname.name=limgeun2")
@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest
public class SpringbootoutconfigApplicationTests {

    @Autowired
    Environment environment;

    @Test
    public void contextLoads() {
        assertThat(environment.getProperty("kingname.name")).isEqualTo("limgeun2");
    }

}
