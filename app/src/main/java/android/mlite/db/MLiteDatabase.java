package android.mlite.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.mlite.pojo.Aula;
import android.mlite.pojo.Quiz;

import java.util.List;


public class MLiteDatabase {

	private static MLiteHelper helper;
	private static SQLiteDatabase db;

	public static void inicializar(Context contexto) {
		if (helper == null) {
			helper = new MLiteHelper(contexto);
		}
	}

	public static void encerrarSessao() {
		if (db != null)
			db.close();
	}

	/**
	 * Monta a lista completa de aulas do banco de dados.
	 * @return uma lista contendo todas as aulas do banco de dados.
	 */
	public static List<Aula> carregarAulas() {

		return MLiteDatabaseMock.carregarAulas();

	}
	
	/**
	 * Monta a representação de uma aula específica a partir de seu identificador.
	 * @param idAula identificador da aula
	 * @return Aula
	 */
	public static Aula carregarAula(Integer idAula) {

		return MLiteDatabaseMock.carregarAula(idAula);

	}
		
	/**
	 * Obtém o quiz de uma aula indicada.
	 * @param idAula identificador da aula
	 * @return O Quiz da aula indicada
	 */
	public static Quiz carregarQuiz(Integer idAula) {

		return MLiteDatabaseMock.carregarQuiz(idAula);
		
	}
	
	/**
	 * Este método deve ser implementado para marcar uma aula como "acessada".
	 * O cálculo do progresso do Usuário é baseado na divisão da quantidade de aulas
	 * acessadas pelo número total de questões do banco de dados.
	 * @param idAula identificador da aula
	 */
	public static void marcarAulaAcessadas(Integer idAula) {
		
	}

}
