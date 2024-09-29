package controller;

import dao.RepoPagamento;
import dao.RepoProduto;
import model.Pagamento;
import model.Produto;

import java.util.List;

public class ControllerPagamento extends Controller {

    public boolean cadastrarPagamento( Integer id_venda, String tipo_pagamento) {
        if (tipo_pagamento.equals("pix") || tipo_pagamento.equals("cartao_credito")||tipo_pagamento.equals("cartao_debito")||tipo_pagamento.equals("dinheiro")) {
            Pagamento p = new Pagamento(null, tipo_pagamento, id_venda);
            new RepoPagamento().save(p);


            return true;
        }
        return false;
    }
    public List<Pagamento> getPagamentos() {
        return new RepoPagamento().getAll();
    }
}
