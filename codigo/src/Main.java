public class Main 
{
    
public static void main(String[]args)
{
    Aplicativo aplicativo = Aplicativo.getInstance(); // Inicializando o Aplicativo para utilizar seus métodos e dados
    Conta henrique = new Conta("oi", "oi1213", 1547);
    Serie Matrix = new Serie("Matrix", "PT-bt", "Acao");

    henrique.buscarSerieNoAplicativo("Titanic");
    henrique.buscarSerieNoAplicativo("Matrix");

    aplicativo.criarConta("oi", "oi1213", 1111);
    aplicativo.buscarSerie("Matrix");

    henrique.assistirSerie("Matrix");
    henrique.assistirSerie("Matrix");
    henrique.assistirSerie("Matrix");
    Serie Titanic = new Serie("Titanic", "PT-BR", "Drama");
    henrique.assistirSerie("Titanic");
    henrique.assistirSerie("Panico");
    henrique.buscarSerieNoAplicativo("Panico");
    henrique.buscarSerieNoAplicativo("Titanic");
    henrique.adicionarSerieEmListaDeAssistirFuturamente("Panico");
    henrique.adicionarSerieEmListaDeAssistirFuturamente("Titanic");
    System.out.println(henrique.retornaListaSeriesAssistirFuturamente());
    System.out.println(henrique.retornaListaSeriesJaAssistidas());

    henrique.removerSerieEmListaDeAssistirFuturamente("Titanic");
    System.out.println(henrique.retornaListaSeriesAssistirFuturamente());
    Conta lucas = new Conta("Lucas@", "oi1213", 123);

    henrique.adicionarSerieEmListaDeAssistirFuturamente("Titanic");
    henrique.adicionarSerieEmListaDeAssistirFuturamente("Matrix");
    henrique.buscarSerieEmSuaListaSeriesAssistirFuturamente("Matrix");
    henrique.buscarSerieEmSuaListaSeriesAssistirFuturamente("Panico");
    henrique.buscarSerieEmSuaListaSeriesAssistidas("Panico");

    System.out.println(Matrix.getContagemVisualizacao()); 
   
    


}





}
