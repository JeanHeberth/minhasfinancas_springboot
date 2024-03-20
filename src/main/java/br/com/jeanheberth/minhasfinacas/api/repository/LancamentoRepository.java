package br.com.jeanheberth.minhasfinacas.api.repository;

import br.com.jeanheberth.minhasfinacas.api.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long > {
}
