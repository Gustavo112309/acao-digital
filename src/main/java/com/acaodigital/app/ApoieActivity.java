package com.acaodigital.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ApoieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apoie);

        ImageView btnVoltar = findViewById(R.id.btnVoltar);
        ImageView btnSairApoie = findViewById(R.id.btnSairApoie);
        TextView tvChavePix = findViewById(R.id.tvChavePix);
        Button btnCopiarPix = findViewById(R.id.btnCopiarPix);
        Button btnWhatsApp = findViewById(R.id.btnWhatsApp);

        // Voltar para a Página Inicial
        btnVoltar.setOnClickListener(v -> finish());

        // Sair para o Login
        btnSairApoie.setOnClickListener(v -> {
            Intent intent = new Intent(ApoieActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Copiar Chave Pix
        btnCopiarPix.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Chave Pix", tvChavePix.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Chave Pix copiada!", Toast.LENGTH_SHORT).show();
        });

        // Abrir WhatsApp
        btnWhatsApp.setOnClickListener(v -> {
            String url = "https://wa.me/5561999999999?text=Olá!%20Gostaria%20de%20apoiar%20a%20ONG%20Ação%20Digital.";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}