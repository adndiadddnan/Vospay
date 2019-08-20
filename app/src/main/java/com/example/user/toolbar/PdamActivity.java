package com.example.user.toolbar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class PdamActivity extends AppCompatActivity {
TextView tvNoPdam, tvPeriode,tvMa, tvMak, tvDenda,tvadmin, tvTotal;
EditText etNoPdam;
Button btnPdam;
CardView cvNoPdam, cvPeriodePdam, cvMa,cvMak,cvDendaPdam, cvAdminPdam, cvTotalPdam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdam);
        etNoPdam = findViewById(R.id.noPdam);
        tvNoPdam = findViewById(R.id.tvNoPdam);
        tvPeriode = findViewById(R.id.tvPeriode);
        tvMa = findViewById(R.id.tvMa);
        tvMak = findViewById(R.id.tvMak);
        tvDenda = findViewById(R.id.tvDenda);
        tvadmin = findViewById(R.id.tvAdmin);
        tvTotal = findViewById(R.id.tvTotal);
        cvAdminPdam = findViewById(R.id.cvAdminPdam);
        cvDendaPdam = findViewById(R.id.cvDendaPdam);
        cvMa = findViewById(R.id.cvMa);
        cvMak = findViewById(R.id.cvMak);
        cvNoPdam = findViewById(R.id.cvNoPdam);
        cvPeriodePdam = findViewById(R.id.cvPeriodePdam);
        cvTotalPdam = findViewById(R.id.cvTotalPdam);
        btnPdam = findViewById(R.id.btnPdam);

        btnPdam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cek();
            }
        });

    }

    public void cek() {
        final ProgressDialog pDialog = new ProgressDialog(PdamActivity.this);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proccess..");
        pDialog.show();
            final String noPln1 = etNoPdam.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = "http://192.168.43.172/tubes/payment/payPdam.php";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                                cvNoPdam.setVisibility(View.VISIBLE);
                                cvAdminPdam.setVisibility(View.VISIBLE);
                                cvPeriodePdam.setVisibility(View.VISIBLE);
                                cvMa.setVisibility(View.VISIBLE);
                                cvMak.setVisibility(View.VISIBLE);
                                cvDendaPdam.setVisibility(View.VISIBLE);
                                cvAdminPdam.setVisibility(View.VISIBLE);
                                cvTotalPdam.setVisibility(View.VISIBLE);
                                tvNoPdam.setText("No PDAM : "+jsonObject1.getString("no_pdam"));
                                tvPeriode.setText("Periode : "+jsonObject1.getString("periode"));
                                tvMa.setText("Meter Awal : "+jsonObject1.getString("meter_awal"));
                                tvMak.setText("Meter Akhir : " + jsonObject1.getString("meter_akhir"));
                                tvDenda.setText("Denda : " + jsonObject1.getString("denda"));
                                tvadmin.setText("Admin : " + jsonObject1.getString("admin"));
                                tvTotal.setText("Total : " +jsonObject1.getString("total"));
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
