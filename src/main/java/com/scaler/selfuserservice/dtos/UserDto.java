package com.scaler.selfuserservice.dtos;

import com.scaler.selfuserservice.models.Role;
import com.scaler.selfuserservice.models.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

    public static UserDto from(User user){
        if (user == null ) return null;
        UserDto dto = new UserDto();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.roles = user.getRoles();
        dto.isEmailVerified = user.isEmailVerified();

        return dto;
    }

}
