package view;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Router;
import view.components.ItemCarrinho;
import view.components.ItemProduto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import view.components.ProductLabel;

public class TelaInicial extends TelaBase {


    public JPanel telaInicial;
    private JButton inicioButton;
    private JPanel menu;
    private JButton cadastrarClienteButton;
    private JPanel productPanel;
    private JButton cancelarButton;
    private JButton finalizarCompraButton;
    private JPanel cartPanel;
    private JPanel totalPanel;
    private JComboBox formasPagamento;
    private static JLabel valorTotal;

    private static ArrayList<ItemCarrinho> carrinho = new ArrayList<>();

    public TelaInicial(Router router) {
        super(router);
        loadPagamentosComboBox();
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaCadastroCliente");
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
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        cartPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                atualizaValorTotal();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    /**
     * TODO: REMOVER, PARA TESTES APENAS
     *
     * @param fileName
     * @return
     */
    public static JSONArray getAllFromJson(String fileName) {
        try {
            String filePath = "data/" + fileName + ".json";

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;

            // Obter o array dentro do objeto JSON
            JSONArray array = (JSONArray) jsonObject.get(fileName);
            return array;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }


    private void loadFeed() {
        productPanel.removeAll();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        JSONArray competicoes = getAllFromJson("produtos");

        for (Object competicao : competicoes) {
            ItemProduto card = createFeedCard((JSONObject) competicao);
            productPanel.add(card);
            productPanel.add(Box.createVerticalStrut(10));
            productPanel.revalidate();
            productPanel.repaint();
        }
    }

    /**
     * Carrega o componente ComboBox com as modalidades cadastradas no sistema
     */
    private void loadPagamentosComboBox() {
        HashMap<Long, String>  pagamentoHashMap = new HashMap<>();
        JSONArray pagamento = getAllFromJson("pagamento");

        for (var i = 0; i < pagamento.size(); i++) {
            Long id = (Long) ((JSONObject) pagamento.get(i)).get("id_pagamento");
            String tipo = (String) ((JSONObject) pagamento.get(i)).get("tipo_pagamento");
            pagamentoHashMap.put(id, tipo);
        }

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String tipo : pagamentoHashMap.values()) {
            model.addElement(tipo);
        }
        formasPagamento.setModel(model);
    }

    private ItemProduto createFeedCard(JSONObject competicoes) {
        Border topBorder = new EmptyBorder(5, 25, 5, 0);
        Font nomeFont = new Font("Arial", Font.BOLD, 24);
        Border defaultBorder = new EmptyBorder(0, 10, 5, 0);
        Font defaultFont = new Font("Arial", Font.BOLD, 14);

        Long id = (Long) competicoes.get("id_produto");
        String nome = (String) competicoes.get("nome");
        String descricao = (String) competicoes.get("descricao");
        Double preco = (Double) competicoes.get("preco");

        ProductLabel nomeLabel = new ProductLabel(nome, topBorder, nomeFont);
        ProductLabel descricaoLabel = new ProductLabel(descricao, topBorder, nomeFont);
        ProductLabel precoLabel = new ProductLabel(preco.toString(), topBorder, nomeFont);


        JButton adicionarCarrinho = new JButton();
        adicionarCarrinho.setText("Adicionar");
        adicionarCarrinho.setName("produto_" + id);
        adicionarCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart(id, nome, preco, descricao);
            }
        });

        ItemProduto card = new ItemProduto();

        card.add(nomeLabel);
        card.add(descricaoLabel);
        card.add(precoLabel);
        card.add(adicionarCarrinho);
        return card;
    }

    private boolean isInCart(Long id) {
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

    private void addToCart(Long id, String nome, Double preco, String descricao) {
        Produto p = new Produto();
        p.setId_produto(id);
        p.setNome(nome);
        p.setPreco(preco);
        p.setDescricao(descricao);

        int quantidade = 1;

        if (!isInCart(id)) {
            ItemCarrinho item = new ItemCarrinho(id, nome, quantidade, preco);
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
