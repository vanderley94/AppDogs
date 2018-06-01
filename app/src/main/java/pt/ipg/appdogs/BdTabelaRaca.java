package pt.ipg.appdogs;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaRaca implements BaseColumns {


    private static final String NOME_TABELA = "Raca";
    private static final String _ID = "ID";
    private static final String CAMPO_NOME = "nome";
    private SQLiteDatabase db;

    public BdTabelaRaca(SQLiteDatabase bd) {
        this.db = db;

    }

    public void criar(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_NOME + " TEXT NOT NULL)");

    }
}
