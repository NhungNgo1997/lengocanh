package com.example.cannang_chieucao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Danhsach_Fragment extends Fragment {
    String url = "http://192.168.1.42/chiso/danhsach.php";
    RecyclerView recyclerView;
    ChiSoAdapter adapter;
    FloatingActionButton floatingActionButton;
    ArrayList<ChiSo> chiSo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.danhsach_fragment,container,false);
        floatingActionButton = view.findViewById(R.id.fab);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        chiSo = new ArrayList<>();

        adapter = new ChiSoAdapter(chiSo, R.layout.item, getActivity());
        recyclerView.setAdapter(adapter);
        load(url);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), Them_ChiSo.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void load(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        chiSo.add(new ChiSo(
                                object.getInt("id"),
                                object.getString("hoten"),
                                Float.valueOf(object.getString("chieucao")),
                                Float.valueOf(object.getString("cannang"))
                        ));


                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Vui lòng kiểm tra kết nối internet của bạn"+error.toString(), Toast.LENGTH_SHORT).show();

                    }

                });


        requestQueue.add(stringRequest);

    }
}
