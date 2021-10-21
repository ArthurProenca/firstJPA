package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria { //Categoria é a entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
