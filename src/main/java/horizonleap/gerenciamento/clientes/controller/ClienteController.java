package horizonleap.gerenciamento.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.repository.DadosClienteDTO;
import horizonleap.gerenciamento.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Consultar dados do Cliente", tags = { "Consulta", "Cliente" })
    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteModel> busca(@PathVariable Integer id_cliente) {

        return ResponseEntity.ok(service.busca(id_cliente));

    }

    @Operation(summary = "Consultar dados do Cliente e seus carros", tags = { "Consulta", "Cliente" })
    @GetMapping("/dadosCliente/{id_cliente}")
    public ResponseEntity<DadosClienteDTO> consultarDadosCliente(@PathVariable Integer id_cliente) {

        return ResponseEntity.ok(service.consultarDadosCliente(id_cliente));
    }

    @Operation(summary = "Altera endereço do cliente", tags = { "Alteração", "Cliente" })
    @PutMapping("/{endereco}")
    public ResponseEntity<ClienteModel> alteraEndereco(@PathVariable String id_cliente,  String endereco) {

        try {
            Integer idCliente = Integer.valueOf(id_cliente);

            ClienteModel cliente = service.updateEndereco(idCliente);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ClienteModel());
        }
    }
}