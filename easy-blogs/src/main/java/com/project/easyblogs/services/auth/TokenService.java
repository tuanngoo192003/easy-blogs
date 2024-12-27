package com.project.easyblogs.services.auth;

import com.project.core.entities.BaseRepository;
import com.project.core.entities.BaseService;
import com.project.easyblogs.entities.auth.Token;
import com.project.easyblogs.repositories.auth.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends BaseService<Token, String> {

    private final TokenRepository repository;

    protected TokenService(BaseRepository<Token, String> repository) {
        super(repository);
        this.repository = (TokenRepository) repository;
    }
}
