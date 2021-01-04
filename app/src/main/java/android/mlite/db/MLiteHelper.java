package android.mlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
		Log.w("[PROJETO_FINAL]", "Montagem do banco ainda não implementado.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		Log.w("[PROJETO_FINAL]", "Ajustes na versão do banco não implementada.");
	}

}
