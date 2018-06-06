package pt.ipg.appdogs;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaAcidente implements BaseColumns {

    private SQLiteDatabase db;
    public BdTabelaAcidente(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar(){
        db.execSQL("CREATE TABLE Acidente( ID INTEGER PRIMARY KEY, TipoDeAcidente TEXT NOT NULL, " +
                "ID_Cao INTEGER  ,  FOREIGN KEY (ID_Cao) REFERENCES BdTabelaRaca._ID)");
    }
}
