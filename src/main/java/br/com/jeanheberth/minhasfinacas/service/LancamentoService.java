package br.com.jeanheberth.minhasfinacas.service;

import br.com.jeanheberth.minhasfinacas.entity.Lancamento;
import br.com.jeanheberth.minhasfinacas.enums.StatusLancamento;

import java.util.List;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);
    Lancamento atualizar(Lancamento lancamento);
    void deletar(Lancamento lancamento);
    List<Lancamento> buscar(Lancamento lancamentoFiltro);
    void atulizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);

}
