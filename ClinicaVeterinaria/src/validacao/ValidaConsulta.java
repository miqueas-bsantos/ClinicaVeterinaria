/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;

import javax.swing.JOptionPane;


/**
 *
 * @author Miqueas Santos
 */
public class ValidaConsulta {

  
    public boolean validaCampos(String campo){
        return !(campo.equals(""));
    }

    public void msgmNome(){
        JOptionPane.showMessageDialog(null, "Digite o nome");
    }
    
     public void msgmCodigo(){
        JOptionPane.showMessageDialog(null, "Digite o codigo");
    }
    
    


}
