package com.acaodigital.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "acoes_sociais")
public class AcaoSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 120)
    private String local;

    @Column(nullable = false, length = 20)
    private String dataEvento;

    @Column(nullable = false, length = 50)
    private String categoria;

    public AcaoSocial() {
    }

    @PrePersist
    public void preencherPadroes() {
        if (this.local == null || this.local.isBlank()) {
            this.local = "A definir";
        }
        if (this.dataEvento == null || this.dataEvento.isBlank()) {
            this.dataEvento = "A definir";
        }
        if (this.categoria == null || this.categoria.isBlank()) {
            this.categoria = "Geral";
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getDataEvento() { return dataEvento; }
    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}