package android.mlite.pojo;

public class Item {

	private Integer id;
	private Questao questao;
	private String descricao;
	private String feddback;
	private Boolean correto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFeddback() {
		return feddback;
	}

	public void setFeddback(String feddback) {
		this.feddback = feddback;
	}

	public Boolean getCorreto() {
		return correto;
	}

	public void setCorreto(Boolean correto) {
		this.correto = correto;
	}

}
