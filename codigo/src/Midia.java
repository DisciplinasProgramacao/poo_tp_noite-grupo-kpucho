package src;
public class Midia 
{
    private String idMidia;
    private String nome;
    private String dataDeLancamento;
    private String idioma; // Falta Implementar a Lista de idiomas / certos idiomas pré-selecionados que podem ser utilizados, conforme a primeira versão do diagrama 
    private String genero; // Falta Implementar a Lista de generos / certos gêneros pré-selecionados que podem utilizadas, conforme a primeira versão do diagrama 
    private int contagemVisualizacao;
    
    private String descricao; // Não implementado

    public Midia(String idMidia, String nome, String dataDeLancamento, String idioma, String genero, int contagemVisualizacao)
    {
        this.idMidia = idMidia;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.idioma = idioma;
        this.genero = genero;
        this.contagemVisualizacao = contagemVisualizacao;
    }


    protected boolean assistirMidia() // Método para registrar a visualização da série
    {
        aumentarContagemVisualizacao();
        return true;
    }

    protected void aumentarContagemVisualizacao() // Método que aumenta em 1 a contagem de visualizalções da série quando for assistida por uma conta
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