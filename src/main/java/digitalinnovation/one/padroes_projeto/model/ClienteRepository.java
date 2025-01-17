package digitalinnovation.one.padroes_projeto.model;

// import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    // public Optional<Cliente> existsByEmail(String email);
    public Boolean existsByEmail(String email);

}