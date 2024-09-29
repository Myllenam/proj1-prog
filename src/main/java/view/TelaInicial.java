package view;

import utils.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends TelaBase {


    public JPanel telaInicial;
    private JButton inicioButton;
    private JPanel menu;
    private JButton cadastrarClienteButton;

    public TelaInicial(Router router) {
        super(router);

        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaCadastroCliente");
            }
        });
    }

}
