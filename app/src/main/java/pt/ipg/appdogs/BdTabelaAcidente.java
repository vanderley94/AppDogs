package pt.ipg.appdogs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class  BdTabelaAcidente implements BaseColumns {

    public static final String NOME_TABELA = "Acidente";
    public static final String CAMPO_ID_RACA = "IdCao";
    public static final String CAMPO_TIPO_DE_ACIDENTE = "TipoDeAcidente";


    public static final String [] ALL_COLUMNS = new String[] { _ID, CAMPO_ID_RACA, CAMPO_TIPO_DE_ACIDENTE };

    private SQLiteDatabase db;
    public BdTabelaAcidente(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                CAMPO_TIPO_DE_ACIDENTE + " TEXT NOT NULL, " +
                CAMPO_ID_RACA + " INTEGER," +
                "FOREIGN KEY (" + CAMPO_ID_RACA + ") REFERENCES " +
                BdTabelaRaca.NOME_TABELA +
                "(" + BdTabelaRaca._ID+")" +
                ")"
        );
    }
    public static ContentValues getContentValues(Acidente acidente){
        ContentValues values = new ContentValues();

        //values.put(_ID,acidente.getID());
        values.put(CAMPO_ID_RACA,acidente.getIdCao());
        values.put(CAMPO_TIPO_DE_ACIDENTE,acidente.getTipoDeAcidente());

        return values;
    }

    public static Acidente getCurrentAcidenteFromCursor(Cursor cursor) {
        final int posId = cursor.getColumnIndex(_ID);
        final int posIdCao = cursor.getColumnIndex(CAMPO_ID_RACA);
        final int posTipoDeAcidente = cursor.getColumnIndex(CAMPO_TIPO_DE_ACIDENTE);

        Acidente acidente = new Acidente();

        acidente.setID(cursor.getInt(posId));
        acidente.setIdCao(cursor.getInt(posIdCao));
        acidente.setTipoDeAcidente(cursor.getString(posTipoDeAcidente));

        return acidente;
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



    public Cursor query (String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}