package android.mlite.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.mlite.pojo.Aula;
import android.mlite.pojo.Item;
import android.mlite.pojo.Questao;
import android.mlite.pojo.Quiz;

import java.util.ArrayList;
import java.util.List;


public class MLiteDatabase {

	private static MLiteHelper helper;
	private static SQLiteDatabase db;

	public static void inicializar(Context contexto) {
		if (helper == null) {
			helper = new MLiteHelper(contexto);
		}

		if (db == null) {
			db = helper.getReadableDatabase();
		}
	}

	public static void encerrarSessao() {
		if (db != null) db.close();
		if (helper != null) helper.close();
	}

	/**
	 * Monta a lista completa de aulas do banco de dados.
	 * @return uma lista contendo todas as aulas do banco de dados.
	 */
	public static List<Aula> carregarAulas() {

		Cursor cursor = db.query(MLiteContract.Aula.TABLE_NAME,
				null, null, null,
				null, null, null);

		List<Aula> aulas = new ArrayList<>();

		while (cursor.moveToNext()) {
			Aula aula = new Aula();
			aula.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Aula._ID)));
			aula.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_TITULO)));
			aula.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_DESCRICAO)));
			aula.setVideo(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_VIDEO)));
			aula.setMiniatura(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_MINIATURA)));
			aula.setAcessada(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_ACESSADA)) == 1);
			aulas.add(aula);
		}
		cursor.close();

		return aulas;

	}

	public static List<Quiz> carregarQuizzes(Integer idAula) {

		List<Quiz> quizzes = new ArrayList<>();

		String selection = MLiteContract.Quiz.COLUMN_NAME_ID_AULA + " = ?";
		String[] selectionArgs = { idAula.toString() };

		Cursor cursor = db.query(MLiteContract.Quiz.TABLE_NAME, null,
				selection, selectionArgs, null, null, null);

		while (cursor.moveToNext()) {
			Quiz quiz = new Quiz();
			quiz.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Quiz._ID)));
			quiz.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Quiz.COLUMN_NAME_TITULO)));
			quizzes.add(quiz);
		}

		cursor.close();
		return quizzes;
		
	}

	public static List<Questao> carregarQuestoes(Integer idQuiz) {

		List<Questao> questoes = new ArrayList<>();

		String selection = MLiteContract.Questao.COLUMN_NAME_ID_QUIZ + " = ?";
		String[] selectionArgs = { idQuiz.toString() };

		Cursor cursor = db.query(MLiteContract.Questao.TABLE_NAME, null,
				selection, selectionArgs, null, null, null);

		while (cursor.moveToNext()) {
			Questao questao = new Questao();
			questao.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Questao._ID)));
			questao.setEnunciado(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Questao.COLUMN_NAME_ENUNCIADO)));
			questoes.add(questao);
		}

		cursor.close();
		return questoes;

	}

	public static List<Item> carregarItems(Integer idQuestao) {

		List<Item> itens = new ArrayList<>();

		String selection = MLiteContract.Item.COLUMN_NAME_ID_QUESTAO + " = ?";
		String[] selectionArgs = { idQuestao.toString() };

		Cursor cursor = db.query(MLiteContract.Item.TABLE_NAME, null,
				selection, selectionArgs, null, null, null);

		while (cursor.moveToNext()) {
			Item item = new Item();
			item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Item._ID)));
			item.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Item.COLUMN_NAME_DESCRICAO)));
			item.setFeedback(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Item.COLUMN_NAME_FEEDBACK)));
			item.setCorreto(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Item.COLUMN_NAME_CORRETO)) == 1);
			itens.add(item);
		}

		cursor.close();
		return itens;

	}
	
	/**
	 * Este método deve ser implementado para marcar uma aula como "acessada".
	 * O cálculo do progresso do Usuário é baseado na divisão da quantidade de aulas
	 * acessadas pelo número total de questões do banco de dados.
	 * @param idAula identificador da aula
	 */
	public static void marcarAulaAcessada(Integer idAula) {
		if (db.isReadOnly()) {
			db = helper.getWritableDatabase();
		}

		ContentValues values = new ContentValues();
		values.put(MLiteContract.Aula.COLUMN_NAME_ACESSADA, 1);

		String selection = MLiteContract.Aula._ID + " = ?";
		String[] selectionArgs = { idAula.toString() };

		db.update(
				MLiteContract.Aula.TABLE_NAME,
				values,
				selection,
				selectionArgs
		);
	}


	public static Aula carregarAula(Integer idAula) {

		String selection = MLiteContract.Aula._ID + " = ?";
		String[] selectionArgs = { idAula.toString() };

		Cursor cursor = db.query(MLiteContract.Aula.TABLE_NAME,
				null, selection, selectionArgs,
				null, null, null);

		while (cursor.moveToNext()) {
			Aula aula = new Aula();
			aula.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Aula._ID)));
			aula.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_TITULO)));
			aula.setDescricao(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_DESCRICAO)));
			aula.setVideo(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_VIDEO)));
			aula.setMiniatura(cursor.getString(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_MINIATURA)));
			aula.setAcessada(cursor.getInt(cursor.getColumnIndexOrThrow(MLiteContract.Aula.COLUMN_NAME_ACESSADA)) == 1);
			cursor.close();
			return aula;
		}

		cursor.close();
		return null;

	}

}
