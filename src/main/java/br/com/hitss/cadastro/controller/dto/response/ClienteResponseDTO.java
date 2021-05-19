package br.com.hitss.cadastro.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String idade;
    private String cpf;
    private LocalDate dataNascimento;

}
