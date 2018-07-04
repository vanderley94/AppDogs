package pt.ipg.appdogs;

//import android.app.LoaderManager;
import android.support.v4.content.CursorLoader;
//import android.content.CursorLoader;
import android.content.Intent;
//import android.content.Loader;

import android.widget.CursorAdapter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;


public class EditAppActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int RACA_CURSOR_LOADER_ID = 0;
    private EditText editTextNome;
    private EditText etEditTextTratamento;
    private Spinner spinnerAcidente;
    private Spinner spinnerIDCao;

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
        etEditTextTratamento = (EditText)findViewById(R.id.editTextTratamento);
        spinnerAcidente = (Spinner) findViewById(R.id.spinnerAcidente);
        spinnerIDCao = (Spinner) findViewById(R.id.spinnerIDCao);


        acidente = BdTabelaAcidente.getCurrentAcidenteFromCursor(cursorAcidente);

        editTextNome.setText(acidente.getNome());
        etEditTextTratamento.setText(acidente.getTratamento());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//coloca a verdadeiro a vi

        getSupportLoaderManager().initLoader(RACA_CURSOR_LOADER_ID,null,  this);///verificar isto v

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(RACA_CURSOR_LOADER_ID,null,  this);///verificar v
    }

     public void cancel(View view){
        finish();

     }

     public void save (View view){
        //toda: validaçoes
         // todo: validations
         acidente.setNome(editTextNome.getText().toString());
         acidente.setTratamento(etEditTextTratamento.getText().toString());
         acidente.setTipoDeAcidente((String) spinnerAcidente.getSelectedItem());
         acidente.setIdCao((int) spinnerIDCao.getSelectedItemId());

         int recordsAffected = getContentResolver().update(
                 Uri.withAppendedPath(AppDogsContentProvider.ACIDENTE_URI,Integer.toString(acidente.getID())),
                 BdTabelaAcidente.getContentValues(acidente),
                 null,
                 null
         );

         if(recordsAffected >0){
             Toast.makeText(this,"Acidente guardado com sucesso",Toast.LENGTH_LONG).show();
             finish();
             return;

         }
         Toast.makeText(this,"O acidente nao foi guardado",Toast.LENGTH_LONG).show();

     }

     @NonNull
     @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if(id == RACA_CURSOR_LOADER_ID){
            return new CursorLoader(this,AppDogsContentProvider.RACA_URI, BdTabelaRaca.ALL_COLUMNS,null,null,null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        SimpleCursorAdapter cursorAdapterRaca = new SimpleCursorAdapter(
                this, android.R.layout.activity_list_item,
                data,
                new String[]{BdTabelaRaca.NOME_TABELA},
                new int []{android.R.id.text1}
                );


        spinnerIDCao.setAdapter(cursorAdapterRaca);

        int IdRaca = acidente.getIdCao();

        for(int i = 0; i < spinnerIDCao.getCount(); i++){
            Cursor cursor = (Cursor) spinnerIDCao.getItemAtPosition(i);

            final int posId = cursor.getColumnIndex(BdTabelaRaca._ID); //// contar numeros de id

            if(IdRaca == cursor.getInt(posId)){                       //////passa os ids para spinner
                spinnerIDCao.setSelection(i);
                break;
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

