package src;
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Aplicativo  
{

    private static HashMap<String, Conta> Contas;
    private static HashMap<String , Midia> Midias; // Hashamp de Mídias que armazena objetos das classes Series e Filmes
    private static Conta contaAtual; // Um objeto da classe Conta que é instanciado na hora da chamada do método 'realizarLogin()'. Esse objeto serve para facilitar operações no Menu (Main).
   
    public Aplicativo() throws IOException
    {
        Contas = new HashMap<>();
        Midias = new HashMap<>();
        carregarArquivoEspectadores(); // Método que carrega o arquivo 'POO_Espectadores.csv' e cria diversos objetos da classe Conta que são adicionados no Hashmap<Conta> Contas
        carregarArquivoSeries();  // Método que carrega o arquivo 'POO_Series.csv' e cria diversos objetos da classe Serie, que são adicionados no Hashmap<Midia> Midias
        carregarArquivoFilmes();  // Método que carrega o arquivo 'POO_Filmes.csv' e cria diversos objetos da classe Filme, que são adicionados no Hasmap<Midia> Midias
        carregarArquivoAudiencia();  // Método que carrega o arquivo 'POO_Audiencia.csv' e atribui alguns dados a objetos Serie, Midia e Filme existentes no Hashmap<Midia> Midias. Também atribui dados a objetos da Conta existentes no Hashmap<Conta> Contas
    
    }

    public static boolean criarConta(String nome, String login, String senha) // Método que irá criar um objeto da classe Conta, se o id da conta for único e, irá adicionar esse objeto ao Hashmap<Conta> Contas
    {
        if(Contas.containsKey(login)) // Condição para verificar se existe ou não um login associado a uma conta já criada dentro do Hashmap<Conta> Contas
        {
            System.out.println("Conta com o login: "+login+" já existe!");
            return false;
        }
        Conta novaConta = new Conta(nome, login, senha); 
        Contas.put(login, novaConta);
        return true;
    }

    public static boolean realizarLogin(String login, String senha) // Método para realizar o login em uma conta em que as credencias são validas (login e senha). Com isso, o Aplicativo salva esse objeto da Conta como conta atual logada no aplicativo
    {
        Conta conta = Aplicativo.buscarConta(login, senha);
        if(conta != null)
        {
            contaAtual = conta; // Armazenando a conta que passou pelo login como conta atual do aplicativo para que possa ser usada em outras operações 
            return true;
        }
        return false;
    }

    public static boolean realizarLogoff() // Método que realiza um log-off da conta Atual armazenada no Aplicativo (Atribui 'null' ao objeto 'Conta contaAtual')
    {
        contaAtual = null;
        return true;
    }

    private static Conta buscarConta(String login, String senha) // Método que é chamado pelo 'realizarLogin()'. Faz uma busca no 'Hashmap<Conta> Contas' para verificar se existe uma conta com login e senha recebidas por parâmetro.
    {
        Conta conta  = Contas.get(login);
        if(conta != null && conta.verificaSenha(senha)) 
        {
            return conta;
        }
        return null;
    }

    public static boolean buscarConta(String login) // Método para buscar uma conta no 'Hashmap<Conta> Contas' e verificar se ela existe
    {
        Conta conta  = Contas.get(login);
        if(conta != null)
        {
            return true;
        }
        return false;
    }


    public static boolean adicionarSerie(String IdSerie, String nome, String dataDeLancamento, int quantidadeEpisodios, int contagemVisualizacao) // Método que adiciona um objeto da classe Serie ao 'Hashmap<Midia> Midias'. (Esse objeto só é adicionado se tiver um 'IdSerie' único)
    {
        Midia verificarMidia = Midias.get(IdSerie);
        if(verificarMidia != null)
        {
            System.out.println("Já existe uma mídia com esse Id");
            return false;
        }
        Serie serieAdicionada = new Serie(IdSerie, nome, dataDeLancamento, quantidadeEpisodios, contagemVisualizacao);
        Midias.put(IdSerie, serieAdicionada);
        return true;
    }

    public static boolean adicionarFilme(String IdFilme, String nome, String dataDeLancamento, int duracao, int contagemVisualizacao) // Método que adiciona um objeto da classe Filme ao 'Hashmap<Midia> Midias'. (Esse objeto só é adicionado se tiver um 'IdFilme' único)
    {
        Midia verificarMidia = Midias.get(IdFilme);
        if(verificarMidia != null)
        {
            System.out.println("Já existe uma mídia com esse Id");
            return false;
        }
        Filme filmeAdicionado = new Filme(IdFilme, nome, dataDeLancamento, duracao, contagemVisualizacao);
        Midias.put(IdFilme, filmeAdicionado);
        return true;
    }

    private static void carregarArquivoSeries() throws IOException  // Método para carregar Series/Midias por meio da leitura do arquivo csv 'POO_Series.csv'
    {                                                                               
        BufferedReader br = new BufferedReader(new FileReader(new File ("./codigo/arquivos/POO_Series.csv")));
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

            Serie serieAdicionada = new Serie(IdSerie, nomeSerie, dataLancamento,-1,0); // Criando um novo objeto do classe Serie, que será adicionado ao 'Hashmap<Midia> Midias' com base na leitura do arquivo. (Passando como parâmetro a Contagem de visualizações e a Quantidade de Episódios/Duração como números negativos pois ainda não foi implementado seus valores)

            Midias.put(IdSerie, serieAdicionada); // Adicionando essa récem criado objeto ao 'Hashmap<Midia> Midias'
        }
        br.close();
        
    }

    private static void carregarArquivoFilmes() throws IOException  // Método para carregar Filmes/Midias por meio da leitura do arquivo csv 'POO_Series.csv'
    {                                                                               
        BufferedReader br = new BufferedReader(new FileReader(new File ("./codigo/arquivos/POO_Filmes.csv")));
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

            Filme filmeAdicionada = new Filme(IdFilme, nomeFilme, dataLancamento, duracao, 0); // Criando um novo objeto da classe Filme, que é adicionado ao 'Hashmap<Midia> Midias', com base na leitura do arquivo 'POO_Filmes.csv'

            Midias.put(IdFilme, filmeAdicionada); // Adicionando essa récem criado objeto ao 'Hashmap<Midia> Midias' 
        }
        br.close();
    }

    private static HashMap<String, Conta> carregarArquivoEspectadores() throws IOException  // Método para carregar Contas por meio da leitura do arquivo 'POO_Espectadores.csv'
    {                                                          
        BufferedReader br = new BufferedReader(new FileReader(new File ("./codigo/arquivos/POO_Espectadores.csv")));
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

            Conta conta = new Conta(nome, login, senha); // Criando um novo objeto da classe Conta com base na leitura do arquivo 'POO_Espectadores.csv'
            Contas.put(login, conta); // Adicionando esse récem criado objeto ao 'Hashmap<Conta> Contas'
        }
        br.close();
        return Contas;
    }

    private static void carregarArquivoAudiencia() throws IOException
    {         
        Random rd = new Random();                                                 // Método para carregar dados de Midias e Contas por meio da leitura do arquivo 'POO_Audiência.csv'
        BufferedReader br = new BufferedReader(new FileReader(new File ("./codigo/arquivos/POO_Audiencia.csv")));
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
            String FA = campos[1]; // FA = Determina se a Midia que foi assisitida por uma Conta será adicionada em sua Lista de assisitr futuramente (F) ou na Lista de Mídias assistidas (A)
            String IdSerie = campos[2];

            Midia serieAudiencia = getMidia(IdSerie);
            Conta conta = Contas.get(login);
                
            if(FA.equals("F")) // Se for igual a 'F', a mídia será armazenada na Conta com o login lido na lista de assistir futuramente 
            {
                conta.adicionarMidiaEmListaDeAssistirFuturamentePorId(IdSerie);
            }
            else if (FA.equals("A")) // Se for igual a 'A', a mídia será armazenada na lista séries assistidas
            {
                serieAudiencia.assistirMidia(); // Chamando o método 'assistirMidia()' para aumentar a visualização da Mídia, lida pelo arquivo com o 'IdMidia'
                conta.adicionarEmListaDeMidiasJaAssistidas(serieAudiencia);

                int avaliacaoAleatoria = rd.nextInt(1, 6);
                conta.avaliarAleatorio(serieAudiencia.getNome(),avaliacaoAleatoria, LocalDate.now(), serieAudiencia); // Gerando avaliações aleatórias para as Midias quando forem carregadas no sistema
            } 
        
        }
    }
     

    public static Midia buscarMidiaPorId(String idMidia) // Método que busca um objeto da classe Midia no 'Hashmap<Midia> Midias> por meio de sua chave (idMidia)
    {
        Midia midiaProcurada = Midias.get(idMidia);
        if(midiaProcurada != null) // Verificando no 'Hashmap<Midia> Midias' se a chave do objeto (idMidia), passada por parâmetro, existe  
        {
            return midiaProcurada;
        }
        return null;
    }

    public static Midia buscarMidiaPorNome(String nomeMidia) // Método que busca um objeto da classe Midia no 'Hashmap<Midia> Midias' por meio do seu nome.
    {
        for(Midia midia : Midias.values())
        {
            if(midia.getNome().equals(nomeMidia))
            {
                return midia;
            }
        }
        return null;
    }

    public static List<String> buscarMidiasPorIdioma(String idiomaMidia) // Método que busca todas as mídias do 'Hashmap<Midia> Midias' pelo idioma passado por parâmetro e, retorna uma lista de objetos da classe Midia, filtradas por esse idioma
    {
        List<String> midiasFiltradasPorIdioma = new ArrayList<>();

        for(Midia midia : Midias.values())
        {
            if(midia.getIdioma().equals(idiomaMidia))
            {
                midiasFiltradasPorIdioma.add(midia.getNome());
            }
        }
        return midiasFiltradasPorIdioma;
    }

    public static List<String> buscarMidiasPorGenero(String generoMidia) // Método que busca todas as mídias  do 'Hashmap<Midia> Midias' pelo gênero passado por parâmetro e, retorna uma lista de objetos da classe Midia, filtradas por esse gênero
    {
        List<String> midiasFiltradasPorGenero = new ArrayList<>();
        for(Midia midia : Midias.values())
        {
            if(midia.getGenero().equals(generoMidia))
            {
                midiasFiltradasPorGenero.add(midia.getNome());
            }
        }
        return midiasFiltradasPorGenero;
    }

    public static Conta getConta(String login)  // Método para acessar o 'Hashmap<Conta> Contas' e retornar um objeto da classe Conta que possuí a mesma chave que o 'login', passado por parâmetro
    {
        return Contas.get(login);
    }

    public static Midia getMidia(String IdMidia)  //  Método para acessar o 'Hashmap<Midia> Midias' e retornar um objeto da classe Midia que possuí a mesma chave que o 'idMidia', passado por parâmetro
    {
        return Midias.get(IdMidia);
    }

    public static Serie getSerie(String IdSerie)  // Método para acessar o 'Hashmap<Midia> Midias' e retornar um objeto da classe Serie, encontrado pela chave de 'IdSerie', passado por parâmetro. (Método criado para acesso específico de uma Série)
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

    public static Filme getFilme(String IdFilme)  // Método para acessar o 'Hashmap<Midia> Midias' e retornar um objeto da classe Filme, encontrado pela chave 'IdFilme', passado por parâmetro. (Método criado para acesso específico de um Filme)
    {
       Midia midia = Midias.get(IdFilme);
       if(midia instanceof Filme)
       {
        System.out.println("Achado o filme");
        Filme filme = (Filme) midia;
        return filme;
       }
       System.out.println("Não encontrado o filme");
       return null;
    }

    public static Conta getContaAtual()
    {
        return contaAtual;
    }

    public static HashMap<String, Conta> getContas()
    {
        return new HashMap<>(Contas);
    }

    public static HashMap<String, Midia> getMidias()
    {
        return new HashMap<>(Midias);
    }
    
}


