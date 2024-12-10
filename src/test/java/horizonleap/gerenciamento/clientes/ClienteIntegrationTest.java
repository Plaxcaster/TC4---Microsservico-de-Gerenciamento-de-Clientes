package horizonleap.gerenciamento.clientes;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.model.EnderecoUF;
import horizonleap.gerenciamento.clientes.repository.ClienteRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        // Clear the database before each test
        clienteRepository.deleteAll();
    }

    @Test
    public void testCadastrarCliente() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/cadastrarCliente";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        String requestBody = "nome=Test%20Name&endereco=Test%20Address&infoContato=123456789&cpf=12345678901&uf=DF";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<ClienteModel> result = restTemplate.postForEntity(uri, request, ClienteModel.class);

        // Verify request succeed
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Test Name", result.getBody().getNome());
    }

    @Test
    public void testGetCliente() throws URISyntaxException {
        // Add a cliente to the database
        ClienteModel savedCliente = clienteRepository.save(new ClienteModel("Test Name", "Test Address", "123456789", "12345678901", EnderecoUF.DF));

        final String baseUrl = "http://localhost:" + port + "/cliente/" + savedCliente.getId();
        URI uri = new URI(baseUrl);

        ResponseEntity<ClienteModel> result = restTemplate.getForEntity(uri, ClienteModel.class);

        // Verify request succeed
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Test Name", result.getBody().getNome());
    }

    @Test
    public void testUpdateCliente() throws URISyntaxException {
        // Add a cliente to the database
        ClienteModel savedCliente = clienteRepository.save(new ClienteModel("Test Name", "Test Address", "123456789", "12345678901", EnderecoUF.DF));

        final String baseUrl = "http://localhost:" + port + "/cliente/" + savedCliente.getId() + "/endereco";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        String requestBody = "endereco=New Address";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<ClienteModel> result = restTemplate.exchange(uri, HttpMethod.PUT, request, ClienteModel.class);

        // Verify request succeed
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Test Name", result.getBody().getNome());
        assertEquals("New Address", result.getBody().getEndereco());
    }
}
