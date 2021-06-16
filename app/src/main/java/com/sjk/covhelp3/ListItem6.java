package com.sjk.covhelp3;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListItem6 extends AppCompatActivity  implements AdapterView.OnItemClickListener    {


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item6);

        listView = (ListView) findViewById(R.id.lv_items6);
        editTextSearchItem = (EditText)findViewById(R.id.txt8);

        listView.setOnItemClickListener(this);





        getItems();

    }


    private void getItems() {

        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, " https://script.google.com/macros/s/AKfycbye6vkfPKm1jeNKyqK4bVFc85w8sQwTeMKwINszF4aqPgo2vR0xojZaORoKmwn_ghX74Q/exec?action=getItems",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }


    private void parseItems(String jsonResposnce) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String district = jo.getString("dis");
                String hospital = jo.getString("name");
                String tbeds = jo.getString("rr");
                String vbeds = jo.getString("time");
                String ict = jo.getString("state");
                String vct = jo.getString("age");
                String cont = jo.getString("dr");
                String d1 = jo.getString("gender");
                String d2 = jo.getString("mobile");
                String d3 = jo.getString("email");






                HashMap<String, String> item = new HashMap<>();
                item.put("district", district);
                item.put("hospital", hospital);
                item.put("tbeds",tbeds);
                item.put("vbeds",vbeds);
                item.put("ict",ict);
                item.put("vct",vct);
                item.put("cont",cont);
                item.put("d1",d1);
                item.put("d2",d2);
                item.put("d3",d3);



                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this,list,R.layout.list_item_row6,
                new String[]{"hospital","district","tbeds","vbeds","ict","vct","cont","d1","d2","d3"},new int[]{R.id.tv_dis,R.id.tv_hos,R.id.tv_tbeds3,R.id.tv_lp});


        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ListItem6.this.adapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ItemDetails6.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String itemId = map.get("hospital").toString();
        String itemName = map.get("district").toString();
        String brand = map.get("tbeds").toString();
        String price = map.get("ict").toString();
        String ict = map.get("cont").toString();
        String vct = map.get("vct").toString();
        String cont = map.get("d1").toString();
        String d1 = map.get("d2").toString();


        String d3 = map.get("d3").toString();





        // String sno = map.get("sno").toString();

        // Log.e("SNO test",sno);
        intent.putExtra("name",itemId);
        intent.putExtra("address",itemName);
        intent.putExtra("tbeds",brand);
        intent.putExtra("vbeds",price);
        intent.putExtra("ict",ict);
        intent.putExtra("vct",vct);
        intent.putExtra("cont",cont);
        intent.putExtra("d1",d1);

        intent.putExtra("d2",d3);




        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }










}