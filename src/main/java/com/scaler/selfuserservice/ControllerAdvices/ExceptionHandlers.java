package com.scaler.selfuserservice.ControllerAdvices;


import com.scaler.selfuserservice.dtos.ExceptionDto;
import com.scaler.selfuserservice.exceptions.PasswordNotFoundException;
import com.scaler.selfuserservice.exceptions.UserNotFoundException;
import com.scaler.selfuserservice.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMesasgae("zero se diveide nahi karte Lodu");
        return new ResponseEntity<>(exceptionDto, HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(UserNotFoundException message){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleUsernameNotfoundException(UsernameNotFoundException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.valueOf(302)
        );
    }

    @ExceptionHandler(PasswordNotFoundException.class)
    private ResponseEntity<ExceptionDto> handlePasswordNotFoundException(PasswordNotFoundException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.NOT_FOUND
        );
    }



}
