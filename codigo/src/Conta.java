package src;
import java.util.*;

public class Conta 
{

    private String nome;
    private String login;
    private String senha;

    private List<Serie> ListaSeriesAssistirFuturamente; 
    private List<Serie> ListaSeriesJaAssistidas; 

    private Serie serieAtual; 
    
    public Conta(String nome, String login, String senha)
    {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        Aplicativo.criarConta(this); // Chama o método criarConta do aplicativo, passando o objeto criado dessa classe como parâmetro para ser adicionado ao Hasmap de Contas presente no Aplicativo

        
        
        this.ListaSeriesAssistirFuturamente = new ArrayList<>(); // Criando uma lista de series para assistir futuramente. Cada conta vai ter uma lista individual e diferente por isso o uso do .this
        this.ListaSeriesJaAssistidas = new ArrayList<>();
    }


    public boolean buscarSerieNoAplicativoPorNome(String nomeSerie) // Metodo que busca a serie no Hasmap de Series do aplicativo por Nome
    {
       if(Aplicativo.buscarSeriePorNome(nomeSerie)) // Chama método do aplicativo, para verificar se existe a série que está sendo buscada no Hashmap de Serie
       {
        this.serieAtual = armazenaSerie(nomeSerie); // Chamando o metodo armazenaSerie, para que fique salvo nessa classe Conta a série que o usuário está pesquisando e conseguir usar esse objeto em outros métodos, como 'adicionarSerieEmlistaDeAssistirFuturamente'
        return true;
       }

       return false;
    }

    public List<Serie> buscarSeriesNoAplicativoPorIdioma(String idiomaSerie) // Método que busca todas as séries do aplicativo pelo idioma, passado por parâmetro, e retorna uma lista de séries filtradas por esse idioma
    {

        return Aplicativo.buscarSeriesPorIdioma(idiomaSerie);
    }

    public List<Serie> buscarSeriesNoAplicativoPorGenero(String generoSerie) // Método que busca todas as séries do aplicativo pelo gênero, passado por parâmetro, e retorna uma lista de séries filtradas por esse gênero
    {
        return Aplicativo.buscarSeriesPorGenero(login);
    }

    public boolean buscarSerieEmSuaListaSeriesAssistirFuturamente(String nomeSerie) // Método que verifica e busca se uma série existente na lista de series para assitir futuramente, por meio do nome da série procurada
    {
        for(Serie serie : this.ListaSeriesAssistirFuturamente) // For each para verificar toda a lista
        {
            if(serie.getNome().equals(nomeSerie)) // Comparando se o nome da série, passado por parâmetro, está na lista
            {
                System.out.println("Serie Encontrada!");
                return true;
            }
        }
        System.out.println("Serie nao Encontrada!");
        return false;
    }

    public boolean buscarSerieEmSuaListaSeriesAssistidas(String nomeSerie) // Método que verifica e busca se uma série existente na lista de series assistidas, por meio do nome da série procurada
    {
        for(Serie serie : this.ListaSeriesJaAssistidas) // For each para verificar toda a lista
        {
            if(serie.getNome().equals(nomeSerie)) // Comparando se o nome da série, passado por parâmetro, está na lista
            {
                System.out.println("Serie Encontrada!");
                return true;
            }
        }
        System.out.println("Serie nao Encontrada!");
        return false;
    }



    public boolean assistirSerie(String nomeSerie) // Método para assitir uma série. Esse método verifica se a série existe dentro do aplicativo e, se existir, aumenta sua contagem de visualizações
    {
        if(buscarSerieNoAplicativoPorNome(nomeSerie)) // Condição verificando se o nome da série passado por parâmetro existe dentro do Hasmap de Series
        {
            
            serieAtual.assitirSerie(); // Chama o método para aumentar a contagem de visualização em 1
            if(!ListaSeriesJaAssistidas.contains(serieAtual)) // Colocando uma condição para verificar se a Serie que foi assitida já está na lista de series assistidas, para assim não ser adicionada novamente
            {
                adicionarEmListaDeSeriesJaAssistidas(serieAtual);
            }
            return true;
        }
        System.out.println("Serie nao encontrada!");
        return false;
     
    }
    
    private Serie armazenaSerie(String nomeSerie) // Método que armazena a série atual que o usuário está executando operações
    {
       return Aplicativo.retornaSerieExistente(nomeSerie); // Cria uma série temporária para poder acessar seus métodos privados.Exemplo: aumentar a contagem de visualização em 1 da Serie assistida e assitir essa série
        
    }

    public String retornaListaSeriesJaAssistidas() // Método que retorna todas as séries na lista de séries já assistidas 
    {
        if(this.ListaSeriesJaAssistidas.isEmpty()) // Verifica se a lista de series assistidas possui alguma série assistida
        {
            return "";

        }
        StringBuilder sb = new StringBuilder();
        for(Serie serieAssistida: this.ListaSeriesJaAssistidas) // Verifica cada série da lista para adicioná-la em uma String para impressão ao usuário
        {
            sb.append(serieAssistida.getNome()).append("/ ");
        }
        return sb.toString();
    }


    public String retornaListaSeriesAssistirFuturamente() // Mesmo comportamento que o metodo 'retornaListaSeriesJaAssistidas()', porém, verifica e retorna a lista de séries para assitir futuramente
    {
        if(this.ListaSeriesJaAssistidas.isEmpty())
        {
           
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(Serie serieParaAdicionar: this.ListaSeriesAssistirFuturamente)
        {
            sb.append(serieParaAdicionar.getNome()).append("/ ");
        }
        return sb.toString();
    }



    public boolean adicionarSerieEmListaDeAssistirFuturamente(String nomeSerieAdicionada) // Método que adiciona uma serie em uma lista de séries para assistir futuramente
    {
        if(buscarSerieNoAplicativoPorNome(nomeSerieAdicionada)) // Verifica se a série que foi passada por parâmetro existe no aplicativo
        {
            this.ListaSeriesAssistirFuturamente.add(serieAtual); 
            return true;
        }
        return false;
    }

    

    public boolean removerSerieEmListaDeAssistirFuturamente(String nomeSerieRemovida) // Método que remove uma serie da lista de assitir futuramente, se essa série existir
    {
        if(buscarSerieNoAplicativoPorNome(nomeSerieRemovida))
        {
            this.ListaSeriesAssistirFuturamente.remove(serieAtual);
            return true;
        }
        return false;
    }

    public void adicionarEmListaDeSeriesJaAssistidas(Serie serieAdicionada) // Metodo que adiciona automaticamente uma Serie que foi assistida pelo método 'assistirSerie()' na lista de séries para assistir futuramente
    {
        this.ListaSeriesJaAssistidas.add(serieAdicionada);
    }

    public String getLogin()
    {
        return this.login;
    }

}





