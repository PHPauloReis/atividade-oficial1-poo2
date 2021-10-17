package Entities;

import Exceptions.InvalidCpfException;
import Exceptions.InvalidNameException;

public abstract class People {
    private String name;
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException {
        if (name.trim().length() == 0) {
            throw new InvalidNameException("Nome inválido!");
        }

        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws InvalidCpfException {
        this.cpf = cpf;
    }
}
