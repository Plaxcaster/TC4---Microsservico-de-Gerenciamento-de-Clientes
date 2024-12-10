package horizonleap.gerenciamento.clientes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.model.EnderecoUF;
import horizonleap.gerenciamento.clientes.repository.ClienteRepository;
import horizonleap.gerenciamento.clientes.service.ClienteService;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        ClienteModel cliente = new ClienteModel("Nome", "Endereco", "InfoContato", "12345678901", EnderecoUF.DF);
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(cliente);

        ClienteModel savedCliente = clienteService.save("Nome", "Endereco", "InfoContato", "12345678901", EnderecoUF.DF);

        assertEquals("Nome", savedCliente.getNome());
        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
    }

    @Test
    public void testBusca() {
        ClienteModel cliente = new ClienteModel("Nome", "Endereco", "InfoContato", "12345678901", EnderecoUF.DF);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteModel foundCliente = clienteService.busca(1);

        assertEquals("Nome", foundCliente.getNome());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateEndereco() {
        ClienteModel cliente = new ClienteModel("Nome", "Endereco", "InfoContato", "12345678901", EnderecoUF.DF);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(cliente);

        ClienteModel updatedCliente = clienteService.updateEndereco(1);

        assertEquals("Nome", updatedCliente.getNome());
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
    }
}
