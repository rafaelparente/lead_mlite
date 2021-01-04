package android.mlite.pojo;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

	private Integer id;
	private Aula aula;
	private String titulo;

	private List<Questao> questoes;

	public Quiz() {
		questoes = new ArrayList<Questao>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void adicionarQuestao(Questao questao) {
		if (questoes != null)
			questoes.add(questao);
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}
	
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

}
