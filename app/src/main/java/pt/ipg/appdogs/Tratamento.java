package pt.ipg.appdogs;

public class Tratamento {
    private int ID;
    private String Descricao;
    private int IDAcidente;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public int getIDAcidente() {
        return IDAcidente;
    }

    public void setIDAcidente(int IDAcidente) {
        this.IDAcidente = IDAcidente;
    }
}
