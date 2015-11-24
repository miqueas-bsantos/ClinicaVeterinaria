/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;
import javax.swing.JOptionPane;

/**
 *
 * @author miqueas.bsantos
 */
public class ValidaMovimentacao {
    
    
    public boolean validaMaterial(String status){
        return !(status.equals("nenhum"));
    }
    
    public void mostrarMsgmMaterial(){
        JOptionPane.showMessageDialog(null, "Selecione o status para Atualizar");
    }
    
    public boolean validarCampo(String quantidade, String cod) {
        return !(quantidade.equals("") || cod.equals("") || quantidade.equals("0"));
    }
    
    public void mostrarMsgm(String quantidade, String cod){  
        if (quantidade.equals("") & cod.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente Codigo e Quantidade");
        }else if (quantidade.equals("") || quantidade.equals("0")) {
            JOptionPane.showMessageDialog(null, "Informe a quantidade");
        }else {
            JOptionPane.showMessageDialog(null, "Informe a Codigo");
        }
    }
    
    public void mostraMsgmBaixa(){
            JOptionPane.showMessageDialog(null, "Produto em baixa");
    }
    
    public void mostraMsgmAlta(){
            JOptionPane.showMessageDialog(null, "Produto em Alta");
    }
    
    public void msgmAtulizarInvalida(){
            JOptionPane.showMessageDialog(null, "Não existe produto para Atualizar Status");
    }
    
}
