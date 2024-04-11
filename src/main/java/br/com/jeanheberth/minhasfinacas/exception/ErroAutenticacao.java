package br.com.jeanheberth.minhasfinacas.exception;

public class ErroAutenticacao extends RuntimeException {

    public ErroAutenticacao(String messagem) {
        super(messagem);
    }
}
