package br.com.jeanheberth.minhasfinacas.api.controller;

import br.com.jeanheberth.minhasfinacas.api.dto.UsuarioDTO;
import br.com.jeanheberth.minhasfinacas.api.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.api.exception.ErroAutenticacao;
import br.com.jeanheberth.minhasfinacas.api.exception.RegraNegocioException;
import br.com.jeanheberth.minhasfinacas.api.repository.UsuarioRepository;
import br.com.jeanheberth.minhasfinacas.api.service.UsuarioService;
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

   @PostMapping("/autenticar")
   public ResponseEntity autenticar(@RequestBody UsuarioDTO usuarioDTO){
       try {
           Usuario usuarioAutenticado = usuarioService.autenticar(usuarioDTO.getEmail(), usuarioDTO.getSenha());
           return ResponseEntity.ok(usuarioAutenticado);
       }catch (ErroAutenticacao e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
   }

    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .dataCadastro(usuarioDTO.getDataCadastro())
                .build();
        try {
            Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
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



