package br.com.jeanheberth.minhasfinacas.api.controller;

import br.com.jeanheberth.minhasfinacas.api.dto.UsuarioDTO;
import br.com.jeanheberth.minhasfinacas.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.exception.ErroAutenticacao;
import br.com.jeanheberth.minhasfinacas.exception.RegraNegocioException;
import br.com.jeanheberth.minhasfinacas.repository.UsuarioRepository;
import br.com.jeanheberth.minhasfinacas.service.LancamentoService;
import br.com.jeanheberth.minhasfinacas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LancamentoService lancamentoService;

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
    public ResponseEntity obterSaldo(@PathVariable ("id") Long id){
        Optional<Usuario> usuario = usuarioService.obterPorId(id);
        if (!usuario.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);

    }
}



