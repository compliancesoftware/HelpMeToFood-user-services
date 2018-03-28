package br.com.douglasfernandes.UserServices.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@javax.persistence.Entity
@Table(name="usuario")
public class Usuario implements Entity {

    @Id
    @Column(name="cpf")
    private long cpf;

    @Column(name="nome")
    private String nome;

    @Column(name="rg")
    private long rg;

    @Column(name="email")
    private String email;

    @Column(name="telefone")
    private String telefone;

    @Column(name="endereco")
    private String endereco;

    @Column(name="numero")
    private String numero;

    @Column(name="bairro")
    private String bairro;

    @Column(name="cidade")
    private String cidade;

    @Column(name="estado")
    private String estado;

    @Column(name="cep")
    private long cep;

}
