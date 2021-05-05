
package mx.com.gm.servicio;

//Clase de servicio

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Recuperamos el objeto de usuario
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username); 
        }
        
        //Para recuperar los roles del usuario
        //Envolver clase para cumplir con el estandar de JPA
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol: usuario.getRoles()){
            //Envolver cada objeto
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        //Retornamos un objeto con toda la información
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
