package com.example.tasarim_odev;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;



import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    Button yurtdisisayfa;

    TextView textView;
    EditText inputText,inputText2,inputText3,inputText4;

    double sayi1=0,sayi2=0,sayi3=0,sayi4=0,sonuc=0,karlifiyat=0,gelirvergifiyat=0,satisfiyati=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView3);
        inputText=(EditText) findViewById(R.id.editTextText);//ana fiyat
        inputText2=(EditText) findViewById(R.id.editTextText2);//kdv değeri
        inputText3=(EditText) findViewById(R.id.editTextText6);//kar oranı
        inputText4=(EditText) findViewById(R.id.editTextText7);//gelir vergisi


    }

    @SuppressLint("SetTextI18n")
    public void hesapla(View view) {//hesaplama sayi=anafiyat+(anafiyat*kdvdegeri/100)

        if (inputText.length()==0){
            inputText.setText(String.valueOf(sayi1));
        }
        else {
            sayi1=Double.parseDouble(inputText.getText().toString());
        }

        if (inputText2.length()==0){
            inputText2.setText(String.valueOf(sayi2));
        }
        else {
            sayi2=Double.parseDouble(inputText2.getText().toString());
        }

        if (inputText3.length()==0){
            inputText3.setText(String.valueOf(sayi3));
        }
        else {
            sayi3=Double.parseDouble(inputText3.getText().toString());
        }

        if (inputText4.length()==0){
            inputText4.setText(String.valueOf(sayi4));
        }
        else {
            sayi4=Double.parseDouble(inputText4.getText().toString());
        }


        //sayi1=Double.parseDouble(inputText.getText().toString());
        //sayi2=Double.parseDouble(inputText2.getText().toString());
        //sayi3=Double.parseDouble(inputText3.getText().toString());
        //sayi4=Double.parseDouble(inputText4.getText().toString());


        //double sonuc=sayi1 + (sayi1*sayi2/100);

        sonuc=sayi1/(1+(sayi2/100));//gerçek fiyat 83.3

        karlifiyat=sonuc*(1+(sayi3/100));//karlı fiyat 95.83

        gelirvergifiyat=(karlifiyat-sonuc)*(sayi4/100);//gelir vergisi 2.5

        satisfiyati= (karlifiyat*(1+(sayi2/100)))+gelirvergifiyat;


        textView.setText(Math.round(satisfiyati)+" tl");
    }

    public void sayfagecis(View view) {//yurt dışı sayfasına geçiş
        yurtdisisayfa = findViewById(R.id.button2);
        Intent yurtdisisayfasi = new Intent(MainActivity.this,MainActivity3.class);
        startActivity(yurtdisisayfasi);
    }

}