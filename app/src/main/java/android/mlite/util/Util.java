package android.mlite.util;

import java.io.FileNotFoundException;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class Util {

	// Constantes de Preferências Compartilhadas
	public static final String SP_PERFIL_USUARIO = "PERFIL_USUARIO";
	public static final String SP_PERFIL_CRIADO = "PERFIL_CRIADO";
	public static final String SP_PERFIL_NOME_USUARIO = "NOME_USUARIO";
	public static final String SP_PERFIL_NOME_USUARIO_PADRAO = "Usuário";
	public static final String SP_PERFIL_URI_IMAGEM = "URI_IMAGEM_USUARIO";
	
	// Geral
	public static final String REC_AULA = "aula";
	public static final String VIDEO_CARREGADO = "VIDEO_CARREGADO";
	public static final String POSICAO_ATUAL_VIDEO = "POSICAO_VIDEO";
	public static final String STATUS_VIDEO = "STATUS_VIDEO";
	
	// Constantes numéricas
	public static final int TAMANHO_MINIATURA_FOTO_PERFIL = 120;
	public static final int RESULT_SELECIONAR_IMAGEM = 1;
	public static final float TRANSPARENTE_30 = 0.3f;
	public static final float TRANSPARENTE_0 = 1;
	
	// Logging
	public static final String TAG_ERRO = "[PF_ERRO]";
	public static final String TAG_AVISO = "[PF_AVISO]";
	
	public static final String MSG_ARQUIVO_NAO_ENCONTRADO = "Arquivo da imagem não encontrado.";
	public static final String MSG_EXCECAO = "Falha no sistema. Por favor, verificar o log de erros.";
	public static final String TITULO_CORRECAO = "Correção do Quiz - ";
	

	/**
	 * Fornece o Bitmap redimensionado de uma imagem baseado no seu URI.
	 * @param cr referência do ContentResolver (deve ser obtido na activity que disparou o método)
	 * @param uriImagem URI da imagem na galeria
	 * @return Bitmap da imagem redimensioanada
	 * @throws FileNotFoundException Falha em caso de arquivo não encontrado
	 */
	public static Bitmap decodeUri(ContentResolver cr, Uri uriImagem) throws FileNotFoundException {

		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(cr.openInputStream(uriImagem), null, o);

		// Ajuste da escala da imagem
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		while (true) {
			if (width_tmp / 2 < TAMANHO_MINIATURA_FOTO_PERFIL
					|| height_tmp / 2 < TAMANHO_MINIATURA_FOTO_PERFIL) {
				break;
			}
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		// Geração da figura no tamanho desejado
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		return BitmapFactory.decodeStream(cr.openInputStream(uriImagem),
				null, o2);

	}
}
