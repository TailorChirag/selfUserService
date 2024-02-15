package com.scaler.selfuserservice.repositiories;

import com.scaler.selfuserservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository <Token,Long> {

    Token save(Token token);

    Optional<Token> findByValueAndDeleted(String value, boolean deleted);

    Optional<Token> findByValueAndDeletedAndExpiryAtBefore(String value, boolean deleted, Date date);
}
