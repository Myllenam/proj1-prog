package view;

import controller.ControllerCliente;
import controller.ControllerPagamento;
import controller.ControllerVenda;
import model.Cliente;
import model.Pagamento;
import utils.Principal;
import utils.Router;
import view.components.ClienteComboItem;

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
    private ControllerVenda controllerVenda;

    public JPanel telaPagamento;
    private JButton voltarButton;
    private JComboBox clientesSelect;
    private JComboBox pagamentosSelect;
    private JButton finalizarPagamentoButton;
    private JPanel Container;
    private JCheckBox informarClienteCheckbox;
    private JCheckBox gerarNotaFiscalCheckBox;
    private JLabel valorTotal;
    private JLabel clienteLabel;

    public TelaPagamento(Router router) {
        super(router);
        controllerCliente = new ControllerCliente();
        controllerPagamento = new ControllerPagamento();
        controllerVenda = new ControllerVenda();


        finalizarPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processaVenda();
            }
        });

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
                informarClienteCheckbox.setSelected(false);
                gerarNotaFiscalCheckBox.setSelected(false);
                clientesSelect.setVisible(false);
                clienteLabel.setVisible(false);
                loadClientesComboBox();
                loadPagamentosComboBox();
                valorTotal.setText(String.format("%.2f", Principal.getValorTotal()));
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        informarClienteCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (informarClienteCheckbox.isSelected()) {
                    clientesSelect.setVisible(true);
                    clienteLabel.setVisible(true);
                } else {
                    clientesSelect.setVisible(false);
                    clienteLabel.setVisible(false);
                }
            }
        });
    }

    private void processaVenda() {
        showMessage("Compra finalizada com sucesso");
        boolean informaCliente = informarClienteCheckbox.isSelected();
        boolean gerarNota = gerarNotaFiscalCheckBox.isSelected();

        Object cliente = clientesSelect.getSelectedIndex();
        System.out.println(cliente);
        int idCliente = obterIdClienteSelecionado();
        if (informaCliente) {
           processaDadosCliente(idCliente);
        } else {
            controllerVenda.cadastrarVenda(Principal.getValorTotal());
        }

        if (gerarNota) {
            router.route("telaNotaFiscal");
        } else {
            router.route("telaInicial");
        }
    }

    /**
     * Processamento para avaliar se o cliente tem direito a sua recompensa com base na sua pontuação
     * @param idCliente
     */
    private void processaDadosCliente(int idCliente) {
        controllerVenda.cadastrarVenda(Principal.getValorTotal(), idCliente);
        boolean recompensaCliente = controllerCliente.verificaRecompensa(idCliente);
        if (recompensaCliente) {
            showMessage("Parabéns, você acumulou pontos de fidelidade suficientes para ganhar uma chaleira! :D \n Agora seus pontos serão zerados para começar a contar novamente.");
        }
    }

    private int obterIdClienteSelecionado() {
        ClienteComboItem selectedItem = (ClienteComboItem) clientesSelect.getSelectedItem();
        if (selectedItem != null) {
            int idCliente = selectedItem.getId();
            return idCliente;
        } else {
            return -1;
        }
    }

    private void loadClientesComboBox() {
        List<Cliente> clientesCadastrados = controllerCliente.getClientes();
        DefaultComboBoxModel<ClienteComboItem> model = new DefaultComboBoxModel<>();

        for (Cliente c : clientesCadastrados) {
            ClienteComboItem item = new ClienteComboItem(c.getId_cliente(), c.getNome());
            model.addElement(item);
        }

        clientesSelect.setModel(model);
    }

    private void loadPagamentosComboBox() {
        HashMap<Integer, String> pagamentoHashMap = new HashMap<>();
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
