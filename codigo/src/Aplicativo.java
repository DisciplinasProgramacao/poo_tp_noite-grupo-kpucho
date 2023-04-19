import java.util.*;

public class Aplicativo
{

    private static Aplicativo instanciaUnica; // Criando uma variável estática de instância única da classe Aplicativo / Técnica 'Singleton'

    private HashMap<String, Conta> Contas;
    private HashMap<String , Serie> Series;
   
    private Aplicativo() // Construtor privado para impedir a criação de múltiplas instâncias, já que só existe um aplicativo
    {
        Contas = new HashMap<>();
        Series = new HashMap<>();
   
    }



    public static Aplicativo getInstance() // Método estático para obter a instância única do aplicativo    
    {
        if(instanciaUnica == null)
        {
            instanciaUnica = new Aplicativo();
        }
        return instanciaUnica;
    }


    protected boolean criarConta(String email, String login, int senha) // Método que irá criar conta do usuário no Aplicativo 
    {
        if(Contas.containsKey(login)) // Condição para verificar se existe ou não um login associado a uma conta já criada no Aplicativo 
        {
            System.out.println("Conta com o login: "+login+" já existe!");
            return false;
        }
        
            Conta novaConta = new Conta(login, email, senha); // Cria uma nova conta e adiciona ao Hasmap de contas
            Contas.put(login, novaConta);
            return true;
        
       
    }
    // Public ou Protected - PENSAR
    public boolean criarConta(Conta conta) 
    {
        if(Contas.containsKey(conta.getLogin())) // Condição para verificar se existe ou não um login associado a uma conta já criada no Aplicativo 
        {
            System.out.println("Conta com o login: "+conta.getLogin()+" já existe!");
            return false;
        }
         // adiciona a conta ao Hasmap de contas
        Contas.put(conta.getLogin(), conta);
         return true;
    }

    public void adicionarSerie(Serie novaSerie) // Adiciona a série no Hasmap do aplicativo
    {
        Series.put(novaSerie.getNome(), novaSerie);
    }

    public boolean buscarSerie(String nomeSerie) // Método que irá buscar se uma série existe ou não no aplicativo
    {
        if(Series.containsKey(nomeSerie)) // Verificando no Hasmap se a chave (nome), passado por parametro, do objeto existe  
        {
            System.out.println("Série existente: "+nomeSerie);
            return true;
        }
        System.out.println("Serie não existe:" +nomeSerie);
        return false;
    }
    
    public Serie retornaSerieExistente(String nomeSerie) // Método que irá retornar uma Serie existente no Hashmap de Series, para que esse objeto retornado possa ser usado em outros metodos como no 'assistirSerie()'
    {
        if(buscarSerie(nomeSerie))
        {
            Serie serieExistente = Series.get(nomeSerie);
            return serieExistente;
        }
        return null;
    }
}





