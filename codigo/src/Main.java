package src;
import java.io.IOException;
import java.util.*;


import java.time.*;


public class Main 
{
    static Scanner sc = new Scanner(System.in);
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();
    int entradaUsuario = -1;

    do
    {
        try
        {
            System.out.println("-- TELA INICIAL --");
            System.out.println("0: Criar Conta");
            System.out.println("1: Fazer Login");
            System.out.println("2: Continuar sem Login");
            System.out.println("3: Entrar com conta Administradora");
            System.out.println("4: Encerrar");
            String input = sc.nextLine();
            entradaUsuario = Integer.parseInt(input);
            switch(entradaUsuario)
            {
                case 0:
                criarConta();
                break;
    
                case 1: 
                loginConta();
                break;
                
                case 2:
                plataformaSemConta();
                break;
                
                case 3:
                plataformaAdministradora();
                break;
                
                case 4:
                System.out.println("\nObrigado e volte sempre! :) \n");
                break;
    
                default:
                System.out.println("\nOpção Inválida!\n");
            }
    
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nCaractér Inválido! Tente novamente\n");
        }

        
    }while(entradaUsuario!= 4);

}

public static void criarConta()
{

    System.out.println("-- CRIANDO CONTA -- ");
    System.out.println("Digite o nome da conta: ");
    String contaNome = sc.nextLine();

    System.out.println("Digite o login da conta: ");
    String contaLogin = sc.nextLine();

    System.out.println("Digite a senha da conta: ");
    String contaSenha = sc.nextLine();

    if(Aplicativo.criarConta(contaNome, contaLogin, contaSenha))
    {
        System.out.println("\nConta Criada!\n");
        return;
    }
    else
    {
        System.out.println("\nConta não foi criada!\n");
        return;
    }
    
}

public static void loginConta()
{
    int entradaUsuario = 1;
    String contaLogin;
    do
    {
    System.out.println("-- FAZER LOGIN --");
    System.out.println("Digite seu login: ");
    contaLogin = sc.nextLine();
    System.out.println("Digite sua senha: ");
    String contaSenha = sc.nextLine();

    if(Aplicativo.realizarLogin(contaLogin, contaSenha) == false)
    {
        System.out.println("\n Login ou senha incorreto!\n");
        System.out.println("0: Tentar novamente");
        System.out.println("1: Voltar a tela inicial");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

        if(entradaUsuario == 1)
        {
            return;
        }
    }
    else
    {
        System.out.println("\nLogin feito com sucesso!\n");
        break;
    }
    }while(entradaUsuario == 0);
       
    plataformaComConta(contaLogin);

}

