package android.mlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MLiteHelper extends SQLiteOpenHelper {
	
	public MLiteHelper(Context context) {
		super(context, MLiteContract.NOME_BANCO, null, MLiteContract.VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/**
		 * Implemente aqui a criação do banco de dados e a
		 * inserção de registros iniciais (para teste da aplicação)
		 */
		db.execSQL(MLiteContract.Aula.SQL_CREATE_ENTRIES);
		db.execSQL(MLiteContract.Quiz.SQL_CREATE_ENTRIES);
		db.execSQL(MLiteContract.Questao.SQL_CREATE_ENTRIES);
		db.execSQL(MLiteContract.Item.SQL_CREATE_ENTRIES);

		/** Aula 1 */
		ContentValues values_a1 = new ContentValues();
		values_a1.put(MLiteContract.Aula.COLUMN_NAME_TITULO,
				"De onde vem a energia elétrica?");
		values_a1.put(MLiteContract.Aula.COLUMN_NAME_DESCRICAO,
				"Entenda quais as tecnologias envolvidas na produção de energia eletétrica.");
		values_a1.put(MLiteContract.Aula.COLUMN_NAME_VIDEO, "me000729");
		values_a1.put(MLiteContract.Aula.COLUMN_NAME_MINIATURA, "ime000729");
		values_a1.put(MLiteContract.Aula.COLUMN_NAME_ACESSADA, 0);
		long id_a1 = db.insert(MLiteContract.Aula.TABLE_NAME, null, values_a1);

		// Quiz
		ContentValues values_qz1 = new ContentValues();
		values_qz1.put(MLiteContract.Quiz.COLUMN_NAME_TITULO,
				"Vamos testar seus conhecimentos sobre energia elétrica");
		values_qz1.put(MLiteContract.Quiz.COLUMN_NAME_ID_AULA, id_a1);
		long id_qz1 = db.insert(MLiteContract.Quiz.TABLE_NAME, null, values_qz1);

		// Questão
		ContentValues values_q1 = new ContentValues();
		values_q1.put(MLiteContract.Questao.COLUMN_NAME_ENUNCIADO,
				"Como se chama a usina que produz energia com a força das águas?");
		values_q1.put(MLiteContract.Questao.COLUMN_NAME_ID_QUIZ, id_qz1);
		long id_q1 = db.insert(MLiteContract.Questao.TABLE_NAME, null, values_q1);

		// Itens
		ContentValues values_i1 = new ContentValues();
		values_i1.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Hidropónica.");
		values_i1.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Esse termo corresponde a uma técnica de cultivo de vegetais sem solo.");
		values_i1.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i1.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q1);
		long id_i1 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i1);

		ContentValues values_i2 = new ContentValues();
		values_i2.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Termoelétrica.");
		values_i2.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Esse tipo de usina produz energia a partir da queima de combustíveis.");
		values_i2.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i2.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q1);
		long id_i2 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i2);

		ContentValues values_i3 = new ContentValues();
		values_i3.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Nuclear.");
		values_i3.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Esse tipo de usina utiliza elementos radioativos como o Urânio para produzir energia.");
		values_i3.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i3.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q1);
		long id_i3 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i3);

		ContentValues values_i4 = new ContentValues();
		values_i4.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Hidroelétrica.");
		values_i4.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Correto! O nome hidroelétrica vem da composição de Hidro (água) e Elétrica (eletricidade).");
		values_i4.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 1);
		values_i4.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q1);
		long id_i4 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i4);

		// Questão
		ContentValues values_q2 = new ContentValues();
		values_q2.put(MLiteContract.Questao.COLUMN_NAME_ENUNCIADO,
				"Qual o nome do físico americano considerado pai da Energia Elétrica?");
		values_q2.put(MLiteContract.Questao.COLUMN_NAME_ID_QUIZ, id_qz1);
		long id_q2 = db.insert(MLiteContract.Questao.TABLE_NAME, null, values_q2);

		// Itens
		ContentValues values_i5 = new ContentValues();
		values_i5.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Benjamin Franklin");
		values_i5.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Correto! Benjamin Franklin viveu entre os séculos XVII e XVIII.");
		values_i5.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 1);
		values_i5.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q2);
		long id_i5 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i5);

		ContentValues values_i6 = new ContentValues();
		values_i6.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"George Washington");
		values_i6.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Geroge Washington foi o primeiro presidente dos Estados Unidos.");
		values_i6.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i6.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q2);
		long id_i6 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i6);

		ContentValues values_i7 = new ContentValues();
		values_i7.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Thomas Edison");
		values_i7.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Thomas Edison é considerado o criador da lâmpada elétrica.");
		values_i7.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i7.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q2);
		long id_i7 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i7);

		ContentValues values_i8 = new ContentValues();
		values_i8.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Graham Bell");
		values_i8.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Bell foi o inventor do telefone.");
		values_i8.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i8.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q2);
		long id_i8 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i8);

		// Questão
		ContentValues values_q3 = new ContentValues();
		values_q3.put(MLiteContract.Questao.COLUMN_NAME_ENUNCIADO,
				"É verdade que é possível produzir energia elétrica a partir da força dos ventos?");
		values_q3.put(MLiteContract.Questao.COLUMN_NAME_ID_QUIZ, id_qz1);
		long id_q3 = db.insert(MLiteContract.Questao.TABLE_NAME, null, values_q3);

		// Itens
		ContentValues values_i9 = new ContentValues();
		values_i9.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Sim. Essa categoria chama-se Energia Eólica.");
		values_i9.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Correto! A produção de energia eólica é bastante utilizada por todo o mundo.");
		values_i9.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 1);
		values_i9.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q3);
		long id_i9 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i9);

		ContentValues values_i10 = new ContentValues();
		values_i10.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Não, ainda não há como transformar vento em energia elétrica.");
		values_i10.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. A energia produzida com a força dos ventos chama-se eólica e é bastante utilizada no nordeste Brasileiro.");
		values_i10.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i10.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q3);
		long id_i10 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i10);
		/** ------ */

		/** Aula 2 */
		ContentValues values_a2 = new ContentValues();
		values_a2.put(MLiteContract.Aula.COLUMN_NAME_TITULO,
				"De onde vem o arco-íris?");
		values_a2.put(MLiteContract.Aula.COLUMN_NAME_DESCRICAO,
				"Venha descobrir os mistérios do arco-íris.");
		values_a2.put(MLiteContract.Aula.COLUMN_NAME_VIDEO, "me000728");
		values_a2.put(MLiteContract.Aula.COLUMN_NAME_MINIATURA, "ime000728");
		values_a2.put(MLiteContract.Aula.COLUMN_NAME_ACESSADA, 0);
		long id_a2 = db.insert(MLiteContract.Aula.TABLE_NAME, null, values_a2);

		// Quiz
		ContentValues values_qz2 = new ContentValues();
		values_qz2.put(MLiteContract.Quiz.COLUMN_NAME_TITULO,
				"Será que você aprendeu tudo sobre o arco-íris?");
		values_qz2.put(MLiteContract.Quiz.COLUMN_NAME_ID_AULA, id_a2);
		long id_qz2 = db.insert(MLiteContract.Quiz.TABLE_NAME, null, values_qz2);

		// Questão
		ContentValues values_q4 = new ContentValues();
		values_q4.put(MLiteContract.Questao.COLUMN_NAME_ENUNCIADO,
				"Um arco-íris normalmente se forma quando a luz do sol atravessa:");
		values_q4.put(MLiteContract.Questao.COLUMN_NAME_ID_QUIZ, id_qz2);
		long id_q4 = db.insert(MLiteContract.Questao.TABLE_NAME, null, values_q4);

		// Itens
		ContentValues values_i11 = new ContentValues();
		values_i11.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"A grama verde.");
		values_i11.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. A grama não gera arco-íris.");
		values_i11.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i11.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q4);
		long id_i11 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i11);

		ContentValues values_i12 = new ContentValues();
		values_i12.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Gotículas de água no ar.");
		values_i12.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Correto! Gotículas de água no ar são a principal forma de ocorrência de arco-íris.");
		values_i12.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 1);
		values_i12.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q4);
		long id_i12 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i12);

		ContentValues values_i13 = new ContentValues();
		values_i13.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Um pedaço de madeira.");
		values_i13.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. A luz do sol não pode atravessar um pedaço de madeira.");
		values_i13.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i13.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q4);
		long id_i13 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i13);

		ContentValues values_i14 = new ContentValues();
		values_i14.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Um banco de areia.");
		values_i14.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. A areia não tem capacidade de gerar arco-íris.");
		values_i14.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i14.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q4);
		long id_i14 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i14);

		// Questão
		ContentValues values_q5 = new ContentValues();
		values_q5.put(MLiteContract.Questao.COLUMN_NAME_ENUNCIADO,
				"Como se chama o efeito óptico que gera o arco-íris?");
		values_q5.put(MLiteContract.Questao.COLUMN_NAME_ID_QUIZ, id_qz2);
		long id_q5 = db.insert(MLiteContract.Questao.TABLE_NAME, null, values_q5);

		// Itens
		ContentValues values_i15 = new ContentValues();
		values_i15.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Diapasão.");
		values_i15.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. Diapasão é uma peça metálica que auxilia a afinação de instrumentos musicais.");
		values_i15.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i15.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q5);
		long id_i15 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i15);

		ContentValues values_i16 = new ContentValues();
		values_i16.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Fusão.");
		values_i16.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. A fusão corresponde a um processo de mudança de estado físico de um material.");
		values_i16.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i16.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q5);
		long id_i16 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i16);

		ContentValues values_i17 = new ContentValues();
		values_i17.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Inflação.");
		values_i17.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Incorreto. A inflação é um efeito econômico de variação de preços.");
		values_i17.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 0);
		values_i17.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q5);
		long id_i17 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i17);

		ContentValues values_i18 = new ContentValues();
		values_i18.put(MLiteContract.Item.COLUMN_NAME_DESCRICAO,
				"Refração.");
		values_i18.put(MLiteContract.Item.COLUMN_NAME_FEEDBACK,
				"Correto! A refração consiste na divisão das componentes da luz ao atravessar um prisma pela mudança de direção dos raios de luz.");
		values_i18.put(MLiteContract.Item.COLUMN_NAME_CORRETO, 1);
		values_i18.put(MLiteContract.Item.COLUMN_NAME_ID_QUESTAO, id_q5);
		long id_i18 = db.insert(MLiteContract.Item.TABLE_NAME, null, values_i18);
		/** ------ */
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL(MLiteContract.Item.SQL_DELETE_ENTRIES);
		db.execSQL(MLiteContract.Questao.SQL_DELETE_ENTRIES);
		db.execSQL(MLiteContract.Quiz.SQL_DELETE_ENTRIES);
		db.execSQL(MLiteContract.Aula.SQL_DELETE_ENTRIES);
		onCreate(db);
	}

}
