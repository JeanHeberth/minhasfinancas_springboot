package br.com.jeanheberth.minhasfinacas.api.service.impl;

import br.com.jeanheberth.minhasfinacas.api.entity.Lancamento;
import br.com.jeanheberth.minhasfinacas.api.service.LancamentoService;
import br.com.jeanheberth.minhasfinacas.api.enums.StatusLancamento;
import br.com.jeanheberth.minhasfinacas.api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;


    @Override
    public Lancamento salvar(Lancamento lancamento) {
        lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        return lancamentoRepository.save(lancamento);
    }

    @Override
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        lancamentoRepository.delete(lancamento);

    }

    @Override
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return lancamentoRepository.findAll(example);
    }

    @Override
    public void atulizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
        lancamento.setStatusLancamento(statusLancamento);
        atualizar(lancamento);
    }
}
