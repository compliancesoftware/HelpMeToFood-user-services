package br.com.douglasfernandes.UserServices.services.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.douglasfernandes.UserServices.dao.UsuarioDao;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import br.com.douglasfernandes.UserServices.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Usuario salvarUsuario(Usuario usuario) throws ServiceException {
        try {
            usuario.setSenha(DigestUtils.sha1Hex(usuario.getSenha()));
            usuario.setAtivo(true);
            Calendar now = Calendar.getInstance();
            usuario.setUltimoAcesso(now);
            usuario.setDataExpiracaoToken(now);

            Usuario salvo = usuarioDao.saveAndFlush(usuario);

            if (salvo == null) {
                throw new ServiceException("Erro ao tentar salvar usuário.");
            }

            return salvo;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws ServiceException {
        try {
            List<Usuario> lista = usuarioDao.findAll();

            if (lista == null) {
                throw new ServiceException("Nenhum usuário encontrado.");
            }

            return lista;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public Usuario findByNome(String nome) throws ServiceException {
        try {
            Usuario found = usuarioDao.findByNome(nome);

            if (found == null) {
                throw new ServiceException("Usuário não encontrado.");
            }

            return found;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
