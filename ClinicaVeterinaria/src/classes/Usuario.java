/**
 * TADS 2º SEMESTRE PROJETO CLINICA VETERINARIA CONTROLE DE ESTOQUE
 * 
 * DATA: 19/11/2015
 * 
 * GRUPO: MIQUEAS, VICTOR E GUSTAVO.
 * 
 * PROFª: ALESSANDRA.
 * 
 * REPOSITORIO:
 * 
 */
package classes;
/**
 * Classe logar o usuário no sitema
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 primeira aplicação
 *
 */
import java.util.ArrayList;

public class Usuario {
    
    private int codigo;
    private String usurario;
    private String senha;
    private boolean aviso;

    public boolean isAviso() {
        return aviso;
    }

    public void setAviso(boolean aviso) {
        this.aviso = aviso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getUsurario() {
        return usurario;
    }
    
    public void setUsurario(String usurario) {
        this.usurario = usurario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
 
    /**
     * consulta usuário no banco se existir algum dado é verificado no arraylist
     * da consulta caso não for encontrado retorna 0 e usuario não consegue logar
     * @param user
     * @return 
     *  int
     */
    public int codigoFunc(Usuario user){
        ArrayList<Usuario> listaDeUsuario = new UsuarioDAO().consultarUsuarios();//instancia usuarioDAO responsavel por consultar o banco e retorna a lista de usuários
        if (listaDeUsuario.size() > 0) {
            for (Usuario usuarioAux : listaDeUsuario) {
                //aqui compara login e senha, com o parametro de entrada "logarUsuario(usuario user)"
                if (usuarioAux.getUsurario().equals(user.getUsurario()) && ("["+usuarioAux.getSenha()+"]").equals(user.getSenha())) {
                    return usuarioAux.getCodigo();
                }
            }
        }
        return 0;
    }
    
    
}
