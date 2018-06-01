package pt.ipg.appdogs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdAppDogsOpenHelper extends SQLiteOpenHelper {

    private static final String DOGS_DB = "Dogs.db";
    private static final int BASEDADOS_VERSION = 1;

    public BdAppDogsOpenHelper(Context context){
        super(context , DOGS_DB,null, BASEDADOS_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BdTabelaRaca bdTabelaRaca = new BdTabelaRaca(db);
        bdTabelaRaca.criar();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
