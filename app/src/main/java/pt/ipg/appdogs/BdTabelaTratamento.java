package pt.ipg.appdogs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaTratamento implements BaseColumns {
    public static final String CAMPO_ID_ACIDENTE = "IDAcidente";
    public static final String NOME_TABELA = "Tratamento";
    public static final String CAMPO_DESCRICAO = "Descricao";
    private SQLiteDatabase db;

    public static final String [] ALL_COLUMNS = new String[] { _ID, CAMPO_ID_ACIDENTE , CAMPO_DESCRICAO  };

    public BdTabelaTratamento(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + "( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_DESCRICAO + " TEXT NOT NULL, " +
                CAMPO_ID_ACIDENTE + " INTEGER  ,  FOREIGN KEY (IDAcidente) REFERENCES " +
                BdTabelaAcidente.NOME_TABELA +
                " ("+BdTabelaAcidente._ID+")" +
                ")"
        );
    }

    public static ContentValues getContentValues(Tratamento tratamento){
        ContentValues values = new ContentValues();

        values.put(_ID,tratamento.getID());
        values.put(CAMPO_ID_ACIDENTE,tratamento.getIDAcidente());
        values.put(CAMPO_DESCRICAO,tratamento.getDescricao());

        return values;
    }

    public static Tratamento getCurrentTratamentoFromCursor(Cursor cursor){
        final int posId = cursor.getColumnIndex(_ID);
        final int posIDAcidente = cursor.getColumnIndex(CAMPO_ID_ACIDENTE);
        final int posDescricao = cursor.getColumnIndex(CAMPO_DESCRICAO);

        Tratamento tratamento = new Tratamento();

        tratamento.setID(cursor.getInt(posId));
        tratamento.setIDAcidente(cursor.getInt(posIDAcidente));
        tratamento.setDescricao(cursor.getString(posDescricao));

        return tratamento;

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

