package view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ProductLabel extends JLabel {

    public ProductLabel(String text, Border border, Font font) {
        super.setText(text);
        super.setBorder(border);
        super.setFont(font);
    }

}
