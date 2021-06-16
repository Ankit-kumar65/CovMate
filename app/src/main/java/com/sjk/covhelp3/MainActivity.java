package com.sjk.covhelp3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;




import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @SuppressLint("SetJavaScriptEnabled")



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            case R.id.b0:

                return true;




            default:
                return super.onOptionsItemSelected(item);
        }


    }









    Button buttonListItem,buttonListItem2,buttonListItem3,buttonListItem4,buttonListItem5,buttonListItem6,buttonListItem7,buttonListItem8,buttonListItem9,buttonListItem10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if( ! CheckNetwork.isInternetAvailable(this)) //returns true if internet available
        {
            //if there is no internet do this
            setContentView(R.layout.activity_main);
            //Toast.makeText(this,"No Internet Connection, Chris",Toast.LENGTH_LONG).show();

            new AlertDialog.Builder(this) //alert the person knowing they are about to close
                    .setTitle("No Internet connection available")
                    .setMessage("Please Check your Mobile data or Wifi network.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    //.setNegativeButton("No", null)
                    .show();

        }


        buttonListItem = (Button)findViewById(R.id.btn_list_items);

        buttonListItem.setOnClickListener(this);


        buttonListItem2 = (Button)findViewById(R.id.btn_list_items2);

        buttonListItem2.setOnClickListener(this);

        buttonListItem3 = (Button)findViewById(R.id.btn_list_items3);

        buttonListItem3.setOnClickListener(this);

        buttonListItem4 = (Button)findViewById(R.id.btn_list_items4);

        buttonListItem4.setOnClickListener(this);


        buttonListItem5 = (Button)findViewById(R.id.btn_list_items5);

        buttonListItem5.setOnClickListener(this);

        buttonListItem6 = (Button)findViewById(R.id.btn_list_items6);

        buttonListItem6.setOnClickListener(this);

        buttonListItem7 = (Button)findViewById(R.id.btn_list_items7);

        buttonListItem7.setOnClickListener(this);

        buttonListItem8 = (Button)findViewById(R.id.btn_list_items8);

        buttonListItem8.setOnClickListener(this);

        buttonListItem9 = (Button)findViewById(R.id.btn_list_items9);

        buttonListItem9.setOnClickListener(this);

        buttonListItem10 = (Button)findViewById(R.id.btn_list_items10);

        buttonListItem10.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {


        if(v==buttonListItem){

            Intent intent = new Intent(getApplicationContext(),ListItem.class);
            startActivity(intent);
        }

        if(v==buttonListItem2){

            Intent intent = new Intent(getApplicationContext(),ListItem2.class);
            startActivity(intent);
        }

        if(v==buttonListItem3){

            Intent intent = new Intent(getApplicationContext(),ListItem3.class);
            startActivity(intent);
        }

        if(v==buttonListItem4){

            Intent intent = new Intent(getApplicationContext(),ListItem4.class);
            startActivity(intent);
        }

        if(v==buttonListItem5){

            Intent intent = new Intent(getApplicationContext(),ListItem5.class);
            startActivity(intent);
        }

        if(v==buttonListItem6){

            Intent intent = new Intent(getApplicationContext(),activity2.class);
            startActivity(intent);
        }


        if(v==buttonListItem7){

            Intent intent = new Intent(getApplicationContext(),activity3.class);
            startActivity(intent);
        }


        if(v==buttonListItem8){

            Intent intent = new Intent(getApplicationContext(),activity4.class);
            startActivity(intent);
        }

        if(v==buttonListItem9){

            Intent intent = new Intent(getApplicationContext(),activity5.class);
            startActivity(intent);
        }

        if(v==buttonListItem10){

            Intent intent = new Intent(getApplicationContext(),ListItem6.class);
            startActivity(intent);
        }








    }





}

class CheckNetwork {

    private static final String TAG = CheckNetwork.class.getSimpleName();

    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            Log.d(TAG,"No Internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                Log.d(TAG," Internet connection available...");
                return true;
            }
            else
            {
                Log.d(TAG," Internet connection");
                return true;
            }

        }










    }





}

