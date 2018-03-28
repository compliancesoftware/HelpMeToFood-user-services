package br.com.douglasfernandes.UserServices.dao;

import br.com.douglasfernandes.UserServices.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

    List<Usuario> findByNome(String nome);

}
