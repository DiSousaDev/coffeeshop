package br.dev.diego.coffeeshop.models.enums;

public enum Category {

    BEBIDAS_QUENTES(1L),
    BEBIDAS_FRIAS(2L),
    COMIDAS(3L),
    ACESSORIOS(4L);

    private final Long cod;

    Category(Long cod) {
        this.cod = cod;
    }

    public Long getCod() {
        return cod;
    }

    public static Category toEnum(Long cod) {
        if(cod == null) {
            return null;
        }
        for(Category t : Category.values()) {
            if(cod.equals(t.getCod())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Código enum inválido. " + cod);
    }

}
