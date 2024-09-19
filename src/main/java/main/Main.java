/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author mylle
 */
public class Main {
     public static void main(String[] args){
        try{
        UIManager.put("Button.background", new Color(0, 150, 136));
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch(Exception e){
            e.printStackTrace();
        }
        
        new View.PaginaInicial().setVisible(true);

    }
    
}
