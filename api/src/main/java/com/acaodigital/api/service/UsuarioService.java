package com.acaodigital.api.service;

import com.acaodigital.api.model.Usuario;
import com.acaodigital.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            return usuarioOpt.get();
        }
        throw new RuntimeException("E-mail ou senha inválidos.");
    }
}