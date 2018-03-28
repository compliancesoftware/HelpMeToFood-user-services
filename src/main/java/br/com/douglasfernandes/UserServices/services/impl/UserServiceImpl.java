package br.com.douglasfernandes.UserServices.services.impl;

import br.com.douglasfernandes.UserServices.dao.UsuarioDao;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import br.com.douglasfernandes.UserServices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Usuario salvarUsuario(Usuario usuario) throws RuntimeException {
        Usuario salvo = null;

        try {
            salvo = usuarioDao.saveAndFlush(usuario);
            if(salvo == null) {
                throw new RuntimeException("Erro ao tentar salvar usuario.");
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return salvo;
    }

    @Override
    public List<Usuario> listarUsuarios() throws RuntimeException {
        List<Usuario> lista =  null;

        try {
            lista = usuarioDao.findAll();
        } catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return lista;
    }

    @Override
    public Usuario obterUsuarioporCpf(long cpf) throws RuntimeException {
        Usuario encontrado = null;

        try {
            encontrado = usuarioDao.findOne(new Long(cpf));
        } catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return encontrado;
    }

}
