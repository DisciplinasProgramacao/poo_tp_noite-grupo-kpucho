package src;

public class Filmes {
    private int IdFilme;
    private String nome;
    private String dataDeLancamento;
    private int duracao;
    private int contagemVisualizacao;

    public Filmes(int idFilme, String nome, String dataDeLancamento, int duracao)
    {
        this.IdFilme = IdFilme;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.duracao = duracao;
    }

    protected boolean assitirFilme() // Método para registrar a visualização da série
    {
        aumentarContagemVisualizacao();
        return true;
    }

    private void aumentarContagemVisualizacao() // Método que aumenta em 1 a contagem de visualizalções da série quando for assistida por uma conta
    {
        this.contagemVisualizacao++;       
    }


}
