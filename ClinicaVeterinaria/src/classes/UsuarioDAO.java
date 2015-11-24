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
 * Classe Responsável por consultar dados do usuário no banco de dados
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 primeira aplicação
 *
 */
import java.sql.ResultSet;//interface utilizada para receber dados do banco sql
import java.sql.SQLException;
import java.sql.Statement;//interface ultilizada para executar os camando do sql
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class UsuarioDAO {

    ConectaBanco conectar = new ConectaBanco();//instancia da classe conectaBanco
    private Statement stm;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    
    /**
     * busca os dados da tabela de funcionário com campos "nome e senha"
     * e retorna todas informações do usuário caso ele exista.
     * @return 
     * arraylist de Usuarios
     */
    
    public ArrayList consultarUsuarios() {
        conectar.conexao();
        try {
            ResultSet rs;
            stm = conectar.conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM FUNCIONARIO");//executando a requisaão no banco passando o comando como parametro
            while (rs.next()) {
                Usuario usuario = new Usuario();//instanciando a classe usuario para passar os dados da tabela
                usuario.setCodigo(rs.getInt("ID_FUNC"));
                usuario.setUsurario(rs.getString("NOME"));
                usuario.setSenha(rs.getString("SENHA"));
                usuarios.add(usuario);
            }
            stm.close();
            return usuarios;
        } catch (SQLException msg) {
            JOptionPane.showMessageDialog(null, msg.getMessage() + " Erro de conexao com banco de dados.");
            return null;
        } finally {
            conectar.desconecta();
        }
    }

}
