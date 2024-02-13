package com.scaler.selfuserservice.repositiories;

import com.scaler.selfuserservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository <Token,Long> {

    Token save(Token token);
}
