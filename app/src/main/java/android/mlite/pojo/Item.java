package android.mlite.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

	private Integer id;
	private Questao questao;
	private String descricao;
	private String feedback;
	private Boolean correto;

	public Item() { }

	protected Item(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readInt();
		}
		questao = in.readParcelable(Questao.class.getClassLoader());
		descricao = in.readString();
		feedback = in.readString();
		byte tmpCorreto = in.readByte();
		correto = tmpCorreto == 0 ? null : tmpCorreto == 1;
	}

	public static final Creator<Item> CREATOR = new Creator<Item>() {
		@Override
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}

		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};

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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Boolean getCorreto() {
		return correto;
	}

	public void setCorreto(Boolean correto) {
		this.correto = correto;
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
		dest.writeParcelable(questao, flags);
		dest.writeString(descricao);
		dest.writeString(feedback);
		dest.writeByte((byte) (correto == null ? 0 : correto ? 1 : 2));
	}

}
