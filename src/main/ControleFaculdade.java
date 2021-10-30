package main;

import java.lang.reflect.Field;

import main.entidades.Aluno;
import main.entidades.Pessoa;
import main.servicos.Manipulador;

/**
 * ControleFaculdade
 */
public class ControleFaculdade {

    public static void main(String[] args) {
        System.out.println(ControleFaculdade.class.getSimpleName());
        var m = new Manipulador<>(Aluno.class);
        m
            .carregaCampos()
            .stream()
            .map(Field::getName)
            .forEach(System.out::println);
    }
}