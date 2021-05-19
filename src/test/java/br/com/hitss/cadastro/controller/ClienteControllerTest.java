package br.com.hitss.cadastro.controller;

import br.com.hitss.cadastro.controller.dto.ClienteMapper;
import br.com.hitss.cadastro.exception.ClienteNaoEncontradoException;
import br.com.hitss.cadastro.model.Cliente;
import br.com.hitss.cadastro.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.hitss.cadastro.DataFatory.clienteResponseDTOs;
import static br.com.hitss.cadastro.DataFatory.clientes;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteMapper clienteMapper;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void deveRetornarListaDeClientes() throws Exception {
        when(clienteService.findAll()).thenReturn(clientes());
        when(clienteMapper.toClienteResponseDTO(any(Cliente.class))).thenAnswer(AdditionalAnswers.returnsElementsOf(clienteResponseDTOs()));

        this.mockMvc.perform(get("/clientes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Han Solo")))
                .andExpect(content().string(containsString("751.574.000-04")))
                .andExpect(content().string(containsString("Luke Skywalker")))
                .andExpect(content().string(containsString("715.403.230-80")));
    }

    @Test
    public void deveEncontrarClienteERetornarSucesso() throws Exception {
        when(clienteService.findById(any())).thenReturn(clientes().get(0));
        when(clienteMapper.toClienteResponseDTO(any(Cliente.class))).thenReturn(clienteResponseDTOs().get(0));

        this.mockMvc.perform(get("/clientes/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Han Solo")))
                .andExpect(content().string(containsString("751.574.000-04")));
    }

    @Test
    public void deveRetornarNotFoundQuandoNaoEncontrarCliente() throws Exception {
        when(clienteService.findById(any())).thenThrow(ClienteNaoEncontradoException.class);

        this.mockMvc.perform(get("/clientes/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Cliente não encontrado"));
    }

}
