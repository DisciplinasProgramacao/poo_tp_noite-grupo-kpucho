package src;
import java.io.IOException;


public class Main 
{
    
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();

    Conta ada = Aplicativo.getConta("Ada3");

    ada.assistirMidia("Pink is the new Red");
    ada.assistirMidia("Pink is the new Red");
    ada.assistirMidia("Pink is the new Red");

    System.out.println( ada.retornaListaMidiasJaAssistidas());
    Midia pink = Aplicativo.getMidia("3481");
    ada.assistirMidia("Pink is the new Red");
    System.out.println(pink.getContagemVisualizacao());
    
    Conta x = Aplicativo.getConta("Mar123542");
    System.out.println(x.retornaListaMidiasAssistirFuturamente());
    System.out.println(x.retornaListaMidiasJaAssistidas());
    System.out.println("_________________");
    System.out.println(ada.retornaListaMidiasJaAssistidas());
    System.out.println("__________");
    System.out.println(ada.buscarMidiaEmSuaListaSeriesAssistidas("Pink is the new Red"));

    System.out.println("==");

    Filme filme1 = Aplicativo.getFilme("8488");
    System.out.println(filme1.getDuracao());
    System.out.println(filme1.getGenero());
    System.out.println(filme1.getIdioma());
}
}
