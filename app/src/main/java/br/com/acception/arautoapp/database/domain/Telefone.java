package br.com.acception.arautoapp.database.domain;

import co.uk.rushorm.core.RushObject;

/**
 * Created by andersonmarques on 27/04/15.
 */
public class Telefone extends RushObject {
    private String numero;

    public Telefone() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
