package com.acaodigital.api.service;

import com.acaodigital.api.model.AcaoSocial;
import com.acaodigital.api.repository.AcaoSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcaoSocialService {

    @Autowired
    private AcaoSocialRepository repository;

    public List<AcaoSocial> listarTodas() {
        return repository.findAll();
    }

    public AcaoSocial salvar(AcaoSocial acao) {
        return repository.save(acao);
    }

    public AcaoSocial atualizar(Long id, AcaoSocial dadosAtualizados) {
        AcaoSocial existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ação social não encontrada."));

        existente.setTitulo(dadosAtualizados.getTitulo());
        existente.setDescricao(dadosAtualizados.getDescricao());
        existente.setLocal(dadosAtualizados.getLocal());
        existente.setDataEvento(dadosAtualizados.getDataEvento());
        existente.setCategoria(dadosAtualizados.getCategoria());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}