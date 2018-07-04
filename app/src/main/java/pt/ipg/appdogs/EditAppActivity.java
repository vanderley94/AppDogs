package pt.ipg.appdogs;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;


public class EditAppActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int RACA_CURSOR_LOADER_ID = 0;
    private EditText editTextNome;
    private EditText etEditTextTratamento;
    private Spinner spinnerAcidente;
    private Acidente acidente;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setContentView(toolbar);

        Intent intent = getIntent();

        int acidenteId = intent.getIntExtra(MainActivity.ACIDENTE_ID,-1);

        if(acidenteId == -1){
            finish();;
            return;
        }

        Cursor cursorAcidente = getContentResolver().query(
                Uri.withAppendedPath(AppDogsContentProvider.ACIDENTE_URI,Integer.toString(acidenteId)),
                BdTabelaAcidente.ALL_COLUMNS,null,null,null
        );
        if(!cursorAcidente.moveToNext()){
            Toast.makeText(this,"Acidente not found",Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        etEditTextTratamento = findViewById(R.id.editTextTratamento);
        spinnerAcidente = findViewById(R.id.spinnerAcidente);

        acidente = BdTabelaAcidente.getCurrentAcidenteFromCursor(cursorAcidente);

        editTextNome.setText(acidente.getNome());
        etEditTextTratamento.setText(acidente.getTratamento());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//coloca a verdadeiro a vi

        getSupportLoaderManager().initLoader(RACA_CURSOR_LOADER_ID,null, (android.support.v4.app.LoaderManager.LoaderCallbacks<Object>) this);///verificar isto

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(RACA_CURSOR_LOADER_ID,null, (android.support.v4.app.LoaderManager.LoaderCallbacks<Object>) this);///verificar
    }

     public void cancel(View view){
        finish();

     }

     public void save (View view){
        //toda: validaçoes
         // todo: validations
         acidente.setNome(editTextNome.getText().toString());
         acidente.setTratamento(etEditTextTratamento.getText().toString());
         acidente.setTipoDeAcidente(spinnerAcidente.getSelectedItemId());

     }

     @NonNull
     @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