public static void plataformaSemConta()
{
    int entradaUsuario = -1;
    do
    {
        try
        {
            System.out.println("-- Plataforma Streaming --");
            System.out.println("Logado em uma conta anônima");
            System.out.println("\n0: Voltar a tela Inicial");
            System.out.println("1: Buscar mídia por Id");
            System.out.println("2: Buscar mídia pelo nome");
            System.out.println("3: Filtrar mídias por um idioma");
            System.out.println("4: Filtrar mídias por um gênero");

            System.out.println("---------------------------------------------------------------");
            String input = sc.nextLine();
            entradaUsuario = Integer.parseInt(input);

            switch(entradaUsuario)
            {
                case 0:
                break;

                case 1:
                plataformaBuscarMidiaPorId();
                break;

                case 2:
                plataformaBuscarMidiaPorNome();
                break;

                case 3:
                plataformaBuscarMidiasPeloIdioma();
                break;

                case 4:
                plataformaBuscarMidiasPeloGenero();
                break;

                default:
                System.out.println("\nOpção Inválida!\n");
                break;

            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nCaractér Inválido! Tente novamente\n");
        }

    }while(entradaUsuario != 0);

}

public static void plataformaAdministradora()
{
    int entradaUsuario = -1;
    do
    {
        try
        {
            System.out.println("-- Plataforma Streaming --");
            System.out.println("Logado como ADMINISTRADORA");
            System.out.println("\n0: Voltar a tela Inicial");
            System.out.println("1: Buscar mídia por Id");
            System.out.println("2: Buscar mídia pelo nome");
            System.out.println("3: Filtrar mídias por um idioma");
            System.out.println("4: Filtrar mídias por um gênero");
            System.out.println("5: Assistir uma mídia");
            System.out.println("6: Adicionar uma Série");
            System.out.println("7: Adicionar um Filme");
            System.out.println("8: Buscar uma conta");
            System.out.println("9: Exibir Relatórios");

            System.out.println("---------------------------------------------------------------");
            String input = sc.nextLine();
            entradaUsuario = Integer.parseInt(input);

            switch(entradaUsuario)
            {
                case 0:
                break;

                case 1:
                plataformaBuscarMidiaPorId();
                break;

                case 2:
                plataformaBuscarMidiaPorNome();
                break;

                case 3:
                plataformaBuscarMidiasPeloIdioma();
                break;

                case 4:
                plataformaBuscarMidiasPeloGenero();
                break;

                case 5:
                System.out.println("\nPara assistir uma mídia você deve estar logado em uma conta cadastrada em nosso aplicativo!\n");
                break; 

                case 6:
                plataformaAdministradoraAdicionarSerie();
                break;

                case 7:
                plataformaAdministradoraAdicionarFilme();
                break;

                case 8:
                plataformaAdministradoraBuscarConta();
                break;
            
                default:
                System.out.println("\nOpção Inválida!\n");
                break;

                case 9:
                plataformaAdministradoraRelatorios();

            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nCaractér Inválido! Tente novamente\n");
        }
        
    }while(entradaUsuario != 0);
}

public static void plataformaAdministradoraAdicionarSerie()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite dados da Série para ser adicionada -- ");
        System.out.println("ID da Série: ");
        String IDSerie = sc.nextLine();
        System.out.println("Nome da Série: ");
        String nomeSerie = sc.nextLine();
        System.out.println("Data de Lançamento: ");
        String dataSerie = sc.nextLine();
        System.out.println("Quantidade de episódios: ");
        String quantidadeEpisodios = sc.nextLine();
        int quantidadeEpisodiosInt = Integer.parseInt(quantidadeEpisodios);
        System.out.println("Contagem de visualizações: ");
        String contagemVizualizacoes = sc.nextLine();
        int contagemVizualizacoesInt = Integer.parseInt(contagemVizualizacoes);

        boolean verificaAdicao = Aplicativo.adicionarSerie(IDSerie, nomeSerie, dataSerie, quantidadeEpisodiosInt, contagemVizualizacoesInt);
        if(!verificaAdicao)
        {
            System.out.println("\n Erro! Série não foi adicionada! Esse ID "+IDSerie+" já está registrado no sistema");
        }
        else
        {
            System.out.println("\nSucesso! Série de ID "+IDSerie+" foi adicionada no sistema.");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Adicionar outra Série");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}

public static void plataformaAdministradoraAdicionarFilme()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite dados do Filme para ser adicionado -- ");
        System.out.println("ID do Filme: ");
        String IDFilme = sc.nextLine();
        System.out.println("Nome da Filme: ");
        String nomeFilme = sc.nextLine();
        System.out.println("Data de Lançamento: ");
        String dataFilme = sc.nextLine();
        System.out.println("Duração: ");
        String duracaoFilme = sc.nextLine();
        int duracaoFilmeInt = Integer.parseInt(duracaoFilme);
        System.out.println("Contagem de visualizações: ");
        String contagemVizualizacoes = sc.nextLine();
        int contagemVizualizacoesInt = Integer.parseInt(contagemVizualizacoes);

        boolean verificaAdicao = Aplicativo.adicionarFilme(IDFilme, nomeFilme, dataFilme, duracaoFilmeInt, contagemVizualizacoesInt);
        if(!verificaAdicao)
        {
            System.out.println("\n Erro! Filme não foi adicionado! Esse ID "+IDFilme+" já está registrado no sistema");
        }
        else
        {
            System.out.println("\nSucesso! Filme de ID "+IDFilme+" foi adicionado no sistema.");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Adicionar outro Filme");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}

public static void plataformaAdministradoraBuscarConta()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite o login da conta a ser buscada: ");
        String loginConta = sc.nextLine();
        boolean verificaConta = Aplicativo.buscarConta(loginConta);


        if(!verificaConta )
        {
            System.out.println("\nConta com login "+loginConta+" não existe!");
        }
        else
        {
            System.out.println("\nConta com login "+loginConta+" existe no sistema!");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}

public static void plataformaAdministradoraRelatorios()
{
    int entradaUsuario = -1;
    do
    {
        try
        {
            System.out.println("-- Plataforma Streaming --");
            System.out.println("Logado como ADMINISTRADORA");
            System.out.println("\n0: Voltar a tela Anterior");
            System.out.println("1: Qual cliente assistiu mais mídias");
            System.out.println("2: Qual cliente tem mais avaliações");
            System.out.println("3: Porcentagem de clientes com pelo menos 15 avaliações");
            System.out.println("4: 10 mídias com melhor média de avaliações");
            System.out.println("5: 10 mídias com mais visualizações");
             System.out.println("6: 10 mídias com melhor média de avaliações filtradas por Gênero");
            System.out.println("7: 10 mídias com mais vizualizações filtradas por Gênero ");
            //SEPARAR POR GÊNERO
           

            System.out.println("---------------------------------------------------------------");
            String input = sc.nextLine();
            entradaUsuario = Integer.parseInt(input);

            switch(entradaUsuario)
            {
                case 0:
                break;

                case 1:
                plataformaAdministradoraRelatorio01();
                break;

                case 2:
                plataformaAdministradoraRelatorio02();
                break;

                case 3:
                plataformaAdministradoraRelatorio03();
                break;

                case 4:
                plataformaAdministradoraRelatorio04();
                break;

                case 5:
                plataformaAdministradoraRelatorio05();
                break;

                case 6:
                plataformaAdministradoraRelatorio06();
                break;

               
                case 7:
                plataformaAdministradoraRelatorio07();
                break;
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nCaractér Inválido! Tente novamente\n");
        }
        
    }while(entradaUsuario != 0);
}

public static void plataformaAdministradoraRelatorio01()
{
    HashMap<String, Conta> Contas2 = Aplicativo.getContas();

    Conta contaComMaisMidia = null;
    int maxMidiasAssistidas = 0;

    for(Conta conta : Contas2.values())
    {
        int numeroMidiaAssistidas = conta.getListaMidiaAssistidas().size();
        if(numeroMidiaAssistidas > maxMidiasAssistidas)
        {
            maxMidiasAssistidas = numeroMidiaAssistidas;
            contaComMaisMidia = conta;
        }
    }

    if(contaComMaisMidia != null)
    {
        System.out.println("\nCliente que assistiu mais mídias: ");
        System.out.println("Login: " +contaComMaisMidia.getLogin());
        System.out.println("Quantidade de mídias assistidas: " +maxMidiasAssistidas+"\n");

    }
    else
    {
        System.out.println("\nNenhum cliente Encontrado");
    }

}

public static void plataformaAdministradoraRelatorio02()
{
    HashMap<String, Conta> Contas2 = Aplicativo.getContas();
    Conta contaComMaisAvaliacao = null;
    int maxMidiasAvaliada = 0;

    for(Conta conta : Contas2.values())
    {
        int numeroMidiaAvaliada = conta.getAvaliacao().size();
        if(numeroMidiaAvaliada > maxMidiasAvaliada)
        {
            maxMidiasAvaliada = numeroMidiaAvaliada;
            contaComMaisAvaliacao = conta;
        }
    }

    if(contaComMaisAvaliacao != null)
    {
        System.out.println("\nCliente que mais avaliou: ");
        System.out.println("Login: " +contaComMaisAvaliacao.getLogin());
        System.out.println("Quantidade de mídias avaliadas: " +maxMidiasAvaliada+"\n");

    }
    else
    {
        System.out.println("\nNenhum cliente Encontrado");
    }

}

public static void plataformaAdministradoraRelatorio03() 
{
    HashMap<String, Conta> Contas02 = Aplicativo.getContas();
    int totalConta = Contas02.size();
    int contaComAvaliacoes = 0;

    for (Conta conta : Contas02.values()) {
        HashMap<Midia, Avaliacao> avaliacoes = conta.getAvaliacao();
        int numAvaliacoes = avaliacoes.size();

        if (numAvaliacoes >= 15) {
            contaComAvaliacoes++;
        }
    }

    double porcentagem = (double) contaComAvaliacoes / totalConta * 100;

    System.out.println("\nPorcentagem de Contas com pelo menos 15 avaliações: " + porcentagem + "% \n");
}

public static void plataformaAdministradoraRelatorio04()
{
    HashMap<String, Midia> midias = Aplicativo.getMidias();
    List<Midia> midiasSelecionadas = new ArrayList<>();

    for (Midia midia : midias.values()) {
        if (midia.getContagemVisualizacao() >= 100) {
            midiasSelecionadas.add(midia);
        }
    }

    midiasSelecionadas.sort(Comparator.comparingDouble(Midia::getMediaAvaliacao).reversed());

    int numMidias = Math.min(midiasSelecionadas.size(), 10);

    System.out.println("\nTop 10 mídias com a melhor média de avaliações e pelo menos 100 visualizações:");

    for (int i = 0; i < numMidias; i++) {
        Midia midia = midiasSelecionadas.get(i);
        System.out.println("Mídia: " + midia.getNome());
        System.out.println("Média de avaliações: " + midia.getMediaAvaliacao());
        System.out.println("Visualizações: " + midia.getContagemVisualizacao());
        System.out.println();
    }
} 

public static void plataformaAdministradoraRelatorio05()
{
    HashMap<String, Midia> midias = Aplicativo.getMidias();

    List<Midia> midiasOrdenadasPorVisualizacoes = new ArrayList<>(midias.values());

    // Classificar as mídias com base no número de visualizações em ordem decrescente
    Collections.sort(midiasOrdenadasPorVisualizacoes, new Comparator<Midia>() 
    {
        @Override
        public int compare(Midia m1, Midia m2) {
            return Integer.compare(m2.getContagemVisualizacao(), m1.getContagemVisualizacao());
        }
    });

    System.out.println("\nAs 10 mídias com mais visualizações:");

    int contador = 1;
    for (Midia midia : midiasOrdenadasPorVisualizacoes) {
        System.out.println(contador + ". " + midia.getNome() + " - Visualizações: " + midia.getContagemVisualizacao());
        contador++;

        if (contador > 10) {
            break;
        }
    }
    System.out.println();
}

public static void plataformaAdministradoraRelatorio06() 
{
    HashMap<String, Midia> midias = Aplicativo.getMidias();
    List<Midia> midiasSelecionadas = new ArrayList<>();

    System.out.println("\nLista de gêneros:");
    System.out.println("Acao, Anime, Aventura, Comedia, Documentario, Drama, Policial, Romance, Suspense");
    System.out.print("Escolha um gênero para filtrar o relatório: ");
    String generoEscolhido = sc.nextLine();

    for (Midia midia : midias.values()) 
    {
        if (midia.getGenero().equalsIgnoreCase(generoEscolhido) && midia.getContagemVisualizacao() >= 100) {
            midiasSelecionadas.add(midia);
        }
    }

    midiasSelecionadas.sort(Comparator.comparingDouble(Midia::getMediaAvaliacao).reversed());

    int numMidias = Math.min(midiasSelecionadas.size(), 10);

    System.out.println("\nTop 10 mídias com a melhor média de avaliações e pelo menos 100 visualizações no gênero " + generoEscolhido + ":");

    for (int i = 0; i < numMidias; i++) 
    {
        Midia midia = midiasSelecionadas.get(i);
        System.out.println("Mídia: " + midia.getNome());
        System.out.println("Média de avaliações: " + midia.getMediaAvaliacao());
        System.out.println();
    }

    System.out.println();
}

public static void plataformaAdministradoraRelatorio07()
{
    HashMap<String, Midia> midias = Aplicativo.getMidias();
    List<Midia> midiasSelecionadas = new ArrayList<>();

    System.out.println("\nLista de gêneros:");
    System.out.println("Acao, Anime, Aventura, Comedia, Documentario, Drama, Policial, Romance, Suspense");
    System.out.print("Escolha um gênero para filtrar o relatório: ");
    String generoEscolhido = sc.nextLine();


    for (Midia midia : midias.values()) {
        if (midia.getGenero().equalsIgnoreCase(generoEscolhido)) {
            midiasSelecionadas.add(midia);
        }
    }

    midiasSelecionadas.sort(Comparator.comparingInt(Midia::getContagemVisualizacao).reversed());

    int numMidias = Math.min(midiasSelecionadas.size(), 10);

    System.out.println("\nTop 10 mídias com mais visualizações no gênero " + generoEscolhido + ":");

    for (int i = 0; i < numMidias; i++) {
        Midia midia = midiasSelecionadas.get(i);
        System.out.println("Mídia: " + midia.getNome());
        System.out.println("Visualizações: " + midia.getContagemVisualizacao());
        System.out.println();
    }
}


public static void plataformaComConta(String login)
{
   
    int entradaUsuario = -1;
    do
    {
        try
        {
            Conta contaA = Aplicativo.getContaAtual();
            
            System.out.println("-- Plataforma Streaming --");
            System.out.println("Logado na conta: "+login);
            System.out.println("Permissão: "+contaA.getTipoCliente());
            System.out.println("\n0: Voltar a tela Inicial");
            System.out.println("1: Buscar mídia por Id");
            System.out.println("2: Buscar mídia pelo nome");
            System.out.println("3: Filtrar mídias por um idioma");
            System.out.println("4: Filtrar mídias por um gênero");
            System.out.println("5: Assistir uma mídia");
            System.out.println("6: Mostrar mídias já assistidas");
            System.out.println("7: Adicionar mídia para assistir futuramente pelo nome");
            System.out.println("8: Adicionar mídia para assistir futuramente pelo Id");
            System.out.println("9: Mostrar mídias adicionadas para Assistir Futuramente");
            System.out.println("10: Remover mídia da lista de assistir futuramente");
            System.out.println("11: Avaliar Mídia");
            System.out.println("12: Se tornar 'PROFISSIONAL' (PARA TESTES)");

            System.out.println("---------------------------------------------------------------");
            String input = sc.nextLine();
            entradaUsuario = Integer.parseInt(input);

            switch(entradaUsuario)
            {
                case 0:
                break;

                case 1:
                plataformaBuscarMidiaPorId();
                break;

                case 2:
                plataformaBuscarMidiaPorNome();
                break;

                case 3:
                plataformaBuscarMidiasPeloIdioma();
                break;

                case 4:
                plataformaBuscarMidiasPeloGenero();
                break;

                case 5:
                plataformaContaAssistirMidia();
                break;

                case 6:
                plataformaContaMostrarMidiasAssistidas();
                break;

                case 7:
                plataformaContaAdicionarAssistirFuturamentePeloNome();
                break;

                case 8:
                plataformaContaAdicionarAssistirFuturamentePeloId();
                break;
                
                case 9:
                plataformaContaMostrarMidiasAssistirFuturamente();
                break;

                case 10:
                plataformaContaRemoverMidiaAssistirFuturamente();
                break;

                case 11:
                plataformaContaAvaliarMidia();
                break;

                case 12:
                contaA.setProfissional();
                break;


                default:
                System.out.println("\nOpção Inválida!\n");
                break;  
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nCaractér Inválido! Tente novamente\n");
        }
        
        
    }while(entradaUsuario != 0);

}

public static void plataformaBuscarMidiaPorId()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite o ID da mídia a ser procurada: ");
        String idMidia = sc.nextLine();
        Midia midiaProcurada = Aplicativo.buscarMidiaPorId(idMidia);

        if(midiaProcurada == null)
        {
            System.out.println("\nMídia com ID "+idMidia+" não existe!");
        }
        else
        {
            System.out.println("\nMídia encontrada!");
            System.out.println(midiaProcurada.toString());
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);

    
}

public static void plataformaBuscarMidiaPorNome()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite o Nome da mídia a ser procurada: ");
        String nomeMidia = sc.nextLine();
        Midia midiaProcurada = Aplicativo.buscarMidiaPorNome(nomeMidia);

        if(midiaProcurada == null)
        {
            System.out.println("\nMídia com nome "+nomeMidia+" não existe!");
        }
        else
        {
            System.out.println("\nMídia encontrada!");
            System.out.println(midiaProcurada.toString());
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);


}


public static void plataformaBuscarMidiasPeloIdioma()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite o idioma das mídias a serem procuradas: ");
        String idiomaMidias = sc.nextLine();
        List<String> midiasFiltradasPorIdioma = new ArrayList<>();
        midiasFiltradasPorIdioma = Aplicativo.buscarMidiasPorIdioma(idiomaMidias);

        System.out.println(midiasFiltradasPorIdioma);

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);

}

public static void plataformaBuscarMidiasPeloGenero()
{
    int entradaUsuario = 0;
    do
    {
        System.out.println("Digite o gênero das mídias a serem procuradas: ");
        String generoMidias = sc.nextLine();
        List<String> midiasFiltradasPorGenero = new ArrayList<>();
        midiasFiltradasPorGenero = Aplicativo.buscarMidiasPorGenero(generoMidias);

        System.out.println(midiasFiltradasPorGenero);

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);

}

public static void plataformaContaAssistirMidia()
{
    try
    {

        int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();
    do
    {
        System.out.println("Digite o nome da mídia a ser assistida: ");
        String nomeMidia = sc.nextLine();

        if(!contaLogada.assistirMidia(nomeMidia))
        {
            System.out.println("\n Mídia com o nome "+nomeMidia+" não existe!");
        }
        else
        {

            System.out.println("\n"+nomeMidia+" foi assistido(a)!");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Assistir outra mídia");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
    }
    catch (IllegalAccessError e)
    {
        System.out.println("\nNão possuí permissão para assistir essa Mídia. Essa Mídia é um Lançamento!"); 
        System.out.println("Tente Novamente!\n");
    }

}

public static void plataformaContaMostrarMidiasAssistidas()
{
    Conta contaLogada = Aplicativo.getContaAtual();
    String midiasAssistidas = contaLogada.retornaListaMidiasJaAssistidas();
    
    if(midiasAssistidas.equals(""))
    {
        System.out.println("\nNenhuma mídia foi assistida ainda.\n");
        return;
    }
    else
    {
        System.out.println("\n Mídias Assistidas: ");
        System.out.println(midiasAssistidas+"\n");
    }

    plataformaContaBuscarMidiaAssistidas(midiasAssistidas);
    
}

public static void plataformaContaBuscarMidiaAssistidas(String midiasAssistidas)
{
    int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();

    System.out.println("\n0: Voltar a Plataforma de Streaming");
    System.out.println("1: Buscar uma mídia em sua lista de mídias assistidas");
    String input = sc.nextLine();
    entradaUsuario = Integer.parseInt(input);
    
    while(entradaUsuario!= 0)
    {
        System.out.println("Digite o nome da mídia a ser buscada de sua lista de mídias assistidas: ");
        String nomeMidia = sc.nextLine();
        boolean verificaBusca = contaLogada.buscarMidiaEmSuaListaAssistidas(nomeMidia);

        if(!verificaBusca)
        {
            System.out.println("\n Mídia com o nome "+nomeMidia+" não existe na sua lista!");
        }
        else
        {
            System.out.println("\nMídia "+nomeMidia+" está em sua lista de mídias já assistidas!");
        }
        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Buscar outra mídia");
        input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }
}

public static void plataformaContaAdicionarAssistirFuturamentePeloNome()
{
    int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();
    do
    {
        System.out.println("Digite o nome da mídia a ser adicionada para assistir futuramente: ");
        String nomeMidia = sc.nextLine();

        boolean verificaMidias = contaLogada.adicionarMidiaEmListaDeAssistirFuturamente(nomeMidia);

        if(!verificaMidias)
        {
            System.out.println("\n Mídia com o nome "+nomeMidia+" não existe!");
        }
        else
        {

            System.out.println("\n"+nomeMidia+" foi adicionada a sua lista de Assistir Futuramente!");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Adicionar outra mídia");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}

public static void plataformaContaAdicionarAssistirFuturamentePeloId()
{
    
    int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();
    do
    {
        System.out.println("Digite o ID da mídia a ser adicionada para assistir futuramente: ");
        String IDMidia = sc.nextLine();
        boolean verificaMidias = contaLogada.adicionarMidiaEmListaDeAssistirFuturamentePorId(IDMidia);

        if(!verificaMidias)
        {
            System.out.println("\n Mídia com o ID "+IDMidia+" não existe!");
        }
        else
        {

            System.out.println("\n"+IDMidia+" foi adicionada a sua lista de Assistir Futuramente!");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Adicionar outra mídia");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}

public static void plataformaContaMostrarMidiasAssistirFuturamente()
{
    Conta contaLogada = Aplicativo.getContaAtual();
    String midiasAssistirFuturamente = contaLogada.retornaListaMidiasAssistirFuturamente();
    
    if(midiasAssistirFuturamente.equals(""))
    {
        System.out.println("\nNenhuma mídia foi adicionada para Assistir futuramente ainda.\n");
    }
    else
    {
        System.out.println("\n Mídias para Assistir Futuramente: ");
        System.out.println(midiasAssistirFuturamente+"\n");
    }
    plataformaContaBuscarMidiaAssistirFuturamente(midiasAssistirFuturamente);
}

public static void plataformaContaBuscarMidiaAssistirFuturamente(String midiasAssistirFuturamente)
{
    int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();

    System.out.println("\n0: Voltar a Plataforma de Streaming");
    System.out.println("1: Buscar uma mídia em sua lista de mídias para assistir futuramente");
    String input = sc.nextLine();
    entradaUsuario = Integer.parseInt(input);
    
    while(entradaUsuario!= 0)
    {
        System.out.println("Digite o nome da mídia a ser buscada de sua lista de mídias para assistir futuramente: ");
        String nomeMidia = sc.nextLine();
        boolean verificaBusca = contaLogada.buscarMidiaEmSuaListaAssistirFuturamente(nomeMidia);

        if(!verificaBusca)
        {
            System.out.println("\n Mídia com o nome "+nomeMidia+" não existe na sua lista!");
        }
        else
        {
            System.out.println("\nMídia "+nomeMidia+" está em sua lista de mídias para assistir futuramente!");
        }
        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Buscar outra mídia");
        input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }
}

public static void plataformaContaRemoverMidiaAssistirFuturamente()
{
    int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();
    
    do
    {
        System.out.println("Digite o nome da mídia a ser removida de sua lista para assistir futuramente: ");
        String nomeMidia = sc.nextLine();
        int verificaLista = contaLogada.removerMidiaEmListaDeAssistirFuturamente(nomeMidia);

        if(verificaLista == -1)
        {
            System.out.println("\n Mídia com o nome "+nomeMidia+" não existe!");
        }
        else if (verificaLista == 0)
        {

            System.out.println("\nMídia não foi removida pois a lista de mídias para Assistir Futuramente está vazia!");
        }
        else
        {
            System.out.println("\nMídia "+nomeMidia+" foi removida!");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Remover outra mídia");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}

public static void plataformaContaAvaliarMidia()
{
    try
    {
        int entradaUsuario = 0;
        Conta contaLogada = Aplicativo.getContaAtual();
        do
        {
            System.out.println("Digite o nome da mídia a ser avaliada: ");
            String nomeMidia = sc.nextLine();
    
    
            
            System.out.println("\nDigite sua avaliação da mídia (1 a 5): ");
            String avaliacaoMidiaString = sc.nextLine();
            int avaliacaoMidia = Integer.parseInt(avaliacaoMidiaString);
    
            if(!contaLogada.buscarMidiaNoAplicativoPorNome(nomeMidia))
            {
                System.out.println("\n Mídia com o nome "+nomeMidia+" não existe!");
            }
            else if(avaliacaoMidia > 5 || avaliacaoMidia < 1)
            {
                System.out.println("\n Avaliação Inválida.");
            }
            else
            {
                int resultadoAvalicao = contaLogada.avaliarMidia(nomeMidia, avaliacaoMidia, LocalDate.now());
    
                if(resultadoAvalicao == 0 )
                {
                    System.out.println("\nMidia "+nomeMidia+" foi avalida!");
                }
                else if(resultadoAvalicao == -1)
                {
                    System.out.println("\nMidia já foi avalida por essa conta. Avalição não foi registrada!");
                }
                else
                {
                    System.out.println("\nComentário Registrado!");
                }
            }
            
            System.out.println("\n0: Voltar a Plataforma de Streaming");
            System.out.println("1: Avaliar outra mídia");
            String input = sc.nextLine();
            entradaUsuario = Integer.parseInt(input);
    
        }while(entradaUsuario != 0);   
    }
    catch (IllegalAccessError e)
    {
        System.out.println("\nNão possuí permissão para avaliar essa Mídia. Essa Mídia é um Lançamento!"); 
        System.out.println("Tente Novamente!\n");
    }
   
}


}
