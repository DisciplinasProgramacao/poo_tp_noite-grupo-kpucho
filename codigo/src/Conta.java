package src;
import java.util.*;

public class Conta 
{

    private String nome;
    private String login;
    private String senha;

    private List<Midia> ListaMidiasAssistirFuturamente; 
    private List<Midia> ListaMidiasJaAssistidas; 

    private Midia midiaAtual; 
    
    public Conta(String nome, String login, String senha)
    {
        this.nome = nome;
        this.login = login;
        this.senha = senha;

        this.ListaMidiasAssistirFuturamente = new ArrayList<>(); // Criando uma lista de Midias para assistir futuramente. Cada conta vai ter uma lista individual e diferente por isso o uso do .this
        this.ListaMidiasJaAssistidas = new ArrayList<>();
    }

    public boolean verificaSenha(String senha) // Método que verifica se senha passada por parâmetro é igual a da conta que está se tentando fazer login no método Aplicativo.realizarLogin();
    {
        if(this.senha.equals(senha))
        {
            return true;
        }
        return false;   
    }

    public boolean buscarMidiaNoAplicativoPorNome(String midiaNome) // Metodo que busca a mídia no Hasmap de Midias do aplicativo pelo Nome
    {
       Midia midiaProcurada = Aplicativo.buscarMidiaPorNome(midiaNome);// Chama o método de buscar uma mídia pelo nome do aplicativo, para verificar se existe a mídia que está sendo buscada no Hashmap de Mídia
       if(midiaProcurada != null) 
       {       
        this.midiaAtual = armazenaMidia(midiaProcurada.getIdMidia()); // Chamando o metodo 'armazenaMídia', para que fique salvo nessa classe Conta a mídia que o usuário está pesquisando e assim, conseguir usar essa mídia em outros métodos, como no método 'adicionarMidiaEmlistaDeAssistirFuturamente'
        return true;
       }

       return false;
    }

    public List<Midia> buscarMidiasNoAplicativoPorIdioma(String midiaNome) // Método que busca todas as mídias do aplicativo pelo idioma, passado por parâmetro, e retorna uma lista de mídias filtradas por esse idioma
    {

        return Aplicativo.buscarMidiasPorIdioma(midiaNome);
    }

    public List<Midia> buscarMidiasNoAplicativoPorGenero(String midiaGenero) // Método que busca todas as mídias do aplicativo pelo gênero, passado por parâmetro, e retorna uma lista de mídias filtradas por esse gênero
    {
        return Aplicativo.buscarMidiasPorGenero(midiaGenero);
    }

    public boolean buscarMidiaEmSuaListaSeriesAssistirFuturamente(String nomeMidia) // Método que verifica e busca se uma mídias existente na lista de mídias para assitir futuramente, por meio do nome da mídia procurada
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

    public boolean buscarMidiaEmSuaListaSeriesAssistidas(String nomeMidia) // Método que verifica e busca se uma mídia existente na lista de míddas assistidas, por meio do nome da mídia procurada
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
        if(buscarMidiaNoAplicativoPorNome(nomeMidia)) // Condição verificando se o nome da mídia, passado por parâmetro, existe dentro do Hasmap de Mídias
        {
            
            midiaAtual.assistirMidia(); // Chama o método para aumentar a contagem de visualização em 1
            if(!ListaMidiasJaAssistidas.contains(midiaAtual)) // Colocando uma condição para verificar se a Mídia, que foi assitida, já está na lista de mídias assistidas, para assim não ser adicionada novamente
            {
                adicionarEmListaDeMidiasJaAssistidas(midiaAtual);
            }
            return true;
        }
        System.out.println("Midia nao encontrada!");
        return false;
     
    }
    
    private Midia armazenaMidia(String idMidia) // Método que armazena a mídia atual que o usuário está executando operações
    {
       return Aplicativo.buscarMidiaPorId(idMidia); // Cria uma mídia temporária para poder acessar seus métodos privados.Exemplo: aumentar a contagem de visualização em 1 da Mídia assistida e assitir essa mídia
        
    }

    public String retornaListaMidiasJaAssistidas() // Método que retorna todas as mídias na lista de mídias já assistidas 
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
        if(this.ListaMidiasJaAssistidas.isEmpty())
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


    public boolean adicionarMidiaEmListaDeAssistirFuturamentePorId(String idMidiaAdicionada) // Método que adiciona uma mídia em uma lista de mídias para assistir futuramente
    {
        Midia midiaProcurada = Aplicativo.buscarMidiaPorId(idMidiaAdicionada);
        if(midiaProcurada != null ) 
        {
            this.ListaMidiasAssistirFuturamente.add(midiaProcurada); 
            return true;
        }
        return false;
    }

    

    public boolean removerMidiaEmListaDeAssistirFuturamente(String nomeMidia) // Método que remove uma mídia da lista de assistir futuramente, se essa mídia existir
    {
        if(buscarMidiaNoAplicativoPorNome(nomeMidia))
        {
            this.ListaMidiasAssistirFuturamente.remove(midiaAtual);
            return true;
        }
        return false;
    }

    public void adicionarEmListaDeMidiasJaAssistidas(Midia midiaAicionada) // Metodo que adiciona automaticamente uma mídia, que foi assistida pelo método 'assistirMidia()', na lista de mídias para assistir futuramente
    {
        this.ListaMidiasJaAssistidas.add(midiaAicionada);
    }

    public String getLogin()
    {
        return this.login;
    }

}



