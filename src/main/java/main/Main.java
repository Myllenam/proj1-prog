/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import dao.RepoCliente;
import model.Cliente;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
     public static void main(String[] args){
        try{
        UIManager.put("Button.background", new Color(245, 198, 198));
         UIManager.put("Button.hoverBackground", new Color(255, 182, 193)); 
            UIManager.put("Button.focusedBackground", new Color(240, 128, 128)); 
    
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch(UnsupportedLookAndFeelException e){
        }
        Cliente c1 = new Cliente(null, "Leticia", "leticia@gmail.com", "98765432178", 100);
        new RepoCliente().save(c1);
        
//        new view.PaginaInicial().setVisible(true);
         new view.CadastroCliente().setVisible(true);

    }
    
}
