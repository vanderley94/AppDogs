package pt.ipg.appdogs;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.text.BreakIterator;

public class AppDogsContentProvider extends ContentProvider {

    public static final String AUTHORITY = "pt.ipg.appdogs";

    public static final Uri Base_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri ACIDENTE_URI = Uri.withAppendedPath(Base_URI,BdTabelaAcidente.NOME_TABELA);
    public static final Uri RACA_URI = Uri.withAppendedPath(Base_URI,BdTabelaRaca.NOME_TABELA);


    public static final int RACA = 100;
    public static final int RACA_ID = 101;
    public static final int ACIDENTE = 200;
    public static final int ACIDENTE_ID = 201;


    public static final String MULTIPLE_ITEMS = "vnd.android.cursor.dir";
    public static final String SINGLE_ITEM = "vnd.android.cursor.item";
    BdAppDogsOpenHelper appDogsOpenHelper;

    private static UriMatcher getAppDogsUriMatcher(){

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY,"raca", RACA);
        uriMatcher.addURI(AUTHORITY,"raca/#", RACA_ID);

        uriMatcher.addURI(AUTHORITY,"acidente", ACIDENTE);
        uriMatcher.addURI(AUTHORITY,"acidente/#", ACIDENTE_ID);



        return uriMatcher;
    }

    @Nullable
    @Override
    public boolean onCreate() {
        appDogsOpenHelper = new BdAppDogsOpenHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = appDogsOpenHelper.getReadableDatabase();
        String id = uri.getLastPathSegment();

        UriMatcher matcher = getAppDogsUriMatcher();

        switch (matcher.match(uri)){
            case RACA:
                return new BdTabelaRaca(db).query(projection,selection,selectionArgs,null,null,sortOrder);

            case ACIDENTE:
                return new BdTabelaAcidente(db).query(projection,selection,selectionArgs,null,null,sortOrder);


            case RACA_ID:
                return new BdTabelaRaca(db).query(projection,BdTabelaRaca._ID + "=?",new String[]{id},null,null,null);

            case ACIDENTE_ID:
                return new BdTabelaAcidente(db).query(projection,BdTabelaAcidente._ID + "=?",new String[]{id},null,null,null);



            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);



        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        UriMatcher matcher = getAppDogsUriMatcher();
        switch (matcher.match(uri)){
            case RACA:
                return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + BdTabelaRaca.NOME_TABELA;

            case ACIDENTE:
                return MULTIPLE_ITEMS+ "/" + AUTHORITY + "/" + BdTabelaAcidente.NOME_TABELA;


            case RACA_ID:
                return SINGLE_ITEM + "/" + AUTHORITY + "/" + BdTabelaRaca.NOME_TABELA;

            case ACIDENTE_ID:
                return SINGLE_ITEM+ "/" + AUTHORITY + "/" + BdTabelaAcidente.NOME_TABELA;


            default:
                throw new UnsupportedOperationException("UnKnown URI"+ uri);
        }


    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = appDogsOpenHelper.getWritableDatabase();

        UriMatcher matcher = getAppDogsUriMatcher();

        long id = -1;

        switch (matcher.match(uri)){
            case RACA:
                id = new BdTabelaRaca(db).insert(values);
                break;

            case ACIDENTE:
                id = new BdTabelaAcidente(db).insert(values);


            default:
                throw new UnsupportedOperationException("Invalid URI: "+ uri);
        }

        if(id > 0){
            notifyChanges(uri);
            return Uri.withAppendedPath(uri,Long.toString(id));
        }else{
            throw new SQLException("Could not insert record");
        }
    }

    private void notifyChanges(@NonNull Uri uri){
        getContext().getContentResolver().notifyChange(uri,null);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase db = appDogsOpenHelper.getWritableDatabase();

        UriMatcher matcher = getAppDogsUriMatcher();
        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)) {
            case RACA:
                rows = new BdTabelaRaca(db).delete(BdTabelaRaca._ID + "=?", new String[]{id});
                break;

            case ACIDENTE:
                rows = new BdTabelaAcidente(db).delete(BdTabelaAcidente._ID + "=?", new String[]{id});
                break;

            default:
                throw new UnsupportedOperationException("Invalid URI: "+ uri);
        }

        if(rows > 0 ){
            notifyChanges(uri);
        }
        return rows;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase db = appDogsOpenHelper.getWritableDatabase();

        UriMatcher matcher = getAppDogsUriMatcher();

        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)){
            case RACA_ID:
                rows = new BdTabelaRaca(db).update(values,BdTabelaRaca._ID+"=?", new String[]{id});
                break;

            case ACIDENTE_ID:
                rows = new BdTabelaAcidente(db).update(values,BdTabelaAcidente._ID+"=?", new String[]{id});
                break;

        }

        if (rows > 0){
            notifyChanges(uri);
        }

        return rows;
    }
}
