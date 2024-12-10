package horizonleap.gerenciamento.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import horizonleap.gerenciamento.clientes.model.ClienteModel;
import horizonleap.gerenciamento.clientes.model.EnderecoUF;
import horizonleap.gerenciamento.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cadastrarCliente")
public class CadastrarCliente {
   @Autowired
   ClienteService service;

   @Operation(summary = "Cadastrar Cliente", tags = { "Cadastro", "Cliente" })
   @PostMapping
   public ResponseEntity<ClienteModel> cadastrarCliente(
         @RequestParam String nome,
         @RequestParam String endereco,
         @RequestParam String infoContato,
         @RequestParam String cpf,
         @RequestParam EnderecoUF uf) {

      ClienteModel cliente = service.save(nome, endereco, infoContato, cpf, uf);
      return ResponseEntity.ok(cliente);

   }
}
