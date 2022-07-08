package br.com.jeanheberth.minhasfinacas.model.repository;

import br.com.jeanheberth.minhasfinacas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository <Lancamento, Long> {
}
