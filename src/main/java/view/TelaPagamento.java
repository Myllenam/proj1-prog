package view;

import utils.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaPagamento extends TelaBase {

    public JPanel telaPagamento;
    private JButton voltarButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton button1;
    private JPanel Container;

    public TelaPagamento(Router router) {
        super(router);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                router.route("telaInicial");
            }
        });
    }
}
