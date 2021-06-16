package com.sjk.covhelp3;





import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetails extends AppCompatActivity {


    TextView textViewitemName, textViewbrand, textViewprice,textViewId,textViewict,textViewvct,textViewcont;

    String itemId, itemName, brand, price,ict,vct,cont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_details);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("district");
        itemName = intent.getStringExtra("hospital");
        brand = intent.getStringExtra("tbeds");
        price = intent.getStringExtra("vbeds");
        ict = intent.getStringExtra("ict");
        vct = intent.getStringExtra("vct");
        cont = intent.getStringExtra("cont");

        textViewId = (TextView)findViewById(R.id.tv_dis);
        textViewitemName = (TextView) findViewById(R.id.tv_hos);
        textViewbrand = (TextView) findViewById(R.id.tv_tbeds);
        textViewprice = (TextView) findViewById(R.id.tv_vbeds);
        textViewict = (TextView) findViewById(R.id.tv_ict);
        textViewvct = (TextView) findViewById(R.id.tv_vct);
        textViewcont = (TextView) findViewById(R.id.tv_cont);

        textViewId.setText(itemId);
        textViewitemName.setText(itemName);
        textViewbrand.setText(brand);
        textViewprice.setText(price);
        textViewict.setText(ict);
        textViewvct.setText(vct);
        textViewcont.setText(cont);


    }
}