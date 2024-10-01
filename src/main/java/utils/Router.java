package utils;

import javax.swing.*;
import java.awt.*;

public class Router extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private Container contentPane = getContentPane();

    public Router() {
        setTitle("Padaria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        super.setSize(1280, 720);
        super.setResizable(false);


        cardPanel = new JPanel();
        cardPanel.setPreferredSize(new java.awt.Dimension(1280, 720)); // Garante que o painel tenha o mesmo tamanho da janela

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);


        contentPane.setLayout(new BorderLayout());
        contentPane.add(cardPanel, BorderLayout.CENTER);

        super.pack();  // Se quiser ajustar com base nos componentes, mas chame antes de setSize
        super.setSize(1280, 720);
    }

    public void route(String destiny) {
        cardLayout.show(cardPanel, destiny);
    }

    public void addComponent(JPanel panel, String name) {
        cardPanel.add(panel, name);
    }


}
