// Matheus Felipe Trinca - Jo�o Vitor Pereira

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
	
	/* ----- METODOS AUXILIARES ----- */
	
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
							  +"6.Remover despesa\n"
							  +"7.Finalizar");
			System.out.println("=================================");
			op = sc.nextInt();
			if (op < 1 || op > 7) {
				System.out.println("Valor Digitado inv�lido - Digite Novamente");
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
								  +"   a.Por descri��o\n"
								  +"   b.Por tipo\n"
								  +"   ===============\n");
				opSub = sc.next().toLowerCase().charAt(0);
				if(opSub == 'a') {
					op = 8;
					break;
				}else if(opSub == 'b') {
					op = 9;
					break;
				}else {
					System.out.println("Valor Digitado inv�lido - Digite Novamente");
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
			if(despesa.getTipo().equalsIgnoreCase(categoria)) {
				soma += despesa.getValor();
			}
		}
		return soma;
	}
	
	// Busca na descri��o do objeto a palavra digitada no menu, e retorna a despesa, se encontrada.
	// Considerando que a descri��o seja um breve texto, frase ou palavra.
	public static List<Despesa> buscaPalavra(List<Despesa> despesas, String palavra) {
		List<Despesa> achados = new ArrayList<>();
		int tamanhoPalavra = palavra.length();
		
		for(Despesa despesa : despesas) {
			for(int i = 0; i <= despesa.getDescricao().length() - tamanhoPalavra; i++) {
				String sub = despesa.getDescricao().substring(i, (i+tamanhoPalavra));
				if(sub.equalsIgnoreCase(palavra)) {
					achados.add(despesa);
					break;
				}
			}
		}
		return achados;
	}
	
	public static void imprimir(Despesa despesa) {
		System.out.println(" ### DESPESA ###");
		System.out.println(despesa);
		
	}
	
	public static boolean buscaDespesaId(List<Despesa> despesas, int id) {
		for(Despesa despesa : despesas) {
			if(despesa.getCodigo() == id) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean buscaDespesaTipo(List<Despesa> despesas, String tipo) {
		for(Despesa despesa : despesas) {
			if(despesa.getTipo().equalsIgnoreCase(tipo)) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Despesa> excluir(List<Despesa> despesas, int codRemocao){
		for(Despesa despesa : despesas) {
			if(despesa.getCodigo() == codRemocao) {
				despesas.remove(despesa);
				return despesas;
			}
		}
		return despesas;
	}
	
	/*------ MAIN ------*/

	
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
					System.out.print("Digite a descri��o: ");
					String descricao = sc.nextLine();
					System.out.print("Digite o tipo: ");
					String tipo = sc.nextLine();
					System.out.print("Valor: ");
					double valor = sc.nextDouble();
					System.out.print("Data(DD/MM/AAAA): ");
					Date data = sdf.parse(sc.next());
					despesas.add(new Despesa(idDespesa, descricao, tipo, valor, data));
					idDespesa ++;
					System.out.print("Deseja novo cadastro? (S/N)");
					char novo = sc.next().toLowerCase().charAt(0);
					System.out.println();
					sc.nextLine();
					if(novo == 'n') 
						break;
				}
				break;
			case 2:
				if(!despesas.isEmpty()) {
					for(Despesa despesa : despesas) 
						imprimir(despesa);
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
						System.out.println("N�o existem despesas ou a categoria selecionada � inv�lida");

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
			case 6:
				if(!despesas.isEmpty()) {
					while(!despesas.isEmpty()) {
						for(Despesa despesa : despesas) {
							imprimir(despesa);
						}
						System.out.println();
						System.out.println("Digite o c�digo da despesa a ser removida");
						int codRemocao = sc.nextInt();
													
						if(buscaDespesaId(despesas, codRemocao)) {
							excluir(despesas, codRemocao);
							System.out.println("Despesa removida");
							System.out.println();
						}else {
							System.out.println("Despesa n�o encontrada");
							System.out.println();
						}
						
						if(despesas.isEmpty()) {
							System.out.println("Todas s despesas foram exclu�das");
							System.out.println();
							break;
						}
							
						for(Despesa despesa : despesas) {
							imprimir(despesa);
						}
						System.out.println("Deseja nova exclus�o? (S/N): ");
						int opEx = sc.next().toLowerCase().charAt(0);
						if(opEx == 'n') {
							sc.nextLine();
							break;
						}
					}
					
				}else{
					System.out.println("Cadastre ao menos 1 despesa");
				}
				System.out.println();
				System.out.println("Aperter quaquer caractere para voltar ao menu inicial");
				sc.nextLine();
				break;
				
			case 8:
				if(!despesas.isEmpty()) {
					String palavra;
					while(true) {
						// considera palavras acima de 4 letras;
						System.out.println("Digite uma palavra para a busca");
						palavra = sc.next();
						int tamanhoPalavra = palavra.length();
						if(tamanhoPalavra < 4) {
							System.out.println("Palavra inv�lida, digite palavras com o minimo de 4 caracteres");
						}else {
							break;
						}
					}
					if(buscaPalavra(despesas, palavra).isEmpty()) {
						System.out.println("N�o foram achadas descri��es relacionadas");
						sc.nextLine();
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
			case 9:
				/* MOSTRAR OS OBJETOS da categoria/tipo que a pessoa digitar
				 * AQUI VC PERGUNTA
				 * */
				
			}
		}while(op != 7);
		
		System.out.println("FIM");

		

	}

}
