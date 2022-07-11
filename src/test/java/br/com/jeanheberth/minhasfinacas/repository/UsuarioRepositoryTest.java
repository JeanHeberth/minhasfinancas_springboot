package br.com.jeanheberth.minhasfinacas.repository;


import br.com.jeanheberth.minhasfinacas.model.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.model.repository.UsuarioRepository;
import br.com.jeanheberth.minhasfinacas.service.UsuarioService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        // Cenário
        Usuario usuario = Usuario.builder().nome("Jean Heberth").email("jean@gmail.com").build();
        usuarioRepository.save(usuario);

        // Ação / Execução
        boolean resultado = usuarioRepository.existsByEmail("jean@gmail.com");

        //Verificação
        Assertions.assertThat(resultado).isTrue();


    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail(){
        //cenário


        //Ação / Execução


        //Verificação
    }
}
