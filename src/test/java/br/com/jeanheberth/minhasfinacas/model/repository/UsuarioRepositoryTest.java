package br.com.jeanheberth.minhasfinacas.model.repository;

import br.com.jeanheberth.minhasfinacas.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        // Cenário
        Usuario usuario = criarUsuario();
        testEntityManager.persist(usuario);

        // Ação / Execução
        boolean resultado = usuarioRepository.existsByEmail("jean@gmail.com");

        //Verificação
        Assertions.assertThat(resultado).isTrue();


    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
        //cenário


        //Ação / Execução
        boolean resultado = usuarioRepository.existsByEmail("jean@gmail.com");


        //Verificação
        Assertions.assertThat(resultado).isFalse();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        //Cenário
        Usuario usuario = criarUsuario();

        //Ação
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        // Cenário
        Usuario usuario = criarUsuario();
        testEntityManager.persist(usuario);

        //Verificação
        Optional<Usuario> resultado = usuarioRepository.findByEmail("usuario@gmail.com");

        Assertions.assertThat(resultado.isPresent()).isTrue();
    }

    @Test
    public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoExisteNaBase() {
        // Cenário

        //Verificação
        Optional<Usuario> resultado = usuarioRepository.findByEmail("usuario@gmail.com");

        Assertions.assertThat(resultado.isPresent()).isFalse();
    }

    public static Usuario criarUsuario() {
        return Usuario
                .builder()
                .nome("usuario")
                .email("usuario@gmail.com")
                .senha("123456").build();

    }
}