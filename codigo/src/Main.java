package src;
import java.io.IOException;


public class Main 
{
    
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();
    Conta henrique = new Conta("henrique", "henriquecm122", "1547");

    System.out.println(Aplicativo.buscarSeriePorNome("Titanic"));
    henrique.assistirSerie("Titanic");
    henrique.assistirSerie("Titanic");
    
    Conta Lucas = aplicativo.getConta("Lucas22");
    Serie titanic = aplicativo.getSerie("Titanic");
    System.out.println( titanic.getContagemVisualizacao()); 

    Lucas.assistirSerie("Titanic");
    Conta testeLucas = new Conta("lucas", "Lucas22", "13342");

    System.out.println(Lucas.retornaListaSeriesJaAssistidas()); 
    
    System.out.println("=========");

    System.out.println(titanic);

    titanic.setIdioma("PT-BR");
    System.out.println(Aplicativo.buscarSeriesPorIdioma("PT-BR"));

    System.out.println(    henrique.buscarSeriesNoAplicativoPorIdioma("PT-BR"));

    System.out.println("============");
    
    System.out.println(  Aplicativo.realizarLogin("Lucas22", "123"));

    System.out.println(  Aplicativo.realizarLogin("Lucas22", "1222"));
}





}
