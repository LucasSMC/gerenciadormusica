package br.com.anjs.musica.exceptions;

public class EntidadeNaoEncontrada extends Exception{

    public EntidadeNaoEncontrada() {
    }

    public EntidadeNaoEncontrada(String message) {
        super(message);
    }
}
