package br.com.jeanheberth.minhasfinacas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String email;
    private String nome;
    private String senha;
    private LocalDate data_cadastro;

}
