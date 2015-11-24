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
 * Classe para atualizar dados do banco na tabela produtos também registra histórico de movimentação
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 primeira inclusão no pacote
 * 
 */


import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Movimento {
    
    private final int qtdMaxEstoque = 7000;
    private int quantidadeMovimento;
    private int codigoFuncionario;
    private int tipoMovimento;
    private int codigoProduto;
    
    private final MovivmentoDAO movimentar = new MovivmentoDAO();

    public int getQuantidadeMax() {
        return qtdMaxEstoque;
    }
    
    public int getQuantidadeMovimento() {
        return quantidadeMovimento;
    }

    public void setQuantidadeMovimento(int quantidadeMovimento) {
        this.quantidadeMovimento = quantidadeMovimento;
    }

    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public int getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(int tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    
    /**
     * Cosulta a quantidade no banco e quantidade(na classe) maxima do estoque
     * verifica se é possivel inserir mais produtos, caso não seja possivel
     * exibe uma mensagem na tela,, "além de registrar o movimento" chamando 
     * uma função externa.
     *
     * @param quantidade
     * @param cod
     * @param m
     */
    public void entrada(int quantidade, int cod, Movimento m){
        int quantidadeTotal = new Consulta().consultarQuantidadeTotal();//consulta a quantidade total do estoque
        int quantidadeDoItem = consultaQuatItem(cod);//consulta a quantidade do item que esta em estoque
        //se a soma da quantidae que está em estoque mais a quantidade inserida, entra na condição
        if ((quantidadeTotal+quantidade) >= qtdMaxEstoque) {
            int opcao = JOptionPane.showConfirmDialog(null, "Seu estoque atingiu o volume maximo, Deseja Continuar"
                    , "Volume maximo", JOptionPane.YES_NO_OPTION);
            if (opcao == 0) {
                movimentar.entradaOuSaida((quantidade+quantidadeDoItem), cod);
                movimentar.registrarMovimento(m);
            }
        }else{
                movimentar.entradaOuSaida((quantidade+quantidadeDoItem), cod);
                movimentar.registrarMovimento(m);
        }
    }
    
    /**
     * Cosulta a quantidade do item no banco, caso seja menor que 0 ou
     * quantidade solicitada seja maior que a quantidae em estoque, é exibido
     * a mesagem para o usuario, caso contrário é retirado do estoque, "além
     * de registrar o movimento" chamando uma função externa.
     * 
     * @param quantidade
     * @param cod
     * @param m
     */
    public void saida(int quantidade, int cod, Movimento m){
        int quantidadeDoItem = consultaQuatItem(cod);//consulta a quantidade do item que esta em estoque
        //Caso a quantidade seja maior ou menor que 0 referente a quantidade do banco não é permitido a retidada do item, é exibido a mensagem
        if ((quantidade) < 0 || quantidade > quantidadeDoItem) {
            //não pode ser mair ou menor, em relação a quantidade do estoque
            JOptionPane.showMessageDialog(null, "Digite uma quantidade válida");
        }else {
            movimentar.entradaOuSaida((quantidadeDoItem-quantidade), cod);
            movimentar.registrarMovimento(m);
        }
    }
    
    /**
     * Metodo registra historico de movimento e atualiza os status do material clínico
     * parametors de entradas todas informações de movimentos
     * codigo produto, status a ser atualizado, e propripedades de movimento.
     * 
     * @param cod
     * @param status
     * @param m 
     */
     public void estrializarMaterial(int cod, String status, Movimento m){
         movimentar.esterializar(status, cod);
         movimentar.registrarMovimento(m);
     }
     
       /**
     * Consulta a quantidade do item Informado
     *
     * @param codigo
     * @return um dado do tipo interio com a quantidade
     */
    public int consultaQuatItem(int codigo) {
        ArrayList<Produto> produtosQuantidade = new ConsultaDAO().consultarProduto();
        for (Produto itemQuantidade : produtosQuantidade) {
            if (itemQuantidade.getCodigo() == codigo) {
                return itemQuantidade.getQuantidade();
            }
        }
        return 0;
    }

}
