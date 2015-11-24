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
 * Classe responsável por fazer inserção dos Produtos informados e suas informações adicionais.
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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author miqueas.bsantos
 * 
 */
public class ProdutoDAO {

    ConectaBanco conectar = new ConectaBanco();
    private ArrayList<Produto> p = new ArrayList<>();
    private PreparedStatement preparedStm;
    
    /**
     * Cadastra propriedades do produto informado. Os dados são apenas Inseridos. 
     * Paramentro de entrada Produtos tem que conter todas as informções
     * @param p 
     */
    public void cadastrarProduto(Produto p) {
        conectar.conexao();
        try {
            preparedStm = conectar.conn.prepareStatement("INSERT INTO PRODUTOS(ID_PRODUTO, QUANTIDADE, VALOR, NOME, ID_TIPO_PRODUTO, QUANT_MAX, QUANT_MIN)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStm.setInt(1, p.getCodigo());
            preparedStm.setInt(2, p.getQuantidade());
            preparedStm.setDouble(3, p.getValor());
            preparedStm.setString(4, p.getNome());
            preparedStm.setInt(5, p.getTipoProduto());
            preparedStm.setInt(6, p.getQuantMax());
            preparedStm.setInt(7, p.getQuantMin());
            preparedStm.executeUpdate();
            preparedStm.close();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado Com Sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao asdasd");
        } finally {
            conectar.desconecta();
        }

    }
    /**
     * Cadastra medicamento. Os dados são apenas Inseridos. 
     * Paramentro de entrada Produtos tem que conter todas as informções
     * 
     * @param p 
     */
    public void cadastrarMedicamento(Produto p) {
        conectar.conexao();
        try {
            preparedStm = conectar.conn.prepareStatement("INSERT INTO MEDICACAO(ID_PRODUTO, MEDIDA, "
                    + "LABORATORIO, ESTADO, TARJA, TIPO_DO_ANIMAL) VALUES (?, ?, ?, ?, ?, ?)");
            //medicaocao
            preparedStm.setInt(1, p.getCodigo());
            preparedStm.setString(2, p.getMedida());
            preparedStm.setString(3, p.getLaboratorio());
            preparedStm.setString(4, p.getEstado());
            preparedStm.setString(5, p.getTarja());
            preparedStm.setString(6, p.getTipoAnimal());
            preparedStm.executeUpdate();
            preparedStm.close();
            //  JOptionPane.showMessageDialog(null, "MEDICAMENTO Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "Erro de conexao");
        } finally {
            conectar.desconecta();
        }

    }
    
    /**
     * Cadastra material clinico. Os dados são apenas Inseridos. 
     * 
     * Paramentro de entrada Produtos tem que conter todas as informções
     * @param p 
     */
    public void cadastrarMaterialClinico(Produto p) {
        conectar.conexao();
        try {
            preparedStm = conectar.conn.prepareStatement("INSERT INTO MATERIAL(ID_PRODUTO, STATUS) VALUES(?, ?)");
            preparedStm.setInt(1, p.getCodigo());
            preparedStm.setString(2, p.getEstado());
            preparedStm.executeUpdate();
            preparedStm.close();
            //  JOptionPane.showMessageDialog(null, "Material Medico Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
        } finally {
            conectar.desconecta();
        }

    }

    /**
     * Cadastra material de limpeza, Os dados são apenas Inseridos. 
     * 
     * Paramentro de entrada Produtos tem que conter todas as informções
     * @param p 
     */
    public void cadastrarMaterialLimpeza(Produto p) {
        conectar.conexao();
        try {
            preparedStm = conectar.conn.prepareStatement("INSERT INTO MATERIAL_LIMPEZA(ID_PRODUTO) VALUES(?)");
            preparedStm.setInt(1, p.getCodigo());
            preparedStm.executeUpdate();
            preparedStm.close();
            //  JOptionPane.showMessageDialog(null, "Material de limpeza Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
        } finally {
            conectar.desconecta();
        }

    }
/*
    public void cadastraQuantidade(produto p) {
        conectar.conexao();
        try {
            PreparedStatement prepraredStm;
            prepraredStm = conectar.conn.prepareStatement("INSERT INTO CONTROLE_ESTOQUE(COD_PRODUTO, QUANT_MAX, COD_TIPO) VALUES(?, ?,?)");
            prepraredStm.setInt(1, p.getCodigo());
            prepraredStm.setInt(2, p.getQuantMax());
            prepraredStm.setInt(3, p.getTipoProduto());
            prepraredStm.executeUpdate();
            prepraredStm.close();
            JOptionPane.showMessageDialog(null, "Quantidade Salva com sucessos");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
        } finally {
            conectar.desconecta();
        }
    }

    public int consultQuantTotal() {
        conectar.conexao();
        try {
            int quantidade = 0;
            //produto = new Produtos();
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT QUANTIDADE FROM PRODUTOS");
            while (resultadoConsulta.next()) {
                quantidade += resultadoConsulta.getInt("QUANTIDADE");
            }
            //   JOptionPane.showMessageDialog(null, "Entrou com sucesso em quantidade");
            return quantidade;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Algum erro no tipo produto\n" + e.getMessage());
            return 0;
        } finally {
            conectar.desconecta();
        }
    }

    public ArrayList ItemExistente() {
        conectar.conexao();
        try {
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT * FROM PRODUTOS");
            while (resultadoConsulta.next()) {
                produto pResult = new produto();
                pResult.setCodigo(resultadoConsulta.getInt("COD_PRODUTO"));
                pResult.setQuantidade(resultadoConsulta.getInt("QUANTIDADE"));
                p.add(pResult);
            }
            resultadoConsulta.close();
            stm.close();
            return p;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
            return null;
        } finally {
            conectar.desconecta();
        }

    }

    public int consultaQuantMax(int codigo) {
        conectar.conexao();
        try {
            int a = 0;
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT QUANTMAX FROM TIPO_PRODUTO WHERE ID=" + codigo);
            a = resultadoConsulta.getInt("QUANTMAX");
            resultadoConsulta.close();
            stm.close();
            return a;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro de conexao");
            return 0;
        } finally {
            conectar.desconecta();
        }
    }
*/
}
