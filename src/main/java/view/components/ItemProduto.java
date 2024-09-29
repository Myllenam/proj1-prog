package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemProduto extends JPanel {
    public ItemProduto() {
        Dimension cardSize = new Dimension(500, 150);
        Border cardBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setMaximumSize(cardSize);
        super.setPreferredSize(cardSize);
        super.setBorder(cardBorder);
    }

    public ItemProduto(int width, int height, int borderWidth) {
        Dimension cardSize = new Dimension(width, height);
        Border cardBorder = BorderFactory.createLineBorder(Color.BLACK, borderWidth);

        JPanel card = new JPanel();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setMaximumSize(cardSize);
        super.setPreferredSize(cardSize);
        super.setBorder(cardBorder);
    }
}
