package pt.ipg.appdogs;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AppDogsContentProvider extends ContentProvider {

    public static final String AUTHORITY = "pt.ipg.appdogs";
    public static final int RACA = 100;
    public static final int RACA_ID = 101;
    public static final int ACIDENTE = 200;
    public static final int ACIDENTE_ID = 201;
    public static final int TRATAMENTO = 300;
    public static final int TRATAMENTO_ID = 301;
    BdAppDogsOpenHelper appDogsOpenHelper;

    private static UriMatcher getAppDogsUriMatcher(){

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY,"raca", RACA);
        uriMatcher.addURI(AUTHORITY,"raca/#", RACA_ID);

        uriMatcher.addURI(AUTHORITY,"acidente", ACIDENTE);
        uriMatcher.addURI(AUTHORITY,"acidente/#", ACIDENTE_ID);

        uriMatcher.addURI(AUTHORITY,"tratamento", TRATAMENTO);
        uriMatcher.addURI(AUTHORITY,"tratamento/#", TRATAMENTO_ID);



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

            case TRATAMENTO:
                return new BdTabelaAcidente(db).query(projection,selection,selectionArgs,null,null,sortOrder);

            case RACA_ID:
                return new BdTabelaRaca(db).query(projection,BdTabelaRaca._ID + "=?",new String[]{id},null,null,null);

            case ACIDENTE_ID:
                return new BdTabelaAcidente(db).query(projection,BdTabelaAcidente._ID + "=?",new String[]{id},null,null,null);

            case TRATAMENTO_ID:
                return new BdTabelaAcidente(db).query(projection,BdTabelaAcidente._ID + "=?",new String[]{id},null,null,null);

            default:
                throw new UnsupportedOperationException("Invalid URI: " + uri);



        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
