package src;
public class Serie extends Midia
{
    private int quantidadeEpisodios;
    
    
    public Serie(String IdSerie, String nome, String dataDeLancamento, int quantidadeEpisodios, int contagemVisualizacao)
    {
        super(IdSerie, nome, dataDeLancamento, contagemVisualizacao);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }


    public int getQuantidadeEpisodios() 
    {
		return this.quantidadeEpisodios;
	}

  
	public void setQuantidadeEpisodios(int quantidadeEpisodios) 
  {
		this.quantidadeEpisodios = quantidadeEpisodios;
	}

}
