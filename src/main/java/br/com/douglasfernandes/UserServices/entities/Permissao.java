package br.com.douglasfernandes.UserServices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name="users")
public class Permissao implements Entity{

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @JoinColumn(name = "users")
    private Usuario usuario;

    @JoinColumn(name = "features")
    private Funcionalidade funcionalidade;

    @Column(name = "leitura")
    private boolean leitura;

    @Column(name = "escrita")
    private boolean escrita;

    @Column(name = "atualizacao")
    private boolean atualizacao;

}
