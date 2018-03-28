package br.com.douglasfernandes.UserServices.Testes.ObterUsuario;

import br.com.douglasfernandes.UserServices.UserServicesApplication;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = UserServicesApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles({"dev"})
public class ObterUsuarioPassos {

    private Usuario usuario;

    @Dado("^pesquisa do usuario com cpf (\\d+)$")
    public void testePesquisaPorCPF(long cpf) throws Throwable {
        usuario = Usuario.builder()
                .nome("Douglas Fernandes da Silva Filho")
                .cpf(cpf).build();
    }

    @Entao("^retorna \"(.*?)\"$")
    public void validaNome(String nome) {
        assertEquals(usuario.getNome(), nome);
    }

}
