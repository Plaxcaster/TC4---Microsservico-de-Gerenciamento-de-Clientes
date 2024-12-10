package horizonleap.gerenciamento.clientes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import horizonleap.gerenciamento.clientes.controller.CadastrarCliente;
import horizonleap.gerenciamento.clientes.controller.ClienteController;
import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.model.EnderecoUF;
import horizonleap.gerenciamento.clientes.repository.DadosClienteDTO;
import horizonleap.gerenciamento.clientes.service.ClienteService;

public class ClienteControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClienteController clienteController;

    @InjectMocks
    private CadastrarCliente cadastrarCliente;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController, cadastrarCliente).build();
    }


    @Test
    public void testConsultarDadosCliente() throws Exception {
        ClienteModel cliente = new ClienteModel("Nome", "Endereco", "InfoContato", "12345678901", EnderecoUF.DF);
        DadosClienteDTO dadosCliente = new DadosClienteDTO(1, "Nome", "Endereco", "InfoContato");
        when(clienteService.consultarDadosCliente(1)).thenReturn(dadosCliente);

        mockMvc.perform(get("/cliente/dadosCliente/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Nome"));

        verify(clienteService, times(1)).consultarDadosCliente(1);
    }


}
