package br.com.hitss.cadastro;

import br.com.hitss.cadastro.controller.dto.response.ClienteResponseDTO;
import br.com.hitss.cadastro.model.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataFatory {

    public static List<Cliente> clientes(){

        List<Cliente> clientes = new ArrayList<>();

        clientes.add(Cliente.builder()
                .id(1l)
                .nome("Han Solo")
                .dataNascimento(LocalDate.parse("1867-06-23"))
                .idade("78")
                .cpf("751.574.000-04")
                .build());

        clientes.add(Cliente.builder()
                .id(2l)
                .nome("Luke Skywalker")
                .dataNascimento(LocalDate.parse("1867-06-23"))
                .idade("78")
                .cpf("715.403.230-80")
                .build());

        return clientes;
    }

    public static List<ClienteResponseDTO> clienteResponseDTOs(){
        return clientes().stream()
                .map(cliente -> ClienteResponseDTO.builder()
                        .id(cliente.getId())
                        .cpf(cliente.getCpf())
                        .nome(cliente.getNome())
                        .dataNascimento(cliente.getDataNascimento())
                        .build())
                .collect(Collectors.toList());
    }


}
