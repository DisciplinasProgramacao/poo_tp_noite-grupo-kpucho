package src;
public class Midia 
{
    protected String idMidia;
    protected String nome;
    protected String dataDeLancamento;
    protected String idioma; // Falta Implementar a Lista de idiomas / certos idiomas pré-selecionados que podem ser utilizados, conforme a primeira versão do diagrama 
    protected String genero; // Falta Implementar a Lista de generos / certos gêneros pré-selecionados que podem utilizadas, conforme a primeira versão do diagrama 
    protected int contagemVisualizacao;
    
    protected String descricao; // Não implementado

    public Midia(String idMidia, String nome, String dataDeLancamento, String idioma, String genero, int contagemVisualizacao)
    {
        this.idMidia = idMidia;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.idioma = idioma;
        this.genero = genero;
        this.contagemVisualizacao = contagemVisualizacao;
    }


    protected boolean assistirMidia() // Método para registrar a visualização da Mídia
    {
        aumentarContagemVisualizacao();
        return true;
    }

    protected void aumentarContagemVisualizacao() // Método que aumenta em 1 a contagem de visualizações da mídia quando for assistida por uma conta
    {
        this.contagemVisualizacao++;       
    }

    public String getNome()
    {
        return this.nome;
    }
    
    public int getContagemVisualizacao()
    {
        return this.contagemVisualizacao;
    }

    public String getIdioma()
    {
        return this.idioma;
    }

    public String getGenero()
    {
        return this.genero;
    }

    public boolean setIdioma(String idioma)
    {
        this.idioma = idioma;
        return true;
    }

    public String getIdMidia()
    {
        return this.idMidia;
    }
}
