package me.enosent.controller;

import me.enosent.controller.dto.AccountDto;
import me.enosent.domain.Account;
import me.enosent.repository.AccountRepository;
import me.enosent.service.AccountService;
import me.enosent.support.ErrorAccountMessage;
import me.enosent.support.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 28.
 * Time: 오후 11:33
 *
 * Tool : chrome://apps/ Postman - REST Clinet
 */
@Controller
public class AccountController {

    @Autowired private ModelMapper modelMapper;
    @Autowired private AccountService service;
    @Autowired private AccountRepository repository;

    @Autowired private ErrorAccountMessage errorMessage;

    // cf) /reference/post.png
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Request request, BindingResult result) {
        if (result.hasErrors()) {

            List<ErrorResponse> errorList = result.getAllErrors()
                                                        .stream()
                                                        .map(err -> new ErrorResponse(err.getCode(), err.getDefaultMessage()))
                                                        .collect(Collectors.toList());

            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }

        Account account = modelMapper.map(request, Account.class);
        Account newAccount = service.createNewAccount(account);

        return new ResponseEntity<>( modelMapper.map(newAccount, AccountDto.Response.class) , HttpStatus.CREATED);
    }

    // cf) /reference/total.png
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity getAccounts() {

        List<AccountDto.Response> responses =  repository.findAll()
                                                            .stream()
                                                            .map(a -> modelMapper.map(a, AccountDto.Response.class))
                                                            .collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // cf) /reference/single.png
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity getAccount(@PathVariable int id){
        Account account = repository.findOne(id);
        if(account == null) {
            return new ResponseEntity<>(new ErrorResponse("account.is.null", errorMessage.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(account, AccountDto.Response.class), HttpStatus.OK);
    }

    /*
        PUT : 전체 데이터 갱신 ( cf) /reference/put.png )
        PATCH : 일부 데이터만 갱신 ( cf) /reference/patch.png )
     */
    @RequestMapping(value = "/accounts/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity updateAccount(@PathVariable int id, @RequestBody AccountDto.Update request, BindingResult result){
        if (result.hasErrors()) {

            List<ErrorResponse> errorList = result.getAllErrors()
                                                        .stream()
                                                        .map(err -> new ErrorResponse(err.getCode(), err.getDefaultMessage()))
                                                        .collect(Collectors.toList());

            return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
        }

        Account account = repository.findOne(id);

        if(account == null) {
            return new ResponseEntity<>(new ErrorResponse("account.is.null", errorMessage.getMessage()), HttpStatus.BAD_REQUEST);
        }

        Account updateAccount = modelMapper.map(request, Account.class);

        return new ResponseEntity<>(modelMapper.map(service.updateAccount(account, updateAccount), AccountDto.Response.class), HttpStatus.OK);
    }

    // cf) /reference/delete.png
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable int id) {
        Account account = repository.findOne(id);

        if(account == null) {
            return new ResponseEntity<>(new ErrorResponse("account.is.null", errorMessage.getMessage()), HttpStatus.BAD_REQUEST);
        }

        repository.delete(account);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
