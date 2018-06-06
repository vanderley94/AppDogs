package pt.ipg.appdogs;

public class Acidente {
    private int ID;
    private String TipoDeAcidente;
    private int ID_Cao;

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

    public int getID_Cao() {
        return ID_Cao;
    }

    public void setID_Cao(int ID_Cao) {
        this.ID_Cao = ID_Cao;
    }
}
