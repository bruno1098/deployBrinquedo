package br.com.brinquedos.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.lang.reflect.Field;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brinquedo")
@SequenceGenerator(name = "sqBrinquedo", sequenceName = "sq_brinquedo", allocationSize = 1)
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "preco")
    private Double preco;




}
