package src;
import java.io.IOException;


public class Main 
{
    
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();
    Aplicativo.carregarArquivoEspectadores();
    Aplicativo.carregarArquivoSeries();
    Aplicativo.carregarArquivoAudiencia();

    Conta ada = Aplicativo.getConta("Ada3");

    ada.assistirSerie("Pink is the new Red");
    ada.assistirSerie("Pink is the new Red");

    System.out.println( ada.retornaListaSeriesJaAssistidas());
    Serie pink = Aplicativo.getSerie("3481");
    System.out.println(pink.getContagemVisualizacao());
  

}







}
