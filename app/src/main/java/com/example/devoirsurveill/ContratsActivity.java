package com.example.devoirsurveill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContratsActivity extends AppCompatActivity {
    SQLiteDatabase dataB;
    EditText _txtRechercheNomClient;
    ImageButton _btnRecherche;

    EditText _txtReference,_txtDateDebut,_txtDateFin,_txtRedevence,_txtClient,_txtAdresseClient;
    ImageButton _listDateDebut,_listDateFin,_listClient;

    LinearLayout _layNaviguer,_laySave;
    ImageButton _btnFirst,_btnPrevious,_btnNext,_btnEnd;
    ImageButton _btnAdd,_btnUpdate,_btnCancel;

    //creation d'un curseur
    Cursor curseur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrats);
        _txtRechercheNomClient=(EditText) findViewById(R.id.txtRechercheNomClient);
        _btnRecherche=(ImageButton) findViewById(R.id.btnRecherche);
        _txtReference=(EditText) findViewById(R.id.txtReference);
        _txtDateDebut=(EditText) findViewById(R.id.txtDateDebut);
        _txtDateFin=(EditText) findViewById(R.id.txtDateFin);
        _txtRedevence=(EditText) findViewById(R.id.txtRedevence);
        _txtClient=(EditText) findViewById(R.id.txtClient);
        _txtAdresseClient=(EditText) findViewById(R.id.txtAdresseClient);
        _listDateDebut=(ImageButton)findViewById(R.id.listDateDebut);
        _listDateFin=(ImageButton) findViewById(R.id.listDateFin);
        _listClient=(ImageButton) findViewById(R.id.listClient);
        _layNaviguer=(LinearLayout) findViewById(R.id.layNaviguer);
        _laySave=(LinearLayout) findViewById(R.id.laySave);
        _btnFirst=(ImageButton) findViewById(R.id.btnFirst);
        _btnPrevious=(ImageButton) findViewById(R.id.btnPrevious);
        _btnNext=(ImageButton) findViewById(R.id.btnNext);
        _btnEnd=(ImageButton) findViewById(R.id.btnEnd);
        _btnAdd=(ImageButton) findViewById(R.id.btnAdd);
        _btnUpdate=(ImageButton) findViewById(R.id.btnUpdate);
        _btnCancel=(ImageButton) findViewById(R.id.btnCancel);

        //creation de la base de donnée
        dataB=openOrCreateDatabase("BDAssurance",MODE_PRIVATE,null);
        _layNaviguer.setVisibility(View.INVISIBLE);
        //_laySave.setVisibility(View.INVISIBLE);
       // dataB.execSQL("drop table Contrat");
        //Requêtes de creation des tables
        dataB.execSQL("CREATE TABLE IF NOT EXISTS Client(id integer primary key autoincrement,nom varchar,adresse varchar,tel integer,fax integer,contact varchar,telContact integer);");
        dataB.execSQL("CREATE TABLE IF NOT EXISTS Contrat(id integer primary key autoincrement,reference Varchar,dateDebut Varchar,dateFin Varchar,redevence double,client_id INTEGER);");

        /*on verifie si les deux tables sont vides.Si oui alors on fait une requête d'inserssion*/
        SQLiteStatement Cli=dataB.compileStatement("SELECT COUNT(*) FROM Client");
        SQLiteStatement Contrt=dataB.compileStatement("SELECT COUNT(*) FROM Contrat");
        long e,f;
        e=Cli.simpleQueryForLong();
        f=Contrt.simpleQueryForLong();

        //si une des deux tables est vide,alors on va remplir à nouveau les deux tables.
        if(e==0|f==0){
            dataB.execSQL("INSERT INTO Client(nom,adresse) VALUES(?,?)",new String[]{"Taky","Menzha6"});
            dataB.execSQL("INSERT INTO Contrat(reference,dateDebut,dateFin,redevence) VALUES(?,?,?,?)",new String[]{"Tunis","29/11/2022","31/11/2022","2000"});
        }
        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                curseur=dataB.rawQuery("SELECT Contrat.*,Client.nom,Client.adresse,Client.id " +
                                         "FROM Contrat LEFT OUTER JOIN Client ON " +
                                         " Client.nom LIKE ? " +
                                         "AND Client.id=Contrat.client_id",new String[]{"%"+_txtRechercheNomClient.getText().toString()+"%"});
                try {
                    curseur.moveToFirst();
                    _txtReference.setText(curseur.getString(1));
                    _txtDateDebut.setText(curseur.getString(2));
                    _txtDateFin.setText(curseur.getString(3));
                    _txtRedevence.setText(curseur.getString(4));
                    _txtClient.setText(curseur.getString(6));
                    _txtAdresseClient.setText(curseur.getString(7));
                    if(curseur.getCount()==1){
                        _layNaviguer.setVisibility(View.VISIBLE);

                    }else{
                        _btnNext.setEnabled(false);
                        _btnPrevious.setEnabled(true);
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Aucun resultat ",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    _txtReference.setText("");
                    _txtDateDebut.setText("");
                    _txtDateFin.setText("");
                    _txtRedevence.setText("");
                    _txtClient.setText("");
                    _txtAdresseClient.setText("");
                    _layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });
        _btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    curseur.moveToFirst();
                    _txtReference.setText(curseur.getString(1));
                    _txtDateDebut.setText(curseur.getString(2));
                    _txtDateFin.setText(curseur.getString(3));
                    _txtRedevence.setText(curseur.getString(4));
                    _txtClient.setText(curseur.getString(6));
                    _txtAdresseClient.setText(curseur.getString(7));
                    if(curseur.getCount()==1){
                        _layNaviguer.setVisibility(View.VISIBLE);

                    }else{
                        _btnNext.setEnabled(false);
                        _btnPrevious.setEnabled(true);
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Aucun resultat ",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    _txtReference.setText("");
                    _txtDateDebut.setText("");
                    _txtDateFin.setText("");
                    _txtRedevence.setText("");
                    _txtClient.setText("");
                    _txtAdresseClient.setText("");
                    _layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });
        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                         try {
                    curseur.moveToNext();
                    _txtReference.setText(curseur.getString(1));
                    _txtDateDebut.setText(curseur.getString(2));
                    _txtDateFin.setText(curseur.getString(3));
                    _txtRedevence.setText(curseur.getString(4));
                    _txtClient.setText(curseur.getString(6));
                    _txtAdresseClient.setText(curseur.getString(7));
                    if(curseur.getCount()==1){
                        _layNaviguer.setVisibility(View.VISIBLE);

                    }else{
                        _btnNext.setEnabled(false);
                        _btnPrevious.setEnabled(true);
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Aucun resultat ",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    _txtReference.setText("");
                    _txtDateDebut.setText("");
                    _txtDateFin.setText("");
                    _txtRedevence.setText("");
                    _txtClient.setText("");
                    _txtAdresseClient.setText("");
                    _layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });
        _btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    curseur.moveToPrevious();
                    _txtReference.setText(curseur.getString(1));
                    _txtDateDebut.setText(curseur.getString(2));
                    _txtDateFin.setText(curseur.getString(3));
                    _txtRedevence.setText(curseur.getString(4));
                    _txtClient.setText(curseur.getString(6));
                    _txtAdresseClient.setText(curseur.getString(7));
                    if(curseur.getCount()==1){
                        _layNaviguer.setVisibility(View.VISIBLE);

                    }else{
                        _btnNext.setEnabled(false);
                        _btnPrevious.setEnabled(true);
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Aucun resultat ",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    _txtReference.setText("");
                    _txtDateDebut.setText("");
                    _txtDateFin.setText("");
                    _txtRedevence.setText("");
                    _txtClient.setText("");
                    _txtAdresseClient.setText("");
                    _layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });
        _btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    curseur.moveToLast();
                    _txtReference.setText(curseur.getString(1));
                    _txtDateDebut.setText(curseur.getString(2));
                    _txtDateFin.setText(curseur.getString(3));
                    _txtRedevence.setText(curseur.getString(4));
                    _txtClient.setText(curseur.getString(6));
                    _txtAdresseClient.setText(curseur.getString(7));
                    if(curseur.getCount()==1){
                        _layNaviguer.setVisibility(View.VISIBLE);

                    }else{
                        _btnNext.setEnabled(false);
                        _btnPrevious.setEnabled(true);
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Aucun resultat ",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    _txtReference.setText("");
                    _txtDateDebut.setText("");
                    _txtDateFin.setText("");
                    _txtRedevence.setText("");
                    _txtClient.setText("");
                    _txtAdresseClient.setText("");
                    _layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}