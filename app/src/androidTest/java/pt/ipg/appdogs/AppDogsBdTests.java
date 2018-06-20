package pt.ipg.appdogs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AppDogsBdTests {

    private Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }
    @Before
    public void setUp() {
        getContext().deleteDatabase(BdAppDogsOpenHelper.DOGS_DB);
    }

    @Test
    public void openAppDogsBdTest(){
        //Contexto do aplicativo em teste
        Context appContext = getContext();

        BdAppDogsOpenHelper bdAppDogsOpenHelper = new BdAppDogsOpenHelper(appContext);
        SQLiteDatabase db = bdAppDogsOpenHelper.getReadableDatabase();
        assertTrue("Não pode Abrir, Crie a base de dados da AppDogs",db.isOpen());
        db.close();
    }

    @Test
    public void AcidenteCRUDtest(){
        BdAppDogsOpenHelper bdAppDogsOpenHelper = new BdAppDogsOpenHelper(getContext());
        SQLiteDatabase db =bdAppDogsOpenHelper.getWritableDatabase();
        BdTabelaRaca tabelaRaca = new BdTabelaRaca(db);

        Raca raca = new Raca();
        raca.setNome("Pitbul");
        long idCao = tabelaRaca.insert(BdTabelaRaca.getContentValues(raca));

        BdTabelaAcidente tabelaAcidente = new BdTabelaAcidente(db);
        //Insert / create (C)RUD
        Acidente acidente = new Acidente();

        acidente.setTipoDeAcidente("Lesao na pata esquerda");
        acidente.setIdCao((int) idCao);
        long id = tabelaAcidente.insert(BdTabelaAcidente.getContentValues(acidente));

        assertNotEquals("Falhou ao inserir o Acidente",-1,id);

        //read C(R)UD
        Cursor cursor = tabelaAcidente.query(BdTabelaAcidente.ALL_COLUMNS,null,null,null,null,null);
        assertEquals("Failed to read acidente", 1, cursor.getCount());
        assertTrue("Failed to read the first acidente", cursor.moveToNext());
        assertEquals("Incorrect tipo de acidente ","Lesao na pata esquerda" , acidente.getTipoDeAcidente());
        // update CR(U)D
        acidente.setTipoDeAcidente("Lesao na pata");
  ;

        int rowsAffected = tabelaAcidente.update(
                BdTabelaAcidente.getContentValues(acidente),
                BdTabelaAcidente._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Failed to update book", 1, rowsAffected);

        // query/read C(R)UD
        assertEquals("Incorrect tipo acidente ","Lesao na pata" , acidente.getTipoDeAcidente());

// delete CRU(D)
        rowsAffected = tabelaAcidente.delete(
                BdTabelaAcidente._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Failed to delete book", 1, rowsAffected);

        Cursor cursorU = tabelaAcidente.query(BdTabelaAcidente.ALL_COLUMNS, null, null, null, null, null);
        assertEquals("ACIDENTES found after delete ???", 0, cursorU.getCount());
    }

    @Test
    public void racaCRUDtest(){
        BdAppDogsOpenHelper bdAppDogsOpenHelper = new BdAppDogsOpenHelper(getContext());

        SQLiteDatabase db = bdAppDogsOpenHelper.getWritableDatabase();

        BdTabelaRaca tabelaRaca = new BdTabelaRaca(db);

        Raca raca = new Raca();
        raca.setNome("bullDog");

        //Insert/create (C)RUD
        long id = insertRaca(tabelaRaca,raca);

        //query/read C(R)UD
        raca = ReadFirstRaca(tabelaRaca,"Labrador",id);

        //update CR(U)D
        raca.setNome("Labrador");
        int rowsAffected = tabelaRaca.update(
                BdTabelaRaca.getContentValues(raca),
                BdTabelaRaca._ID+"=?",
                new String[]{Long.toString(id)}

        );
        assertEquals("Falhou a atulização da raça", 1, rowsAffected);
        //query/read C(R)UD
        raca = ReadFirstRaca(tabelaRaca,"MMMM",id);

        //delete CRU(D)
        rowsAffected = tabelaRaca.delete(
                BdTabelaRaca._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Falhou o delete da raça", 1, rowsAffected);

        Cursor cursor = tabelaRaca.query(BdTabelaRaca.ALL_COLUMNS,null,null,null,null,null);
        assertEquals("A raça encontrada depois do delete?", 0, cursor.getCount());

    }


    private long insertRaca(BdTabelaRaca tabelaRaca, Raca raca){
        long id = tabelaRaca.insert(BdTabelaRaca.getContentValues(raca));

        assertNotEquals("Falhou a inserir uma Raça",-1,id);

        return id;
    }

    @NonNull
    private Raca ReadFirstRaca(BdTabelaRaca tabelaRaca, String expectedNome, long expectedId){
        Cursor cursor = tabelaRaca.query(BdTabelaRaca.ALL_COLUMNS,null,null,null,null,null );
        assertEquals("Falhou a leitura das Raças",1,cursor.getColumnCount());
        assertTrue("Falhou a leitura da primeira raça",cursor.moveToNext());

        Raca raca = BdTabelaRaca.getCurrentRacaFromCursor(cursor);

        assertEquals("Nome da raça incorreto",expectedNome,raca.getNome());
        assertEquals("Nome da raça incorreto",expectedId,raca.getID());

        assertEquals("Incorrect category name", expectedNome, raca.getNome());
        assertEquals("Incorrect category id", expectedId, raca.getID());



        return raca;

    }





















}
