package view;

import controller.ControllerCliente;
import controller.ControllerPagamento;
import controller.ControllerProduto;
import model.Produto;
import utils.Principal;
import utils.Router;
import view.components.ItemCarrinho;
import view.components.ItemProduto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
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
    private JButton finalizarCompraButton;
    private JPanel cartPanel;
    private JPanel totalPanel;
    private JButton sairButton;
    private JScrollPane scrollProductPanel;
    private JButton limparCarrinhoButton;
    private static JLabel valorTotal;

    public TelaInicial(Router router) {
        super(router);
        controllerProduto = new ControllerProduto();
        controllerCliente = new ControllerCliente();
        controllerPagamento = new ControllerPagamento();


        loadFeed();
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
        limparCarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal.setValorTotal(0.0);
                Principal.limpaCarrinho();
                cartPanel.removeAll();
                cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
                valorTotal.setText("Valor Total: R$"+String.format("%.2f", 0.0)+"");
                cartPanel.add(valorTotal);
                cartPanel.revalidate();
                cartPanel.repaint();

            }
        });
        finalizarCompraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Principal.getValorTotal() <= 0){
                    showMessage("Nenhum item foi adicionado no carrinho!");
                } else {
                    router.route("telaPagamento");
                }
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
                Principal.setVenda(null);
                Principal.setCliente(null);
                Principal.setValorTotal(0.0);
                Principal.limpaCarrinho();
                loadFeed();
                cartPanel.removeAll();
                cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
                valorTotal.setText("Valor Total: R$"+String.format("%.2f", 0.0)+"");
                cartPanel.add(valorTotal);
                cartPanel.revalidate();
                cartPanel.repaint();
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
        }
        productPanel.revalidate();
        productPanel.repaint();

        scrollProductPanel.revalidate();
        scrollProductPanel.repaint();
    }


    private ItemProduto createFeedCard(Produto p) {
        Border topBorder = new EmptyBorder(5, 25, 5, 0);
        Font nomeFont = new Font("Arial", Font.BOLD, 24);
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
        for (ItemCarrinho i : Principal.getCarrinho()) {
            if (i.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static void atualizaValorTotal() {
        double total = 0;

        for (ItemCarrinho i : Principal.getCarrinho()) {
            total += i.getValorTotal();
        }
        Principal.setValorTotal(total);
        valorTotal.setText("Valor Total: R$"+String.format("%.2f", total)+"");

    }



    private void addToCart(Produto p) {
        if (!isInCart(p.getId_produto())) {
            ItemCarrinho item = new ItemCarrinho(p.getId_produto(), p.getNome(), 1, p.getPreco());
            Principal.addItemCarrinho(item);
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
