package com.example.user.toolbar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PlnActivity extends AppCompatActivity {
    TextView noPln, daya,periode, denda,admin,totalHarga;
    EditText  noplnn;
    Button btnPln;
    CardView cvNoPln, cvDayaPln, cvDendaPln, cvAdminPln, cvTotalPln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pln);

        noPln = findViewById(R.id.noPln);
        daya  = findViewById(R.id.daya);
        periode = findViewById(R.id.periode);
        denda = findViewById(R.id.denda);
        admin = findViewById(R.id.adminPln);
        totalHarga = findViewById(R.id.total);
        btnPln = findViewById(R.id.btnPln);
        noplnn = findViewById(R.id.noplnn);
        cvAdminPln = findViewById(R.id.cvAdminPln);
        cvDayaPln = findViewById(R.id.cvDayaPln);
        cvDendaPln = findViewById(R.id.cvDendaPln);
        cvNoPln = findViewById(R.id.cvNoPln);
        cvTotalPln = findViewById(R.id.cvTotalPln);
        btnPln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cek();
            }
        });
    }

    public void cek(){
        final ProgressDialog pDialog = new ProgressDialog(PlnActivity.this);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proccess..");
        pDialog.show();
        final String noPln1 = noplnn.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.43.172/tubes/payment/payPln.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                            cvNoPln.setVisibility(View.VISIBLE);
                            cvAdminPln.setVisibility(View.VISIBLE);
                            cvDayaPln.setVisibility(View.VISIBLE);
                            cvDendaPln.setVisibility(View.VISIBLE);
                            cvTotalPln.setVisibility(View.VISIBLE);
                            noPln.setText("No Pln : " + jsonObject1.getString("no_pln"));
                            daya.setText("Daya : " + jsonObject1.getString("daya"));
                            periode.setText("Periode : " + jsonObject1.getString("periode"));
                            denda.setText("Denda : " + jsonObject1.getString("denda"));
                            admin.setText("Admin : " + jsonObject1.getString("admin"));
                            totalHarga.setText("Total : " +jsonObject1.getString("total"));
                            pDialog.dismiss();
                        }catch(JSONException e){
                            System.out.println("gagal");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("hp", noPln1);

                return params;
            }
        };
        queue.add(postRequest);

    }
}
