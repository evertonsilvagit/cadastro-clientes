package br.com.hitss.cadastro.controller.dto.request;

import lombok.Data;

@Data
public class ClienteUpdateRequestDTO {

    private String nome;
    private String idade;
    private String cpf;
    private String dataNascimento;

}
