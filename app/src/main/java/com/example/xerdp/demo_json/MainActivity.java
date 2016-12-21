package com.example.xerdp.demo_json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button btn_start;
    private RelativeLayout activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_start = (Button) findViewById(R.id.btn_start);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);

        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                fun();
                break;
        }
    }


    public void fun() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("mm.json"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }


            inputStreamReader.close();
            bufferedReader.close();


            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            Log.e(TAG, "time==" + jsonObject.getString("time"));

            JSONArray jsonarray = jsonObject.getJSONArray("hello");

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonboject1 = jsonarray.getJSONObject(i);

                Log.e(TAG, "id==" + jsonboject1.getInt("id"));
                Log.e(TAG, "name==" + jsonboject1.getString("name"));
                Log.e(TAG, "sex==" + jsonboject1.getString("sex"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
