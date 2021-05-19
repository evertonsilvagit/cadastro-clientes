package br.com.hitss.cadastro.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", 400);
        body.put("mensagem", "Existem campos inválidos");

        List<Map<String, Object>> erros = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, Object> campos = new LinkedHashMap<>();
                    campos.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return campos;
                })
                .collect(Collectors.toList());

        body.put("campos", erros);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
