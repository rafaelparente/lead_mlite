package android.mlite.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Questao implements Parcelable {

	private Integer id;
	private Quiz quiz;
	private String enunciado;
	private List<Item> itens;

	public Questao() {
		itens = new ArrayList<Item>();
	}

	protected Questao(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readInt();
		}
		quiz = in.readParcelable(Quiz.class.getClassLoader());
		enunciado = in.readString();
		itens = in.createTypedArrayList(Item.CREATOR);
	}

	public static final Creator<Questao> CREATOR = new Creator<Questao>() {
		@Override
		public Questao createFromParcel(Parcel in) {
			return new Questao(in);
		}

		@Override
		public Questao[] newArray(int size) {
			return new Questao[size];
		}
	};

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
		dest.writeParcelable(quiz, flags);
		dest.writeString(enunciado);
		dest.writeTypedList(itens);
	}

}
