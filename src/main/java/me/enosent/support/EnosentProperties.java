package me.enosent.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 29.
 * Time: 오전 12:10
 * To change this template use File | Settings | File Templates.
 */
@ConfigurationProperties("enosent")
@Component
public class EnosentProperties {

    private String job;
    private String age;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
