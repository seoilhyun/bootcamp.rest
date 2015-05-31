package me.enosent.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 29.
 * Time: 오전 12:04
 * To change this template use File | Settings | File Templates.
 */
public class AccountDto {

    @Data
    public static class Request {
        @NotBlank
        @Size(min = 6)
        private String username;

        @NotBlank
        private String password;
    }

    @Data
    public static class Response {
        public int id;
        public String username;
    }

    @Data
    public static class Update {
        public String username;
        public String password;
    }
}
