package cucumberTest.features;

import java.util.List;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.douglasfernandes.UserServices.UserServicesApplication;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import br.com.douglasfernandes.UserServices.rest.api.endpoints.ApiV1Endpoints;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import lombok.extern.log4j.Log4j;

@ContextConfiguration(classes = UserServicesApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Log4j
public class APIObterUsuarioStepdefs {

    private ResponseEntity<?> response;

    private int httpStatusCode;

    private void doGetForUserByName(final String name) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080" + ApiV1Endpoints.API_V1_USUARIOS_ROOT_ENDPOINT + "/" + name;

        try {
            response = restTemplate.getForEntity(url, Usuario.class);

            httpStatusCode = response.getStatusCode().value();
        } catch (HttpClientErrorException hcee) {
            httpStatusCode = hcee.getStatusCode().value();
        }
    }

    private void doGetUsers() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080" + ApiV1Endpoints.API_V1_USUARIOS_ROOT_ENDPOINT + "/";

        try {
            response = restTemplate.getForEntity(url, List.class);

            httpStatusCode = response.getStatusCode().value();
        } catch (HttpClientErrorException hcee) {
            httpStatusCode = hcee.getStatusCode().value();
        }
    }

    @Dado("^usuario com nome \"(.*?)\"$")
    public void usuarioComNome(final String nome) {
        doGetForUserByName(nome);
        Assert.assertNotNull(response.getBody());
    }

    @Entao("^verifica seu email \"(.*?)\"$")
    public void verificaSeuEmail(final String email) {
        Usuario usuario = (Usuario) response.getBody();
        Assert.assertEquals(usuario.getEmail(), email);
    }

    @Dado("^uma requisicao GET contra a API$")
    public void umaRequisicaoGETContraAAPI() {
        doGetUsers();
    }

    @Entao("^obter lista de usuarios$")
    public void obterListaDeUsuarios() {
        Assert.assertNotNull(response.getBody());
    }

    @E("^verificar se o usuario com id (\\d+) e nome \"([^\"]*)\" esta na lista$")
    public void verificarSeOUsuarioComIdENomeEstaNaLista(final Long id, String nome) {
        ObjectMapper mapper = new ObjectMapper();
        List<Usuario> lista = mapper.convertValue(response.getBody(), new TypeReference<List<Usuario>>() {
        });

        Usuario achado = lista.stream().filter((u -> u.getId().equals(id))).findFirst().orElse(null);

        Assert.assertNotNull(achado);
        Assert.assertEquals(nome, achado.getNome());
    }
}
