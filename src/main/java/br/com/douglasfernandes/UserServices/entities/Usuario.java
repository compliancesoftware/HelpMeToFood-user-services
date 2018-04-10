package br.com.douglasfernandes.UserServices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name="users")
public class Usuario implements Entity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;

    @Column(name="senha")
    private String senha;

    @Column(name="telefone")
    private String telefone;

    @Column(name = "ultimo_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar ultimoAcesso;

    @Column(name="token")
    private String token;

    @Column(name = "data_expiracao_token")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataExpiracaoToken;

    @Column(name="ativo")
    private boolean ativo;

}
