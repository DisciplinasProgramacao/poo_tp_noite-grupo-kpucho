package src;

public class Filme extends Midia
{
    private int duracao;
    private String descricao; // Não implementado
    
    public Filme(String IdFilme, String nome, String dataDeLancamento,String idioma, String genero, int duracao, int contagemVisualizacao)
    {
        super(IdFilme, nome, dataDeLancamento, idioma, genero, contagemVisualizacao);
        this.duracao = duracao;
    }

    //MUDAR ISSO / Ctrl c v
    public int getDuracao() 
    {
		return this.duracao;
	}
  
	public void setDuracao(int duracao) 
    {
		this.duracao = duracao;
	}
    
}
