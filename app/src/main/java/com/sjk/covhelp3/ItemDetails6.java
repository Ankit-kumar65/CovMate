package com.sjk.covhelp3;





import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetails6 extends AppCompatActivity {


    TextView textViewitemName, textViewbrand, textViewprice,textViewId,textViewict,textViewvct,textViewcont,textd1,textd2,textslot,textd3;

    String itemId, itemName, brand, price,ict,vct,cont,d1,d2,slot,d3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_details6);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("address");
        itemName = intent.getStringExtra("name");
        brand = intent.getStringExtra("tbeds");
        price = intent.getStringExtra("vbeds");
        ict = intent.getStringExtra("ict");
        vct = intent.getStringExtra("vct");
        cont = intent.getStringExtra("cont");
        d1 = intent.getStringExtra("d1");
        d2 = intent.getStringExtra("d2");







        textViewId = (TextView)findViewById(R.id.tv_block);
        textViewitemName = (TextView) findViewById(R.id.tv_hos);
        textViewbrand = (TextView) findViewById(R.id.tv_dis);
        textViewprice = (TextView) findViewById(R.id.tv_center);
        textViewict = (TextView) findViewById(R.id.tv_vac);
        textViewvct = (TextView) findViewById(R.id.tv_age);
        textViewcont = (TextView) findViewById(R.id.tv_cap);
        textd1 = (TextView) findViewById(R.id.tv_dose1);
        textd2 = (TextView) findViewById(R.id.tv_dose2);


        textViewId.setText(itemId);
        textViewitemName.setText(itemName);
        textViewbrand.setText(brand);
        textViewprice.setText(price);
        textViewict.setText(ict);
        textViewvct.setText(vct);
        textViewcont.setText(cont);
        textd1.setText(d1);
        textd2.setText(d2);


    }
}