package pt.ipg.appdogs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.net.ContentHandler;

public class BdTabelaRaca implements BaseColumns {


    public static final String NOME_TABELA = "Raca";

    private static final String CAMPO_NOME = "nome";
    private SQLiteDatabase db;

    public BdTabelaRaca(SQLiteDatabase db) {
        this.db = db;

    }

    public void criar(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + " ("+_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAMPO_NOME + " TEXT NOT NULL)");

    }



    public static ContentValues getContentValues(Raca raca){
        ContentValues values = new ContentValues();

        values.put(_ID,raca.getID());
        values.put(CAMPO_NOME,raca.getNome());

        return values;
    }

    public static Raca getCurrentRacaFromCursor(Cursor cursor){
        final int posId = cursor.getColumnIndex(_ID);
        final int posnome = cursor.getColumnIndex(CAMPO_NOME);

        Raca raca = new Raca();

        raca.setID(cursor.getInt(posId));
        raca.setNome(cursor.getString(posnome));

        return raca;
    }


    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA, null, values);
    }





    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(NOME_TABELA, values, whereClause, whereArgs);
    }



    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(NOME_TABELA, whereClause, whereArgs);
    }

}
