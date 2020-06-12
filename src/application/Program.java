// Matheus Felipe Trinca - João Vitor Pereira

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
	
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		int op;

		while(true) {
			System.out.println();
			System.out.println("=========== DESPESAS ===========");
			System.out.println("1.Cadastrar ou Adicionar Despesa\n"
							  +"2.Listar todas as Despesas\n"
							  +"3.Consulta por determinada despesa\n"
							  +"4.Imprimir total de despesas\n"
							  +"5.Imprimir despesas por categoria\n"
							  +"6.Finalizar");
			System.out.println("=================================");
			op = sc.nextInt();
			if (op < 1 || op > 6) {
				System.out.println("Valor Digitado inválido - Digite Novamente");
				System.out.println();
			}
			else 
				break;
		}
		
		if(op == 3) {
			char opSub;
			while(true) {
				System.out.println("3.Consulta por determinada despesa\n"
								  +"   ===============\n"
								  +"   a.Por descrição\n"
								  +"   b.Por tipo\n"
								  +"   ===============\n");
				opSub = sc.next().toLowerCase().charAt(0);
				if(opSub == 'a') {
					op = 7;
					break;
				}else if(opSub == 'b') {
					op = 8;
					break;
				}else {
					System.out.println("Valor Digitado inválido - Digite Novamente");
					System.out.println();
				}
				
		}
	}
		return op;
}
	public static double getTotal(List<Despesa> despesas) {
		double soma = 0;
		for(Despesa despesa : despesas) {
			soma += despesa.getValor();
		}
		return soma;
	}
	
	public static double getTotalCategoria(List<Despesa> despesas, String categoria) {
		double soma = 0;
		for(Despesa despesa : despesas) {
			if(despesa.getTipo().equals(categoria)) {
				soma += despesa.getValor();
			}
		}
		return soma;
	}
	
	// Busca na descrição do objeto a palavra digitada no menu, e retorna a despesa, se encontrada.
	// Considerando que a descrição seja um breve texto, frase ou palavra.
	public static List<Despesa> buscaPalavra(List<Despesa> despesas, String palavra) {
		List<Despesa> achados = new ArrayList<>();
		int tamanhoPalavra = palavra.length();
		
		for(Despesa despesa : despesas) {
			for(int i = 0; i <= despesa.getDescricao().length() - tamanhoPalavra; i++) {
				String sub = despesa.getDescricao().substring(i, (i+tamanhoPalavra));
				if(sub.equals(palavra)) {
					achados.add(despesa);
					break;
				}
			}
		}
		return achados;
	}

	
	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int idDespesa = 1; 
		int op;
		
		List<Despesa> despesas = new ArrayList<>();
		
		do {
			op = menu();
			switch (op) {
			case 1:
				while(true) {
					System.out.println("Cadastro de despesa #"+idDespesa);
					System.out.print("Digite a descrição: ");
					String descricao = sc.nextLine();
					System.out.print("Digite o tipo: ");
					String tipo = sc.nextLine();
					System.out.print("Valor: ");
					double valor = sc.nextDouble();
					System.out.print("Data(DD/MM/AAAA): ");
					Date data = sdf.parse(sc.next());
					despesas.add(new Despesa(idDespesa, descricao, tipo, valor, data));
					idDespesa ++;
					System.out.println("Deseja novo cadastro? (S/N)");
					char novo = sc.next().toLowerCase().charAt(0);
					if(novo == 'n') 
						break;
				}
				break;
			case 2:
				if(!despesas.isEmpty()) {
					for(Despesa despesa : despesas) 
						System.out.println(despesa);
						sc.nextLine();
				}else {
					System.out.println("Cadastre ao menos 1 despesa");
				}
				System.out.println();
				System.out.println("Aperte qualquer tecla para voltar ao menu inicial");
				sc.nextLine();
				break;
			case 4:
				if(!despesas.isEmpty()) {
					System.out.println("Total de despesas: R$"+String.format("%.2f",getTotal(despesas)));
				}else {
					System.out.println("Cadastre ao menos 1 despesa");
				}
				System.out.println();
				System.out.println("Aperter quaquer caractere para voltar ao menu inicial");
				sc.nextLine();
				break;
			case 5:
				if(!despesas.isEmpty()) {
					System.out.println("Digite a categoria");
					String categoria = sc.next();
					double total = getTotalCategoria(despesas, categoria);
					if(total == 0) {
						System.out.println("Não existem despesas ou a categoria selecionada é inválida");
						sc.nextLine();
					}else
						System.out.println("Total de despesas de "+categoria+" R$"+String.format("%.2f",total));
						sc.nextLine();
				}else {
					System.out.println("Cadastre ao menos 1 despesa");
				}
				System.out.println();
				System.out.println("Aperter quaquer caractere para voltar ao menu inicial");
				sc.nextLine();
				break;
			case 7:
				if(!despesas.isEmpty()) {
					String palavra;
					while(true) {
						// considera palavras acima de 4 letras;
						System.out.println("Digite uma palavra para a busca");
						palavra = sc.next();
						int tamanhoPalavra = palavra.length();
						if(tamanhoPalavra < 4) {
							System.out.println("Palavra inválida, digite palavras com o minimo de 4 caracteres");
						}else {
							break;
						}
					}
					if(buscaPalavra(despesas, palavra).isEmpty()) {
						System.out.println("Não foram achadas descrições relacionadas");
					}else {
						System.out.println("Despesas encontradas");
						for(Despesa despesa : buscaPalavra(despesas, palavra)) {
							System.out.println(despesa);
							System.out.println();
						}
						sc.nextLine();
					}
				}else {
					System.out.println("Cadastre ao menos 1 despesa");
				}
				System.out.println();
				System.out.println("Aperter quaquer caractere para voltar ao menu inicial");
				sc.nextLine();
				break;
			case 8:
				/* MOSTRAR OS OBJETOS da categoria/tipo que a pessoa digitar
				 * AQUI VC PERGUNTA
				 * */
				
			}
		}while(op != 6);
		
		System.out.println("FIM");

		

	}

}
