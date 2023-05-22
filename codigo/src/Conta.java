package src;
import java.util.*;
import java.time.*;

public class Conta 
{

    private String nome;
    private String login;
    private String senha;
    private boolean contaEspecialista;

    private List<Midia> ListaMidiasAssistirFuturamente; 
    private List<Midia> ListaMidiasJaAssistidas; 

    private Map<Midia, Avaliacao> avaliacoes = new HashMap<>();

    private Midia midiaAtual; // Objeto que armazena qual objeto da classe Midia a conta está manipulando (Criado para rapidez/facilidade em algumas operações)
    
    public Conta(String nome, String login, String senha)
    {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.contaEspecialista = false;

        this.ListaMidiasAssistirFuturamente = new ArrayList<>(); // Criando uma lista de Midias para assistir futuramente.
        this.ListaMidiasJaAssistidas = new ArrayList<>();  // Criando uma lista de Midias já assistidas.
       
    }

    public boolean verificaSenha(String senha) // Método que verifica se 'senha' passada por parâmetro é igual a do objeto da classe Conta, que se está tentando fazer login 'Aplicativo.realizarLogin()'
    {
        if(this.senha.equals(senha))
        {
            return true;
        }
        return false;   
    }

    public boolean buscarMidiaNoAplicativoPorNome(String midiaNome) // Método que busca um objeto da classe Midia no 'Hashmap<Midia> Midias' por meio do seu nome.
    {
       Midia midiaProcurada = Aplicativo.buscarMidiaPorNome(midiaNome);
       if(midiaProcurada != null) 
       {       
        this.midiaAtual = armazenaMidia(midiaProcurada.getIdMidia()); // Chamando o metodo 'armazenaMidia()', para que fique salvo nesse objeto da classe Conta a mídia que o usuário está pesquisando e realizando operações e assim, conseguir usar essa mídia em outros métodos, como no método 'adicionarMidiaEmlistaDeAssistirFuturamente'
        return true;
       }

       return false;
    }

    public List<String> buscarMidiasNoAplicativoPorIdioma(String midiaIdioma) // Método que busca todas as mídias do aplicativo pelo idioma passado por parâmetro e retorna uma lista de objetos da classe Midia, filtradas por esse idioma
    {
        return Aplicativo.buscarMidiasPorIdioma(midiaIdioma);
    }

    public List<String> buscarMidiasNoAplicativoPorGenero(String midiaGenero) // Método que busca todas as mídias do aplicativo pelo gênero passado por parâmetro e retorna uma lista de objetos da classe Midia, filtradas por esse gênero
    {
        return Aplicativo.buscarMidiasPorGenero(midiaGenero);
    }

    public boolean buscarMidiaEmSuaListaAssistirFuturamente(String nomeMidia) // Método que verifica e busca se uma mídias existente na lista 'List<Midia> ListaMidiasAssistirFuturamente', por meio do nome da mídia procurada
    {
        for(Midia midia : this.ListaMidiasAssistirFuturamente) // For each para verificar toda a lista
        {
            if(midia.getNome().equals(nomeMidia)) // Comparando se o nome da mídia, passada por parâmetro, está na lista
            {
                System.out.println("Midia Encontrada!");
                return true;
            }
        }
        System.out.println("Midia nao Encontrada!");
        return false;
    }

    public boolean buscarMidiaEmSuaListaAssistidas(String nomeMidia) // Método que verifica e busca se uma mídia existente na lista 'List<Midia> ListaMidiasJaAssistidas', por meio do nome da mídia procurada
    {
        for(Midia midia : this.ListaMidiasJaAssistidas) // For each para verificar toda a lista
        {
            if(midia.getNome().equals(nomeMidia)) // Comparando se o nome da mídia, passada por parâmetro, está na lista
            {
                System.out.println("Midia Encontrada!");
                return true;
            }
        }
        System.out.println("Midia nao Encontrada!");
        return false;
    }

    public boolean assistirMidia(String nomeMidia) // Método para assitir uma mídia. Esse método verifica se a mídia existe dentro do aplicativo e, se existir, aumenta sua contagem de visualizações
    {
        if(buscarMidiaNoAplicativoPorNome(nomeMidia)) // Condição verificando se o nome da mídia, passado por parâmetro, existe dentro do 'Hashmap<Midia> Midias' 
        {
            
            midiaAtual.assistirMidia(); // Chama o método 'assistirMidia()' da mídia encontrada na busca para aumentar a sua contagem de visualização em 1
            if(!ListaMidiasJaAssistidas.contains(midiaAtual)) // Colocando uma condição para verificar se a Mídia, que foi assitida, já está na lista 'List<Midia> ListaMidiasJaAssistidas', para assim não ser adicionada novamente
            {
                adicionarEmListaDeMidiasJaAssistidas(midiaAtual);
            }
            return true;
        }
        return false;
     
    }

