
public class Serie
{
    private String  nome;
    private String idioma; // Falta Implementar a Lista de idiomas / certos idiomas pré-selecionados que podem ser utilizados, conforme a primeira versão do diagrama 
    private String genero; // Falta Implementar a Lista de generos / certos gêneros pré-selecionados que podem utilizadas, conforme a primeira versão do diagrama 

    
    private String descricao; // Não implementado
    private int contagemVisualizacao; 
    
    public Serie(String nome, String idioma, String genero)
    {
        this.nome = nome;
        this.idioma = idioma;
        this.genero = genero;

        Aplicativo.adicionarSerie(this); // Adicionando a série no Hashmap do aplicativo logo após  seu construtor ser chamado
    }
    
    protected boolean assitirSerie() // Método para registrar a visualização da série
    {
        aumentarContagemVisualizacao(); // Chamando o metodo que irá adicionar visualizações na série assistida
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
}
