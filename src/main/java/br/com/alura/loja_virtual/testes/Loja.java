package br.com.alura.loja_virtual.testes;

import java.util.Scanner;
import br.com.alura.loja_virtual.acoes.*;

public class Loja {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        while (true) {
            Inicial.inicial();
            System.out.print("\nDigite a opção desejada: ");
            int opcao = teclado.nextInt();
            if (opcao > 5){
                System.out.println("\nOpção inválida!!!");
                System.out.println("Digite somente valores entre 1 e 5.\n");
            } else if (opcao == 1){
                Cadastar.cadastrar();
            } else if (opcao == 2) {
                Alterar.alterar();
            } else if (opcao == 3) {
                Consultar.consultar();
            } else if (opcao == 4) {
                Excluir.excluir();
            } else if (opcao == 5) {
                System.out.println("\nEncerrando a conexão...\n");
                System.out.println("Obrigado por usar nosso sistema.");
                System.out.println("Volte sempre!!!");
                break;
            }
        }

    }
}
