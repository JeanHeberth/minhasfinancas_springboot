package br.com.jeanheberth.minhasfinacas.repository;

import br.com.jeanheberth.minhasfinacas.entity.Lancamento;
import br.com.jeanheberth.minhasfinacas.enums.StatusLancamento;
import br.com.jeanheberth.minhasfinacas.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value = "SELECT SUM(l.valor) FROM Lancamento l JOIN l.usuario u " +
            "WHERE u.id = :idUsuario AND l.tipo = :tipo AND l.status = :status GROUP BY u")
    BigDecimal obterSaldoPorTipoLancamentoEUsuarioEStatus(
            @Param("idUsuario") Long idUsuario,
            @Param("tipo") TipoLancamento tipo,
            @Param("status") StatusLancamento status);


}
