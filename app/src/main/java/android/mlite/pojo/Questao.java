package android.mlite.pojo;

import java.util.ArrayList;
import java.util.List;

public class Questao {

	private Integer id;
	private Quiz quiz;
	private String enunciado;
	private List<Item> itens;

	public Questao() {
		itens = new ArrayList<Item>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void adicionarItem(Item item) {
		if (itens != null) {
			itens.add(item);
		}
	}

}
