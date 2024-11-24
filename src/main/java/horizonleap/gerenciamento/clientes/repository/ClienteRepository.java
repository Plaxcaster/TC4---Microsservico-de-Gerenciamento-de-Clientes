package horizonleap.gerenciamento.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import horizonleap.gerenciamento.clientes.model.ClienteModel;



@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel,Integer>{

}
