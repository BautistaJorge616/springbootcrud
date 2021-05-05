package mx.com.gm.dao;

import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    //Solicitud del framework de seguridad
    Usuario findByUsername(String username);
}
