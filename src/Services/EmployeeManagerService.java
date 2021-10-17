package Services;

import Entities.Employee;
import Exceptions.EmployeeAlreadyExistsException;
import Exceptions.EmployeeNotFoundException;
import Exceptions.InvalidCpfException;
import Exceptions.InvalidNameException;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagerService {
    ArrayList<Employee> employees = new ArrayList<>();

    public void showHeader() {
        System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println("*=*=*=*=* Bem-vindo ao Gestor de Funcionários da UNIME *=*=*=*=*");
        System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println("*                  Selecione uma opção:                        *");
        System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Listar funcionário");
        System.out.println("3 - Pesquisar funcionário");
        System.out.println("4 - Encerrar");
    }

    public void showEmployees() throws EmployeeNotFoundException {
        if (this.employees.isEmpty()) {
            throw new EmployeeNotFoundException("Ainda não há funcionários cadastrados!");
        }

        for (Employee employee : employees) {
            System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("* Funcionário: " + employee.getName());
            System.out.println("* Matrícula: " + employee.getRegistrationCode());
            System.out.println("* CPF: " + employee.getCpf());
            System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        }
    }

    public void addEmployee() throws InvalidCpfException, InvalidNameException, EmployeeAlreadyExistsException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do funcionário:");
        String name = scanner.nextLine();

        System.out.println("Informe o cpf do funcionário:");
        String cpf = scanner.nextLine();

        System.out.println("Informe a Matrícula do funcionário:");
        String registrationCode = scanner.nextLine();

        Employee employee = new Employee(name, cpf, registrationCode);

        this.employees.add(employee);
    }

    public void updateEmployee() throws InvalidCpfException, InvalidNameException, EmployeeNotFoundException, EmployeeAlreadyExistsException, Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do funcionário:");
        String name = scanner.nextLine();

        Employee employee = findEmployeeByName(name);
        int selectedEmployeeId = this.employees.indexOf(employee);

        System.out.println("Qual dado você quer atualizar?");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Matrícula");
        int option = scanner.nextInt();

        scanner = new Scanner(System.in);

        switch (option) {
            case 1 -> {
                System.out.println("Informe o nome do funcionário:");
                String newName = scanner.nextLine();
                employee.setName(newName);
            }
            case 2 -> {
                System.out.println("Informe o cpf do funcionário (Apenas números):");
                String newCpf = scanner.nextLine();
                employee.setCpf(newCpf);
            }
            case 3 -> {
                System.out.println("Informe a matrícula do funcionário (Apenas números):");
                String newRegistrationCode = scanner.nextLine();
                employee.setRegistrationCode(newRegistrationCode);
            } default -> {
                throw new Exception("Opção Inválida!");
            }
        }

        this.employees.set(selectedEmployeeId, employee);
    }

    public void removeEmployee() throws EmployeeNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do funcionário:");
        String name = scanner.nextLine();

        Employee employee = findEmployeeByName(name);

        this.employees.remove(employee);
    }

    private Employee findEmployeeByName(String name) throws EmployeeNotFoundException {
        Employee employee = this.employees.stream().filter(
            e -> name.equals(e.getName())
        ).findFirst().orElse(null);

        if (employee == null) {
            throw new EmployeeNotFoundException("Funcionário não encontrado!");
        }

        return employee;
    }

}
