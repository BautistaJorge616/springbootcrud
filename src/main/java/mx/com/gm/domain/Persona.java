
package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import javax.validation.constraints.*;

//Genera todos los metodos get/set/toString/hashcode/equals
@Data
//Convertir a una clase de entidad
@Entity
//Mapear bien la tabla para evitar errores
@Table(name="persona")
public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    //Mapeo de la llave primaria
    @Id
    //Forma de generar la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email
    private String email;
   
    private String telefono;
    
    @NotNull
    private Double saldo;
    
}
