/**
 * TADS 2º SEMESTRE PROJETO CLINICA VETERINARIA CONTROLE DE ESTOQUE
 * 
 * DATA: 11/01/2015
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
 * Classe com as propriedades de um Produto, chamada de metodos da classe ProdutoDAO 
 * e resgistra dados de produtos além de consultar disponibilidade do estoque informado ao usuário,
 * dependendo da sua resposta o produto é registrado.
 *
 * @author miqueas b.santos
 *
 * @version 1.0
 *
 * @since Release 15/11/2015 01 primeira aplicação
 *
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Produto {
    
    //ESSA CLASSE DEFINI OS GETTERS E SETTERS DE PRODUTOS E SUAS PROPRIEDADES
    //VARIAVEIS RESPONSAVEL POR RECEBER OS DADOS DO PRODUTO
    private int codigo;
    private String nome;
    private double valor;
    private int quantidade;
    private int quantMax;
    private int quantMin;
    private String medida;
    private String estado;
    private String tarja;
    private String tipoAnimal;
    private int tipoProduto;
    private int tipoEntrada;

    public int getQuantMax() {
        return quantMax;
    }

    public void setQuantMax(int quantMax) {
        this.quantMax = quantMax;
    }

    public int getQuantMin() {
        return quantMin;
    }

    public void setQuantMin(int quantMin) {
        this.quantMin = quantMin;
    }
    
    private ArrayList<Produto> p = new ArrayList<>();
    private final ProdutoDAO banco = new ProdutoDAO();
    

    public int getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(int tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public int getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(int tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }
    private String laboratorio;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getMedida() {
        return medida;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
   
    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTarja() {
        return tarja;
    }

    public void setTarja(String tarja) {
        this.tarja = tarja;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
    
    /**
     * Consulta a quantidade do produto e retorna se o codigo já existe
     * usado para validar o cadastro do produto
     * 
     * @param codigo
     * @return 
     */
    public boolean consultarCodigo(int codigo) {
        ArrayList<Produto> produtosLista = new ConsultaDAO().consultarProduto();//responsavel por receber os produtos do bancoo
        for (Produto arrayAux : produtosLista) {
            if (arrayAux.getCodigo() == codigo) {
                return false;
            }
        }
        return true;
    }

    /**
     * verifica se o produto já é cadastrado, consulta a disponibilidade do banco
     * informa ao usuário se o estoque esta disponivel, registra no banco as informacoes
     * principais dos produtos(NOME,QUANTIDA,VALOR,QUANT MAX, QUAN MIN, ID TIPO, COD PRODUTO)
     * e informações adicionais de seus produtos(MATERIAL DE LIMPEZA, MEDICAÇÃO E MATERIAL CLINICO).
     * 
     * 
     * @param ps 
     */
    public void entradaProduto(Produto ps) {
        
        if (consultarCodigo(ps.getCodigo()) & disponibilidade(ps)) {
            banco.cadastrarProduto(ps);
          //banco.cadastraQuantidade(ps);
            switch (ps.getTipoProduto()) {
                    case 1:
                        //Material clinico
                        banco.cadastrarMaterialClinico(ps);
                        break;
                    case 2:
                        //Material de limpeza
                        banco.cadastrarMaterialLimpeza(ps);
                        break;
                    case 3:
                        //Medicação 
                        banco.cadastrarMedicamento(ps);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Preenha corretamente");
                }
            System.out.println("Entrou no cadastro");
        }else{
             JOptionPane.showMessageDialog(null, "Esse produto Já está Cadastrado\n Consulte a tela \"Movimentação\" Caso Queira Inserir Mais Produtos");
        }
    }
    
    /**
     * verifica a disponibilidade do banco retornando valor boolean além de questionar
     * ao usuário se quer inserir o produto mesmo com estoque cheio
     * 
     * @param ps
     * @return 
     */
    public boolean disponibilidade(Produto ps) {
        int quantidadeTotal = new Consulta().consultarQuantidadeTotal();
        if ((ps.getQuantidade() + quantidadeTotal) >= new Movimento().getQuantidadeMax()) {
            int opcao = JOptionPane.showConfirmDialog(null, "Seu estoque atingiu o volume maximo, Deseja Continuar", "Volume maximo", JOptionPane.YES_NO_OPTION);
            if (opcao == 0) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }
    
    
    
}
