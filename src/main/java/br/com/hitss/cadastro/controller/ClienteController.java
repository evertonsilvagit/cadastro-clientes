package br.com.hitss.cadastro.controller;

import br.com.hitss.cadastro.controller.dto.ClienteMapper;
import br.com.hitss.cadastro.controller.dto.request.ClienteCreateRequestDTO;
import br.com.hitss.cadastro.controller.dto.request.ClienteReplaceRequestDTO;
import br.com.hitss.cadastro.controller.dto.request.ClienteUpdateRequestDTO;
import br.com.hitss.cadastro.controller.dto.response.ClienteResponseDTO;
import br.com.hitss.cadastro.exception.ClienteExistenteException;
import br.com.hitss.cadastro.exception.ClienteNaoEncontradoException;
import br.com.hitss.cadastro.exception.DataInvalidaException;
import br.com.hitss.cadastro.model.Cliente;
import br.com.hitss.cadastro.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DateTimeException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping(path = "/clientes")
@RestController
public class ClienteController {

    ClienteService clienteService;

    ClienteMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> list(){
        List<ClienteResponseDTO> clientes = this.clienteService.findAll()
                .stream()
                .map(mapper::toClienteResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) throws ClienteNaoEncontradoException {
        Cliente cliente = this.clienteService.findById(id);
        return new ResponseEntity<>(mapper.toClienteResponseDTO(cliente), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody @Valid ClienteCreateRequestDTO clienteCreateRequestDTO)
            throws DataInvalidaException, ClienteExistenteException {
        Cliente cliente;
        try {
            cliente = mapper.toCliente(clienteCreateRequestDTO);
        } catch (DateTimeException ex) {
            throw new DataInvalidaException();
        }

        Cliente saved = this.clienteService.create(cliente);
        return new ResponseEntity<>(mapper.toClienteResponseDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteResponseDTO> replace(@PathVariable Long id, @RequestBody @Valid ClienteReplaceRequestDTO clienteReplaceRequestDTO)
            throws ClienteNaoEncontradoException, DataInvalidaException, ClienteExistenteException {
        Cliente cliente;
        try {
            cliente = mapper.toCliente(clienteReplaceRequestDTO);
        } catch (DateTimeException ex) {
            throw new DataInvalidaException();
        }
        Cliente saved = this.clienteService.update(id, cliente);
        return new ResponseEntity<>(mapper.toClienteResponseDTO(saved), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ClienteUpdateRequestDTO clienteUpdateRequestDTO)
            throws ClienteNaoEncontradoException, DataInvalidaException, ClienteExistenteException {
        Cliente cliente;
        try {
            cliente = mapper.toCliente(clienteUpdateRequestDTO);
        } catch (DateTimeException ex) {
            throw new DataInvalidaException();
        }
        Cliente saved = this.clienteService.update(id, cliente);
        return new ResponseEntity<>(mapper.toClienteResponseDTO(saved), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.clienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
