package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produto;
    private String nome;
    private String descricao;
    private double preco;

    public Produto() {
    }

    public Produto(Integer id_produto, String nome,String descricao, double preco) {
        super();
        this.id_produto = id_produto    ;
        this.nome = nome;
        this.descricao= descricao;
       this.preco=preco;
    }


    public Integer getId_produto() {
        return id_produto;
    }


    public String getNome() {
        return nome;
    }



    public double getPreco() {
        return preco;
    }



    public String getDescricao() {
        return descricao;
    }

}
