package android.mlite.db;


import android.provider.BaseColumns;

public class MLiteContract {

	private MLiteContract() {}

	public static final String NOME_BANCO = "mlite.db";
	public static final int VERSAO = 1;

	/**
	 * Defina nessa classe a estrutura das tabelas do banco de
	 * dados da aplicação. Vocé pode fazer modificações no que
	 * foi sugerido em aula ou usado na classes do pacote 
	 * android.mlite.pojo, contanto que não deixe de cumprir os
	 * requisitos do projeto.
	 */

	public static class Aula implements BaseColumns {
		public static final String TABLE_NAME = "aula";
		public static final String COLUMN_NAME_TITULO = "titulo";
		public static final String COLUMN_NAME_DESCRICAO = "descricao";
		public static final String COLUMN_NAME_VIDEO = "video";
		public static final String COLUMN_NAME_MINIATURA = "miniatura";
		public static final String COLUMN_NAME_ACESSADA = "acessada";

		public static final String SQL_CREATE_ENTRIES =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_NAME_TITULO + " TEXT," +
						COLUMN_NAME_DESCRICAO + " TEXT," +
						COLUMN_NAME_VIDEO + " TEXT," +
						COLUMN_NAME_MINIATURA + " TEXT," +
						COLUMN_NAME_ACESSADA + " INTEGER)";

		public static final String SQL_DELETE_ENTRIES =
				"DROP TABLE IF EXISTS " + TABLE_NAME;
	}

	public static class Quiz implements BaseColumns {
		public static final String TABLE_NAME = "quiz";
		public static final String COLUMN_NAME_TITULO = "titulo";
		public static final String COLUMN_NAME_ID_AULA = "id_aula";

		public static final String SQL_CREATE_ENTRIES =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_NAME_TITULO + " TEXT," +
						COLUMN_NAME_ID_AULA + " INTEGER," +
						"FOREIGN KEY(" + COLUMN_NAME_ID_AULA +
						") REFERENCES " + Aula.TABLE_NAME + "(" + Aula._ID + "))";

		public static final String SQL_DELETE_ENTRIES =
				"DROP TABLE IF EXISTS " + TABLE_NAME;
	}

	public static class Questao implements BaseColumns {
		public static final String TABLE_NAME = "questao";
		public static final String COLUMN_NAME_ENUNCIADO = "enunciado";
		public static final String COLUMN_NAME_ID_QUIZ = "id_quiz";

		public static final String SQL_CREATE_ENTRIES =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_NAME_ENUNCIADO + " TEXT," +
						COLUMN_NAME_ID_QUIZ + " INTEGER," +
						"FOREIGN KEY(" + COLUMN_NAME_ID_QUIZ +
						") REFERENCES " + Quiz.TABLE_NAME + "(" + Quiz._ID + "))";

		public static final String SQL_DELETE_ENTRIES =
				"DROP TABLE IF EXISTS " + TABLE_NAME;
	}

	public static class Item implements BaseColumns {
		public static final String TABLE_NAME = "item";
		public static final String COLUMN_NAME_DESCRICAO = "descricao";
		public static final String COLUMN_NAME_FEEDBACK = "feedback";
		public static final String COLUMN_NAME_CORRETO = "correto";
		public static final String COLUMN_NAME_ID_QUESTAO = "id_questao";

		public static final String SQL_CREATE_ENTRIES =
				"CREATE TABLE " + TABLE_NAME + " (" +
						_ID + " INTEGER PRIMARY KEY," +
						COLUMN_NAME_DESCRICAO + " TEXT," +
						COLUMN_NAME_FEEDBACK + " TEXT," +
						COLUMN_NAME_CORRETO + " INTEGER," +
						COLUMN_NAME_ID_QUESTAO + " INTEGER," +
						"FOREIGN KEY(" + COLUMN_NAME_ID_QUESTAO +
						") REFERENCES " + Questao.TABLE_NAME + "(" + Questao._ID + "))";

		public static final String SQL_DELETE_ENTRIES =
				"DROP TABLE IF EXISTS " + TABLE_NAME;
	}
	
}
