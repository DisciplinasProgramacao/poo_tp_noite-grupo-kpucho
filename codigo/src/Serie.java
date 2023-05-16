package src;
public class Serie
{
    private String IdSerie;
    private String nome;
    private String dataDeLancamento;
    private String idioma; // Falta Implementar a Lista de idiomas / certos idiomas pré-selecionados que podem ser utilizados, conforme a primeira versão do diagrama 
    private String genero; // Falta Implementar a Lista de generos / certos gêneros pré-selecionados que podem utilizadas, conforme a primeira versão do diagrama 

    
    private String descricao; // Não implementado
    private int contagemVisualizacao; 
    
    public Serie(String IdSerie, String nome, String dataDeLancamento,String idioma, String genero)
    {
        this.IdSerie = IdSerie;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.idioma = idioma;
        this.genero = genero;

        Aplicativo.adicionarSerie(this); // Adicionando a série no Hashmap do aplicativo logo após  seu construtor ser chamado
    }

    protected boolean assitirSerie() // Método para registrar a visualização da série
    {
        aumentarContagemVisualizacao();
        return true;
    }

    private void aumentarContagemVisualizacao() // Método que aumenta em 1 a contagem de visualizalções da série quando for assistida por uma conta
    {
        this.contagemVisualizacao++;       
    }

    public int getContagemVisualizacao() 
    {
        return this.contagemVisualizacao;
    }
    
    public String getNome()
    {
        return this.nome;
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

    public String getIdSerie()
    {
        return this.IdSerie;
    }
}
