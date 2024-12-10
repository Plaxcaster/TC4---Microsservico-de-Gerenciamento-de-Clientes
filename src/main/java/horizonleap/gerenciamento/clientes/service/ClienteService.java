package horizonleap.gerenciamento.clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import horizonleap.gerenciamento.clientes.gateway.ClienteEventGateway;
import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.repository.ClienteRepository;
import horizonleap.gerenciamento.clientes.repository.DadosClienteDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteEventGateway gateway;

    public ClienteModel save(String nome, String endereco, String infoContato) {

        ClienteModel clienteSemId = new ClienteModel(nome, endereco, infoContato);

        var cliente = clienteRepository.save(clienteSemId);
        gateway.clienteCriado(cliente);
        return cliente;
    }

    public ClienteModel busca(int id_cliente) {
        return clienteRepository.findById(id_cliente).get();
    }

    public DadosClienteDTO consultarDadosCliente(Integer id_cliente) {
        ClienteModel cliente = clienteRepository.findById(id_cliente).get();

        return new DadosClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEndereco(),
                cliente.getInfoContato());

    }

    public ClienteModel updateEndereco(Integer idCliente, String endereco) {
        var cliente = clienteRepository.findById(idCliente).get();

        cliente.setEndereco(endereco);
        cliente = clienteRepository.saveAndFlush(cliente);

        gateway.clienteCriado(cliente);
        return cliente;
    }

}
