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
 * Classe Consultar dados do banco e retorna-los.
 * 
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 primeira aplicação
 *
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Miqueas Santos
 */
public class ConsultaDAO {

    private ArrayList listaProdutos = new ArrayList();
    private Produto produto = new Produto();
    ConectaBanco conectar = new ConectaBanco();
    private ResultSet resultadoConsulta;
    private Statement stm;//Statement é uma interface utilizada para executar instruções SQL.

    /**
     * Essa função não recebe parametro nenhum e consulta todos os produtos AAA
     * tabela produtos
     *
     * @return array de produtos"
     */
    public ArrayList consultarProduto() {
        conectar.conexao();
        try {
            stm = conectar.conn.createStatement();//O método CreateStatement() cria uma instância de Statement para execução de SQL.
            resultadoConsulta = stm.executeQuery("SELECT * FROM PRODUTOS;");//retorna uma lista de dados consultada pela requisição
            while (resultadoConsulta.next()) {
                Produto nProdutos = new Produto();
                nProdutos.setCodigo(resultadoConsulta.getInt("ID_PRODUTO"));
                nProdutos.setQuantidade(resultadoConsulta.getInt("QUANTIDADE"));
                nProdutos.setValor(resultadoConsulta.getDouble("VALOR"));
                nProdutos.setNome(resultadoConsulta.getString("NOME"));
                nProdutos.setTipoProduto(resultadoConsulta.getInt("ID_TIPO_PRODUTO"));
                nProdutos.setQuantMax(resultadoConsulta.getInt("QUANT_MAX"));
                nProdutos.setQuantMin(resultadoConsulta.getInt("QUANT_MIN"));
                listaProdutos.add(nProdutos);
            }
            stm.close();
            resultadoConsulta.close();
            return listaProdutos;
        } catch (SQLException e) {//exceção
            JOptionPane.showMessageDialog(null, " erro na consulta dos produtos \n" + e.getMessage());
            return null;
        } finally {
            conectar.desconecta();
        }
    }

    /*
     public ArrayList consultarProdutoTipo(int codigoTipoProduto) {
     conectar.conexao();
     try {
     stm = conectar.conn.createStatement();
     resultadoConsulta = stm.executeQuery("SELECT * FROM PRODUTOS WHERE ID_TIPOPRODUTO=" + codigoTipoProduto);
     while (resultadoConsulta.next()) {
     p.add(new Object[]{
     resultadoConsulta.getInt("COD_PRODUTO"),
     resultadoConsulta.getInt("QUANTIDADE"),
     resultadoConsulta.getDouble("VALOR"),
     resultadoConsulta.getString("NOME"),
     resultadoConsulta.getInt("ID_TIPOPRODUTO")
     });
     }
     JOptionPane.showMessageDialog(null, "Consulta efetuada com sucesso");
     stm.close();
     resultadoConsulta.close();
     return p;
     } catch (SQLException e) {//exceção
     JOptionPane.showMessageDialog(null, " Algum erro \n" + e.getMessage());
     return null;
     } finally {
     conectar.desconecta();
     }
     }
     */
    /**
     * Consulta o historico diretamente no banco e retorna um arrayList de
     * produtos
     *
     * @return arraylist Produtos
     */
    public ArrayList consultarHistorico() {
        conectar.conexao();
        try {//A.NOME AS NOME_FUNC, C.NOME AS NOME_PRODUTO, B.QUANTIDADE, B.DATA_MOV, D.TIPO
            stm = conectar.conn.createStatement();//O método CreateStatement() cria uma instância de Statement para execução de SQL.
            resultadoConsulta = stm.executeQuery("SELECT * FROM HISTORICO_MOV");//retorna uma lista de dados consultada pela requisição
            while (resultadoConsulta.next()) {
                listaProdutos.add(new Object[]{
                    resultadoConsulta.getString("NOME_FUNC"),
                    resultadoConsulta.getInt("ID_PRODUTO"),
                    resultadoConsulta.getString("NOME_PRODUTO"),
                    resultadoConsulta.getInt("QUANTIDADE"),
                    resultadoConsulta.getString("DATA_MOV"),
                    resultadoConsulta.getString("DESCRICAO")
                });
            }
            stm.close();
            resultadoConsulta.close();
            return listaProdutos;
        } catch (SQLException e) {//exceção
            JOptionPane.showMessageDialog(null, " erro na consulta dos produtos \n" + e.getMessage());
            return null;
        } finally {
            conectar.desconecta();
        }
    }

