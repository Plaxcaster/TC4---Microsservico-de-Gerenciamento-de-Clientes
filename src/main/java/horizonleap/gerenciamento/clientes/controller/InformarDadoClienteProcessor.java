package horizonleap.gerenciamento.clientes.controller;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InformarDadoClienteProcessor implements Function<Integer , ClienteModel> {
    
    private final ClienteService service;

    @Override
    public ClienteModel apply(Integer idCliente) {
        log.info("Buscando cliente " + idCliente);
        return service.busca(idCliente);
    }
}
