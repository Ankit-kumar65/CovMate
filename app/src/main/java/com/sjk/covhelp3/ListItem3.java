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

public class ListItem3 extends AppCompatActivity  implements AdapterView.OnItemClickListener   {


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item3);

        listView = (ListView) findViewById(R.id.lv_items3);
        editTextSearchItem = (EditText)findViewById(R.id.txt5);
        listView.setOnItemClickListener(this);




        getItems();

    }


    private void getItems() {

        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, " https://script.google.com/macros/s/AKfycbzw_DkLoqxfe0a3W4EcEWnlx_UqPBcUgdidnswuCHachGsn46Ic8cJNRTn3285PNYffhQ/exec?action=getItems",
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

                String district = jo.getString("state");
                String hospital = jo.getString("name");
                String tbeds = jo.getString("mobile");
                String vbeds = jo.getString("email");
                String adr = jo.getString("address");



                HashMap<String, String> item = new HashMap<>();
                item.put("district", district);
                item.put("hospital", hospital);
                item.put("tbeds",tbeds);
                item.put("vbeds",vbeds);
                item.put("adr",adr);


                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this,list,R.layout.list_item_row3,
                new String[]{"district","hospital","tbeds","adr","vbeds"},new int[]{R.id.tv_dis3,R.id.tv_hos3,R.id.tv_tbeds3});


        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ListItem3.this.adapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ItemDetails3.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String itemId = map.get("district").toString();
        String itemName = map.get("hospital").toString();
        String brand = map.get("adr").toString();
        String price = map.get("vbeds").toString();
        String ict = map.get("tbeds").toString();



        // String sno = map.get("sno").toString();

        // Log.e("SNO test",sno);
        intent.putExtra("district",itemId);
        intent.putExtra("hospital",itemName);
        intent.putExtra("brand",brand);
        intent.putExtra("price",price);
        intent.putExtra("ict",ict);



        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }










}