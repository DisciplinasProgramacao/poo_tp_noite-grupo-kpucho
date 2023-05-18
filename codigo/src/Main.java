package src;
import java.io.IOException;
import java.util.*;


public class Main 
{
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();
    Scanner sc = new Scanner(System.in);

    int entradaUsuario;

    do
    {
        System.out.println("-- TELA INICIAL --");
        System.out.println("0: Criar Conta");
        System.out.println("1: Fazer Login");
        System.out.println("2: Continuar sem Login");
        System.out.println("3: Encerrar");
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
            //plataformaSemConta();
            break;
        }
    }while(entradaUsuario!= 3);
    
}

public static void criarConta()
{
    Scanner sc = new Scanner(System.in);

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
    Scanner sc = new Scanner(System.in);
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

public static void plataformaComConta(String login)
{
    Scanner sc = new Scanner(System.in);

   
    int entradaUsuario = 0;
    do
    {
        System.out.println("-- Plataforma Streaming --");
        System.out.println("Logado na conta: "+login);
        System.out.println("\n0: Voltar a tela Inicial");
        System.out.println("1: Buscar mídia por Id");
        System.out.println("2: Buscar mídia pelo nome");
        System.out.println("3: Filtrar mídias por um idioma");
        System.out.println("4: Filtrar mídias por um gênero");
        System.out.println("5: Assistir uma mídia");
        System.out.println("6: Mostrar mídias já assistidas");
        System.out.println("7: Adicionar mídia em sua lista de Já Assistidas");
        System.out.println("8: Adicionar mídia para assistir futuramente pelo nome");
        System.out.println("9: Adicionar mídia para assistir futuramente pelo Id");
        System.out.println("10: Remover mídia da lista de assistir futuramente");
        System.out.println("11: Buscar uma mídia em sua lista de Já Assistidas");
        System.out.println("12: Buscar uma mídia na sua lista de Assistir Futuramente");
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
            // NÃO FOI IMPLEMENTADO AINDA, HÁ UM ISSUE ABERTO NO GITHUB EXPLICANDO O PORQUÊ!
            break;

            case 8:
            plataformaContaAdicionarAssistirFuturamentePeloNome();
            break;
            
        }
        
    }while(entradaUsuario != 0);

}

public static void plataformaBuscarMidiaPorId()
{
    Scanner sc = new Scanner(System.in);
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
    Scanner sc = new Scanner(System.in);
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
    Scanner sc = new Scanner(System.in);
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
    Scanner sc = new Scanner(System.in);
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
    Scanner sc = new Scanner(System.in);
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
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);

}

public static void plataformaContaMostrarMidiasAssistidas()
{
    Conta contaLogada = Aplicativo.getContaAtual();
    String midiasAssistidas = contaLogada.retornaListaMidiasJaAssistidas();
    
    if(midiasAssistidas.equals(""))
    {
        System.out.println("\nNenhuma mídia foi assistida ainda.\n");
    }
    else
    {
        System.out.println("\n Mídias Assistidas: ");
        System.out.println(midiasAssistidas+"\n");
    }
    
}

public static void plataformaContaAdicionarAssistirFuturamentePeloNome()
{
    Scanner sc = new Scanner(System.in);
    int entradaUsuario = 0;
    Conta contaLogada = Aplicativo.getContaAtual();
    do
    {
        System.out.println("Digite o nome da mídia a ser adicionada para assistir futuramente: ");
        String nomeMidia = sc.nextLine();

        if(!contaLogada.adicionarMidiaEmListaDeAssistirFuturamente(nomeMidia))
        {
            System.out.println("\n Mídia com o nome "+nomeMidia+" não existe!");
        }
        else
        {

            System.out.println("\n"+nomeMidia+" foi adicionada a sua lista de Assistir Futuramente!");
        }

        System.out.println("\n0: Voltar a Plataforma de Streaming");
        System.out.println("1: Realizar outra busca");
        String input = sc.nextLine();
        entradaUsuario = Integer.parseInt(input);

    }while(entradaUsuario != 0);
}








//public static void plataformaSemConta()
//{

//}



 
}
