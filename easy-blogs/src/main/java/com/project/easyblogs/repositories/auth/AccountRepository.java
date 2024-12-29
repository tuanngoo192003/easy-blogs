package com.project.easyblogs.repositories.auth;

import com.project.core.entities.BaseRepository;
import com.project.easyblogs.dtos.AccountInfoDTO;
import com.project.easyblogs.entities.auth.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, String> {

    @Query(value = "SELECT a.id, a.username, a.email, a.password, a.status, a.is_verified as isVerified, a.roles \n" +
            "FROM auth.accounts a " +
            "\tWHERE a.username = :username", nativeQuery = true)
    AccountInfoDTO findAccountByUsername(@Param("username") String username);
}
