package me.enosent;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 28.
 * Time: 오후 11:31
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
public class Application {

    // cf> http://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();

        // cf> http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
        // application.setBanner();

        application.run(Application.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
