package pt.ipg.appdogs;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaTratamento implements BaseColumns {
    public static final String CAMPO_ID_ACIDENTE = "IDAcidente";
    public static final String TABELA_NOME = "Tratamento";
    public static final String CAMPO_DESCRICAO = "Descricao";
    private SQLiteDatabase db;

    public BdTabelaTratamento(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar(){
        db.execSQL("CREATE TABLE " + TABELA_NOME + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_DESCRICAO + " TEXT NOT NULL, " +
                CAMPO_ID_ACIDENTE + " INTEGER  ,  FOREIGN KEY (IDAcidente) REFERENCES " +
                BdTabelaAcidente.NOME_TABELA +
                " ("+BdTabelaAcidente._ID+")" +
                ")"
        );
    }



}

