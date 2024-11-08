package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Venda implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venda;
    private Date data;
    private double valor_total;
    private Integer id_cliente;

    public Venda() {
    }

    public Venda(Integer id_venda, Date data, double valor_total, Integer id_cliente) {
        super();
        this.id_venda = id_venda;
        this.data = data;
        this.valor_total = valor_total;
        this.id_cliente = id_cliente;
    }


    public double getValor_total() {
        return valor_total;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }
}
