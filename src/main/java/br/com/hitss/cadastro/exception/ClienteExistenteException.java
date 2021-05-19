package br.com.hitss.cadastro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Este cliente já foi cadastrado")
public class ClienteExistenteException extends Exception {
}
