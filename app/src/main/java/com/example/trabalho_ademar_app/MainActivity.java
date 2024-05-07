package com.example.trabalho_ademar_app;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

    EditText nome, email, telefone;
    Button inserir, consultar;
    ListView listViewParticipante;

    gerenciadorDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //-----------associar componentes com seus elementos java----//
        nome = findViewById(R.id.editnome);
        email = findViewById(R.id.textView1);
        telefone = findViewById(R.id.textView2);
        listViewParticipante = findViewById(R.id.listViewUsers);
        inserir = findViewById(R.id.btnInserir);
        consultar = findViewById(R.id.btnConsultar);

        //-----------instanciar o objeto - banco de dados ---------//
        db = new gerenciadorDB(this);

        //-----------evento do botão inserir-----------//
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeLocal = nome.getText().toString();
                String cpfLOCAL = email.getText().toString();
                String telefoneLocal = telefone.getText().toString();

                boolean checkInsert = db.insertdate(nomeLocal, cpfLOCAL, telefoneLocal);
                if (checkInsert) {
                    Toast.makeText(MainActivity.this, "dados inseridos com sucesso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "falha ao inserir!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //-----------evento do botão consultar--------//
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listaParticipantes = new ArrayList<>();
                Cursor cursor = db.getData();

                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "TABELA VAZIA", Toast.LENGTH_SHORT).show();

                } else {
                    while (cursor.moveToNext()) {
                        listaParticipantes.add("nome: " + cursor.getString(0));
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaParticipantes);
                listViewParticipante.setAdapter(adapter);
                cursor.close();
            }

        });

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