package controller;

import dao.RepoCliente;
import dao.RepoVenda;
import model.Cliente;
import model.Venda;
import utils.Principal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ControllerVenda extends Controller {

    /**
     * Processamento para venda sem cliente informado
     * @param valor
     * @return
     */
    public boolean cadastrarVenda(double valor) {
        if (valor > 0) {
            Venda v = new Venda();
            v.setValor_total(valor);
            v.setData(getDataAtual());
            new RepoVenda().save(v);
            Principal.setVenda(v);
            return true;
        }
        return false;
    }

    /**
     * Processamento para cliente informado, atualiza os pontos do cliente
     * @param valor
     * @param idCliente
     * @return
     */
    public boolean cadastrarVenda(double valor, int idCliente) {
        if (valor > 0) {
            Venda v = new Venda();
            v.setValor_total(valor);
            v.setData(getDataAtual());
            v.setId_cliente(idCliente);
            new RepoVenda().save(v);
            Principal.setVenda(v);

            atualizaPontosCliente(idCliente, valor);

            return true;
        }
        return false;
    }

    /**
     * Ao realizar uma compra atualiza a quantidade de pontos daquele cliente de acordo com o valor da compra realizada
     * @param idCliente
     * @param valorCompra
     */
    private void atualizaPontosCliente(int idCliente, double valorCompra) {
        int pontosAdicionados = (int) (valorCompra / 100) * 100;

        Cliente cliente = new RepoCliente().getById(idCliente);
        if (cliente != null) {
            Principal.setCliente(cliente);
            cliente.setPontos(cliente.getPontos() + pontosAdicionados);
            new RepoCliente().update(cliente);
        }
    }

    private Date getDataAtual() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atStartOfDay(); // Começo do dia (meia-noite)
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }


}
