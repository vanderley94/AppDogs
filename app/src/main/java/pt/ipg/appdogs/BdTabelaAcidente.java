package pt.ipg.appdogs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class  BdTabelaAcidente implements BaseColumns {

    public static final String NOME_TABELA = "Acidente";

    public static final String CAMPO_NOME = "nome";
    public static final String CAMPO_ID_RACA = "IdCao";
    public static final String CAMPO_TIPO_DE_ACIDENTE = "TipoDeAcidente";
    public static final String CAMPO_TRATAMENTO = "Tratamento";



    public static final String [] ALL_COLUMNS = new String[] { _ID, CAMPO_ID_RACA, CAMPO_TIPO_DE_ACIDENTE };

    private SQLiteDatabase db;
    public BdTabelaAcidente(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ CAMPO_NOME + " TEXT NOT NULL,"+
                CAMPO_TIPO_DE_ACIDENTE + " TEXT NOT NULL, " + CAMPO_TRATAMENTO + " TEXT NOT NULL, " +
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
        values.put(CAMPO_NOME,acidente.getNome());
        values.put(CAMPO_ID_RACA,acidente.getIdCao());
        values.put(CAMPO_TIPO_DE_ACIDENTE,acidente.getTipoDeAcidente());
        values.put(CAMPO_TRATAMENTO,acidente.getTratamento());

        return values;
    }

    public static Acidente getCurrentAcidenteFromCursor(Cursor cursor) {

        final int posNome = cursor.getColumnIndex(CAMPO_NOME);
        final int posId = cursor.getColumnIndex(_ID);
        final int posIdCao = cursor.getColumnIndex(CAMPO_ID_RACA);
        final int posTipoDeAcidente = cursor.getColumnIndex(CAMPO_TIPO_DE_ACIDENTE);
        final int posTratamento = cursor.getColumnIndex(CAMPO_TRATAMENTO);

        Acidente acidente = new Acidente();

        acidente.setNome(cursor.getString(posNome));
        acidente.setID(cursor.getInt(posId));
        acidente.setIdCao(cursor.getInt(posIdCao));
        acidente.setTipoDeAcidente(cursor.getString(posTipoDeAcidente));
        acidente.setTratamento(cursor.getString(posTratamento));

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