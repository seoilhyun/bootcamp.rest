package me.enosent.service;

import me.enosent.domain.Account;
import me.enosent.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public Account createNewAccount(Account account) {
        account.setDate(new Date());
        return repository.save(account);
    }

    public Account updateAccount(Account account, Account updateAccount) {

        if(StringUtils.hasText(updateAccount.getUsername())) {
            account.setUsername(updateAccount.getUsername());
        }

        if(StringUtils.hasText(updateAccount.getPassword())) {
            account.setPassword(updateAccount.getPassword());
        }

        return repository.save(account);
    }
}
