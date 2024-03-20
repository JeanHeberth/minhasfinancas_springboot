package br.com.jeanheberth.minhasfinacas.api.exception;

public class ErroAutenticacao extends RuntimeException {

    public ErroAutenticacao(String messagem) {
        super(messagem);
    }
}
