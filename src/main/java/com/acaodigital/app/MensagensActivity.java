package com.acaodigital.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MensagensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagens);

        findViewById(R.id.btnVoltarMensagens).setOnClickListener(v -> finish());

        findViewById(R.id.btnSairMensagens).setOnClickListener(v -> {
            Intent intent = new Intent(MensagensActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        findViewById(R.id.btnResponder1).setOnClickListener(v ->
                Toast.makeText(this, "Respondendo a Mateus Silva...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnResponder2).setOnClickListener(v ->
                Toast.makeText(this, "Respondendo a Ana Souza...", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnResponder3).setOnClickListener(v ->
                Toast.makeText(this, "Respondendo a Carlos Santos...", Toast.LENGTH_SHORT).show());
    }
}