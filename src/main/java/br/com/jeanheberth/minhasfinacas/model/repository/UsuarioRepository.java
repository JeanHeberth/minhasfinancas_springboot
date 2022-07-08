package br.com.jeanheberth.minhasfinacas.model.repository;

import br.com.jeanheberth.minhasfinacas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    //List<Usuario> findByNome(String nomeUsuario);
   // Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

}
