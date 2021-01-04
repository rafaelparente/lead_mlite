package android.mlite.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Aula implements Parcelable {
	
	private Integer id;
	private String titulo;
	private String descricao;
	private String video;
	private String miniatura;
	private Boolean acessada;
	
	private List<Quiz> quizzes;

	public Aula() {
		quizzes = new ArrayList<Quiz>();
	}

	protected Aula(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readInt();
		}
		titulo = in.readString();
		descricao = in.readString();
		video = in.readString();
		miniatura = in.readString();
		byte tmpAcessada = in.readByte();
		acessada = tmpAcessada == 0 ? null : tmpAcessada == 1;
	}

	public static final Creator<Aula> CREATOR = new Creator<Aula>() {
		@Override
		public Aula createFromParcel(Parcel in) {
			return new Aula(in);
		}

		@Override
		public Aula[] newArray(int size) {
			return new Aula[size];
		}
	};

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAcessada() {
		return acessada;
	}
	public void setAcessada(Boolean acessada) {
		this.acessada = acessada;
	}
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}
	
	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	public void adicionarQuiz(Quiz quiz) {
		if (quizzes != null)
			quizzes.add(quiz);
	}
	
	public String getVideo() {
		return video;
	}
	
	public void setVideo(String video) {
		this.video = video;
	}
	
	public String getMiniatura() {
		return miniatura;
	}
	public void setMiniatura(String miniatura) {
		this.miniatura = miniatura;
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
		dest.writeString(titulo);
		dest.writeString(descricao);
		dest.writeString(video);
		dest.writeString(miniatura);
		dest.writeByte((byte) (acessada == null ? 0 : acessada ? 1 : 2));
	}

}
