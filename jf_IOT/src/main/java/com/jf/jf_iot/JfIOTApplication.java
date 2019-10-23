package com.jf.jf_iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.jf.jf_iot.*.mapper")
public class JfIOTApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JfIOTApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JfIOTApplication.class);
    }
}
