import java.util.*;
import java.io.*;

public class Aplicativo
{

    private static HashMap<String, Conta> Contas;
    private static HashMap<String , Serie> Series;
   
    public Aplicativo() throws IOException
    {
        Contas = new HashMap<>();
        Series = new HashMap<>();
        Series = carregarArquivoSeries(); 
        Contas = carregarArquivoEspectadores();
   
    }

    public static boolean criarConta(String nome, String login, String senha) // Método que irá criar conta do usuário no Aplicativo 
    {
        if(Contas.containsKey(login)) // Condição para verificar se existe ou não um login associado a uma conta já criada no Aplicativo 
        {
            System.out.println("Conta com o login: "+login+" já existe!");
            return false;
        }
        
            Conta novaConta = new Conta(nome, login, senha); // Cria uma nova conta e adiciona ao Hasmap de contas
            Contas.put(login, novaConta);
            return true;
        
       
    }
    // Public ou Protected - PENSAR
    public static boolean criarConta(Conta conta) 
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


    private static HashMap<String, Serie> carregarArquivoSeries() throws IOException 
    {                                                                                // Método para carregar Series por meio da leitura do arquivo txt 'SériesArq'
        BufferedReader br = new BufferedReader(new FileReader(new File ("./arquivos/SériesArq.txt")));
        String linha ;

        while((linha = br.readLine()) != null)
        {
            String IdSerie = linha.split(";")[0];
            String nomeSerie = linha.split(";")[1];
            String dataLancamento = linha.split(";")[2];

            Serie serieAdicionada = new Serie(IdSerie, nomeSerie, dataLancamento, "null", "null"  ); // Criando uma nova Serie com base na leitura do arquivo

            Series.put(nomeSerie, serieAdicionada); // Adicionando essa récem criada série ao Hasmap de Serie
        }
      
        return Series;
    }


    private static HashMap<String, Conta> carregarArquivoEspectadores() throws IOException
    {                                                          // Método para carregar Contas por meio da leitura do arquivo txt 'Espectadores'
        BufferedReader br = new BufferedReader(new FileReader(new File ("./arquivos/Espectadores.txt")));
        String linha;

        while((linha = br.readLine()) != null)
        {
            String nome = linha.split(";")[0];
            String login = linha.split(";")[1];
            String senha = linha.split(";")[2];

            Conta conta = new Conta(nome, login, senha); // Criando uma nova Conta com base na leitura do arquivo
            Contas.put(login, conta); // Adicionando essa récem criada conta ao Hashmap de Contas

        }
      
        return Contas;
    }


    public static void adicionarSerie(Serie novaSerie) // Adiciona a série no Hasmap do aplicativo
    {
        Series.put(novaSerie.getNome(), novaSerie);
    }

    public static boolean buscarSerie(String nomeSerie) // Método que irá buscar se uma série existe ou não no aplicativo
    {
        if(Series.containsKey(nomeSerie)) // Verificando no Hasmap se a chave (nome), passado por parametro, do objeto existe  
        {
            return true;
        }
        System.out.println("Serie não existe:" +nomeSerie);
        return false;
    }
    
    public static Serie retornaSerieExistente(String nomeSerie) // Método que irá retornar uma Serie existente no Hashmap de Series, para que esse objeto retornado possa ser usado em outros metodos como no 'assistirSerie()'
    {
        if(buscarSerie(nomeSerie))
        {
            Serie serieExistente = Series.get(nomeSerie);
            return serieExistente;
        }
        return null;
    }

    public Conta getConta(String login)  // Método para acessar o Hashmap de Contas e devolver aquela que possui o login passado por parâmetro. Método criado para acesso as contas no main
    {
        return Contas.get(login);
    }
}




