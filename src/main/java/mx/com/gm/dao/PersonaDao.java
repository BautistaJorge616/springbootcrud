package mx.com.gm.dao;

//Repositorio

import mx.com.gm.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//Parametros Clase = Persona Tipo de llvae primaria = Long
public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
