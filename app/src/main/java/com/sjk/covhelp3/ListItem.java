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

public class ListItem extends AppCompatActivity  implements AdapterView.OnItemClickListener {


    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    EditText editTextSearchItem;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        listView = (ListView) findViewById(R.id.lv_items);
        editTextSearchItem = (EditText)findViewById(R.id.txt2);

        listView.setOnItemClickListener(this);


        getItems();

    }


    private void getItems() {

        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbzRabFzVXNS5CinNSatUmoopdXI5Xsy7Fe2VcABEdp1qlJY3YpZssRTlXPVKimTHX3jRQ/exec?action=getItems",
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

                String district = jo.getString("district");
                String hospital = jo.getString("hospital");
                String tbeds = jo.getString("tbeds");
                String vbeds = jo.getString("vbeds");
                String lup = jo.getString("lupdate");
                String ict = jo.getString("ict");
                String vct = jo.getString("vct");
                String cont = jo.getString("cont");


                HashMap<String, String> item = new HashMap<>();
                item.put("district", district);
                item.put("hospital", hospital);
                item.put("tbeds",tbeds);
                item.put("vbeds",vbeds);
                item.put("lup",lup);
                item.put("ict",ict);
                item.put("vct",vct);
                item.put("cont",cont);

                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this,list,R.layout.list_item_row,
                new String[]{"district","hospital","tbeds","vbeds","lup","ict","vct","cont"},new int[]{R.id.tv_dis,R.id.tv_hos,R.id.tv_tbeds,R.id.tv_vbeds,R.id.tv_lp,R.id.tv_ict,R.id.tv_vct,R.id.tv_cont});


        listView.setAdapter(adapter);
        loading.dismiss();


        editTextSearchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ListItem.this.adapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ItemDetails.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String itemId = map.get("district").toString();
        String itemName = map.get("hospital").toString();
        String brand = map.get("tbeds").toString();
        String price = map.get("vbeds").toString();
        String ict = map.get("ict").toString();
        String vct = map.get("vct").toString();
        String cont = map.get("cont").toString();


        // String sno = map.get("sno").toString();

        // Log.e("SNO test",sno);
        intent.putExtra("district",itemId);
        intent.putExtra("hospital",itemName);
        intent.putExtra("tbeds",brand);
        intent.putExtra("vbeds",price);
        intent.putExtra("ict",ict);
        intent.putExtra("vct",vct);
        intent.putExtra("cont",cont);


        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }





}