    /**
     * Consulta o dados do medicamento para mostra-los nos campos da tela
     * consulta
     *
     * @return arraylist Produtos
     */
    public Produto consultMedidcamento(int codigo) {
        conectar.conexao();
        try {
            //produto = new Produtos();
            
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT * FROM MEDICACAO WHERE ID_PRODUTO=" + codigo + ";");
            produto.setCodigo(resultadoConsulta.getInt("ID_MEDIC"));
            produto.setMedida(resultadoConsulta.getString("MEDIDA"));
            produto.setLaboratorio(resultadoConsulta.getString("LABORATORIO"));
            produto.setEstado(resultadoConsulta.getString("ESTADO"));
            produto.setTarja(resultadoConsulta.getString("TARJA"));
            produto.setTipoAnimal(resultadoConsulta.getString("TIPO_DO_ANIMAL"));
            stm.close();
            resultadoConsulta.close();
            return produto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Erro medicamento \n" + e.getMessage());
            return null;
        } finally {

            conectar.desconecta();
        }
    }

    /**
     * Consulta o dados do Material Clinico para mostra-los nos campos da tela
     * consulta
     *
     * @return arraylist Produtos
     */
    public Produto consultMaterial(int codigo) {
        conectar.conexao();
        try {
            //produto = new Produtos();
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT ID_MATERIAL,STATUS FROM MATERIAL WHERE ID_PRODUTO=" + codigo + ";");
            produto.setCodigo(resultadoConsulta.getInt("ID_MATERIAL"));
            produto.setEstado(resultadoConsulta.getString("STATUS"));
            //System.out.println(produto.getEstado());
            stm.close();
            resultadoConsulta.close();
            return produto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Algum erro \n" + e.getMessage());
            return null;
        } finally {
            conectar.desconecta();
        }
    }

    /**
     * Consulta o dados do Material Limpeza para mostra-los nos tabela da tela
     * consulta
     *
     * @return arraylist Produtos
     */
    public Produto consultMaterialLimpeza(int codigo) {
        conectar.conexao();
        try {
            //produto = new Produtos();
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT * FROM MATERIAL_LIMPEZA WHERE ID_PRODUTO=" + codigo + ";");
            produto.setCodigo(resultadoConsulta.getInt("ID_LIMPEZA"));
            stm.close();
            resultadoConsulta.close();
            return produto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Algum erro \n" + e.getMessage());
            return null;
        } finally {
            conectar.desconecta();
        }
    }

    /*
     public ArrayList consultarCodigo(int codigo) {
     conectar.conexao();
     try {
     stm = conectar.conn.createStatement();
     resultadoConsulta = stm.executeQuery("SELECT * FROM PRODUTOS WHERE COD_PRODUTO ="+codigo+";");
     while (resultadoConsulta.next()) {
     p.add(new Object[]{
     resultadoConsulta.getInt("COD_PRODUTO"),
     resultadoConsulta.getInt("QUANTIDADE"),
     resultadoConsulta.getDouble("VALOR"),
     resultadoConsulta.getString("NOME"),
     resultadoConsulta.getInt("ID_TIPOPRODUTO")
     });
     }
     JOptionPane.showMessageDialog(null, "Consulta efetuada com sucesso");
     stm.close();
     resultadoConsulta.close();
     return p;
     } catch (SQLException e) {//exceção
     JOptionPane.showMessageDialog(null, " Algum erro \n" + e.getMessage());
     return null;
     } finally {
     conectar.desconecta();
     }
     }
     
     public ArrayList consultaNome(String nome) {
     conectar.conexao();
     try {
     stm = conectar.conn.createStatement();
     resultadoConsulta = stm.executeQuery("SELECT * FROM PRODUTOS WHERE nome like '"+nome+"%';");
     while (resultadoConsulta.next()) {
     p.add(new Object[]{
     resultadoConsulta.getInt("COD_PRODUTO"),
     resultadoConsulta.getInt("QUANTIDADE"),
     resultadoConsulta.getDouble("VALOR"),
     resultadoConsulta.getString("NOME"),
     resultadoConsulta.getInt("ID_TIPOPRODUTO")
     });
     }
     JOptionPane.showMessageDialog(null, "Consulta efetuada com sucesso");
     stm.close();
     resultadoConsulta.close();
     return p;
     } catch (SQLException e) {//exceção
     JOptionPane.showMessageDialog(null, " Algum erro \n" + e.getMessage());
     return null;
     } finally {
     conectar.desconecta();
     }
     }
     */
    /**
     * Consulta o dados de um tipo de produto
     *
     * @return arraylist Produtos
     */
    public String consultTipoProduto(int codigo) {
        conectar.conexao();
        try {
            //produto = new Produtos();
            stm = conectar.conn.createStatement();
            resultadoConsulta = stm.executeQuery("SELECT * FROM TIPO_PRODUTO WHERE ID_TIPO_PRODUTO=" + codigo);
            String descricaoProduto = resultadoConsulta.getString("DESCRICAO");
            //JOptionPane.showMessageDialog(null, "Entrou com sucesso em produto tipo");
            return descricaoProduto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Algum erro no tipo produto\n" + e.getMessage());
            return null;
        } finally {
            conectar.desconecta();
        }
    }

