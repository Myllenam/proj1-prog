/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import utils.Router;
import view.*;

import java.awt.Color;
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

        Router router = new Router();
        JPanel telaLogin = new TelaLogin(router).telaLogin;
        JPanel telaInicial = new TelaInicial(router).telaInicial;
        JPanel telaCadastroCliente = new TelaCadastroCliente(router).telaCadastroCliente;
        JPanel telaPagamento = new TelaPagamento(router).telaPagamento;
        JPanel telaNotaFiscal = new TelaNotaFiscal(router).telaNotaFiscal;
        router.addComponent(telaLogin, "telaLogin");
        router.addComponent(telaInicial, "telaInicial");
        router.addComponent(telaCadastroCliente, "telaCadastroCliente");
        router.addComponent(telaPagamento, "telaPagamento");
        router.addComponent(telaNotaFiscal, "telaNotaFiscal");


        router.setVisible(true);


    }

}
