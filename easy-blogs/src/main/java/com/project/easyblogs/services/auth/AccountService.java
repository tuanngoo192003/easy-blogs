package com.project.easyblogs.services.auth;

import com.project.core.entities.BaseRepository;
import com.project.core.entities.BaseService;
import com.project.easyblogs.entities.auth.Account;
import com.project.easyblogs.repositories.auth.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, String> {

    private final AccountRepository repository;

    protected AccountService(BaseRepository<Account, String> repository) {
        super(repository);
        this.repository = (AccountRepository) repository;
    }
}
