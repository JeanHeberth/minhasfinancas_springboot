package br.com.jeanheberth.minhasfinacas.api.service;

import br.com.jeanheberth.minhasfinacas.api.entity.Usuario;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);


}
