package view;

import controller.ControllerCliente;
import utils.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCliente extends TelaBase {
    public JPanel telaCadastroCliente;
    private JButton voltarButton;
    private JButton cadastrarButton;
    private JTextField nome;
    private JTextField cpf;
    private JTextField email;

    public TelaCadastroCliente(Router router) {
        super(router);
        ControllerCliente controller =  new ControllerCliente();

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaInicial");
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cadastrarCliente(nome.getText(), email.getText(), cpf.getText());
                router.route("telaInicial");
            }
        });
    }
}
