package me.enosent.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 28.
 * Time: 오후 11:52
 * To change this template use File | Settings | File Templates.
 */
@Data
@EqualsAndHashCode(of = {"username", "password"})
@Entity
public class Account {

    @Id @GeneratedValue
    private int id;

    @NotBlank
    @Size(min = 6)
    private String username;

    @NotNull
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
