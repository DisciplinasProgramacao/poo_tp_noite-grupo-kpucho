package src;
import java.util.*;
import java.io.*;

public class Aplicativo  
{

    private static HashMap<String, Conta> Contas;
    private static HashMap<String , Midia> Midias;
    private static Conta contaAtual; // AINDA SEM APLICAÇÃO
   
    public Aplicativo() throws IOException
    {
        Contas = new HashMap<>();
        Midias = new HashMap<>();
        carregarArquivoEspectadores();
        carregarArquivoSeries();
        carregarArquivoFilmes();
        carregarArquivoAudiencia();
    
    }

    public static boolean criarConta(String nome, String login, String senha) // Método que irá criar conta do usuário no Aplicativo 
    {
        if(Contas.containsKey(login)) // Condição para verificar se existe ou não um login associado a uma conta já criada no Aplicativo 
        {
            System.out.println("Conta com o login: "+login+" já existe!");
            return false;
        }
        Conta novaConta = new Conta(nome, login, senha); // Cria uma nova conta se não existir e, a adiciona ao Hasmap de contas
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

    private static void carregarArquivoSeries() throws IOException  // Método para carregar Series/Midias por meio da leitura do arquivo csv 'POO_Series.csv'
    {                                                                               
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

            Serie serieAdicionada = new Serie(IdSerie, nomeSerie, dataLancamento, "","",-1,-1); // Criando uma nova Serie com base na leitura do arquivo

            Midias.put(IdSerie, serieAdicionada); // Adicionando essa récem criada série ao Hasmap de Serie
        }
        br.close();
    }

    private static void carregarArquivoFilmes() throws IOException  // Método para carregar Filmes/Midias por meio da leitura do arquivo csv 'POO_Series.csv'
    {                                                                               
        BufferedReader br = new BufferedReader(new FileReader(new File ("./arquivos/POO_Filmes.csv")));
        String linha ;

        while((linha = br.readLine()) != null)
        {
            final String UTF8_BOM = "\uFEFF"; // Foi adicionado essa condição para retirar o caractere '?' do começo das linhas na hora da leitura do arquivo. (Por algum motivo esse caractere estava aparecendo sem nenhuma razão óbvia)
            if (linha.startsWith(UTF8_BOM)) 
            {
                linha = linha.substring(1);
            }
            String IdFilme = linha.split(";")[0];
            String nomeFilme = linha.split(";")[1];
            String dataLancamento = linha.split(";")[2];
            String duracaoString = linha.split(";")[3];
            int duracao = Integer.parseInt(duracaoString);

            Filme filmeAdicionada = new Filme(IdFilme, nomeFilme, dataLancamento, duracao,-1); // Criando uma nova Serie com base na leitura do arquivo

            Midias.put(IdFilme, filmeAdicionada); // Adicionando essa récem criada série ao Hasmap de Serie
        }
        br.close();
    }

    private static HashMap<String, Conta> carregarArquivoEspectadores() throws IOException  // Método para carregar Contas por meio da leitura do arquivo csv 'POO_Espectadores'
    {                                                          
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
    {                                                          // Método para carregar dados de Séries e Contas por meio da leitura do arquivo txt 'POO_Audiência'
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

            Midia serieAudiencia = getMidia(IdSerie);
            Conta conta = Contas.get(login);
                
            if(FA.equals("F")) // Se for igual a F, será armazenado a série/mídia na lista de assistir futuramente 
            {
                conta.adicionarMidiaEmListaDeAssistirFuturamentePorId(IdSerie);
            }
            else if (FA.equals("A")) // Se for igual a A, será armazenado a série/mídia na lista séries assistidas
            {
                serieAudiencia.assistirMidia(); // Chamando o método 'assistirMidia()' para aumentar a visualização da Mídia
                conta.adicionarEmListaDeMidiasJaAssistidas(serieAudiencia);
            } 
        
        }
    }
     
    public static void adicionarMidia(Midia novaMidia) // Adiciona a Mídia no Hasmap do aplicativo
    {
        Midias.put(novaMidia.getIdMidia(), novaMidia);
    }

    public static Midia buscarMidiaPorId(String idMidia) // Método que irá buscar se uma Mídia existe ou não no aplicativo pelo Id da Mídia 
    {
        Midia midiaProcurada = Midias.get(idMidia);
        if(midiaProcurada != null) // Verificando no Hasmap se a chave (idMidia), passada por parâmetro, do objeto existe  
        {
            return midiaProcurada;
        }
        System.out.println("Midia não existe:" +idMidia);
        return null;
    }

    public static Midia buscarMidiaPorNome(String nomeMidia) // Método que busca uma Mída no Hasmap de midia pelo nome e retorna a Mídia, se esta existir
    {
        for(Midia midia : Midias.values())
        {
            if(midia.getNome().equals(nomeMidia))
            {
                return midia;
            }
        }
        System.out.println("Midia não existe:" +nomeMidia);
        return null;
    }

    public static List<Midia> buscarMidiasPorIdioma(String idiomaMidia) // Método que busca todas as mídias do aplicativo pelo idioma passado por parâmetro e retorna uma lista de mídias filtradas por esse idioma
    {
        List<Midia> midiasFiltradasPorIdioma = new ArrayList<>();

        for(Midia midia : Midias.values())
        {
            if(midia.getIdioma().equals(idiomaMidia))
            {
                midiasFiltradasPorIdioma.add(midia);
            }
        }
        return midiasFiltradasPorIdioma;
    }

    public static List<Midia> buscarMidiasPorGenero(String generoMidia) // Método que busca todas as mídias do aplicativo pelo gênero passado por parâmetro e retorna uma lista de mídias filtradas por esse gênero
    {
        List<Midia> midiasFiltradasPorGenero = new ArrayList<>();
        for(Midia midia : Midias.values())
        {
            if(midia.getIdioma().equals(generoMidia))
            {
                midiasFiltradasPorGenero.add(midia);
            }
        }
        return midiasFiltradasPorGenero;
    }

    public static Conta getConta(String login)  // Método para acessar o Hashmap de Contas e devolver aquela que possui o login passado por parâmetro. Método criado para acesso as contas no main
    {
        return Contas.get(login);
    }

    public static Midia getMidia(String IdMidia)  // Método para acessar o Hashmap de Midias e retornar o objeto encontrado pela chave de Id da Mídia, passado por parâmetro. Método criado para acesso específico de uma Mídia
    {
        return Midias.get(IdMidia);
    }

    public static Serie getSerie(String IdSerie)  // Método para acessar o Hashmap de Midias e retornar um objeto de Série, encontrado pela chave de Id da série, passado por parâmetro. Método criado para acesso específico de uma Série
    {
       Midia midia = Midias.get(IdSerie);
       if(midia instanceof Serie)
       {
        System.out.println("Achada a série");
        Serie serie = (Serie) midia;
        return serie;
       }
       System.out.println("Não encontrado a série");
       return null;
    }

    public static Filme getFilme(String IdFilme)  // Método para acessar o Hashmap de Midias e retornar um objeto de Filme, encontrado pela chave de Id do filme, passado por parâmetro. Método criado para acesso específico de um filme
    {
       Midia midia = Midias.get(IdFilme);
       if(midia instanceof Filme)
       {
        System.out.println("Achada o filme");
        Filme filme = (Filme) midia;
        return filme;
       }
       System.out.println("Não encontrado o filme");
       return null;
    }
    
}


