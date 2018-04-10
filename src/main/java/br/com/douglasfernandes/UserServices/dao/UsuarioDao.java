package br.com.douglasfernandes.UserServices.dao;

import br.com.douglasfernandes.UserServices.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

    Usuario findByNome(String nome);

}
