package com.cibertec.service;

import com.cibertec.model.Rol;
import com.cibertec.model.Usuario;
import com.cibertec.repository.RolRepository;
import com.cibertec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder
            = new BCryptPasswordEncoder();

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    public Usuario buscarUsuarioPorNomusuario(String nomUsuario){
        return usuarioRepository.findByNomusuario(nomUsuario);
    }
    public Usuario guardarUsuario(Usuario usuario){
        usuario.setNombres(usuario.getNombres());
        usuario.setApellidos(usuario.getApellidos());
        usuario.setNomusuario(usuario.getNomusuario());
        usuario.setEmail(usuario.getEmail());
        usuario.setPassword(bCryptPasswordEncoder
                .encode(usuario.getPassword()));
        usuario.setActivo(true);
        Rol rol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<Rol>(Arrays.asList(rol)));
        return usuarioRepository.save(usuario);
    }

}