package src;
public class Serie extends Midia
{
    private int quantidadeEpisodios;
    
    private String descricao; // Não implementado
    
    public Serie(String IdSerie, String nome, String dataDeLancamento,String idioma, String genero, int quantidadeEpisodios, int contagemVisualizacao)
    {
        super(IdSerie, nome, dataDeLancamento, idioma, genero, contagemVisualizacao);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    
    protected boolean assitirSerie() // Método para registrar a visualização da série
    {
        aumentarContagemVisualizacao();
        return true;
    }

    //MUDAR ISSO / Ctrl c v
    public int getQuantidadeEpisodios() {
		return this.quantidadeEpisodios;
	}

  
	public void setQuantidadeEpisodios(int quantidadeEpisodios) {
		this.quantidadeEpisodios = quantidadeEpisodios;
	}



}
