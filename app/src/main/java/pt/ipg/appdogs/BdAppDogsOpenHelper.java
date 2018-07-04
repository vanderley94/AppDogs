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

        BdTabelaTratamento bdTabelaTratamento = new BdTabelaTratamento(db);
        bdTabelaTratamento.criar();

       if(!PRODUCTION){  ///modo protecao
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

        int idAcidenteL =  (int) bdTabelaAcidente.insert(BdTabelaAcidente.getContentValues(acidente));

        acidente = new Acidente();
        acidente.setNome("Bulldog");
        acidente.setTipoDeAcidente("Lesão na parte de tronco");
        acidente.setTratamento("Amarar bem a perna!");
        acidente.setIdCao(idRacaBulldog);
        bdTabelaAcidente.insert(bdTabelaAcidente.getContentValues(acidente));

        int idAcidenteB =  (int) bdTabelaAcidente.insert(BdTabelaAcidente.getContentValues(acidente));


        acidente = new Acidente();
        acidente.setNome("Labrador");
        acidente.setTipoDeAcidente("Lesão na cabeça");
        acidente.setTratamento(" Estancar o sangue!");
        acidente.setIdCao(idRacaLabrador);

        bdTabelaAcidente.insert(bdTabelaAcidente.getContentValues(acidente));

        int idAcidenteX =  (int) bdTabelaAcidente.insert(BdTabelaAcidente.getContentValues(acidente));



        ///////////////////////////////////////////////////////
        BdTabelaTratamento bdTabelaTratamento = new BdTabelaTratamento(db);

        Tratamento tratamento = new Tratamento();
        tratamento.setDescricao("Esticar a pena e dirigir ao centro de tramaneto!");
        tratamento.setIDAcidente(idAcidenteL);
        bdTabelaTratamento.insert(bdTabelaTratamento.getContentValues(tratamento));

        tratamento = new Tratamento();
        tratamento.setDescricao("Estacar o Sangue!");
        tratamento.setIDAcidente(idAcidenteB);
        bdTabelaTratamento.insert(bdTabelaTratamento.getContentValues(tratamento));

        tratamento = new Tratamento();
        tratamento.setDescricao("Estacar o Sangue!");
        tratamento.setIDAcidente(idAcidenteX);
        bdTabelaTratamento.insert(bdTabelaTratamento.getContentValues(tratamento));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
