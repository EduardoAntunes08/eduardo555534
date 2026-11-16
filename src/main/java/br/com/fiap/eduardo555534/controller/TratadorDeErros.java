package br.com.fiap.eduardo555534.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.fiap.eduardo555534.exception.RecursoNaoEncontradoException;
import br.com.fiap.eduardo555534.exception.RegraNegocioException;

@ControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String naoEncontrado(RecursoNaoEncontradoException e, Model model) {
        model.addAttribute("mensagem", e.getMessage());
        return "erro";
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String regraDeNegocio(RegraNegocioException e, Model model) {
        model.addAttribute("mensagem", e.getMessage());
        return "erro";
    }
}
