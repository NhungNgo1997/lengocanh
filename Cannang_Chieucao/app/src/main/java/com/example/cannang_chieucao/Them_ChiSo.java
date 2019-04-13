package com.example.cannang_chieucao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Them_ChiSo extends AppCompatActivity {
    String url="http://192.168.1.42/chiso/them.php";



    EditText editten, editchieucao, editcannang;
    Button btnthem, btnhuy;




    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_item);

        editten= findViewById(R.id.edhoten);
        editchieucao=findViewById(R.id.edchieucao);
        editcannang= findViewById(R.id.edcannang);
        btnthem=findViewById(R.id.btnthem);
        btnhuy=findViewById(R.id.btnhuy);



        onClick();

    }



    public void onClick(){
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngay=editten.getText().toString().trim();

                String ten= editchieucao.getText().toString().trim();
                String diachi=editcannang.getText().toString().trim();


                if(ten.isEmpty()||diachi.isEmpty()||ngay.isEmpty()){
                    Toast.makeText(Them_ChiSo.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }else {
                    them(url);
                }
            }
        });
    }
    public void them(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(Them_ChiSo.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    Toast.makeText(Them_ChiSo.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(Them_ChiSo.this, MainActivity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(Them_ChiSo.this, "Lỗi"+response.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Them_ChiSo.this, "Vui lòng kiểm tra kết nối internet của bạn", Toast.LENGTH_SHORT).show();

                    }

                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("hoten", editten.getText().toString().trim());

                params.put("chieucao", editchieucao.getText().toString().trim());

                params.put("cannang",editcannang.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}

