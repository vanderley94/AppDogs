package pt.ipg.appdogs;

public class Acidente {
    private int ID;
    private String Nome;
    private String TipoDeAcidente;
    private String Tratamento;
    private int IdCao;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTipoDeAcidente() {
        return TipoDeAcidente;
    }

    public void setTipoDeAcidente(String tipoDeAcidente) {
        TipoDeAcidente = tipoDeAcidente;
    }

    public int getIdCao() {
        return IdCao;
    }

    public void setIdCao(int idCao) { IdCao = idCao; }

    public String getTratamento() {
        return Tratamento;
    }

    public void setTratamento(String tratamento) {
        Tratamento = tratamento;
    }
}
