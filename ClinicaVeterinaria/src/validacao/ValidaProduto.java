package validacao;


import classes.Produto;
import javax.swing.JOptionPane;


/**
 *
 * @author miqueas.bsantos
 */
public class ValidaProduto {

    
    public boolean validarCamposPrincipal(Produto p){
        return !(p.getCodigo()==0 || p.getQuantidade()==0 || p.getTipoProduto()==0 || p.getQuantMax() < p.getQuantMin() ||
                 p.getNome().equals("") || p.getValor()==0 || p.getQuantMax()==0 || p.getQuantMin()==0);
    }
    
    public boolean validarMedicamento(Produto p){
        return !(p.getLaboratorio().equals("") || p.getMedida().equals("") || p.getEstado().equals("nennhum") ||
                 p.getTarja().equals("nenhuma") || p.getEstado().equals("nenhum") || p.getTipoAnimal().equals("nenhum") );
    }
    
    public boolean validarMaterialMedico(Produto p){
        return !(p.getEstado().equals("nenhum"));
    }
    
    public void mensagemPricipal(Produto p){
        if (p.getCodigo()==0 & p.getQuantidade()==0 & p.getTipoProduto()==0 & p.getQuantMax() < p.getQuantMin() &
             p.getNome().equals("") & p.getValor() == 0 & p.getQuantMax()==0 & p.getQuantMin()==0) 
        {
            JOptionPane.showMessageDialog(null, "\nPreencha os campos corretamente:");
        }else if(p.getCodigo()==0){
            JOptionPane.showMessageDialog(null, "\nInforme o codigo");
        }else if(p.getQuantidade()==0){
            JOptionPane.showMessageDialog(null, "\nInforme a quantidade");
        }else if(p.getTipoProduto()==0){
            JOptionPane.showMessageDialog(null, "\nInforme o tipo de produto");
        }else if (p.getNome().equals("")){
            JOptionPane.showMessageDialog(null, "\nInforme o nome do produto");
        }else if(p.getValor() == 0){
            JOptionPane.showMessageDialog(null, "\nInforme um valor válido");
        }else if ((p.getValor()+"").equals("")){
            JOptionPane.showMessageDialog(null, "\nInforme o valor");
        }else if(p.getQuantMin()==0 || p.getQuantMax()==0){
            JOptionPane.showMessageDialog(null, "\nInforme o QdtMin/Max Válida");
        }else if(p.getQuantMax() < p.getQuantMin()){
            JOptionPane.showMessageDialog(null, "\nQuantidade Max Não Pode Ser menor que Minima");
        }
        
    }
    
    public void mensagemMedicamento(Produto p){
        if (p.getLaboratorio().equals("") & p.getMedida().equals("") & p.getEstado().equals("nennhum") &
            p.getTarja().equals("nenhum") & p.getTipoAnimal().equals("nenhum") & p.getEstado().equals("nenhum")) 
        {
            JOptionPane.showMessageDialog(null, "Preencha as informações do medicamento corretamente");
        }else if(p.getLaboratorio().equals("")){
            JOptionPane.showMessageDialog(null, "informe o laboratório");
        }else if(p.getMedida().equals("")){
            JOptionPane.showMessageDialog(null, "informe a medida do medicamento");
        }else if(p.getEstado().equals("nennhum")){
            JOptionPane.showMessageDialog(null, "informe o tipo do medicamento");
        }else if(p.getTarja().equals("nenhum")){
            JOptionPane.showMessageDialog(null, "informe a tarja do medicamento");
        }else if(p.getEstado().equals("nenhum")){
            JOptionPane.showMessageDialog(null, "informe o estado do medicamento");
        }else if(p.getTipoAnimal().equals("nenhum")){
            JOptionPane.showMessageDialog(null, "Informe a espécie");
        }
    }
    
    public void mensagemMaterial(Produto p){
        JOptionPane.showMessageDialog(null, "\nInforme o Status do material");
    }

}
