package br.com.douglasfernandes.UserServices.Testes.ObterUsuario;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.douglasfernandes.UserServices.UserServicesApplication;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import br.com.douglasfernandes.UserServices.rest.api.endpoints.ApiV1Endpoints;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import lombok.extern.log4j.Log4j;

@ContextConfiguration(classes = UserServicesApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Log4j
public class ObterUsuarioStepdefs {

    Usuario usuario;

    int httpStatusCode;

    private void doPost(Object request) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/" + ApiV1Endpoints.API_V1_USUARIOS_ROOT_ENDPOINT + "/"
                + ApiV1Endpoints.API_V1_USUARIOS_BUSCA_POR_NOME_ENDPOINT;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Usuario> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                    Usuario.class);

            usuario = response.getBody();

            httpStatusCode = response.getStatusCode().value();
        } catch (HttpClientErrorException hcee) {
            httpStatusCode = hcee.getStatusCode().value();
        }
    }

    @Dado("^usuario com nome \"(.*?)\"$")
    public void usuarioComNome(String nome) {
        doPost(nome);
        Assert.assertNotNull(usuario);
    }

    @Entao("^verifica seu email \"(.*?)\"$")
    public void verificaSeuEmail(String email) {
        Assert.assertEquals(usuario.getEmail(), email);
    }

}
