package pt.ipg.appdogs;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks <Cursor>{

    public static final String ACIDENTE_ID = "ACIDENTE_ID";
    private static int ACIDENTE_CURSOR_LOADER_ID = 0;




    private RecyclerView recyclerViewAci;
    private AppDogsCursorAdapter appDogsCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/




        recyclerViewAci = (RecyclerView) findViewById(R.id.RecyclerViewAci);

        recyclerViewAci.setLayoutManager(new LinearLayoutManager(this));
        appDogsCursorAdapter = new  AppDogsCursorAdapter(this);
        recyclerViewAci.setAdapter(appDogsCursorAdapter);

        appDogsCursorAdapter.setViewHolderClickListener(new View.OnClickListener() { // quando clicar para ver a lista
            @Override
            public void onClick(View v) {
                editAppDogs();
            }
        });

        getSupportLoaderManager().initLoader(ACIDENTE_CURSOR_LOADER_ID, null, this);

    }
    private void editAppDogs() {
            ////////////A acrescentar o que visualizar dentro da lista ***********************
        int id = appDogsCursorAdapter.getLastAcidenteClicked();

        Intent intent = new Intent(this,EditAppActivity.class);
        intent.putExtra(ACIDENTE_ID,id);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(ACIDENTE_CURSOR_LOADER_ID,null,this); /// Para reustarar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, @Nullable Bundle args) {

        if(id == ACIDENTE_CURSOR_LOADER_ID){
            return new CursorLoader(this,AppDogsContentProvider.ACIDENTE_URI, BdTabelaAcidente.ALL_COLUMNS,null,null,null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        appDogsCursorAdapter.refreahData(data);

    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<Cursor> loader) {
        appDogsCursorAdapter.refreahData(null);

    }
}
