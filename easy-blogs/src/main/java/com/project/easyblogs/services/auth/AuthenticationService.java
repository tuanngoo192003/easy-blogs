package com.project.easyblogs.services.auth;

import com.project.core.entities.BaseRepository;
import com.project.core.entities.BaseService;
import com.project.easyblogs.entities.auth.Authentication;
import com.project.easyblogs.repositories.auth.AuthenticationRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService extends BaseService<Authentication, String> {

    private final AuthenticationRepository repository;

    protected AuthenticationService(BaseRepository<Authentication, String> repository) {
        super(repository);
        this.repository = (AuthenticationRepository) repository;
    }

    public Authentication findByUsernameAndUserCase(String name, String username) {
        return repository.findByUsernameAndUserCase(name, username);
    }
}
