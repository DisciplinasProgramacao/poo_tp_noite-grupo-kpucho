package src;
import java.io.IOException;


public class Main 
{
    
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();

    Conta ada = Aplicativo.getConta("Ada3");

    ada.assistirSerie("Pink is the new Red");
    ada.assistirSerie("Pink is the new Red");

    System.out.println( ada.retornaListaSeriesJaAssistidas());
    Serie pink = Aplicativo.getSerie("3481");
    System.out.println(pink.getContagemVisualizacao());
    
    Conta x = Aplicativo.getConta("Mar123542");
    System.out.println(x.retornaListaSeriesAssistirFuturamente());
    System.out.println(x.retornaListaSeriesJaAssistidas());
}







}
