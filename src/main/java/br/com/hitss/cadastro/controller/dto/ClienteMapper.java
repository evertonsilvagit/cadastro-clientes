package br.com.hitss.cadastro.controller.dto;

import br.com.hitss.cadastro.controller.dto.request.ClienteCreateRequestDTO;
import br.com.hitss.cadastro.controller.dto.response.ClienteResponseDTO;
import br.com.hitss.cadastro.controller.dto.request.ClienteReplaceRequestDTO;
import br.com.hitss.cadastro.controller.dto.request.ClienteUpdateRequestDTO;
import br.com.hitss.cadastro.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {

    ClienteResponseDTO toClienteResponseDTO(Cliente cliente);

    Cliente toCliente(ClienteCreateRequestDTO clienteCreateRequestDTO);
    Cliente toCliente(ClienteUpdateRequestDTO clienteUpdateRequestDTO);
    Cliente toCliente(ClienteReplaceRequestDTO clienteReplaceRequestDTO);
    void toCliente(Cliente cliente, @MappingTarget Cliente updated);

}
