
package aplicacao;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import dao.RepoCliente;
import entidades.Cliente;
import java.awt.Color;
import javax.swing.UIManager;


public class Main {
     public static void main(String[] args){
//         Cliente c1=new Cliente(null, "Myllena", "myllena@gmail", "09445676523", 100);
//         RepoCliente repo = new RepoCliente();
//         repo.save(c1);
         System.err.println("Hello World");
         
          try{
        UIManager.put("Button.background", new Color(0, 150, 136));
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch(Exception e){
            e.printStackTrace();
        }
     
     }
}
