/*
 *Projeto Clinica Veterinaria TADS 2º, 2015 Senac.
 *
 *PROJETO INTEGRADOR II: DESENVOLVIMENTO ESTRUTURADO DE SISTEMAS
 *
 *Alunos: Miqueas Batista Dos Santos, Victor Augusto e Gustavo Luiz Mafra.
 *
 *Profª: Alessandra
 *
 *DATA 19/11/2015
 *
 *
 */
package classes;

/**
 * Classe Responsavel Por fazer a conexão com o banco
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 aplicação
 *
 */

//IMPORTANDO PACOTES DE INTRUÇÕES DO JDBC PARA CONECTAR AO BANCO DE DADOS
//import java.sql.Statement;
//import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectaBanco {

    //private Statement stm;//Responsavel por prepara e realizar pesquisas no banco de dados
    //ResultSet rs;//Responsavel por armazenar o resultado de uma pesquisa passada para o Statement
    Connection conn;
    private final String drive = "org.sqlite.JDBC";//identifica qual banco de dados o java ira trabalhar
    private final String Camninho = "jdbc:sqlite:C:\\Users\\miqueas.bsantos\\Documents\\NetBeansProjects\\Projeto_Integrador_II\\ClinicaVeterinaria\\bd\\ProjetoClinica.db";

    /**
     * reponsavel por fazer a conexão no banco de dados
     */
    public void conexao() {
        try {
            System.setProperty("jdbc.Drivers", drive);//seta propriedade do drive de conexao
            conn = DriverManager.getConnection(Camninho);//executa a conexao com o banco de dados
            //JOptionPane.showMessageDialog(null, "Conectado com Sucesso");
        } catch (SQLException e) {//exceção
            JOptionPane.showMessageDialog(null, "Erro de conexão \n" + e.getMessage());
        }
    }

    /**
     * fecha a conexao com o banco
     */
    public void desconecta() {
        try {
            conn.close();//fecha a conexao
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão \n" + e.getMessage());
        }
    }

}
