package controller;

import dao.RepoCliente;
import dao.RepoVenda;
import model.Cliente;
import model.Venda;

import java.util.Date;

public class ControllerVenda extends Controller {

    public boolean cadastrarVenda(double valor_total, Date data, Integer id_cliente) {
        if (valor_total > 0) {
            Venda v = new Venda(null, data, valor_total, id_cliente);
            new RepoVenda().save(v);
            if (id_cliente != null) {
                int pontosAdicionados = (int) (valor_total / 100) * 100;

                Cliente cliente = new RepoCliente().getCpf(id_cliente.toString());
                if (cliente != null) {
                    cliente.setPontos(cliente.getPontos() + pontosAdicionados);
                    new RepoCliente().update(cliente);
                }
            }

            return true;
        }
        return false;
         }
}
