package src;
import java.time.*;

class Avaliacao
{
    private Midia midia;
    private LocalDate dataAvaliacao;

    public Avaliacao(Midia midia, LocalDate dataAvaliacao)
    {
        this.midia = midia;
        this.dataAvaliacao = dataAvaliacao;
    }

    public LocalDate getDataAvaliacao()
    {
        return this.dataAvaliacao;
    }
    
}
