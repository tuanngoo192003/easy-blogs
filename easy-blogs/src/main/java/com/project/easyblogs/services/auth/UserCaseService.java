package com.project.easyblogs.services.auth;

import com.project.core.entities.BaseRepository;
import com.project.core.entities.BaseService;
import com.project.easyblogs.entities.auth.UserCase;
import com.project.easyblogs.repositories.auth.UserCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCaseService extends BaseService<UserCase, String> {

    private final UserCaseRepository repository;

    protected UserCaseService(BaseRepository<UserCase, String> repository) {
        super(repository);
        this.repository = (UserCaseRepository) repository;
    }
}
