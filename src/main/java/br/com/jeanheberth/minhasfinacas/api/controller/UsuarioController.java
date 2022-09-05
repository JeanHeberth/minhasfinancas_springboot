package br.com.jeanheberth.minhasfinacas.api.controller;

import br.com.jeanheberth.minhasfinacas.api.dvo.UsuarioDVO;
import br.com.jeanheberth.minhasfinacas.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.exception.ErroAutenticacao;
import br.com.jeanheberth.minhasfinacas.exception.RegraNegocioException;
import br.com.jeanheberth.minhasfinacas.model.repository.UsuarioRepository;
import br.com.jeanheberth.minhasfinacas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDVO dvo) {
        Usuario usuario = Usuario.builder()
                .nome(dvo.getNome())
                .email(dvo.getEmail())
                .senha(dvo.getSenha())
                .dataCadastro(dvo.getData_cadastro())
                .build();
        try {
            Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDVO dvo) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticar(dvo.getEmail(), dvo.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* Método para listar usuário */
    @GetMapping
    public List<Usuario> listar(String nomeUsuario) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
}



