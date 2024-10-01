/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controller.ControllerCliente;
import controller.ControllerProduto;
import model.Cliente;
import model.Produto;
import utils.Router;
import view.TelaCadastroCliente;
import view.TelaInicial;
import view.TelaLogin;
import view.TelaPagamento;

import java.awt.Color;
import java.util.List;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.put("Button.background", new Color(245, 198, 198));
            UIManager.put("Button.hoverBackground", new Color(255, 182, 193));
            UIManager.put("Button.focusedBackground", new Color(240, 128, 128));

            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
        }
//        List<Produto> produtos=new ControllerProduto().getProdutos();
//        Cliente cliente=new ControllerCliente().encontrarCliente("98765434567");
//        Integer id=new ControllerCliente().encontrarIdCliente("98765434567");
//        System.out.println(id);

        Router router = new Router();

        JPanel telaLogin = new TelaLogin(router).telaLogin;
        JPanel telaInicial = new TelaInicial(router).telaInicial;
        JPanel telaCadastroCliente = new TelaCadastroCliente(router).telaCadastroCliente;
        JPanel telaPagamento = new TelaPagamento(router).telaPagamento;

        router.addComponent(telaLogin, "telaLogin");
        router.addComponent(telaInicial, "telaInicial");
        router.addComponent(telaCadastroCliente, "telaCadastroCliente");
        router.addComponent(telaPagamento, "telaPagamento");

        router.setVisible(true);


    }

}
