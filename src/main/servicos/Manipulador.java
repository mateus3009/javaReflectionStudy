package main.servicos;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Manipulador <T> {

    private Class<T> clazz;

    public Manipulador(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<Field> carregaCampos() {
        return carregaCampos(this.clazz)
                .stream()
                .map(c -> { c.setAccessible(true); return c; })
                .collect(Collectors.toList());
    }

    private List<Field> carregaCampos(Class clazz) {
		var campos = Arrays.asList(clazz.getDeclaredFields());
		if (clazz.getSuperclass() == null)
			return campos;
		return Stream
			.of(carregaCampos(clazz.getSuperclass()), campos)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}

    public T criaInstancia(Map<String, Object> values) throws Exception {
        var instancia = clazz.cast(clazz.getDeclaredConstructor().newInstance());
        var campos =  carregaCampos();
        for (var entry : values.entrySet()) {
            campos
                .stream()
                .filter(c -> c.getName().equals(entry.getKey()))
                .forEach(c -> defineValorDoCampo(c, instancia, entry.getValue()));
        }
        return instancia;
    }

    private void defineValorDoCampo(Field campo, T instancia, Object valor) {
        try {
            campo.set(instancia, valor);
        } catch (Exception e) {
            System.err.println("Não foi possível definir o valor do campo: " + campo.getName());
        }
    }

    public Map<String, Object> carregaValores(T instancia) {
        return carregaCampos()
            .stream()
            .map(c -> carregaValorDoCampo(c, instancia))
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private Entry<String, Object> carregaValorDoCampo(Field campo, T instancia) {
        try {
            return new AbstractMap.SimpleEntry<String, Object>(campo.getName(), campo.get(instancia));
        } catch (Exception e) {
            System.err.println("Não foi possivel carregar o valor do campo: " + campo.getName());
        }
        return null;
    }
}
