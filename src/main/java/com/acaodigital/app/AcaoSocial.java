package com.acaodigital.app;

public class AcaoSocial {
    private Long id;
    private String titulo;
    private String descricao;
    private String local;

    public AcaoSocial() {}

    public AcaoSocial(String titulo, String descricao, String local) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    @Override
    public String toString() {
        return titulo + "\n" + descricao + (local != null && !local.isEmpty() ? "\nLocal: " + local : "");
    }
}