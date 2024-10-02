package utils;

import model.Cliente;
import model.Venda;
import view.components.ItemCarrinho;

import java.util.ArrayList;

/**
 * Classe para manter os dados entre as "Sessões", serve como uma especie de cookie
 * para conseguir criar interações entre telas diferentes
 */
public class Principal {

    private static Cliente cliente;

    private static Venda venda;

    private static Double valorTotal = 0.0;

    private static ArrayList<ItemCarrinho> carrinho = new ArrayList<>();

    public static Double getValorTotal() {
        return valorTotal;
    }

    public static void setValorTotal(Double valorTotal) {
        Principal.valorTotal = valorTotal;
    }

    public static ArrayList<ItemCarrinho> getCarrinho() {
        return carrinho;
    }

    public static void addItemCarrinho(ItemCarrinho item) {
        Principal.carrinho.add(item);
    }

    public static void removeItemCarrinho(ItemCarrinho item) {
        Principal.carrinho.remove(item);
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        Principal.cliente = cliente;
    }

    public static Venda getVenda() {
        return venda;
    }

    public static void setVenda(Venda venda) {
        Principal.venda = venda;
    }
    public static void limpaCarrinho(){
        carrinho.clear();
    }
}
