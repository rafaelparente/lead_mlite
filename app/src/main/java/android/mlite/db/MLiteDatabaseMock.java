package android.mlite.db;

import android.mlite.pojo.Aula;
import android.mlite.pojo.Item;
import android.mlite.pojo.Questao;
import android.mlite.pojo.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe é um Mock do banco de dados, ou seja, ela simula
 * registros em um banco de dados que ainda não foi construído para
 * que outras funcionalidades da aplicação possam ser construídas e
 * testadas, como interface gráfica e regras de negócio.
 * 
 * ATENÇÃO: Ao concluir a construçãoo do banco de dados, substitua os
 * método dessa classe por outros que você implementar na classe
 * MLiteDatabaseque. Esses novos métodos deve fazer o acesso ao banco de
 * dados real.
 */
public class MLiteDatabaseMock {

	private static List<Aula> aulas;
	private static int CONT_AULA = 0, CONT_QUIZ = 0, CONT_QUESTAO = 0,
			CONT_ITEM = 0;

	public static void inicializar() {
		
		if(aulas != null)
			return;
		
		aulas = new ArrayList<Aula>();

		/** Aula 1 */
		Aula a1 = new Aula();
		a1.setId(++CONT_AULA);
		a1.setTitulo("De onde vem a energia elétrica?");
		a1.setDescricao("Entenda quais as tecnologias envolvidas na produção de energia eletétrica.");
		a1.setVideo("me000729");
		a1.setMiniatura("ime000729");
		a1.setAcessada(false);
		
		// Quiz
		Quiz qz1 = new Quiz();
		qz1.setId(++CONT_QUIZ);
		qz1.setTitulo("Vamos testar seus conhecimentos sobre energia elétrica");

		// Questão
		Questao q1 = new Questao();
		q1.setId(++CONT_QUESTAO);
		q1.setEnunciado("Como se chama a usina que produz energia com a força das águas?");

		// Itens
		Item i1 = new Item();
		i1.setId(++CONT_ITEM);
		i1.setDescricao("Hidropónica.");
		i1.setFeddback("Incorreto. Esse termo corresponde a uma técnica de cultivo de vegetais sem solo.");
		i1.setCorreto(false);
		
		Item i2 = new Item();
		i2.setId(++CONT_ITEM);
		i2.setDescricao("Termoelétrica.");
		i2.setFeddback("Incorreto. Esse tipo de usina produz energia a partir da queima de combustíveis.");
		i2.setCorreto(false);
		
		Item i3 = new Item();
		i3.setId(++CONT_ITEM);
		i3.setDescricao("Nuclear.");
		i3.setFeddback("Incorreto. Esse tipo de usina utiliza elementos radioativos como o Urânio para produzir energia.");
		i3.setCorreto(false);
		
		Item i4 = new Item();
		i4.setId(++CONT_ITEM);
		i4.setDescricao("Hidroelétrica.");
		i4.setFeddback("Correto! O nome hidroelétrica vem da composição de Hidro (água) e Elétrica (eletricidade).");
		i4.setCorreto(true);
		
		q1.adicionarItem(i1);
		q1.adicionarItem(i2);
		q1.adicionarItem(i3);
		q1.adicionarItem(i4);
		
		// Questão
		Questao q2 = new Questao();
		q2.setId(++CONT_QUESTAO);
		q2.setEnunciado("Qual o nome do físico americano considerado pai da Energia Elétrica?");

		// Itens
		Item i5 = new Item();
		i5.setId(++CONT_ITEM);
		i5.setDescricao("Benjamin Franklin");
		i5.setFeddback("Correto! Benjamin Franklin viveu entre os séculos XVII e XVIII.");
		i5.setCorreto(true);
		
		Item i6 = new Item();
		i6.setId(++CONT_ITEM);
		i6.setDescricao("George Washington");
		i6.setFeddback("Incorreto. Geroge Washington foi o primeiro presidente dos Estados Unidos.");
		i6.setCorreto(false);
		
		Item i7 = new Item();
		i7.setId(++CONT_ITEM);
		i7.setDescricao("Thomas Edison");
		i7.setFeddback("Incorreto. Thomas Edison é considerado o criador da lâmpada elétrica.");
		i7.setCorreto(false);
		
		Item i8 = new Item();
		i8.setId(++CONT_ITEM);
		i8.setDescricao("Graham Bell");
		i8.setFeddback("Incorreto. Bell foi o inventor do telefone.");
		i8.setCorreto(false);
		
		q2.adicionarItem(i5);
		q2.adicionarItem(i6);
		q2.adicionarItem(i7);
		q2.adicionarItem(i8);
		
		// Questão
		Questao q3 = new Questao();
		q3.setId(++CONT_QUESTAO);
		q3.setEnunciado("É verdade que é possível produzir energia elétrica a partir da força dos ventos?");

		// Itens
		Item i9 = new Item();
		i9.setId(++CONT_ITEM);
		i9.setDescricao("Sim. Essa categoria chama-se Energia Eólica.");
		i9.setFeddback("Correto! A produção de energia eólica é bastante utilizada por todo o mundo.");
		i9.setCorreto(true);
		
		Item i10 = new Item();
		i10.setId(++CONT_ITEM);
		i10.setDescricao("Não, ainda não há como transformar vento em energia elétrica.");
		i10.setFeddback("Incorreto. A energia produzida com a força dos ventos chama-se eólica e é bastante utilizada no nordeste Brasileiro.");
		i10.setCorreto(false);
		
		q3.adicionarItem(i9);
		q3.adicionarItem(i10);
		
		qz1.adicionarQuestao(q1);
		qz1.adicionarQuestao(q2);
		qz1.adicionarQuestao(q3);
		
		a1.adicionarQuiz(qz1);
		/** ------ */
		
		/** Aula 2 */
		Aula a2 = new Aula();
		a2.setId(++CONT_AULA);
		a2.setTitulo("De onde vem o arco-íris?");
		a2.setDescricao("Venha descobrir os mistérios do arco-íris.");
		a2.setVideo("me000728");
		a2.setMiniatura("ime000728");
		a2.setAcessada(false);
		
		// Quiz
		Quiz qz2 = new Quiz();
		qz2.setId(++CONT_QUIZ);
		qz2.setTitulo("Será que você aprendeu tudo sobre o arco-íris?");

		// Questão
		Questao q4 = new Questao();
		q4.setId(++CONT_QUESTAO);
		q4.setEnunciado("Um arco-íris normalmente se forma quando a luz do sol atravessa:");

		// Itens
		Item i13 = new Item();
		i13.setId(++CONT_ITEM);
		i13.setDescricao("A grama verde.");
		i13.setFeddback("Incorreto. A grama não gera arco-íris.");
		i13.setCorreto(false);
		
		Item i14 = new Item();
		i14.setId(++CONT_ITEM);
		i14.setDescricao("Gotículas de água no ar.");
		i14.setFeddback("Correto! Gotículas de água no ar são a principal forma de ocorrência de arco-íris.");
		i14.setCorreto(true);
		
		Item i15 = new Item();
		i15.setId(++CONT_ITEM);
		i15.setDescricao("Um pedaço de madeira.");
		i15.setFeddback("Incorreto. A luz do sol não pode atravessar um pedaço de madeira.");
		i15.setCorreto(false);
		
		Item i16 = new Item();
		i16.setId(++CONT_ITEM);
		i16.setDescricao("Um banco de areia.");
		i16.setFeddback("Incorreto. A areia não tem capacidade de gerar arco-íris.");
		i16.setCorreto(false);
		
		q4.adicionarItem(i13);
		q4.adicionarItem(i14);
		q4.adicionarItem(i15);
		q4.adicionarItem(i16);
		
		// Questão
		Questao q5 = new Questao();
		q5.setId(++CONT_QUESTAO);
		q5.setEnunciado("Como se chama o efeito óptico que gera o arco-íris?");

		// Itens
		Item i17 = new Item();
		i17.setId(++CONT_ITEM);
		i17.setDescricao("Diapasão.");
		i17.setFeddback("Incorreto. Diapasão é uma peça metálica que auxilia a afinação de instrumentos musicais.");
		i17.setCorreto(false);
		
		Item i18 = new Item();
		i18.setId(++CONT_ITEM);
		i18.setDescricao("Fusão.");
		i18.setFeddback("Incorreto. A fusão corresponde a um processo de mudança de estado físico de um material.");
		i18.setCorreto(false);
		
		Item i19 = new Item();
		i19.setId(++CONT_ITEM);
		i19.setDescricao("Inflação.");
		i19.setFeddback("Incorreto. A inflação é um efeito econômico de variação de preços.");
		i19.setCorreto(false);
		
		Item i20 = new Item();
		i20.setId(++CONT_ITEM);
		i20.setDescricao("Refração.");
		i20.setFeddback("Correto! A refração consiste na divisão das componentes da luz ao atravessar um prisma pela mudança de direção dos raios de luz.");
		i20.setCorreto(false);
		
		q5.adicionarItem(i17);
		q5.adicionarItem(i18);
		q5.adicionarItem(i19);
		q5.adicionarItem(i20);
		
		qz2.adicionarQuestao(q4);
		qz2.adicionarQuestao(q5);
		
		a2.adicionarQuiz(qz2);
		/** ------ */
		
		aulas.add(a1);
		aulas.add(a2);

	}

	public static List<Aula> carregarAulas() {

		return aulas;

	}
	
	public static Aula carregarAula(Integer idAula) {

		for (Aula a : aulas) {
			if(a.getId() == idAula)
				return a;
		}
		
		return null;

	}

	public static Quiz carregarQuiz(Integer idAula) {

		for (Aula a : aulas) {
			if(a.getId() == idAula){
				return a.getQuizzes().get(0);
			}
		} 
		
		return null;
		
	}

	public static List<Questao> carregarQuestoesQuiz(Integer idQuiz) {

		for (Aula a : aulas) {
			Quiz q = a.getQuizzes().get(0);
			if(q.getId() == idQuiz)
				return q.getQuestoes(); 
		} 
		
		return null;

	}

	public static List<Item> carregarItensQuestao(Integer idQuestao) {
		
		for (Aula a : aulas) {
			Quiz q = a.getQuizzes().get(0);
			for(Questao qt : q.getQuestoes())
				if(qt.getId() == idQuestao)
					return qt.getItens();
		} 

		return null;

	}

}
