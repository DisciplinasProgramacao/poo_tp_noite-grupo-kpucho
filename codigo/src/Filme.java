package src;

public class Filme extends Midia 
{
  private int duracao;

  public Filme(String IdFilme, String nome, String dataDeLancamento,int duracao, int contagemVisualizacao) 
  {
    super(IdFilme, nome, dataDeLancamento, contagemVisualizacao);
    this.duracao = duracao;
  }

  public int getDuracao() 
  {
    return this.duracao;
  }

  public void setDuracao(int duracao) 
  {
    this.duracao = duracao;
  }

}
