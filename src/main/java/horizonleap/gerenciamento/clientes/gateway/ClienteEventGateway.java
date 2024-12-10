package horizonleap.gerenciamento.clientes.gateway;

import horizonleap.gerenciamento.clientes.model.ClienteModel;

public interface ClienteEventGateway {
    void clienteCriado(ClienteModel cliente);
}
