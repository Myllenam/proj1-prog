package view.components;

import utils.Principal;
import view.TelaInicial;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCarrinho extends JPanel {
    private JPanel infoPanel = new JPanel();
    private JPanel actionsPanel = new JPanel();
    private JLabel qtdeLabel = new JLabel();
    private JLabel precoLabel = new JLabel();
    private JLabel precoTotalLabel = new JLabel();

    private Integer id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public ItemCarrinho(Integer id, String nome, Integer qtde, Double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = qtde;
        this.preco = preco;

        Dimension cardSize = new Dimension(500, 100);
        Border cardBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.setMaximumSize(cardSize);
        super.setPreferredSize(cardSize);
        super.setBorder(cardBorder);


        setupInfoPanel();
        setupActionsPanel();

        super.add(infoPanel);
        super.add(Box.createHorizontalGlue());

        super.add(actionsPanel);
    }

    private void setupInfoPanel() {
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        Font nomeFont = new Font("Arial", Font.BOLD, 24);
        JLabel nomeLabel = new JLabel();
        nomeLabel.setText(nome);
        nomeLabel.setFont(nomeFont);

        Font precoFont = new Font("Arial", Font.PLAIN, 16);
        precoLabel.setText("Preço: R$" + preco);
        precoLabel.setFont(precoFont);


        Font precoTotalFont = new Font("Arial", Font.PLAIN, 16);
        precoTotalLabel.setText("Total: R$" + preco);
        precoTotalLabel.setFont(precoTotalFont);

        Font qtdeFont = new Font("Arial", Font.PLAIN, 16);
        qtdeLabel.setText("Quantidade: " + quantidade);
        qtdeLabel.setFont(qtdeFont);

        infoPanel.add(nomeLabel);
        infoPanel.add(precoLabel);
        infoPanel.add(precoTotalLabel);
        infoPanel.add(qtdeLabel);
    }

    private void setupActionsPanel() {
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.X_AXIS));

        Font btnsFont = new Font("Arial", Font.BOLD, 24);

        JButton removeButton = new JButton();
        removeButton.setText("-");
        removeButton.setFont(btnsFont);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Não faz nada caso só reste um item no carrinho
                if (quantidade == 1) {
                    return;
                }
                quantidade--;
                qtdeLabel.setText("Quantidade: " + quantidade);
                double precoTotal = preco * quantidade;
                precoTotalLabel.setText("Total: R$" + String.format("%.2f", precoTotal));
                TelaInicial.atualizaValorTotal();
            }
        });

        JButton adicionarButton = new JButton();
        adicionarButton.setText("+");
        adicionarButton.setFont(btnsFont);
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantidade++;
                double precoTotal = preco * quantidade;
                TelaInicial.atualizaValorTotal();
                precoTotalLabel.setText("Total: R$" + String.format("%.2f", precoTotal));                qtdeLabel.setText("Quantidade: " + quantidade);
            }
        });

        actionsPanel.add(removeButton);
        actionsPanel.add(adicionarButton);
    }

    public Integer getId() {
        return id;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JPanel getActionsPanel() {
        return actionsPanel;
    }

    public JLabel getQtdeLabel() {
        return qtdeLabel;
    }

    public JLabel getPrecoLabel() {
        return precoLabel;
    }

    public JLabel getPrecoTotalLabel() {
        return precoTotalLabel;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return quantidade * preco;
    }
}


