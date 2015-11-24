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
 * Classe para Consultar objetos do tipo produtos, onde serão contidos, instruções e
 * métodos para filtrar as consultas do banco.
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 aplicação
 *
 */
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public final class Consulta extends AbstractTableModel {

    //private produto produt;//resonsavel por receber os dados somente de um certo produto
    private final ConsultaDAO consultaBanco = new ConsultaDAO();

    //Propriedades da classe consulta para preencher a tabela com dados do array
    private ArrayList linhas;
    private String colunas[] = null;

    public Consulta(ArrayList l, String[] c) {
        setLinhas(l);
        setColunas(c);
    }

    public Consulta() {

    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] nomes) {
        this.colunas = nomes;
    }

    //metodos abstratos da classe, consiste em deixar o codigo mais consistente e
    //implementar uma caracteristica propria das classes que a herdam.
    @Override
    public int getColumnCount() {
        return colunas.length;//quantidade de caracteres que vai ter na coluna
    }

    @Override
    public int getRowCount() {
        return linhas.size();//tamanho do array quantidade de linhas
    }

    @Override
    public String getColumnName(int numCol) {
        return colunas[numCol];//retorna o valor que esta na coluna
    }

    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin);//recebe o numero de linhas e colunas e retorna a tabela conforme os numeros passados
        return linha[numCol];
    }

    /**
     * receber os dados que serem mostrado na tabela através de um arraylist de
     * object, também é possivel editar as colunas com o segundo paremetro
     *
     * @param minhaTabela
     * @param dadosDaTabela
     */
    public void iniciarTabela(JTable minhaTabela, ArrayList dadosDaTabela) {
        Consulta modeloTabela = new Consulta(dadosDaTabela, colunas);
        minhaTabela.setModel(modeloTabela);
        //for (int i = 0; i < colunas.length; i++) {
        //  j.getColumnModel().getColumn(i).setPreferredWidth(tamanhoColunas);
        //   j.getColumnModel().getColumn(i).setResizable(false);//não alttera o tamanho da coluna
        // }
        //j.getTableHeader().setReorderingAllowed(false);//não vai reodernar a locação
        //j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//não altera o tamanho da tabela
        //j.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleciona apenas um elemento da tabela;
    }

    /**
     * inicia uma tabela padrão com os seguintes dados com o resultado da
     * consulta "CODIGO PRODUTO", "QUANTIDADE", "VALOR","NOME". primeiro
     * parametro sua jtable a ser populada segundo parametro seus dados para
     * popular as tabelas
     *
     * @param minhaTabela
     * @param dadosDaTabela
     */
    public void iniciarTabelaPadrao(JTable minhaTabela, ArrayList dadosDaTabela) {
        Consulta modeloTabela = new Consulta(dadosDaTabela, new String[]{"CODIGO PRODUTO", "QUANTIDADE", "VALOR", "NOME"});
        minhaTabela.setModel(modeloTabela);
        //for (int i = 0; i < colunas.length; i++) {
        //  j.getColumnModel().getColumn(i).setPreferredWidth(tamanhoColunas);
        //   j.getColumnModel().getColumn(i).setResizable(false);//não alttera o tamanho da coluna
        // }
        //j.getTableHeader().setReorderingAllowed(false);//não vai reodernar a locação
        //j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//não altera o tamanho da tabela
        //j.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//seleciona apenas um elemento da tabela;
    }

    /**
     * metodo converte um arraylist de produtos para um array de object para
     * tabela de consultas.
     *
     * @param produtosLista
     * @return array de object
     */
    public ArrayList convertaParaObjectLista(ArrayList<Produto> produtosLista) {
        ArrayList novaLista = new ArrayList();
        for (Produto novaLista1 : produtosLista) {
            novaLista.add(new Object[]{
                novaLista1.getCodigo(),
                novaLista1.getQuantidade(),
                novaLista1.getValor(),
                novaLista1.getNome(),
                novaLista1.getTipoProduto()
            });
        }
        return novaLista;
    }

    /**
     * metodo converte apenas um produto em um objecto para visualizar o item na
     * tabela porque o jtable so aceita classe object
     *
     * @param produto
     * @return
     */
    public ArrayList convertaParaObject(Produto produto) {
        ArrayList novaLista = new ArrayList();
        novaLista.add(new Object[]{
            produto.getCodigo(),
            produto.getQuantidade(),
            produto.getValor(),
            produto.getNome(),
            produto.getTipoProduto()
        });
        return novaLista;
    }

    /**
     * função consulta um arraylist completo de produtos no banco.
     *
     * @return um array de object
     */
    public ArrayList consultarCompleta() {
        ArrayList<Produto> produtosLista = consultaBanco.consultarProduto();//responsavel por receber os produtos do bancoo
        return convertaParaObjectLista(produtosLista);
    }

    /**
     * função consulta um arraylist completo de produtos no banco e apenas
     * seleciona aqueles que contem a sequencia de caracteres do parametro
     * recebido.
     *
     * @param nome
     * @return array de object
     */
    public ArrayList consultarNome(String nome) {
        ArrayList<Produto> produtosLista = consultaBanco.consultarProduto();//responsavel por receber os produtos do bancoo
        ArrayList produtosPorNome = new ArrayList();
        for (Produto arrayAux : produtosLista) {
            if (arrayAux.getNome().contains(nome)) {
                produtosPorNome.add(arrayAux);
            }
        }
        return convertaParaObjectLista(produtosPorNome);
    }

    /**
     * função consulta um arraylist completo de produtos no banco e apenas
     * seleciona aqueles que contem o codigo do parametro recebido.
     *
     * @param codigo
     * @return array de object
     */
    public ArrayList consultarCodigo(int codigo) {
        ArrayList<Produto> produtosLista = consultaBanco.consultarProduto();//responsavel por receber os produtos do bancoo
        for (Produto arrayAux : produtosLista) {
            if (arrayAux.getCodigo() == codigo) {
                return convertaParaObject(arrayAux);
            }
        }
        return null;
    }

    /**
     * função consulta um arraylist completo de produtos no banco e apenas
     * seleciona aqueles que contem o codigo do 'material' do parametro
     * recebido.
     *
     * @param codigoProduto
     * @return array de object
     */
    public ArrayList consultarTipoProduto(int codigoProduto) {
        ArrayList<Produto> produtosLista = consultaBanco.consultarProduto();//responsavel por receber os produtos do bancoo
        ArrayList produtosPorCodigo = new ArrayList();
        for (Produto arrayAux : produtosLista) {
            if (arrayAux.getTipoProduto() == codigoProduto) {
                produtosPorCodigo.add(arrayAux);
            }
        }
        return convertaParaObjectLista(produtosPorCodigo);
    }

    /**
     * função consulta quantidade total dos produtos cadastrados
     *
     * @return integer
     */
    public int consultarQuantidadeTotal() {
        int quantidadeContadora = 0;
        ArrayList<Produto> produtosLista = consultaBanco.consultarProduto();//responsavel por receber os produtos do bancoo
        for (Produto produtosLista1 : produtosLista) {
            quantidadeContadora += produtosLista1.getQuantidade();
        }
        return quantidadeContadora;
    }

    /**
     * função consulta quantidade total de apenas um item, caso item não
     * encontrado retorna 0
     *
     * @param codigoProduto
     * @return integer
     */
    public int consultarQuantidadeItem(int codigoProduto) {
        ArrayList<Produto> produtosLista = consultaBanco.consultarProduto();//responsavel por receber os produtos do bancoo
        for (Produto produtosLista1 : produtosLista) {
            if (produtosLista1.getCodigo() == codigoProduto) {
                return produtosLista1.getQuantidade();
            }
        }
        return 0;
    }

    /**
     * função consulta produtos que estão acima e abaixo da quantidade ideal
     * para o estoque, porem o mesmo deve passar essas informacões como
     * parametros da função
     *
     * @return array de object
     *
     */
    public ArrayList produtEmBaixaAlta() {
        ArrayList produtosLista = consultaBanco.produtoEmBaixaAlta();//responsavel por receber os produtos do bancoo

        return produtosLista;
    }

    /**
     * consulta os dados da tabela movimento para visualizar as modifições
     * feitas no banco
     *
     * @return array de object
     */
    public ArrayList consultarHistorico() {
        return consultaBanco.consultarHistorico();
    }

    /**
     * consulta daods somente de medicamento de um item do parametro informado
     *
     * @param codigo
     * @return Produto
     */
    public Produto preencheMedicamento(int codigo) {
        return consultaBanco.consultMedidcamento(codigo);
    }

    /**
     * consulta daods somente de Material médico de um item do parametro
     * informado
     *
     * @param codigo
     * @return Produto
     */
    public Produto preencheMaterial(int codigo) {
        return consultaBanco.consultMaterial(codigo);
    }

    /**
     * consulta daods somente de Produtos de Limpeza de um item do parametro
     * informado
     *
     * @param codigo
     * @return Produto
     */
    public Produto preencheLimpeza(int codigo) {
        return consultaBanco.consultMaterialLimpeza(codigo);

    }

    /**
     * consulta dados somente de um tipo de produto(medicação, material e
     * limpeza) de um item do parametro informado
     *
     * @param codigo
     * @return String
     */
    public String TipoMedicamento(int codigo) {
        return consultaBanco.consultTipoProduto(codigo);
    }

    /**
     * consulta quantidade maxima do item Informado
     *
     * @param codigo
     * @return int
     */
    public int quantMax(int cod) {
        return consultaBanco.quantdadeMax(cod);
    }

    /**
     * consulta quantidade minima do item Informado
     *
     * @param codigo
     * @return int
     */
    public int quantMin(int cod) {
        return consultaBanco.quantdadeMin(cod);
    }

    /*
    
     public ArrayList consultaCompleta(){
     return consultaBanco.consultarProduto();
     }
    
     public ArrayList consultaCompletaTipo(int codigoTipoProduto){
     return consultaBanco.consultarProdutoTipo(codigoTipoProduto);
     }
    
     
    
     public ArrayList consultarCodigo(int codigo){
     return consultaBanco.consultarCodigo(codigo);
     }
    
     
    
     public int consultarQuantidade(){
     return consultaBanco.consultQuantidade();
     }
    
    
     public ArrayList consultarPorcentagem(int max, int porcentagemMinima){
     produtosLista = consultaBanco.consultarProduto();
     ArrayList<produto> n = produtosLista;
     for (produto p : n) {
     if ((p.getQuantidade()*100)/max>porcentagemMinima) {
     produtosLista.remove(p);
     }
     }
     return produtosLista;
     }
    
     public ArrayList produtoEmAlta(){
     produtosLista = consultaBanco.consultarProduto();
     int referencia = (consultarQuantidade()/produtosLista.size());
     for (produto p :(ArrayList<produto>) produtosLista) {
     if (p.getQuantidade() < referencia) {
     produtosLista.remove(p);
     }
     }
     return produtosLista;
     }
    
     */
}
