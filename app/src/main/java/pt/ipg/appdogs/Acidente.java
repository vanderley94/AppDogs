package pt.ipg.appdogs;

public class Acidente {
    private int ID;
    private String TipoDeAcidente;
    private int IdCao;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