    /**
     * Consulta o dados de produto que estão em baixa ou em alta comando logico
     * executado atráves da query
     *
     * @return arraylist Object
     */
    public ArrayList produtoEmBaixaAlta() {
        conectar.conexao();
        ArrayList novaLista = new ArrayList();
        try {
            stm = conectar.conn.createStatement();//O método CreateStatement() cria uma instância de Statement para execução de SQL.
            resultadoConsulta = stm.executeQuery("select * from produtos where quantidade < quant_min or quantidade > quant_max;");//retorna uma lista de dados consultada pela requisição
            while (resultadoConsulta.next()) {
                novaLista.add(new Object[]{
                    (resultadoConsulta.getInt("ID_PRODUTO")),
                    (resultadoConsulta.getInt("QUANTIDADE")),
                    (resultadoConsulta.getString("NOME")),
                    (resultadoConsulta.getInt("QUANT_MAX")),
                    (resultadoConsulta.getInt("QUANT_MIN")),});
            }
            stm.close();
            resultadoConsulta.close();
            return novaLista;
        } catch (SQLException e) {//exceção
            JOptionPane.showMessageDialog(null, " Erro na consulta dos produtos \n" + e.getMessage());
            return null;
        } finally {
            conectar.desconecta();
        }
    }

    /**
     * Consulta quantidade Maxima de um produto
     *
     * @return int quantidade
     */
    public int quantdadeMax(int cod) {
        conectar.conexao();

        try {
            stm = conectar.conn.createStatement();//O método CreateStatement() cria uma instância de Statement para execução de SQL.
            resultadoConsulta = stm.executeQuery("SELECT QUANT_MAX FROM PRODUTOS WHERE ID_PRODUTO=" + cod);//retorna uma lista de dados consultada pela requisição

            int quantMax = resultadoConsulta.getInt("QUANT_MAX");
            //(resultadoConsulta.getInt("QUANT_MIN")),

            stm.close();
            resultadoConsulta.close();
            return quantMax;
        } catch (SQLException e) {//exceção
            JOptionPane.showMessageDialog(null, " erro na consulta dos produtos \n" + e.getMessage());
            return 0;
        } finally {
            conectar.desconecta();
        }
    }

    /**
     * Consulta quantidade Minima de um produto
     *
     * @return int
     */
    public int quantdadeMin(int cod) {
        conectar.conexao();
        try {
            stm = conectar.conn.createStatement();//O método CreateStatement() cria uma instância de Statement para execução de SQL.
            resultadoConsulta = stm.executeQuery("SELECT QUANT_MIN FROM PRODUTOS WHERE ID_PRODUTO=" + cod);//retorna uma lista de dados consultada pela requisição

            int quantMax = resultadoConsulta.getInt("QUANT_MIN");
            //(resultadoConsulta.getInt("QUANT_MIN")),

            stm.close();
            resultadoConsulta.close();
            return quantMax;
        } catch (SQLException e) {//exceção
            JOptionPane.showMessageDialog(null, " erro na consulta dos produtos \n" + e.getMessage());
            return 0;
        } finally {
            conectar.desconecta();
        }
    }

    /*
    
     public ArrayList produtoEmBaixa(int quantidade) {
     conectar.conexao();
     try {
     stm = conectar.conn.createStatement();
     resultadoConsulta = stm.executeQuery("SELECT * FROM PRODUTOS WHERE QUANTIDADE < " + quantidade);
     while (resultadoConsulta.next()) {
     p.add(new Object[]{
     resultadoConsulta.getInt("COD_PRODUTO"),
     resultadoConsulta.getInt("QUANTIDADE"),
     resultadoConsulta.getDouble("VALOR"),
     resultadoConsulta.getString("NOME"),
     });
     }
     JOptionPane.showMessageDialog(null, "Consulta efetuada com sucesso");
     stm.close();
     resultadoConsulta.close();
     return p;
     } catch (SQLException e) {//exceção
     JOptionPane.showMessageDialog(null, " Algum erro \n" + e.getMessage());
     return null;
     } finally {
     conectar.desconecta();
     }
     }

     */
}
