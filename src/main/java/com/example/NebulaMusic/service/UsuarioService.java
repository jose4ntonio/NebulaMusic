package com.example.NebulaMusic.service;

import com.example.NebulaMusic.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsuarioService {
    // Mapa global para almacenar los usuarios
    private final Map<String, Usuario> usuarios = new ConcurrentHashMap<>();

    public UsuarioService() {
    }

    // 1. Corregido: Se usa la instancia 'usuario' en minúscula
    public void registrar(Usuario usuario) {
        usuarios.put(usuario.getCorreo(), usuario);
    }

    // 2. Corregido: Ahora recibe el 'correo' como parámetro para poder buscarlo
    public boolean existeCorreo(String correo) {
        return usuarios.containsKey(correo);
    }

    // 3. Corregido: Se cambió el nombre de la variable local a 'usuarioExistente'
    //    para evitar conflictos con el mapa 'usuarios'
    public boolean autenticar(String correo, String contrasenia) {
        Usuario usuarioExistente = usuarios.get(correo);

        // Validamos que el usuario exista y que la contraseña coincida
        return usuarioExistente != null && usuarioExistente.getContrasenia().equals(contrasenia);
    }
}