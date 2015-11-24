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
 * Classe responsável executar as querys de update nas tabelas movimento e
 * registrar historico.
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 primeira aplicação
 *
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe para atualizar dados do banco na tabela produtos e inseri dados de
 * movimento no historico
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 primeira aplicação
 *
 */
public class MovivmentoDAO {

    private PreparedStatement preparedStm;
    ConectaBanco conectar = new ConectaBanco();
    private Statement stm;//É usado para criar um objeto Statement que executa instruções SQL ao banco de dados.

    /**
     * Realiza a movimentção no banco de dados tabela produtos, com o comando
     * update.
     *
     * @param quantidade
     * @param codigo
     */
    public void entradaOuSaida(int quantidade, int codigo) {
        conectar.conexao();
        try {
            String comando = "UPDATE PRODUTOS SET QUANTIDADE=" + quantidade + " WHERE ID_PRODUTO=" + codigo + ";";
            //metedodo deixa os dados pré-copilados e armazenasos no preparedStm, então esses statemants podem ser usados mutiplas vezes
            stm = conectar.conn.createStatement();

            //Executa uma instrução SQL referente a um INSERT, UPDATE e DELETE.
            //Esse método retorna a quantidade de registros que são afetados pela execução do comando SQL.
            stm.executeUpdate(comando);
            stm.close();
            JOptionPane.showMessageDialog(null, "Operação Efetuada Com Sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
        } finally {
            conectar.desconecta();
        }

    }

    /**
     * Atualiza os dados de Status do material clinico com status informado e
     * codigo do produto a ser atualizado
     *
     * @param status
     * @param codigo
     */
    public void esterializar(String status, int codigo) {
        conectar.conexao();
        try {
            String comando = "UPDATE MATERIAL SET STATUS='" + status + "' WHERE ID_PRODUTO=" + codigo + ";";
            //metedodo deixa os dados pré-copilados e armazenasos no preparedStm, então esses statemants podem ser usados mutiplas vezes
            stm = conectar.conn.createStatement();
            //Executa uma instrução SQL referente a um INSERT, UPDATE e DELETE.
            //Esse método retorna a quantidade de registros que são afetados pela execução do comando SQL.
            stm.executeUpdate(comando);
            stm.close();
            JOptionPane.showMessageDialog(null, "Atualização Efetuada Com Sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao Esterializar");
        } finally {
            conectar.desconecta();
        }

    }

    /**
     * Registra o movimento data e todas informações necessarias para consultar
     * quem fez o movimento, quantidade, datas.... entr outros. parametro de
     * entrada dados do movimento 'm'
     *
     * @param m
     */
    public void registrarMovimento(Movimento m) {
        conectar.conexao();
        try {
            preparedStm = conectar.conn.prepareStatement("INSERT INTO MOVIMENTACAO(ID_FUNC, ID_PRODUTO, ID_TIPO_MOV,QUANTIDADE, DATA_MOV) "
                    + "VALUES (?, ?, ?, ?, DATE('NOW'))");
            preparedStm.setInt(1, m.getCodigoFuncionario());
            preparedStm.setInt(2, m.getCodigoProduto());
            preparedStm.setInt(3, m.getTipoMovimento());
            preparedStm.setInt(4, m.getQuantidadeMovimento());
            preparedStm.executeUpdate();
            preparedStm.close();
            // JOptionPane.showMessageDialog(null, "PRODUTO Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao na movimentação");
        } finally {
            conectar.desconecta();
        }
    }

    /*
     public void entradaOuSaida(int quantidade, int codigo) {
     conectar.conexao();
     try {
     String comando = "UPDATE PRODUTOS SET QUANTIDADE=? WHERE cod_produto=?;";
     //metedodo deixa os dados pré-copilados e armazenasos no preparedStm, então esses statemants podem ser usados mutiplas vezes
     preparedStm = conectar.conn.prepareStatement(comando);
     preparedStm.setInt(1, quantidade);
     preparedStm.setInt(2, codigo);

     //Executa uma instrução SQL referente a um INSERT, UPDATE e DELETE.
     //Esse método retorna a quantidade de registros que são afetados pela execução do comando SQL.
     preparedStm.executeUpdate(comando);
     preparedStm.close();
     //JOptionPane.showMessageDialog(null, "PRODUTO Salvo com sucesso");
     } catch (SQLException e) {
     JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
     } finally {
     conectar.desconecta();
     }

     }
     */
}
