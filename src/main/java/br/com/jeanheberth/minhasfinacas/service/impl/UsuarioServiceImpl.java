package br.com.jeanheberth.minhasfinacas.service.impl;

import br.com.jeanheberth.minhasfinacas.exception.RegraNegocioException;
import br.com.jeanheberth.minhasfinacas.model.entity.Usuario;
import br.com.jeanheberth.minhasfinacas.model.repository.UsuarioRepository;
import br.com.jeanheberth.minhasfinacas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com esse email");
        }
    }
}
