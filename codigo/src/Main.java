import java.io.IOException;

public class Main 
{
    
public static void main(String[]args) throws IOException
{
    Aplicativo aplicativo = new Aplicativo();
    Conta henrique = new Conta("henrique", "henriquecm122", "1547");
    Aplicativo.buscarSerie("Titanic");
    henrique.assistirSerie("Titanic");
    henrique.assistirSerie("Titanic");
    
    Conta Lucas = aplicativo.getConta("Lucas22");
    Lucas.assistirSerie("Titanic");
    Conta testeLucas = new Conta("lucas", "Lucas22", "13342");
    


   
    


}





}
