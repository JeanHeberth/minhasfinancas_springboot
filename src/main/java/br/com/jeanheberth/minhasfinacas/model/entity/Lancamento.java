package br.com.jeanheberth.minhasfinacas.model.entity;


import br.com.jeanheberth.minhasfinacas.model.enums.StatusLancamento;
import br.com.jeanheberth.minhasfinacas.model.enums.TipoLancamento;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento", schema = "financas")
@Data

public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne
    private Usuario usuario;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_cadastro", nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataCadastro;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusLancamento statusLancamento;


}
