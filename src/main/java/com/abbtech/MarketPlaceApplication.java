package com.abbtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

import com.abbtech.annotation.CustomTransactionAnnotation;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@EnableAspectJAutoProxy
public class MarketPlaceApplication {

    static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MarketPlaceApplication.class, args);
        var mainClass = ctx.getBean(MarketPlaceApplication.class);
        mainClass.deleteById(1L);

    }


    @CustomTransactionAnnotation
    public String deleteById(Long id) {
        System.out.println("Deleting brand with id: " + id + "");
        return "Brand deleted";
    }

}
