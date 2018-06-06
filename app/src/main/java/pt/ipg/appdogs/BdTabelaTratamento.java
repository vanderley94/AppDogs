package pt.ipg.appdogs;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaTratamento implements BaseColumns {
    private SQLiteDatabase db;

    public BdTabelaTratamento(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar(){
        db.execSQL("CREATE TABLE Tratamento( ID INTEGER PRIMARY KEY, Descricao TEXT NOT NULL, " +
                "IDAcidente INTEGER  ,  FOREIGN KEY (IDAcidente) REFERENCES BdTabelaAcidente._ID)");
    }
}

