package com.example.user.toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Button btnPln, btnPdam, btnPulsa, btnBpjs;
    TextView tvSaldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://192.168.8.103/api/saldo.php";
//        final ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Loading..");
//        pDialog.show();
        btnPln = findViewById(R.id.btnPln);
        btnBpjs = findViewById(R.id.btnBpjs);
        btnPdam = findViewById(R.id.btnPdam);
        btnPulsa = findViewById(R.id.btnPulsa);
        tvSaldo = findViewById(R.id.tvSaldo);
        btnPln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlnActivity.class);
                startActivity(intent);
            }
        });

        btnPdam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PdamActivity.class);
                startActivity(intent);
            }
        });

        btnPulsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PulsaActivity.class);
                startActivity(intent);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);


//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String saldo = jsonObject.getString("saldo");
//                            tvSaldo.setText("Saldo Anda : Rp." + saldo);
//                            pDialog.dismiss();
//                        }catch (JSONException e){
//                            System.out.println("ggl");
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams()  {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("saldo", "rian");
//
//                return params;
//
//            }
//        };
//        queue.add(postRequest);
    }

}
