package view;

import controller.ControllerCliente;
import controller.ControllerPagamento;
import controller.ControllerProduto;
import model.Cliente;
import model.Pagamento;
import model.Produto;
import utils.Router;
import view.components.ItemCarrinho;
import view.components.ItemProduto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import view.components.ProductLabel;

public class TelaInicial extends TelaBase {

    private ControllerProduto controllerProduto;
    private ControllerPagamento controllerPagamento;
    private ControllerCliente controllerCliente;

    public JPanel telaInicial;
    private JPanel menu;
    private JButton cadastrarClienteButton;
    private JPanel productPanel;
    private JButton cancelarButton;
    private JButton finalizarCompraButton;
    private JPanel cartPanel;
    private JPanel totalPanel;
    private JComboBox formasPagamentot;
    private JComboBox clientes;
    private JButton sairButton;
    private static JLabel valorTotal;

    private static ArrayList<ItemCarrinho> carrinho = new ArrayList<>();

    public TelaInicial(Router router) {
        super(router);
        controllerProduto = new ControllerProduto();
        controllerPagamento = new ControllerPagamento();
        controllerCliente = new ControllerCliente();


//        loadFeed();
        loadPagamentosComboBox();
//        loadClientesComboBox();
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaCadastroCliente");
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaLogin");
            }
        });

        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        telaInicial.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                loadFeed();
//                loadPagamentosComboBox();
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });



    }

    private void loadFeed() {
        productPanel.removeAll();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        List<Produto> produtos= controllerProduto.getProdutos();

        for (Produto p : produtos) {
            ItemProduto card = createFeedCard(p);
            productPanel.add(card);
            productPanel.add(Box.createVerticalStrut(10));
            productPanel.revalidate();
            productPanel.repaint();
        }
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
        formasPagamentot.setModel(model);
    }

    private void loadClientesComboBox() {
        HashMap<Integer, String>  pagamentoHashMap = new HashMap<>();
        List<Cliente> clientesCadastrados = controllerCliente.getClientes();

        for (Cliente c : clientesCadastrados) {
            pagamentoHashMap.put(c.getId_cliente(), c.getNome());
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String c : pagamentoHashMap.values()) {
            model.addElement(c);
        }
        clientes.setModel(model);
    }

    private ItemProduto createFeedCard(Produto p) {
        Border topBorder = new EmptyBorder(5, 25, 5, 0);
        Font nomeFont = new Font("Arial", Font.BOLD, 24);
        Border defaultBorder = new EmptyBorder(0, 10, 5, 0);
        Font defaultFont = new Font("Arial", Font.BOLD, 14);
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String precoFormatado = formatadorMoeda.format(p.getPreco());
        ProductLabel nomeLabel = new ProductLabel(p.getNome(), topBorder, nomeFont);
        ProductLabel descricaoLabel = new ProductLabel(p.getDescricao(), topBorder, nomeFont);
        ProductLabel precoLabel = new ProductLabel(precoFormatado, topBorder, nomeFont);


        JButton adicionarCarrinho = new JButton();
        adicionarCarrinho.setText("Adicionar");
        adicionarCarrinho.setName("produto_" + p.getId_produto());
        adicionarCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart(p);
            }
        });

        ItemProduto card = new ItemProduto();

        card.add(nomeLabel);
        card.add(descricaoLabel);
        card.add(precoLabel);
        card.add(adicionarCarrinho);
        return card;
    }

    private boolean isInCart(Integer id) {
        for (ItemCarrinho i : carrinho) {
            if (i.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static void atualizaValorTotal() {
        double total = 0;

        for (ItemCarrinho i : carrinho) {
            total += i.getValorTotal();
        }
        System.out.println("Valor Total " + total);
        valorTotal.setText("Valor Total: R$"+String.format("%.2f", total)+"");

    }

    private void addToCart(Produto p) {
        if (!isInCart(p.getId_produto())) {
            ItemCarrinho item = new ItemCarrinho(p.getId_produto(), p.getNome(), 1, p.getPreco());
            carrinho.add(item);
            cartPanel.add(item);
            cartPanel.add(Box.createVerticalStrut(10));
            cartPanel.revalidate();
            cartPanel.repaint();
        }
        atualizaValorTotal();
    }


    /**
     * Altera o layout do painel de carrinho para os itens ficarem um abaixo do outro
     */
    private void createUIComponents() {
        Font btnsFont = new Font("Arial", Font.BOLD, 24);

        valorTotal = new JLabel();
        valorTotal.setText("Valor Total: R$00.00");
        valorTotal.setFont(btnsFont);
        valorTotal.setBorder(new EmptyBorder(10, 10, 10, 10));
        valorTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.add(valorTotal);
    }
}
