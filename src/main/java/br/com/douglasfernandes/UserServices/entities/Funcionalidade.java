package br.com.douglasfernandes.UserServices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name="features")
public class Funcionalidade implements Entity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="funcionalidade")
    private String funcionalidade;
}
