package com.example.NebulaMusic.controller;

import com.example.NebulaMusic.model.Usuario;
import com.example.NebulaMusic.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UsuarioService usuarioService;

    // En Spring moderno (4.3+), si hay un solo constructor, @Autowired es opcional,
    // por lo que este constructor está perfecto.
    public MainController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/index", "/index.html"})
    public String index(){
        return "index";
    }

    // Se dejó "/" aquí como la página principal de entrada
    @GetMapping({"/", "/iniciar-sesion", "/iniciar-sesion.html"})
    public String iniciarSesion(){
        return "iniciar-sesion";
    }

    // Se eliminó el "/" duplicado
    @GetMapping({"/registro", "/registro.html"})
    public String registro(){
        return "registro";
    }

    // Se eliminó el "/" duplicado
    @GetMapping({"/error", "/error.html"})
    public String errorPage(){
        return "error";
    }

    // Corregido: Se quitó la llave huérfana y se añadió el return faltante
    @PostMapping("/crear-cuenta")
    public String crearCuenta(@ModelAttribute Usuario usuario){
        if (usuarioService.existeCorreo(usuario.getCorreo())){
            return "error"; // Si ya existe, nos manda a la vista de error
        }

        usuarioService.registrar(usuario);

        // Redirige al login para que el usuario inicie sesión con su nueva cuenta
        return "redirect:/iniciar-sesion";
    }

     @PostMapping("/autentication")
        public String autenticacion(@RequestParam("correo") String correo,
                                    @RequestParam("contrasenia") String contrasenia){

        if(usuarioService.autenticar(correo, contrasenia)){
            return "redirect:/index";
        }

        return "redirect:/error";
     }

     @GetMapping("/cerrar-sesion")
     public String cerrarSesion(){
        return "redirect:/iniciar-sesion?logout";
     }
}


