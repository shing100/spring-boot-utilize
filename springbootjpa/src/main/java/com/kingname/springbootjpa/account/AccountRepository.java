package com.kingname.springbootjpa.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    //@Query(nativeQuery = true, value = "select * from account where username = '{0}'")
    Account findByUsername(String username);
}
