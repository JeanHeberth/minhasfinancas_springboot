package br.com.jeanheberth.minhasfinacas.api.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String email;
    private String nome;
    private String senha;
    private LocalDate data_cadastro;

}
