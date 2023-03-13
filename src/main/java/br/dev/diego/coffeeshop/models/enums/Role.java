package br.dev.diego.coffeeshop.models.enums;

public enum Role {

    CUSTOMER(1L),
    STAFF(2L),
    ADMIN(3L);

    private final Long cod;

    Role(Long cod) {
        this.cod = cod;
    }

    public Long getCod() {
        return cod;
    }

    public static Role toEnum(Long cod) {
        if(cod == null) {
            return null;
        }
        for(Role t : Role.values()) {
            if(cod.equals(t.getCod())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Código enum inválido. " + cod);
    }

}
