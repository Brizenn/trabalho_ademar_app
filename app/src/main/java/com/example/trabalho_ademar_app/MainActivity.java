package com.example.trabalho_ademar_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //checkbox//
    private CheckBox checkOp1, checkOp2,checkOp3;

    //radio button//
    private RadioButton radio1, radio2;

    //spinner//
    private Spinner camiseta;

    //swich//
    private Switch notificacao;

    //button//
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //checkbox//
        checkOp1=findViewById(R.id.checkBox1);
        checkOp2=findViewById(R.id.checkBox2);
        checkOp3=findViewById(R.id.checkBox3);

        //radiobutton//
        radio1 = findViewById(R.id.radioButton);

        //spinner//
        camiseta =findViewById(R.id.spinner);

        //switch//
        notificacao= findViewById(R.id.switch1);

        cadastrar = findViewById(R.id.button);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shared preferences//
            SalvarArquivoSharedPreferences();
            }

            private void SalvarArquivoSharedPreferences() {
                SharedPreferences sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //checkbox//
                editor.putBoolean("box1", checkOp1.isChecked());
                editor.putBoolean("box2", checkOp2.isChecked());
            }
        });

    }
}