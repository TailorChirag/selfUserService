package com.scaler.selfuserservice.repositiories;

import com.scaler.selfuserservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositiory extends JpaRepository<User,Long> {

}
