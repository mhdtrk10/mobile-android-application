package com.example.tasarim_odev;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    Button goback;

    TextView textView,textView2,textView3;
    EditText inputText,inputText2,inputText3,inputText4,inputText5;
    String url = "https://api.genelpara.com/embed/para-birimleri.json";
    RequestQueue queue;
    String satir="",satir2="";
    double sayi1=0,sayi2=0,sayi3=0,sayi4=0,sayi5=0,dolardegeri=0,karorani=0,vergidegeri=0,
            kdvdegerli=0,sonuc=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        queue = NetworkController.getInstance(this).getRequestQueue();
        queue.add(new JsonObjectRequest(0,url,null,new listener(),new error()));

        inputText=(EditText) findViewById(R.id.editTextText3);//fiyat
        inputText2=(EditText) findViewById(R.id.editTextText4);//kdv
        inputText3=(EditText) findViewById(R.id.editTextText5);//gelir vergi
        inputText4=(EditText) findViewById(R.id.editTextText8);//kar oranı
        inputText5=(EditText) findViewById(R.id.editTextText9);//KDV2
        textView=(TextView) findViewById(R.id.textView4);
        textView2=(TextView) findViewById(R.id.textView6);
        textView3=(TextView) findViewById(R.id.textView8);

    }

    public void goback(View view) {//normal sayfaya geri dönüş
        goback = findViewById(R.id.button4);
        Intent gotoback = new Intent(MainActivity3.this,MainActivity.class);
        startActivity(gotoback);
    }

    @SuppressLint("SetTextI18n")
    public void hesapla2(View view) {//hesaplama




        sayi1=Double.parseDouble(inputText.getText().toString());//fiyat
        sayi2=Double.parseDouble(inputText2.getText().toString());//kdv
        sayi3=Double.parseDouble(inputText3.getText().toString());//gelir vergi
        sayi4=Double.parseDouble(inputText4.getText().toString());//kar oranı
        sayi5=Double.parseDouble(inputText5.getText().toString());//KDV2

        dolardegeri=sayi1*(Double.parseDouble(satir2));//girilen doların tl degeri
        karorani=dolardegeri*(sayi4/100); //kar oranı
        vergidegeri=karorani*(sayi3/100); //gelir vergisi
        kdvdegerli=(dolardegeri+karorani+vergidegeri)*(1+(sayi2/100));//kdvli ve deger
        sonuc=kdvdegerli*(1+(sayi5/100));//toplam fiyat

        //double sonuc = sayi1+(sayi1*sayi2/100)+(sayi1*sayi3/100);
        textView3.setText(Math.round(sonuc)+" tl");//toplam fiyat
        textView2.setText(Math.round(kdvdegerli)+" tl");//kdv degerli
        textView.setText(dolardegeri+" tl");//tl fiyatı


    }
    private class listener implements Response

            .Listener<JSONObject>{
        public void onResponse(JSONObject response){




            Log.i("gelen cevap:",response.toString());
            satir=response.toString();
            for (int i=0;i<satir.length();i++){
                if (i==17){
                    satir2=satir.substring(i,i+6);

                }


            }
            Log.d("satir cevabi:",satir2);




        }
    }

    private class error implements Response.ErrorListener{
        public void onErrorResponse(VolleyError error){
            Toast.makeText(MainActivity3.this,"hata oluştu"+error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}