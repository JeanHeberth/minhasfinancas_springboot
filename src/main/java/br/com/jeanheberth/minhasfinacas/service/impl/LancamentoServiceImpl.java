package br.com.jeanheberth.minhasfinacas.service.impl;

import br.com.jeanheberth.minhasfinacas.entity.Lancamento;
import br.com.jeanheberth.minhasfinacas.enums.TipoLancamento;
import br.com.jeanheberth.minhasfinacas.exception.RegraNegocioException;
import br.com.jeanheberth.minhasfinacas.service.LancamentoService;
import br.com.jeanheberth.minhasfinacas.enums.StatusLancamento;
import br.com.jeanheberth.minhasfinacas.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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

    @Override
    public BigDecimal obterSaldoPorUsuario(Long id) {
        return null;
    }

    @Override
    public void validar(Lancamento lancamento) {

        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Informe uma Descrição válida.");
        }

        if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
            throw new RegraNegocioException("Informe um Mês válido.");
        }

        if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
            throw new RegraNegocioException("Informe um Ano válido.");
        }

        if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
            throw new RegraNegocioException("Informe um Usuário.");
        }

        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
            throw new RegraNegocioException("Informe um Valor válido.");
        }

        if (lancamento.getTipoLancamento() == null) {
            throw new RegraNegocioException("Informe um tipo de Lançamento.");
        }
    }
    @Override
    public Optional<Lancamento> obterPorId(Long id) {
        return lancamentoRepository.findById(id);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public BigDecimal obterSaldoPorUsuario(Long id) {
//
//        BigDecimal receitas = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, TipoLancamento.RECEITA, StatusLancamento.EFETIVADO);
//        BigDecimal despesas = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuarioEStatus(id, TipoLancamento.DESPESA, StatusLancamento.EFETIVADO);
//
//        if(receitas == null) {
//            receitas = BigDecimal.ZERO;
//        }
//
//        if(despesas == null) {
//            despesas = BigDecimal.ZERO;
//        }
//
//        return receitas.subtract(despesas);
//    }


}
