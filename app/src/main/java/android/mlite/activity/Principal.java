package android.mlite.activity;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.mlite.R;
import android.mlite.db.MLiteDatabase;
import android.mlite.pojo.Aula;
import android.mlite.util.Util;

public class Principal extends Activity {

	private SharedPreferences sp;

	// Valor do progresso do Usuário em termos de aulas assistidas
	private float progresso;

	// layout que armazena as aulas
	private LinearLayout containerAulas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		MLiteDatabase.inicializar(getApplicationContext());

		// Inicializando o objeto de Preferências Compartilhadas
		sp = getSharedPreferences(Util.SP_PERFIL_USUARIO, MODE_PRIVATE);

		// Montagem do painel de informações do Usuário
		montarDadosUsuario();

		// Montagem da lista de aula na tela com acesso assíncrona ao BD
		containerAulas = (LinearLayout) findViewById(R.id.ll_lista_aulas);
		new CarregarAulasTask().execute();
	}

	@Override
	protected void onDestroy() {
		MLiteDatabase.encerrarSessao();
		super.onDestroy();
	}

	/**
	 * Exibe os dados de perfil do Usuário na tela principal.
	 */
	private void montarDadosUsuario() {

		// Resgate do nome do Usuário das Preferências Compartilhadas
		TextView nomeUsuario = (TextView) findViewById(R.id.tv_nome_usuario);
		nomeUsuario.setText(sp.getString(Util.SP_PERFIL_NOME_USUARIO,
				Util.SP_PERFIL_NOME_USUARIO_PADRAO));

		// Resgate da foto do Usuário das Preferências Compartilhadas
		String uriImagemUsuarioStr = sp
				.getString(Util.SP_PERFIL_URI_IMAGEM, "");
		try {
			if (!uriImagemUsuarioStr.isEmpty()) {
				ImageView fotoUsuario = (ImageView) findViewById(R.id.iv_usuario);
				Bitmap imagemUsuario = Util.decodeUri(getContentResolver(),
						Uri.parse(uriImagemUsuarioStr));
				fotoUsuario.setImageBitmap(imagemUsuario);
			}
		} catch (FileNotFoundException fnfe) {
			Toast.makeText(getApplicationContext(),
					Util.MSG_ARQUIVO_NAO_ENCONTRADO, Toast.LENGTH_LONG).show();
			Log.e(Util.TAG_ERRO, Util.MSG_ARQUIVO_NAO_ENCONTRADO);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), Util.MSG_EXCECAO,
					Toast.LENGTH_LONG).show();
			String contexto = "Retorno da seleção de imagem para perfil.\n";
			Log.e(Util.TAG_ERRO, contexto + e.getMessage());
		}

	}

	/**
	 * Exibe a lista de aulas disponíveis na aplicação. O preenchimento do
	 * objeto 'listaAulas' é feito de forma assíncrona durante o acesso ao banco
	 * de dados pela classe {@code CarregarAulasTask}. Também é feito o cálculo
	 * do progresso do aluno (aulas assistidas / aulas totais).
	 */
	public void montarListaAulas(List<Aula> listaAulas) {
		// Verificando o preenchimento da lista de aulas
		if (listaAulas != null && listaAulas.size() > 0) {

			// Inflater - para alocação dinámica de componentes na UI
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			float aulasAcessadas = 0f;
			
			// Iteração na lista de aulas
			for (Aula aula : listaAulas) {

				if(aula.getAcessada())
					aulasAcessadas++;
				
				// Instanciação do LinearLayout base da aula
				LinearLayout linhaAula = (LinearLayout) inflater.inflate(
						R.layout.item_aula, containerAulas, false);

				// ícone da aula (Miniatura do Vídeo)
				ImageView icone = (ImageView) linhaAula
						.findViewById(R.id.iv_miniatura_aula);
				icone.setTag(aula);

				/**
				 * Definindo o ContentDescription como ID da aula para captura
				 * no evento de clique.
				 */
				icone.setContentDescription(aula.getId().toString());

				int miniatura = getResources().getIdentifier(aula.getMiniatura(), 
						"drawable",	getPackageName());
				if (miniatura != 0)
					icone.setImageResource(miniatura);
				icone.setAlpha(aula.getAcessada() ? Util.TRANSPARENTE_30
						: Util.TRANSPARENTE_0);

				// Título da aula
				TextView titulo = (TextView) linhaAula
						.findViewById(R.id.tv_titulo_aula);
				titulo.setText(aula.getTitulo());

				// Descrição da aula
				TextView descricao = (TextView) linhaAula
						.findViewById(R.id.tv_descricao_aula);
				descricao.setText(aula.getDescricao());

				// Adição do layout de aula à lista em tela
				containerAulas.addView(linhaAula);
			}
			
			// Exibição do progresso do Usuário por extenso e em barra
			progresso = aulasAcessadas / listaAulas.size();

			DecimalFormat df = new DecimalFormat("#0");
			TextView progessoTexto = (TextView) findViewById(R.id.tv_progresso_usuario);
			progessoTexto.setText("Progresso: " + df.format(progresso * 100)
					+ "%");

			ProgressBar progessoBarra = (ProgressBar) findViewById(R.id.pb_progresso_usuario);
			progessoBarra.setProgress((int) (progresso * 100));

		}
	}

	/**
	 * Dispara a navegação para o vídeo da aula selecionada
	 * @param v referência ao item da lista de aulas clicado
	 */
	public void abrirVideoAula(View v) {
		Intent intent = new Intent(getApplicationContext(), AssistirVideo.class);
		intent.putExtra("aula", (Aula) v.getTag());
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	/**
	 * Tarefa assíncrona para carregamento da lista de aulas que
	 * fica na tela principal da aplicação. Os dados das aulas estão
	 * no Banco de Dados.
	 */
	private class CarregarAulasTask extends AsyncTask<Void, Void, List<Aula>> {

		@Override
		protected List<Aula> doInBackground(Void... param) {
			// carregamento da lista de aulas
			return MLiteDatabase.carregarAulas();
		}

		@Override
		protected void onPostExecute(List<Aula> aulas) {
			super.onPostExecute(aulas);
			montarListaAulas(aulas);
		}

	}

}
