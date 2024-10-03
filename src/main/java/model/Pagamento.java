package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pagamento implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pagamento;
    private String tipo_pagamento;

    public Pagamento() {
    }

    public Pagamento(Integer id_pagamento, String tipo_pagamento) {
        super();
        this.id_pagamento = id_pagamento;
        this.tipo_pagamento = tipo_pagamento;
    }

    public Integer getId_pagamento() {
        return id_pagamento;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }


}
