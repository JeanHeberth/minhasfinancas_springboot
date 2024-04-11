package br.com.jeanheberth.minhasfinacas.repository;

import br.com.jeanheberth.minhasfinacas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //List<Usuario> findByNome(String nomeUsuario);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
