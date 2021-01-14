package android.mlite.activity;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.mlite.R;
import android.mlite.util.Util;


/**
 * Solicita ao usuario seu nome e imagem no primeiro acesso
 * a aplicação.
 */
public class

MontarPerfil extends Activity {

	private String enderecoImagemGaleria;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_montar_perfil);

		// Inicializando o objeto de Preferências Compartilhadas
		sp = getSharedPreferences(Util.SP_PERFIL_USUARIO, MODE_PRIVATE);

		/*
		 * Caso o perfil do usuario ja tenha sido criado, redireciona-lo para a
		 * tela principal da aplicação.
		 */
		if (sp.getInt(Util.SP_PERFIL_CRIADO, 0) == 1) {
			irParaPrincipal();
		}

	}

	/**
	 * Dispara a navegação para a tela principal da aplicação.
	 */
	private void irParaPrincipal() {
		Intent i = new Intent(this, Principal.class);
		startActivity(i);
		finish();
	}

	/**
	 * Grava o perfil do Usuário (com nome e foto) nas Preferências
	 * Compartilhadas da aplicação. Este método é o ouvinte do evento de clique
	 * no botão "Criar Perfil"
	 * @param v referência do botão Criar Perfil
	 */
	public void criarPerfil(View v) {

		// Captura do nome do Usuário
		TextView txtNomeUsuario = (TextView) findViewById(R.id.et_nome_usuario);
		String nomeUsuario = txtNomeUsuario.getText().toString();

		// Gravação do Perfil do Usuário nas Preferências Compartilhadas
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt(Util.SP_PERFIL_CRIADO, 1);
		editor.putString(Util.SP_PERFIL_NOME_USUARIO, nomeUsuario);
		editor.putString(Util.SP_PERFIL_URI_IMAGEM, enderecoImagemGaleria);
		editor.commit();

		// Redirecionamento para a tela principal
		irParaPrincipal();
	}

	/**
	 * Dispara a abertura da galeria para escolha da imagem do Usuário.
	 * @param v referência do ImageView de miniatura da foto
	 */
	public void selecionarImagem(View v) {

		// Abertura da galeria via Intent
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		startActivityForResult(
				Intent.createChooser(intent,
						getResources().getText(R.string.dialogo_selecao_foto)),
				Util.RESULT_SELECIONAR_IMAGEM);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		try {
			if (resultCode == RESULT_OK
					&& requestCode == Util.RESULT_SELECIONAR_IMAGEM
					&& data != null) {
				
				Uri uriIMagemSelecionada = data.getData();
				enderecoImagemGaleria = uriIMagemSelecionada.toString();

				ImageButton miniaturaImagem = (ImageButton) findViewById(R.id.bt_imagem_usuario);
				miniaturaImagem.setImageBitmap(Util.decodeUri(
						getContentResolver(), uriIMagemSelecionada));
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
			
			SharedPreferences.Editor editor = sp.edit();
			editor.putInt(Util.SP_PERFIL_CRIADO, 0);
			editor.commit();
		}

	}

}
