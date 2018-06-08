package pt.ipg.appdogs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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














    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("pt.ipg.appdogs", appContext.getPackageName());
    }*/
}
