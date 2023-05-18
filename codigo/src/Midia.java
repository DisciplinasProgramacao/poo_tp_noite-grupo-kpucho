package src;
import java.util.*;
public class Midia 
{
    protected String idMidia;
    protected String nome;
    protected String dataDeLancamento;
    protected String idioma; 
    protected String genero; 
    protected int contagemVisualizacao;
    
    protected static final String[] IDIOMA = {"Portugues Brasileiro", "Portugues Portugal", "Ingles", "Espanhol", "Frances"};
    protected static final String[] GENERO = {"Ficcao-cientifica", "Aventura", "Fantasia", "Comedia", "Acao"};


    protected String descricao; // Não implementado

    public Midia(String idMidia, String nome, String dataDeLancamento, int contagemVisualizacao)
    {
        this.idMidia = idMidia;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.contagemVisualizacao = contagemVisualizacao;

        geraIdiomaGeneroAleatorio(); // Chamando o método para gerar Gênero e Idioma aleatório para essa Mídia

    }

    private void geraIdiomaGeneroAleatorio() // Gera um Idioma e um Genero aleatório para essa mídia
    {
        Random rd = new Random();
        int generoAleatorio = rd.nextInt(5);
        int idiomaAleatorio = rd.nextInt(5);

        this.genero = GENERO[generoAleatorio];
        this.idioma = IDIOMA[idiomaAleatorio];
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

    public boolean setGenero(String genero)
    {
        this.genero = genero;
        return true;
    }

    public String getIdMidia()
    {
        return this.idMidia;
    }

    public String toString()
    {
        return "ID: "+this.idMidia+" | Nome: "+this.nome+" | Data de Lançamento: "+this.dataDeLancamento+" | Idioma: "+this.idioma+" | Gênero: "+this.genero+" | Visualizações: "+contagemVisualizacao+" |";
    }
}
