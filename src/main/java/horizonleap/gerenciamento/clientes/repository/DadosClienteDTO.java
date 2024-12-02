package horizonleap.gerenciamento.clientes.repository;

public record DadosClienteDTO(
        int id,
        String nome,
        String endereço,
        String infoContato) {

}
