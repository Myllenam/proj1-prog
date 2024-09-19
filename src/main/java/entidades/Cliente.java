
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    private String nome;
    private String email;
    private String cpf;
    private Integer pontos;

    public Cliente() {
    }

    public Cliente(Integer id_cliente, String nome, String email, String cpf,Integer pontos) {
        super();
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.email = email;
        this.cpf=cpf;
        this.pontos=pontos;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
    
    @Override
    public String toString() {
        return id_cliente+" "+nome+" "+email+" "+cpf+" "+pontos;
    }
    
}
