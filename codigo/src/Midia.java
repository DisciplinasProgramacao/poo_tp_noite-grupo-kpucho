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
    protected TipoMidia tipo;
    protected List<String> Comentarios = new ArrayList<>();

    protected HashMap<Conta, Integer> Avaliacoes = new HashMap<>();
    protected double mediaAvaliacoes = -1;
    
    protected static final String[] GENERO = {"Acao", "Anime", "Aventura", "Comedia", "Documentario", "Drama", "Policial", "Romance", "Suspense"};
    protected static final String[] IDIOMA = {"Ingles", "Portugues", "Alemao", "Espanhol", "Turco"};


    protected String descricao; // Não implementado

    public Midia(String idMidia, String nome, String dataDeLancamento, int contagemVisualizacao)
    {
        this.idMidia = idMidia;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.contagemVisualizacao = contagemVisualizacao;
        geraIdiomaGeneroAleatorio(); // Chamando o método para gerar Gênero e Idioma aleatório para essa Mídia
        geraMidiaLancamentoAleatorio(); // Chamando o método para gerar aleatóriamente se a Mída é um Lançamento
    }

    public void avaliarMidia(int avaliacaoUsuario, Conta contaAtual)
    {
        Avaliacoes.put(contaAtual, avaliacaoUsuario);
        calculaMediaAvaliacoes();
    }

    public void registrarComentario(String comentario, String conta)
    {
        String comentarioFinal = "\nConta: "+conta+" Comentário: "+comentario+"\n";
        Comentarios.add(comentarioFinal);
    }

    public boolean verificacaoContaAvaliada(Conta conta)
    {
        
        if(Avaliacoes.get(conta) == null)
        {
            return true;
        }
        return false;
    }

    private void calculaMediaAvaliacoes() // Método privado para calcular a média das avaliações dessa midia
    {
        int contador = 0;
        double avalicaoMediaTemporaria = 0;
        this.mediaAvaliacoes = 0;
        for(int avalicao : Avaliacoes.values())
        {
            contador++; 
            avalicaoMediaTemporaria += avalicao;
        }
        this.mediaAvaliacoes = avalicaoMediaTemporaria / contador;
        this.mediaAvaliacoes = Math.round(this.mediaAvaliacoes * 100.0)/ 100.0;
    }

    private void geraIdiomaGeneroAleatorio() // Gera um Idioma e um Genero aleatório para essa mídia com base nas opções de seu vetor IDIOMA / GENERO
    {
        Random rd = new Random();
        int generoAleatorio = rd.nextInt(9);
        int idiomaAleatorio = rd.nextInt(5);

        this.genero = GENERO[generoAleatorio];
        this.idioma = IDIOMA[idiomaAleatorio];
    }

    private void geraMidiaLancamentoAleatorio() // Gera aleatoriamente se essa mídia irá ser um Lançamento ou não
    {
        Random rd = new Random();
        int lancamentoAleatorio = rd.nextInt(18);

        if(lancamentoAleatorio == 1)
        {
            this.tipo = TipoMidia.LANCAMENTO;
        }
        else
        {
            this.tipo = TipoMidia.PADRAO;
        }
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

    public double getMediaAvaliacao()
    {
        return this.mediaAvaliacoes;
    }


    public String getIdMidia()
    {
        return this.idMidia;
    }
    public String getComentarios()
    {
        StringBuilder sb = new StringBuilder();
        for(String comentario: this.Comentarios)
        {
            sb.append(comentario+("| \n"));
        }
        
        return sb.toString();
    }

    public boolean getLancamento() // Retorna se a mídia é um lançamento ou não
    {
        if(this.tipo == TipoMidia.LANCAMENTO)
        {
            return true;
        }
        return false;
    }
    public String toString()
    {
        if(getLancamento())
        {
            return "LANÇAMENTO |ID: "+this.idMidia+" | Nome: "+this.nome+" | Data de Lançamento: "+this.dataDeLancamento+" | Idioma: "+this.idioma+" | Gênero: "+this.genero+" | Visualizações: "+this.contagemVisualizacao+" | Média de avaliações: "+this.mediaAvaliacoes +" |" +getComentarios();
        }
        return "ID: "+this.idMidia+" | Nome: "+this.nome+" | Data de Lançamento: "+this.dataDeLancamento+" | Idioma: "+this.idioma+" | Gênero: "+this.genero+" | Visualizações: "+this.contagemVisualizacao+" | Média de avaliações: "+this.mediaAvaliacoes+" |" +getComentarios();
    }
}
