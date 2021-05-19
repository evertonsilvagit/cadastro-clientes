package br.com.hitss.cadastro.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClienteReplaceRequestDTO {

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String idade;

    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String dataNascimento;

}
