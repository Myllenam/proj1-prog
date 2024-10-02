package controller;

import dao.RepoProduto;
import model.Produto;
import java.util.List;

public class ControllerProduto extends Controller {
    public ControllerProduto() {
    }

    public List<Produto> getProdutos() {
            return new RepoProduto().getAll();
}

}

