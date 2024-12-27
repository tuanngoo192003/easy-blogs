package com.project.easyblogs.repositories.auth;

import com.project.core.entities.BaseRepository;
import com.project.easyblogs.entities.auth.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, String> {
}
