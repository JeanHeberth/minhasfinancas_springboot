package br.com.jeanheberth.minhasfinacas.controller;


import br.com.jeanheberth.minhasfinacas.model.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    /* Método para listar usuário */
    @GetMapping
    public List<Usuario>listar(String nomeUsuario) {

        List<Usuario> usuarasios = usuarioRepository.findAll();
        return usuarasios;
    }
}



