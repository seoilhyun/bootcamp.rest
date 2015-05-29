package me.enosent.controller;

import me.enosent.controller.dto.AccountDto;
import me.enosent.domain.Account;
import me.enosent.repository.AccountRepository;
import me.enosent.service.AccountService;
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
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AccountController {

    @Autowired private ModelMapper modelMapper;
    @Autowired private AccountService accountService;
    @Autowired private AccountRepository repository;

    // not working
    /*  {
        "status": 415,
        "error": "Unsupported Media Type",
        "exception": "org.springframework.web.HttpMediaTypeNotSupportedException",
        "message": "Content type 'text/plain;charset=UTF-8' not supported",
        "path": "/accounts"
        }
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity saveAccount(@Valid @RequestBody AccountDto.Request request, BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseEntity<>("accounts.created.error", HttpStatus.BAD_REQUEST);
        }

        Account account = modelMapper.map(request, Account.class);
        Account newAccount =  accountService.saveAccount(account);

        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
    }

    // working!
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity getAccounts() {
        List<Account> accounts = repository.findAll();
        List<AccountDto.Response> responses =  accounts.stream().map(a -> modelMapper.map(a, AccountDto.Response.class)).collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // working!
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity getAccount(@PathVariable int id){
        Account account = repository.findOne(id);
        if(account == null) {
            return new ResponseEntity<>("account.null.error", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(account, AccountDto.Response.class), HttpStatus.OK);
    }

    //TODO PUT & PATCH

    //TODO DELETE
}
