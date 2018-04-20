package br.com.douglasfernandes.UserServices.VO;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.douglasfernandes.UserServices.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioVO {

    @NotNull(message = "O nome do usuário é necessário.")
    private String nome;

    @NotNull(message = "O e-mail do usuário é necessário.")
    private String email;

    @NotNull(message = "A senha do usuário é necessário.")
    private String senha;

    @NotNull(message = "O telefone do usuário é necessário.")
    private String telefone;

    public Usuario toUsuario() {
        return Usuario.builder()
                .nome(this.nome)
                .email(this.email)
                .senha(DigestUtils.sha1Hex(this.senha))
                .telefone(this.telefone)
                .ativo(true)
                .dataExpiracaoToken(Calendar.getInstance())
                .ultimoAcesso(Calendar.getInstance())
                .build();
    }
}
