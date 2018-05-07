package br.com.douglasfernandes.UserServices.rest.api.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.douglasfernandes.UserServices.Messaging.MessageSender;
import br.com.douglasfernandes.UserServices.VO.UsuarioVO;
import br.com.douglasfernandes.UserServices.entities.Usuario;
import br.com.douglasfernandes.UserServices.rest.api.endpoints.ApiV1Endpoints;
import br.com.douglasfernandes.UserServices.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = ApiV1Endpoints.API_V1_USUARIOS_ROOT_ENDPOINT, //
        description = "API para consulta e manutenção de usuarios do sistema.")
@RestController
@RequestMapping(ApiV1Endpoints.API_V1_USUARIOS_ROOT_ENDPOINT)
@Slf4j
public class UserRestApi {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSender messageSender;

    private void sendMessageToQueue(Usuario usuario) {
        try {
            messageSender.send("UserServicesExchange", "UserServices", usuario);
        } catch (JsonProcessingException jpe) {
            log.error(
                    "M=sendMessageToQueue, E=Erro ao tentar converter objeto em json. Verifique o stacktrace seguinte:");
            jpe.printStackTrace();
        }
    }

    @ApiOperation(value = "", //
            notes = "Obter todos os usuários do sistema")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Usuario> usuarios(HttpServletResponse response) throws IOException {
        try {
            List<Usuario> usuarios = userService.listarUsuarios();

            return usuarios;
        } catch (ServiceException ex) {
            log.error("M=usuarios, E=Erro ao tentar obter usuarios. Verifique o stacktrace seguinte:");
            ex.printStackTrace();

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return new ArrayList<>();
        }
    }

    @ApiOperation(value = ApiV1Endpoints.API_V1_USUARIOS_BUSCA_POR_NOME_ENDPOINT, //
            notes = "Obter dados de usuário por nome")
    @RequestMapping(value = ApiV1Endpoints.API_V1_USUARIOS_BUSCA_POR_NOME_ENDPOINT, method = RequestMethod.GET)
    public Usuario obterUsuarioPorNome(@PathVariable("nome") String nome, HttpServletResponse response)
            throws IOException {
        try {
            Usuario usuario = userService.findByNome(nome);

            return usuario;
        } catch (ServiceException ex) {
            log.error("M=obterUsuarioPorNome, E=Erro ao tentar encontrar usuario. Verifique o stacktrace seguinte:");
            ex.printStackTrace();

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = ApiV1Endpoints.API_V1_USUARIOS_SALVAR_ENDPOINT, //
            notes = "Criar novo usuário")
    @RequestMapping(value = ApiV1Endpoints.API_V1_USUARIOS_SALVAR_ENDPOINT, method = RequestMethod.POST)
    public Usuario salvarUsuario(@RequestBody @Valid UsuarioVO usuario, HttpServletResponse response)
            throws IOException {
        try {
            Usuario novoUsuario = usuario.toUsuario();
            novoUsuario = userService.salvarUsuario(novoUsuario);

            sendMessageToQueue(novoUsuario);

            return novoUsuario;
        } catch (ServiceException ex) {
            log.error("M=salvarUsuario, E=Erro ao tentar salvar usuario. Verifique o stacktrace seguinte:");
            ex.printStackTrace();

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return null;
        }
    }
}
