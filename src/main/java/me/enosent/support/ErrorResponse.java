package me.enosent.support;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 31.
 * Time: 오전 12:02
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;

    private String message;
}
