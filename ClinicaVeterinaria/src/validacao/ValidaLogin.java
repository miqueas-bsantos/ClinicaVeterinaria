package validacao;

import classes.Usuario;
import javax.swing.JOptionPane;

public class ValidaLogin {
    
    public boolean validaCampos(Usuario user){
        //se usuário não digitou o login ou a senha, retorna true e inverte a saida, porque quem vai receber a função é um 'if'
        return !(user.getUsurario().equals("") || user.getSenha().equals("[]"));
    }
    
    //verifica qual o campo está vazio e exibi a mesagem para o usuário
    public void validaCampoMgsm(Usuario user){
        if (user.getUsurario().equals("") && user.getSenha().equals("[]")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos!");
        }else if(user.getUsurario().equals("")){
            JOptionPane.showMessageDialog(null, "Informe o usuário");
        }else if(user.getSenha().equals("[]")){
            JOptionPane.showMessageDialog(null, "Informe a senha");
        }
    }
    
    //apenas exibi a mensagem
    public void usuarioNaoEncont(){
        JOptionPane.showMessageDialog(null, "Usuário não encontrado ");
    }
    
  
   

}
