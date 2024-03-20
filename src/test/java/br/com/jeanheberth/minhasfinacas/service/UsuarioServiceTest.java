package br.com.jeanheberth.minhasfinacas.service;


import br.com.jeanheberth.minhasfinacas.api.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.api.exception.ErroAutenticacao;
import br.com.jeanheberth.minhasfinacas.api.exception.RegraNegocioException;
import br.com.jeanheberth.minhasfinacas.api.repository.UsuarioRepository;
import br.com.jeanheberth.minhasfinacas.api.service.UsuarioService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @Test(expected = Test.None.class)
    public void deveSalvarUmUsuario() {
        //Cenário
        Mockito.doNothing().when(usuarioService).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder()
                .id(1l)
                .nome("nome")
                .email("email@email.com")
                .senha("senha").build();

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        //Ação
        Usuario usuarioSalvo = usuarioService.salvarUsuario(new Usuario());
        //Verificação
        Assertions.assertThat(usuarioSalvo).isNotNull();
        Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
        Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
        Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@email.com");
        Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");

    }

    @Test(expected = RegraNegocioException.class)
    public void NaoDeveSalvarUmUsuarioComEmailCadastrado() {
        //Cenário
        String email = "email@email.com";
        Usuario usuario = Usuario.builder().email("email@email.com").build();
        Mockito.doThrow(RegraNegocioException.class).when(usuarioService).validarEmail(email);

        //Ação
        usuarioService.salvarUsuario(usuario);

        //Verificação
        Mockito.verify(usuarioRepository, Mockito.never()).save(usuario);
    }


    @Test(expected = Test.None.class)
    public void deveAutenticarUmUsuarioComSucesso() {
        // Cenário
        String email = "jean@gmail.com";
        String senha = "123";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        //Acao
        Usuario resultado = usuarioService.autenticar(email, senha);

        //Verficacao
        Assertions.assertThat(resultado).isNotNull();
    }

    @Test
    public void deveLancarErroQuandoNaoEncontarUsuarioCadastradoComOEmailInformado() {
        // Cenário
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        //Acao
        Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticar("email@gmail.com", "123"));
        //verificação
        Assertions.assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class).hasMessage("Usuário não encontrado para o email informado.");

    }


    @Test
    public void deveLancarErroQuandoSenhaNaoConcidirComASenhaCadastrada() {
        //Cenário
        String senha = "senha";
        Usuario usuario = Usuario.builder().email("email@gmail.com").senha(senha).build();
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        //Acao
        Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticar("email@gmail.com", "123"));
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha inválida.");


    }

    @Test(expected = Test.None.class)
    public void deveValidarEmail() {
        // Cenário
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        //Ação
        usuarioService.validarEmail("email@gmail.com");
        // Validação

    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarQuandoExistirEmailCadastrado() {
        //Cenário
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        //Ação
        usuarioService.validarEmail("jeanheberth@gmail.com");


    }


}
