package com.example.proy_grupo4.Controllers;

import com.example.proy_grupo4.Entity.UsuariosRegistrado;
import com.example.proy_grupo4.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @GetMapping("ventanaLogin")
    public String ventanaLogin(){
        return "login";
    }
    @GetMapping("redireccionarPorRol")
    public String redireccionarPorRol(Authentication authentication, HttpSession session){
        String rol ="";
        List<GrantedAuthority> authorities=(List<GrantedAuthority>) authentication.getAuthorities();
        for(GrantedAuthority grantedAuthority : authorities){
            System.out.println(grantedAuthority);
            rol=grantedAuthority.getAuthority();
        }

        String username = authentication.getName();//obtengo por correo
        UsuariosRegistrado usuariosRegistrado = usuarioRepository.findByCorreo(username);//busco por email
        session.setAttribute("usuario",usuariosRegistrado);

        switch (rol){
            case "Administrativo" -> {return "redirect:/admin/incidentes";}
            case "Alumno" -> {return "redirect:/incidencia/list";}
            case "Seguridad" -> {return "redirect:/seguridad/inicio";}
            default -> {return"redirect:/incidencia/list";}
        }
    }

}