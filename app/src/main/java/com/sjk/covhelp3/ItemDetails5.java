package com.sjk.covhelp3;





import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetails5 extends AppCompatActivity {


    TextView textViewitemName, textViewbrand, textViewprice,textViewId,textViewict,textViewvct,textViewcont;

    String itemId, itemName, brand, price,ict,vct,cont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_details5);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("district");
        itemName = intent.getStringExtra("hospital");
        brand = intent.getStringExtra("brand");
        price = intent.getStringExtra("price");



        textViewId = (TextView)findViewById(R.id.tv_dis1);
        textViewitemName = (TextView) findViewById(R.id.tv_hos1);
        textViewbrand = (TextView) findViewById(R.id.tv_tbeds1);
        textViewprice = (TextView) findViewById(R.id.tv_vbeds1);



        textViewId.setText(itemId);
        textViewitemName.setText(itemName);
        textViewbrand.setText(brand);
        textViewprice.setText(price);




    }
}