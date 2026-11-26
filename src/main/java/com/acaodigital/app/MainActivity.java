package com.acaodigital.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etTitulo, etDescricao, etLocal;
    private Button btnSalvar;
    private ListView listViewAcoes;
    private SwipeRefreshLayout swipeRefresh;

    private final List<AcaoSocial> listaAcoes = new ArrayList<>();
    private ArrayAdapter<AcaoSocial> adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitulo = findViewById(R.id.etTitulo);
        etDescricao = findViewById(R.id.etDescricao);
        etLocal = findViewById(R.id.etLocal);
        btnSalvar = findViewById(R.id.btnSalvar);
        listViewAcoes = findViewById(R.id.listViewAcoes);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        apiService = RetrofitClient.getApiService();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAcoes);
        listViewAcoes.setAdapter(adapter);

        carregarAcoes();

        swipeRefresh.setOnRefreshListener(this::carregarAcoes);
        btnSalvar.setOnClickListener(v -> cadastrarAcao());

        // Menu de navegação no topo esquerdo
        findViewById(R.id.btnMenu).setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.getMenu().add(0, 1, 0, "Apoie Nosso Trabalho");
            popup.getMenu().add(0, 2, 1, "Mensagens Recebidas");
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == 1) {
                    startActivity(new Intent(MainActivity.this, ApoieActivity.class));
                } else if (item.getItemId() == 2) {
                    startActivity(new Intent(MainActivity.this, MensagensActivity.class));
                }
                return true;
            });
            popup.show();
        });

        // Navegar para a tela Apoie Nosso Trabalho pelo banner
        findViewById(R.id.btnParticipe).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ApoieActivity.class));
        });

        // Sair para a tela de Login
        findViewById(R.id.btnSair).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        // Segurar o clique em um item da lista para excluir
        listViewAcoes.setOnItemLongClickListener((parent, view, position, id) -> {
            AcaoSocial selecionada = listaAcoes.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Excluir Ação")
                    .setMessage("Deseja excluir \"" + selecionada.getTitulo() + "\"?")
                    .setPositiveButton("Excluir", (dialog, which) -> excluirAcao(selecionada.getId()))
                    .setNegativeButton("Cancelar", null)
                    .show();
            return true;
        });
    }

    private void carregarAcoes() {
        swipeRefresh.setRefreshing(true);
        apiService.listarAcoes().enqueue(new Callback<List<AcaoSocial>>() {
            @Override
            public void onResponse(Call<List<AcaoSocial>> call, Response<List<AcaoSocial>> response) {
                swipeRefresh.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    listaAcoes.clear();
                    listaAcoes.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar ações", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AcaoSocial>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cadastrarAcao() {
        String titulo = etTitulo.getText().toString().trim();
        String descricao = etDescricao.getText().toString().trim();
        String local = etLocal.getText().toString().trim();

        if (titulo.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(this, "Preencha título e descrição", Toast.LENGTH_SHORT).show();
            return;
        }

        AcaoSocial novaAcao = new AcaoSocial(titulo, descricao, local);
        apiService.criarAcao(novaAcao).enqueue(new Callback<AcaoSocial>() {
            @Override
            public void onResponse(Call<AcaoSocial> call, Response<AcaoSocial> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Ação cadastrada!", Toast.LENGTH_SHORT).show();
                    etTitulo.setText("");
                    etDescricao.setText("");
                    etLocal.setText("");
                    carregarAcoes();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcaoSocial> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void excluirAcao(Long id) {
        apiService.deletarAcao(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Ação excluída!", Toast.LENGTH_SHORT).show();
                    carregarAcoes();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao excluir", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}