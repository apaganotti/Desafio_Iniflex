package Desafio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações
    	System.out.println("Lista de todos os funcionários e suas informações.");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
            System.out.println("Salário: " + funcionario.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        });

        // 3.4 - Atualizar os salários com 10% de aumento
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        });

        // 3.5 - Agrupar os funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
    	System.out.println("Lista dos funcionários agrupados por função e alterações 3.4 e 3.5 aplicadas:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> {
                System.out.println("  Nome: " + funcionario.getNome());
                System.out.println("  Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                System.out.println("  Salário: " + funcionario.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","));
                System.out.println();
            });
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Funcionários que fazem aniversário em Outubro e Dezembro:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> {
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                    System.out.println("Salário: " + funcionario.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","));
                    System.out.println();
                });

        // 3.9 - Imprimir o funcionário com a maior idade
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(funcionario -> funcionario.getDataNascimento()));
        System.out.println("Funcionário com a maior idade:");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()) + " anos");
        System.out.println();

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome());
        });

        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println();
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + totalSalarios.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","));
        System.out.println();

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("Lista da quantidade de salários mínimos ganha cada funcionário:");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal qtdSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_EVEN);
            System.out.println("Nome: " + funcionario.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
        });
    }
}
