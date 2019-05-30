package com.kingname.springboothttp;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringboothttpApplication {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring";
    }

    public static void main(String[] args) {

        //SpringApplication.run(SpringboothttpApplication.class, args);

        SpringApplication app = new SpringApplication(SpringboothttpApplication.class);
        app.setBanner(((environment, sourceClass, out) -> {
            out.println("===================");
            out.println("LimGeun");
            out.println("===================");
        }));

        //app.setBannerMode(Banner.Mode.OFF);

        app.run(args);
    }

//    @Bean
//    public ServletWebServerFactory serverFactory() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
//        return tomcat;
//    }
//
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(0);
//        return connector;
//    }
}
