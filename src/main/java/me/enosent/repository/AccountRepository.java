package me.enosent.repository;

import me.enosent.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: seoilhyun
 * Date: 2015. 5. 29.
 * Time: 오전 12:18
 * To change this template use File | Settings | File Templates.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
