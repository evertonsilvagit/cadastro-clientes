package br.com.hitss.cadastro.service;

import br.com.hitss.cadastro.controller.dto.ClienteMapper;
import br.com.hitss.cadastro.exception.ClienteExistenteException;
import br.com.hitss.cadastro.exception.ClienteNaoEncontradoException;
import br.com.hitss.cadastro.model.Cliente;
import br.com.hitss.cadastro.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {

    ClienteRepository clienteRepository;

    ClienteMapper mapper;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) throws ClienteNaoEncontradoException {
        return clienteRepository.findById(id)
                .orElseThrow(ClienteNaoEncontradoException::new);

    }

    public Cliente create(Cliente cliente) throws ClienteExistenteException {
        try {
            return this.clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException ex){
            throw new ClienteExistenteException();
        }
    }

    public Cliente update(Long id, Cliente cliente) throws ClienteNaoEncontradoException, ClienteExistenteException {
        Cliente saved = this.clienteRepository.findById(id)
                .orElseThrow(ClienteNaoEncontradoException::new);

        mapper.toCliente(cliente, saved);

        try {
            return this.clienteRepository.save(saved);
        } catch (DataIntegrityViolationException ex){
            throw new ClienteExistenteException();
        }

    }

    public void deleteById(Long id){
        this.clienteRepository.deleteById(id);
    }
}
