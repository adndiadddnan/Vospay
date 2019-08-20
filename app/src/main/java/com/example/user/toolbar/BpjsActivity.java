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

public class BpjsActivity extends AppCompatActivity {
EditText etNoBpjs, etMonth;
TextView tvNoBpjs, tvPeriodeBpjs, tvAdminBpjs, tvTotalBpjs;
Button btnBpjss;
CardView cvNoBpjs, cvPeriodeBpjs, cvAdminBpjs, cvTotalBpjs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpjs);
        cvNoBpjs = findViewById(R.id.cvNoBpjs);
        cvAdminBpjs = findViewById(R.id.cvAdminBpjs);
        cvPeriodeBpjs= findViewById(R.id.cvPeriodeBpjs);
        cvTotalBpjs = findViewById(R.id.cvTotalBpjs);
        etNoBpjs = findViewById(R.id.noBpjs);
        etMonth = findViewById(R.id.Month);
        tvNoBpjs = findViewById(R.id.tvNoBpjs);
        tvPeriodeBpjs = findViewById(R.id.tvPeriodeBpjs);
        tvAdminBpjs = findViewById(R.id.tvAdminBpjs);
        tvTotalBpjs = findViewById(R.id.tvTotalBpjs);

        btnBpjss = findViewById(R.id.btnBpjs);

        btnBpjss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cek();
            }
        });

    }
    public void cek() {
        final ProgressDialog pDialog = new ProgressDialog(BpjsActivity.this);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.setMessage("Proccess..");
        pDialog.show();
            final String noBpjs = etNoBpjs.getText().toString();
            final String month = etMonth.getText().toString();
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = "http://192.168.43.172/tubes/payment/payBpjs.php";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                                cvAdminBpjs.setVisibility(View.VISIBLE);
                                cvNoBpjs.setVisibility(View.VISIBLE);
                                cvPeriodeBpjs.setVisibility(View.VISIBLE);
                                cvTotalBpjs.setVisibility(View.VISIBLE);
                                tvNoBpjs.setText("No BPJS : "+jsonObject1.getString("no_bpjs"));
                                tvPeriodeBpjs.setText("Periode : " +jsonObject1.getString("periode"));
                                tvAdminBpjs.setText("Admin : " + jsonObject1.getString("admin"));
                                tvTotalBpjs.setText("Total : " +jsonObject1.getString("total"));
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
                    params.put("hp", noBpjs);
                    params.put("month", month);

                    return params;
                }
            };
            queue.add(postRequest);
    }
}
