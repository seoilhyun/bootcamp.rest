package me.enosent.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 31.
 * Time: 오후 4:49
 * To change this template use File | Settings | File Templates.
 */
@ConfigurationProperties("account")
@Component
@Data
public class ErrorAccountMessage {

    private String message;
}
