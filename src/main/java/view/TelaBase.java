package view;

import controller.Controller;
import utils.Router;

import javax.swing.*;

abstract public class TelaBase {
    Router router;

    Controller controller;

    public TelaBase(Router router) {
        this.router = router;
    }

    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


}
