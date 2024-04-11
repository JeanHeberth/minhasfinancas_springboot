package br.com.jeanheberth.minhasfinacas.service;

import br.com.jeanheberth.minhasfinacas.entity.Lancamento;
import br.com.jeanheberth.minhasfinacas.enums.StatusLancamento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);
    Lancamento atualizar(Lancamento lancamento);
    void deletar(Lancamento lancamento);
    List<Lancamento> buscar(Lancamento lancamentoFiltro);
    void atulizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);

    BigDecimal obterSaldoPorUsuario(Long id);

    void validar(Lancamento lancamento);

    Optional<Lancamento> obterPorId(Long id);
}
