package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ControladorInicio {

    //Injectar
    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String iniciar(Model model, @AuthenticationPrincipal User user) {

        var personas = personaService.listarPersonas();

        log.info("Ejecutando el controlador Spring MVC");
        
        log.info("Usuario que hizo login: " + user);

        //Compartimos la lista de personas
        model.addAttribute("personas", personas);
        
        var saldoTotal = 0D;
        
        for(var tem: personas){
            saldoTotal += tem.getSaldo();
        }
        
        //Compartimos la variable que contien el saldo total
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        
        //Redirigir hacia la vista
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        //Redirigir hacia la vista
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated Persona persona, BindingResult errors) {
        if (errors.hasErrors()) {
            return "modificar";
        } else {
            personaService.guardar(persona);
            return "redirect:/";
        }

       
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar{idPersona}")
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
