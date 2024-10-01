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
    private JButton notaFiscalButton;

    public TelaLogin(Router router) {
        super(router);
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaInicial");
            }
        });
        notaFiscalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaNotaFiscal");
            }
        });

}

    private void createUIComponents() {
        ImageLogo= new JLabel(new ImageIcon("Borcelle.png"));
    }
}

