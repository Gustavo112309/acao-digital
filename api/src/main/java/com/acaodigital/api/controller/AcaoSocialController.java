package com.acaodigital.api.controller;

import com.acaodigital.api.model.AcaoSocial;
import com.acaodigital.api.service.AcaoSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/acoes")
@CrossOrigin("*")
public class AcaoSocialController {

    @Autowired
    private AcaoSocialService service;

    @GetMapping
    public List<AcaoSocial> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public AcaoSocial criar(@RequestBody AcaoSocial acao) {
        return service.salvar(acao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoSocial> atualizar(@PathVariable Long id, @RequestBody AcaoSocial acao) {
        return ResponseEntity.ok(service.atualizar(id, acao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}