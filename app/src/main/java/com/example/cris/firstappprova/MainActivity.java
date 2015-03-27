package com.example.cris.firstappprova;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.renderscript.RenderScript;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends Activity {
    private static int numeroIgnoto;
    private static int punti = 0;
    private static int tentativi=3;
    private static TextView textView;
    private static int numInserito=0;
    private static Vibrator v;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_main);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Button invia = (Button) findViewById(R.id.button);
        generaNumero();
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et= null;
                String num=null;

                try{
                 et = (EditText) findViewById(R.id.editText);
                    num = et.getText().toString();

                    numInserito = Integer.valueOf(num);

                    tryNumber(numInserito);
                    et.setText("");


            }
            catch (NumberFormatException e){


            }}
        });

    }

    public static void generaNumero(){

        numeroIgnoto= (int)(Math.random()*10+1);

    }
    public void cambiaPunteggio(int n){
        punti+=n;
        textView = (TextView) findViewById(R.id.punteggio);
        textView.setText("PUNTEGGIO: "+punti);

    }
    public void setTries(int n){
        tentativi+=n;
        TextView tries =  (TextView) findViewById(R.id.tentativi);
        tries.setText("Tentativi rimasti: "+tentativi);
        if(tentativi==0){
            textView =(TextView) findViewById(R.id.tvTip);
            textView.setText("GAME OVER");

        }

    }
    public void resetTries(){
        tentativi=3;
        setTries(0);
    }
    public void tryNumber(int n){
        textView=(TextView) findViewById(R.id.tvTip);
        if(tentativi>0){
        if(numeroIgnoto==n ){
            textView.setText("RISPOSTA ESATTA");
            cambiaPunteggio(tentativi+2);
            resetTries();
            setTries(1);
            generaNumero();

        }
        else{

            v.vibrate(100);
            if(n<numeroIgnoto){
                textView.setText("TIP: Prova con qualcosa di piu grande");
            }else{
                textView.setText("TIP: Prova con qualcosa di piu piccolo");

            }

        }


        }
        setTries(-1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add("New Game");
        MenuItem ng = menu.getItem(0);
        ng.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                resetTries();
                generaNumero();
                cambiaPunteggio(-punti);
                textView = (TextView) findViewById(R.id.tvTip);
                textView.setText("TIP: Nuova partita trova il numero");
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }



}
