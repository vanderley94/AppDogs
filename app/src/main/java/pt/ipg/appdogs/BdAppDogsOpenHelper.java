package pt.ipg.appdogs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdAppDogsOpenHelper extends SQLiteOpenHelper {

    private static final boolean PRODUCTION = false;

    public static final String DOGS_DB = "Dogs.db";
    private static final int BASEDADOS_VERSION = 1;

    public BdAppDogsOpenHelper(Context context){
        super(context , DOGS_DB,null, BASEDADOS_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BdTabelaRaca bdTabelaRaca = new BdTabelaRaca(db);
        bdTabelaRaca.criar();

        BdTabelaAcidente bdTabelaAcidente = new BdTabelaAcidente(db);
        bdTabelaAcidente.criar();

       if(!PRODUCTION){  ///
            seed(db);
        }

    }

    private void seed(SQLiteDatabase db){  //passar dados de para dados
        BdTabelaRaca bdTabelaRaca = new BdTabelaRaca(db);

        Raca raca = new Raca();
        raca.setNome("Pitbul");
        int idRacaPitbul = (int) bdTabelaRaca.insert(BdTabelaRaca.getContentValues(raca));


        raca = new Raca();
        raca.setNome("Bulldog");
        int idRacaBulldog = (int) bdTabelaRaca.insert(BdTabelaRaca.getContentValues(raca));

        raca = new Raca();
        raca.setNome("Labrador");
        int idRacaLabrador = (int) bdTabelaRaca.insert(BdTabelaRaca.getContentValues(raca));



        BdTabelaAcidente bdTabelaAcidente = new BdTabelaAcidente(db);

        Acidente acidente = new Acidente();
        acidente.setNome("Pitbul");
        acidente.setTipoDeAcidente("Lesão nas patas");
        acidente.setTratamento("Lavar depois passar o antibiotico !");
        acidente.setIdCao(idRacaPitbul);
        bdTabelaAcidente.insert(bdTabelaAcidente.getContentValues(acidente));


        acidente = new Acidente();
        acidente.setNome("Bulldog");
        acidente.setTipoDeAcidente("Lesão na parte de tronco");
        acidente.setTratamento("Amarar bem a perna!");
        acidente.setIdCao(idRacaBulldog);
        bdTabelaAcidente.insert(bdTabelaAcidente.getContentValues(acidente));



        acidente = new Acidente();
        acidente.setNome("Labrador");
        acidente.setTipoDeAcidente("Lesão na cabeça");
        acidente.setTratamento(" Estancar o sangue!");
        acidente.setIdCao(idRacaLabrador);

        bdTabelaAcidente.insert(bdTabelaAcidente.getContentValues(acidente));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
