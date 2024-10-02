package view;

import utils.Principal;
import utils.Router;
import view.components.ItemCarrinho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TelaNotaFiscal extends TelaBase {
    public JPanel telaNotaFiscal;
    private JPanel clientePanel;
    private JPanel itemsPanel;
    private JPanel infoPanel;
    private JLabel nomeCliente;
    private JLabel cpfCliente;
    private JLabel emailCliente;
    private JLabel valorTotal;
    private JButton voltarButtont;

    public TelaNotaFiscal(Router router) {
        super(router);

        voltarButtont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaInicial");
            }
        });

        telaNotaFiscal.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {
                if (Principal.getCliente() != null) {
                    clientePanel.setVisible(true);
                    nomeCliente.setText(Principal.getCliente().getNome());
                    emailCliente.setText(Principal.getCliente().getEmail());
                    cpfCliente.setText(Principal.getCliente().getCpf());
                } else {
                    clientePanel.setVisible(false);
                }
                valorTotal.setText(String.format("%.2f", Principal.getVenda().getValor_total()));
                loadItemsCompra();
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    private void loadItemsCompra() {
        itemsPanel.removeAll();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        for (ItemCarrinho i : Principal.getCarrinho()) {
            String nomeProduto = i.getNome();
            int qtdeProduto = i.getQuantidade();
            double valorTotalProduto = i.getValorTotal();
            double precoProduto = i.getPreco();

            JPanel itemNota = new JPanel();
            itemNota.setLayout(new BoxLayout(itemNota, BoxLayout.Y_AXIS));


            Font nomeFont = new Font("Arial", Font.BOLD, 14);
            JLabel productLabel = new JLabel();
            productLabel.setFont(nomeFont);
            productLabel.setText(nomeProduto + " - Preço Total: R$" + String.format("%.2f", valorTotalProduto));

            Font infoFont = new Font("Arial", Font.PLAIN, 12);
            JLabel productInfoLabel = new JLabel();
            productInfoLabel.setFont(infoFont);
            productInfoLabel.setText("Quantidade: " + qtdeProduto + " - Preço Unitário: R$" + String.format("%.2f", precoProduto));

            itemNota.add(productLabel);
            itemNota.add(productInfoLabel);

            itemsPanel.add(itemNota);
            itemsPanel.add(Box.createVerticalStrut(10));
            itemsPanel.revalidate();
            itemsPanel.repaint();
        }
    }
}
