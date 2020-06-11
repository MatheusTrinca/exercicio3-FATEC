package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Despesa {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private int codigo;
	private String descricao;
	private String tipo;
	private Double valor;
	private Date data;
	
	public Despesa() {

	}

	public Despesa(int codigo, String descricao, String tipo, Double valor, Date data) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public Date getData() {
		return data;
	}

	public String toString() {
		return codigo+", "+sdf.format(data)+", "+descricao+", "+tipo+", R$"+String.format("%.2f", valor);
	}
	
}
