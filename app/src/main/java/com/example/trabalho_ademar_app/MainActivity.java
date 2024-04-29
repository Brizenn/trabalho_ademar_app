package com.example.trabalho_ademar_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //checkbox//
    private CheckBox checkOp1, checkOp2, checkOp4;

    //radio button//
    private RadioButton radio1, radio2;

    //spinner//
    private Spinner camiseta;

    //swich//
    private Switch notificacao;

    //button//
    private Button cadastrar;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //checkbox//
        checkOp1=findViewById(R.id.checkBox1);
        checkOp2=findViewById(R.id.checkBox2);
        checkOp4=findViewById(R.id.checkBox4);

        //radiobutton//
        radio1 = findViewById(R.id.radiobutton1);

        //spinner//
        camiseta =findViewById(R.id.camiseta);

        //switch//
        notificacao= findViewById(R.id.switch1);

        cadastrar = findViewById(R.id.button);

        //progress bar//
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shared preferences//
            SalvarArquivoSharedPreferences();

            //progressBar//
                progressBar.setVisibility(View.VISIBLE);
            }

            private void SalvarArquivoSharedPreferences() {
                SharedPreferences sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //checkbox//
                editor.putBoolean("box1", checkOp1.isChecked());
                editor.putBoolean("box2", checkOp2.isChecked());

                //radiobutton//
                editor.putBoolean("escolha1",radio1.isChecked());
                editor.putBoolean("escolha2",radio2.isChecked());
                //spinner//
                editor.putString("camiseta",camiseta.getSelectedItem().toString());
                //switch//
                editor.putBoolean("notificação",notificacao.isChecked());

                //salvar shared preferences//
                editor.apply();
            }
        });

    }
}