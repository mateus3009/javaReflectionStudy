package main.servicos;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Manipulador <T> {

    private Class<T> clazz;

    public Manipulador(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<Field> carregaCampos() {
        throw new RuntimeException("Não implementado");
    }

    public T criaInstancia(Map<String, Object> values) {
        throw new RuntimeException("Não implementado");
    }

    public Map<String, Object> carregaValores(T object) {
        throw new RuntimeException("Não implementado");
    }
}
