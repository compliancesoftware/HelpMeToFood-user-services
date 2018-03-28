package br.com.douglasfernandes.UserServices.services;

import br.com.douglasfernandes.UserServices.entities.Usuario;

import java.util.List;

public interface UserService {
    public Usuario salvarUsuario(Usuario usuario) throws RuntimeException;

    public List<Usuario> listarUsuarios() throws RuntimeException;

    public Usuario obterUsuarioporCpf(long cpf) throws RuntimeException;
}
