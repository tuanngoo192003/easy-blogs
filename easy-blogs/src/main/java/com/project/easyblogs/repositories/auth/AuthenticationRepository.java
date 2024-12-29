package com.project.easyblogs.repositories.auth;

import com.project.core.entities.BaseRepository;
import com.project.easyblogs.dtos.AuthenticationDTO;
import com.project.easyblogs.entities.auth.Authentication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends BaseRepository<Authentication, String> {

    @Query(value = "SELECT au.id, au.account_id as accountId, au.usercase_id as userCaseId, a.roles FROM auth.authentication au " +
            "       INNER JOIN auth.accounts a ON au.account_id = a.id " +
            "       INNER JOIN auth.usercases uc ON uc.id = au.user_case_id " +
            "       WHERE a.username = :username AND uc.name = :userCaseName ", nativeQuery = true)
    AuthenticationDTO findByUsernameAndUserCase(@Param("userCaseName") String name,
                                                @Param("username") String username);
}

