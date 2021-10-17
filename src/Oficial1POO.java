/**
 * Institução: Unime - União Metropolitana de Educação e Cultura
 * Curso: Bacharelado em Sistemas de Informação
 * Disciplina: Programação Orientada a Objetos II
 * Docente: Pablo Ricardo Roxo Silva
 * Discente: Paulo Reis dos Santos
 */
import Exceptions.EmployeeAlreadyExistsException;
import Exceptions.EmployeeNotFoundException;
import Exceptions.InvalidCpfException;
import Exceptions.InvalidNameException;
import Services.EmployeeManagerService;

import java.util.Scanner;

public class Oficial1POO {
    public static void main(String[] args) {
        EmployeeManagerService employeeManagerService = new EmployeeManagerService();

        employeeManagerService.showHeader();

        Integer option = null;

        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("O que você deseja fazer? ");
                option = scanner.nextInt();

                if (option < 1 || option > 4) {
                    throw new Exception("Error!");
                }

                switch (option) {
                    case 1 -> {
                        employeeManagerService.addEmployee();
                        System.out.println("Funcionário cadastrado com sucesso!");
                        option = null;
                    }
                    case 2 -> {
                        employeeManagerService.showEmployees();
                        option = null;
                    }
                    case 3 -> {
                        Integer subOption = null;

                        System.out.println("O que você deseja fazer?");
                        System.out.println("Digite 1 para Editar ou 2 para Remover um funcionário");
                        subOption = scanner.nextInt();

                        if (subOption != 1 && subOption != 2) {
                            throw new Exception("Error!");
                        }

                        if (subOption == 1) {
                            employeeManagerService.updateEmployee();
                            System.out.println("Funcionário atualizado com sucesso!");
                        }
                        else {
                            employeeManagerService.removeEmployee();
                            System.out.println("Funcionário removido com sucesso!");
                        }

                        option = null;
                    }
                    case 4 -> {
                        System.out.println("Obrigado por utilizar o Unime Funcionários. Até a próxima!!!");
                    }
                }
            } catch (InvalidNameException | InvalidCpfException | EmployeeNotFoundException | EmployeeAlreadyExistsException e) {
                System.out.println(e.getMessage());
                option = null;
            } catch (Exception e) {
                System.out.println("Informe uma opção válida!");
                option = null;
            }
        }
        while (option == null);
    }
}
