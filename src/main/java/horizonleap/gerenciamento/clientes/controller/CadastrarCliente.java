package horizonleap.gerenciamento.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cadastrarCliente")
public class CadastrarCliente {
   @Autowired
   ClienteService service;

   @Operation(summary = "Cadastrar Cliente", tags = { "Cadastro", "Cliente" })
   @PostMapping
   public ResponseEntity<ClienteModel> cadastrarCliente(String nome, String endereco,
         String infoContato) {

      return ResponseEntity.ok(service.save(nome, endereco, infoContato));

   }

}
