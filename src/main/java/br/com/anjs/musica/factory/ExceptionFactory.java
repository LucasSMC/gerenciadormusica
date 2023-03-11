package br.com.anjs.musica.factory;

import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;

public class ExceptionFactory {

    public static EntidadeNaoEncontrada naoEncontrada(String message) {
        return new EntidadeNaoEncontrada(message);
    }
}
