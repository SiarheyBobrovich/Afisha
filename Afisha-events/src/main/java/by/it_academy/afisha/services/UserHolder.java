package by.it_academy.afisha.services;

import by.it_academy.afisha.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserHolder {

    public UserDto getUser(){
        final UserDto user;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals("anonymousUser", principal)) {
            user = (UserDto) principal;

        } else {
            user = null;
        }

        return user;
    }

}
