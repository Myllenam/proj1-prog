package controller;

import dao.RepoPagamento;
import model.Pagamento;


import java.util.List;

public class ControllerPagamento extends Controller {

    public List<Pagamento> getPagamentos() {
        return new RepoPagamento().getAll();
    }
}
