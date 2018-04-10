package br.com.douglasfernandes.UserServices.services;

import br.com.douglasfernandes.UserServices.entities.Usuario;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface UserService {
    public Usuario salvarUsuario(Usuario usuario) throws ServiceException;

    public List<Usuario> listarUsuarios() throws ServiceException;

    public Usuario findByNome(String nome) throws ServiceException;
}
