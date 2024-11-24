package horizonleap.gerenciamento.clientes.repository;

import java.util.List;



public record DadosClienteDTO(
        int id,
        String nome,
        String endereço,
        String infoContato) {

}
