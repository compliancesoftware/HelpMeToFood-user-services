package br.com.douglasfernandes.UserServices.Testes.ObterUsuario;

import br.com.douglasfernandes.UserServices.UserServicesApplication;
import br.com.douglasfernandes.UserServices.dao.UsuarioDao;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = UserServicesApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Log4j
public class ObterUsuarioPassos {

    private Usuario usuario;

    @Autowired
    private UsuarioDao usuarioDao;

    @Dado("^usuario com nome \"(.*?)\"$")
    public void testePesquisaPorNome(String nome) throws Throwable {
        log.info("M=testePesquisaPorNome, I=Nome para pesquisa: " + nome);
        usuario = usuarioDao.findByNome(nome);
    }

    @Entao("^verifica seu email \"(.*?)\"$")
    public void validaNome(String email) {
        assertEquals(usuario.getEmail(), email);
    }

}