    public boolean avaliarMidia(String nomeMidia, int avaliacaoUsuario, LocalDate dataAvaliacao) // Método que avalia uma Mídia, encontrada pelo seu nome
    {
       
        if(buscarMidiaNoAplicativoPorNome(nomeMidia))
        {
            if(midiaAtual.verificacaoContaAvaliada(this))
            {
                Avaliacao avaliacao = new Avaliacao(midiaAtual, dataAvaliacao);
                avaliacoes.put(midiaAtual, avaliacao);

                midiaAtual.avaliarMidia(avaliacaoUsuario, this, false);
                System.out.println(verificaEspecialista());
                return true;
            }
            else
            {
                return false;
            }

        }

        return true;
    }

    public boolean verificaEspecialista()
    {
        LocalDate hoje = LocalDate.now();
        YearMonth mesAnterior = YearMonth.from(hoje.minusMonths(1));

        int avaliacoesMesAnterior = 0;
        for(Avaliacao avaliacao : avaliacoes.values())
        {
            if(verificaMesAnterior(avaliacao.getDataAvaliacao(), mesAnterior))
            {
                avaliacoesMesAnterior++;
            }
        }

       this.contaEspecialista = avaliacoesMesAnterior >= 5;
       return this.contaEspecialista;
    }

    private boolean verificaMesAnterior(LocalDate data, YearMonth mesAnterior)
    {
        YearMonth dataYearMonth = YearMonth.from(data);
        return dataYearMonth.equals(mesAnterior);

    }
    
    private Midia armazenaMidia(String idMidia) // Método que armazena a mídia atual que o usuário está executando operações (como 'buscarMidiaNoAplicativoPorNome()')
    {
       return Aplicativo.buscarMidiaPorId(idMidia); // Cria uma mídia temporária para poder acessar seus métodos privados.Exemplo: aumentar a contagem de visualização em 1 da Mídia assistida e assitir essa mídia
        
    }

    public String retornaListaMidiasJaAssistidas() // Método que retorna uma String contendo todas as mídias na lista 'List<Midia> ListaMidiasJaAssistidas'
    {
        if(this.ListaMidiasJaAssistidas.isEmpty()) // Verifica se a lista de mídias assistidas possui alguma mídia assistida
        {
            return "";

        }
        StringBuilder sb = new StringBuilder();
        for(Midia midiaAssistida: this.ListaMidiasJaAssistidas) // Verifica cada mídia da lista para adicioná-la em uma String para impressão ao usuário
        {
            sb.append(midiaAssistida.getNome()).append("/ ");
        }
        return sb.toString();
    }


    public String retornaListaMidiasAssistirFuturamente() // Mesmo comportamento que o metodo 'retornaListaMidiasJaAssistidas()', porém, verifica e retorna a lista de mídias para assitir futuramente
    {
        if(this.ListaMidiasAssistirFuturamente.isEmpty())
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(Midia midiaParaAdicionar: this.ListaMidiasAssistirFuturamente)
        {
            sb.append(midiaParaAdicionar.getNome()).append("/ ");
        }
        return sb.toString();
    }



    public boolean adicionarMidiaEmListaDeAssistirFuturamente(String nomeMidiaAdicionada) // Método que adiciona uma mídia em uma lista de mídias para assistir futuramente
    {
        if(buscarMidiaNoAplicativoPorNome(nomeMidiaAdicionada)) // Verifica se a mídia que foi passada por parâmetro existe no aplicativo
        {
            this.ListaMidiasAssistirFuturamente.add(midiaAtual); 
            return true;
        }
        return false;
    }


    public boolean adicionarMidiaEmListaDeAssistirFuturamentePorId(String idMidiaAdicionada) // Método que adiciona uma mídia (se essa existir), encontrada pelo seu 'idMidia', na lista 'List<Midia> ListaMidiasAssistirFuturamente'
    {
        Midia midiaProcurada = Aplicativo.buscarMidiaPorId(idMidiaAdicionada);
        if(midiaProcurada != null ) 
        {
            this.ListaMidiasAssistirFuturamente.add(midiaProcurada); 
            return true;
        }
        return false;
    }

    

    public int removerMidiaEmListaDeAssistirFuturamente(String nomeMidia) // Método que remove uma mídia (se essa existir) da lista 'List<Midia> ListaMidiasAssistirFuturamente'
    {
        if(buscarMidiaNoAplicativoPorNome(nomeMidia))
        {
            if(this.ListaMidiasAssistirFuturamente.isEmpty())
            {
                return 0;
            }
            this.ListaMidiasAssistirFuturamente.remove(midiaAtual);
            return 1;
        }
        return -1;
    }

    protected void adicionarEmListaDeMidiasJaAssistidas(Midia midiaAdicionada) // Metodo que adiciona automaticamente uma mídia, que foi assistida pelo método 'assistirMidia()', na lista 'List<Midia> ListaMidiasAssistirFuturamente'
    {
        this.ListaMidiasJaAssistidas.add(midiaAdicionada);
    }

    public String getLogin()
    {
        return this.login;
    }

}



