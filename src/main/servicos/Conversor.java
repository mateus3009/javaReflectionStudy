package main.servicos;

import java.time.LocalDate;

public class Conversor {

    private Conversor() {}

    public static String paraString(Object obj) {
        if (obj instanceof String)
            return (String) obj;
        if (obj instanceof Integer)
            return ((Integer) obj).toString();
        if (obj instanceof LocalDate)
            return ((LocalDate) obj).toString();
        return null;
    }

    public static <T> T paraObjeto(Class<T> clazz, String valor) {
        if (clazz.equals(String.class))
            return clazz.cast(valor);
        if (clazz.equals(Integer.class))
            return clazz.cast(Integer.parseInt(valor));
        if (clazz.equals(LocalDate.class))
            return clazz.cast(LocalDate.parse(valor));
        return null;
    }
}
