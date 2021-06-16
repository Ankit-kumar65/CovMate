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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class ListItem2 extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    private TextView pin,time;
    private Button search;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item2);




        listView = (ListView) findViewById(R.id.lv_items2);
        listView.setOnItemClickListener(this);


        pin = (TextView) findViewById(R.id.txt3);
        time = (TextView) findViewById(R.id.txt4);
        search = (Button) findViewById(R.id.search);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin1 = pin.getText().toString();
                String time1 = time.getText().toString();
                getItems(pin1,time1);

            }
        });

    }


    private void getItems(String pin, String time) {

        StringBuilder url = new StringBuilder("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=");
        url.append(pin);
        url.append("&date=");
        url.append(time);
        url.append("?action=getItems");

        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ""+url,
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
            JSONArray jarray = jobj.getJSONArray("sessions");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String name = jo.getString("name");
                String address = jo.getString("address");
                String vaccine = jo.getString("vaccine");
                String age = jo.getString("min_age_limit");
                String feetype = jo.getString("fee_type");
                String d1 = jo.getString("district_name");
                String d2 = jo.getString("center_id");
                String cap = jo.getString("available_capacity");
                String d3 = jo.getString("available_capacity_dose1");
                String d4 = jo.getString("available_capacity_dose2");
                String d5 = jo.getString("slots");
                String d6 = jo.getString("fee");



                HashMap<String, String> item = new HashMap<>();
                item.put("name", name);
                item.put("address", address);
                item.put("vaccine",vaccine);
                item.put("age",age);
                item.put("feetype",feetype);
                item.put("d1",d1);
                item.put("d2",d2);
                item.put("cap",cap);
                item.put("d3",d3);
                item.put("d4",d4);
                item.put("d5",d5);
                item.put("d6",d6);


                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this,list,R.layout.list_item_row2,
                new String[]{"name","address","vaccine","age","feetype","d1","d2","cap","d3","d4","d5","d6"},new int[]{R.id.tv_dis2,R.id.tv_hos2,R.id.tv_tbeds2,R.id.tv_vbeds2,R.id.tv_lp2});


        listView.setAdapter(adapter);
        loading.dismiss();







    }



    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ItemDetails2.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String itemId = map.get("address").toString();
        String itemName = map.get("name").toString();
        String brand = map.get("d1").toString();
        String price = map.get("d2").toString();
        String ict = map.get("vaccine").toString();
        String vct = map.get("age").toString();
        String cont = map.get("cap").toString();
        String d1 = map.get("d3").toString();


        String d2 = map.get("d4").toString();
        String slot = map.get("d5").toString();
        String d3 = map.get("d6").toString();



        // String sno = map.get("sno").toString();

        // Log.e("SNO test",sno);
        intent.putExtra("address",itemId);
        intent.putExtra("name",itemName);
        intent.putExtra("district_name",brand);
        intent.putExtra("center_id",price);
        intent.putExtra("ict",ict);
        intent.putExtra("vct",vct);
        intent.putExtra("cont",cont);
        intent.putExtra("d1",d1);

        intent.putExtra("d2",d2);
        intent.putExtra("slots",slot);
        intent.putExtra("d3",d3);


        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }






}

