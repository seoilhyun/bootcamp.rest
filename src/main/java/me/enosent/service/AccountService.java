package me.enosent.service;

import me.enosent.domain.Account;
import me.enosent.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 29.
 * Time: 오전 12:19
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AccountService {

    @Autowired private AccountRepository repository;

    public Account saveAccount(Account account) {
        account.setDate(new Date());
        return repository.save(account);
    }
}
