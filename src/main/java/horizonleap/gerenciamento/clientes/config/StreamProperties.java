package horizonleap.gerenciamento.clientes.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class StreamProperties {
    private String clienteCriadoChanel = "clienteCriado-out-0";
    private String clienteAlteradoChanel = "clienteAlterado-out-0";
}