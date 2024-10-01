package view;

import controller.ControllerCliente;
import controller.ControllerPagamento;
import model.Cliente;
import model.Pagamento;
import utils.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.List;


public class TelaPagamento extends TelaBase {

    private ControllerCliente controllerCliente;
    private ControllerPagamento controllerPagamento;
    public JPanel telaPagamento;
    private JButton voltarButton;
    private JComboBox clientesSelect;
    private JComboBox pagamentosSelect;
    private JButton finalizarPagamentoButton;
    private JPanel Container;
    private JCheckBox inserirClienteNaVendaCheckBox;
    private JCheckBox gerarNotaFiscalCheckBox;

    public TelaPagamento(Router router) {
        super(router);
        controllerCliente = new ControllerCliente();
        controllerPagamento = new ControllerPagamento();



        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaInicial");
            }
        });

        telaPagamento.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {
                loadClientesComboBox();
                loadPagamentosComboBox();
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }
    private void loadClientesComboBox() {
        HashMap<Integer, String> pagamentoHashMap = new HashMap<>();
        List<Cliente> clientesCadastrados = controllerCliente.getClientes();

        for (Cliente c : clientesCadastrados) {
            pagamentoHashMap.put(c.getId_cliente(), c.getNome());
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String c : pagamentoHashMap.values()) {
            model.addElement(c);
        }
        clientesSelect.setModel(model);
    }

    private void loadPagamentosComboBox() {
        HashMap<Integer, String>  pagamentoHashMap = new HashMap<>();
        List<Pagamento> pagamentos = controllerPagamento.getPagamentos();

        for (Pagamento p : pagamentos) {
            pagamentoHashMap.put(p.getId_pagamento(), p.getTipo_pagamento());
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String tipo : pagamentoHashMap.values()) {
            model.addElement(tipo);
        }
        pagamentosSelect.setModel(model);
    }
}
