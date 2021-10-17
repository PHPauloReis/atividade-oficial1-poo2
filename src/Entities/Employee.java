package Entities;

import Exceptions.InvalidCpfException;
import Exceptions.InvalidNameException;

public class Employee extends People {
    private String registrationCode;

    public Employee(
        String name,
        String cpf,
        String registrationCode
    ) throws InvalidNameException, InvalidCpfException {
        this.setName(name);
        this.setCpf(cpf);
        this.setRegistrationCode(registrationCode);
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
