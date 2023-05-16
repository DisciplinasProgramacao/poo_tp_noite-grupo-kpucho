package src;
import java.util.*;
import java.io.*;

public class Aplicativo  
{

    private static HashMap<String, Conta> Contas;
    private static HashMap<String , Serie> Series;
    private static Conta contaAtual;
   
    public Aplicativo() throws IOException
    {
        Contas = new HashMap<>();
        Series = new HashMap<>();
        Contas = carregarArquivoEspectadores();
        Series = carregarArquivoSeries();
        carregarArquivoAudiencia();
    
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

    public static boolean realizarLogin(String login, String senha) // Método para realizar o login em uma conta em que as credencias são validas (login e senha). Com isso, o Aplicativo salva essa Conta como conta atual logada no aplicativo
    {
        Conta conta = Aplicativo.buscarConta(login, senha);
        if(conta != null)
        {
            contaAtual = conta; // Armazenando a conta que passou pelo login como conta atual do aplicativo para que possa ser usada em outras operações futuramente 
            return true;
        }
        return false;
    }

    public static boolean realizarLogoff() // Método que realiza um log-off da Conta Atual armazenada no Aplicativo
    {
        contaAtual = null;
        return true;
    }

    private static Conta buscarConta(String login, String senha) // Método que irá buscar por meio do login se a conta existe no Aplicatio. Se existir, é chamado um método da classe Conta, para verificar se a senha, passada por parâmetro, é igual a senha real da conta analisada
    {
        Conta conta  = Contas.get(login);
        if(conta != null && conta.verificaSenha(senha))
        {
            return conta;
        }
        return null;
    }

    


    private static HashMap<String, Serie> carregarArquivoSeries() throws IOException 
    {                                                                                // Método para carregar Series por meio da leitura do arquivo txt 'SériesArq'
        BufferedReader br = new BufferedReader(new FileReader(new File ("./arquivos/POO_Series.csv")));
        String linha ;

        while((linha = br.readLine()) != null)
        {
            final String UTF8_BOM = "\uFEFF"; // Foi adicionado essa condição para retirar o caractere '?' do começo das linhas na hora da leitura do arquivo. (Por algum motivo esse caractere estava aparecendo sem nenhuma razão óbvia)
            if (linha.startsWith(UTF8_BOM)) 
            {
                linha = linha.substring(1);
            }
            String IdSerie = linha.split(";")[0];
            String nomeSerie = linha.split(";")[1];
            String dataLancamento = linha.split(";")[2];

            Serie serieAdicionada = new Serie(IdSerie, nomeSerie, dataLancamento, "null", "null"  ); // Criando uma nova Serie com base na leitura do arquivo

            Series.put(IdSerie, serieAdicionada); // Adicionando essa récem criada série ao Hasmap de Serie
        }
        br.close();
        return Series;
    }


    private static HashMap<String, Conta> carregarArquivoEspectadores() throws IOException
    {                                                          // Método para carregar Contas por meio da leitura do arquivo txt 'Espectadores'
        BufferedReader br = new BufferedReader(new FileReader(new File ("./arquivos/POO_Espectadores.csv")));
        String linha;
        while((linha = br.readLine()) != null)
        {
            final String UTF8_BOM = "\uFEFF"; // Foi adicionado essa condição para retirar o caractere '?' do começo das linhas na hora da leitura do arquivo. (Por algum motivo esse caractere estava aparecendo sem nenhuma razão óbvia)
            if (linha.startsWith(UTF8_BOM)) 
            {
                linha = linha.substring(1);
            }
            String nome = linha.split(";")[0];
            String login = linha.split(";")[1];
            String senha = linha.split(";")[2];

            Conta conta = new Conta(nome, login, senha); // Criando uma nova Conta com base na leitura do arquivo
            Contas.put(login, conta); // Adicionando essa récem criada conta ao Hashmap de Contas
        }
        br.close();
        return Contas;
    }

    private static void carregarArquivoAudiencia() throws IOException
    {                                                          // Método para carregar Contas por meio da leitura do arquivo txt 'Audiência'
        BufferedReader br = new BufferedReader(new FileReader(new File ("./arquivos/POO_Audiencia.csv")));
        String linha;
        String regex = ";";
        String[] campos = null;
    
        while((linha = br.readLine()) != null)
        {
            final String UTF8_BOM = "\uFEFF"; // Foi adicionado essa condição para retirar o caractere '?' do começo das linhas na hora da leitura do arquivo. (Por algum motivo esse caractere estava aparecendo sem nenhuma razão óbvia)
            if (linha.startsWith(UTF8_BOM)) 
            {
                linha = linha.substring(1);
            }

            campos = linha.split(regex);
            
            String login = campos[0];
            String FA = campos[1];
            String IdSerie = campos[2];

            Conta contaf = getConta(login);
          
            Serie serieAudiencia = getSerie(IdSerie);
            Conta conta = Contas.get(login);
                
            if(FA.equals("F")) // Se for igual a F, será armazenado a série na lista de assistir futuramente 
            {
                conta.adicionarSerieEmListaDeAssistirFuturamentePorId(IdSerie);
            }
            else if (FA.equals("A")) // Se for igual a A, será armazenado a série na lista séries assistidas
            {
                serieAudiencia.assitirSerie(); // Chamando o método 'assistirSerie()' para aumentar a visualização da série
                conta.adicionarEmListaDeSeriesJaAssistidas(serieAudiencia); // METODO 'adicionarEmListaDeSeriesJaAssistidas' PRECISA DE REVISÃO
            } 
        
        }
    }
     

    public static void adicionarSerie(Serie novaSerie) // Adiciona a série no Hasmap do aplicativo
    {
        Series.put(novaSerie.getIdSerie(), novaSerie);
    }




    public static Serie buscarSeriePorId(String idSerie) // Método que irá buscar se uma série existe ou não no aplicativo pelo Id da série 
    {
        Serie serieProcurada = Series.get(idSerie);
        if(serieProcurada != null) // Verificando no Hasmap se a chave (idSerie), passado por parametro, do objeto existe  
        {
            return serieProcurada;
        }
        System.out.println("Serie não existe:" +idSerie);
        return null;
    }

    public static Serie buscarSeriePorNome(String nomeSerie) // Método que busca uma Série no Hasmap de série pelo nome e retorna a Serie se esta existir
    {
        for(Serie serie : Series.values())
        {
            if(serie.getNome().equals(nomeSerie))
            {
                return serie;
            }
        }
        System.out.println("Serie não existe:" +nomeSerie);
        return null;
    }


    public static List<Serie> buscarSeriesPorIdioma(String idiomaSerie) // Método que busca todas as séries do aplicativo pelo idioma passado por parâmetro e retorna uma lista de séries filtradas por esse idioma
    {
        List<Serie> seriesFiltradasPorIdioma = new ArrayList<>();

        for(Serie serie : Series.values())
        {
            if(serie.getIdioma().equals(idiomaSerie))
            {
                seriesFiltradasPorIdioma.add(serie);
            }
        }
        return seriesFiltradasPorIdioma;
    }


    public static List<Serie> buscarSeriesPorGenero(String generoSerie) // Método que busca todas as séries do aplicativo pelo gênero passado por parâmetro e retorna uma lista de séries filtradas por esse gênero
    {
        List<Serie> seriesFiltradasPorGenero = new ArrayList<>();
        for(Serie serie : Series.values())
        {
            if(serie.getIdioma().equals(generoSerie))
            {
                seriesFiltradasPorGenero.add(serie);
            }
        }
        return seriesFiltradasPorGenero;
    }

    
    

    

    public static Conta getConta(String login)  // Método para acessar o Hashmap de Contas e devolver aquela que possui o login passado por parâmetro. Método criado para acesso as contas no main
    {
        return Contas.get(login);
    }

    public static Serie getSerie(String IdSerie)  // Método para acessar o Hashmap de Series e devolver aquela que possui o nome da série passado por parâmetro. Método criado para acesso as séries no main
    {
        return Series.get(IdSerie);
    }
    
}


