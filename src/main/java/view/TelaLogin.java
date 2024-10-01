package view;

import utils.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends TelaBase{
    public JPanel telaLogin;
    private JButton entrarButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel ImageLogo;
    private JButton telaPagamentoButton;

    public TelaLogin(Router router) {
        super(router);
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaInicial");
            }
        });
        telaPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaPagamento");
            }
        });
}

    private void createUIComponents() {
        ImageLogo= new JLabel(new ImageIcon("Borcelle.png"));
    }
}

