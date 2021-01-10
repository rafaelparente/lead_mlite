package android.mlite.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Quiz implements Parcelable {

	private Integer id;
	private Aula aula;
	private String titulo;

	private List<Questao> questoes;

	public Quiz() {
		questoes = new ArrayList<Questao>();
	}

	protected Quiz(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readInt();
		}
		aula = in.readParcelable(Aula.class.getClassLoader());
		titulo = in.readString();
		questoes = in.createTypedArrayList(Questao.CREATOR);
	}

	public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
		@Override
		public Quiz createFromParcel(Parcel in) {
			return new Quiz(in);
		}

		@Override
		public Quiz[] newArray(int size) {
			return new Quiz[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (id == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeInt(id);
		}
		dest.writeParcelable(aula, flags);
		dest.writeString(titulo);
		dest.writeTypedList(questoes);
	}

}
