
package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Clase servicio
@Service
public class PersonaServiceImpl implements PersonaService{
    
    //Injectamos 
    @Autowired
    private PersonaDao personaDao; 
    
    @Override
    //Cuando solo es lectura
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    //Para commit o rollback
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    //Para commit o rollback
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    //Cuando solo es lectura
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
