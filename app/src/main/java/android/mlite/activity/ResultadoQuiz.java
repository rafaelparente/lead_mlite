package android.mlite.activity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.mlite.R;
import android.mlite.pojo.Aula;
import android.mlite.pojo.Item;
import android.mlite.pojo.Questao;
import android.mlite.pojo.Quiz;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class ResultadoQuiz extends Activity {

    // layout que armazena as questões
    private LinearLayout containerQuestoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);

        Intent intent = getIntent();
        Aula aula = intent.getParcelableExtra("aula");

        TextView tv_titulo_quiz = findViewById(R.id.tv_titulo_quiz);
        tv_titulo_quiz.setText(getString(R.string.quiz_titulo, aula.getTitulo()));

        containerQuestoes = findViewById(R.id.ll_questoes);
        int quizIndex = intent.getIntExtra("quizIndex", -1);
        Bundle respostas = intent.getBundleExtra("respostas");
        Quiz quiz = aula.getQuizzes().get(quizIndex);

        TextView tv_descricao_quiz = findViewById(R.id.tv_descricao_quiz);
        tv_descricao_quiz.setText(quiz.getTitulo());

        int acertos = montarListaQuestoes(quiz.getQuestoes(), respostas);
        TextView tv_resumo_desempenho = findViewById(R.id.tv_resumo_acertos);
        //tv_resumo_desempenho.setText("Acertos : " + acertos + " de " + quiz.getQuestoes().size() + " (" + ((acertos * 1.0)/quiz.getQuestoes().size() * 100.0) + "%)");
        tv_resumo_desempenho.setText(getString(R.string.resumo_desempenho, acertos, quiz.getQuestoes().size(),
                Math.round((acertos * 1.0)/quiz.getQuestoes().size() * 100)));
    }

    private int montarListaQuestoes(List<Questao> listaQuestoes, Bundle respostas) {
        int acertos = 0;
        // Verificando o preenchimento da lista de questoes
        if (listaQuestoes != null && listaQuestoes.size() > 0) {
            // Inflater - para alocação dinámica de componentes na UI
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Iteração na lista de questoes
            int index = 0;
            for (Questao questao : listaQuestoes) {
                String idQuestao = questao.getId().toString();
                String idResposta = respostas.getString(idQuestao);

                // Instanciação do LinearLayout base do quiz
                LinearLayout linhaQuestao = (LinearLayout) inflater.inflate(
                        R.layout.item_quiz, containerQuestoes, false);
                if (index++ % 2 == 0) {
                    linhaQuestao.setBackgroundColor(Color.parseColor("#cccccc"));
                }

                TextView enunciado = linhaQuestao.findViewById(R.id.enunciado_questao);
                enunciado.setText(questao.getEnunciado());

                RadioGroup radioGroup = linhaQuestao.findViewById(R.id.radioGroup);
                for (Item item : questao.getItens()) {
                    RadioButton radioButton;
                    if (item.getId().toString().compareTo(idResposta) == 0) {
                        LayoutInflater itemInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        LinearLayout linhaItem = (LinearLayout) itemInflater.inflate(
                                R.layout.item_feedback, radioGroup, false);
                        radioButton = linhaItem.findViewById(R.id.radioButton);
                        radioButton.setChecked(true);

                        ImageView feedbackIcon = linhaItem.findViewById(R.id.imageView);
                        TextView feedbackText = linhaItem.findViewById(R.id.feedback);
                        feedbackText.setText(item.getFeddback());
                        if (item.getCorreto()) {
                            feedbackIcon.setImageResource(R.drawable.correto);
                            feedbackText.setTextColor(Color.parseColor("#FF3F51B5"));
                            acertos++;
                        } else {
                            feedbackIcon.setImageResource(R.drawable.incorreto);
                            feedbackText.setTextColor(Color.parseColor("#FFFF5722"));
                        }
                        radioGroup.addView(linhaItem);
                    }
                    else {
                        radioButton = new RadioButton(this);
                        radioGroup.addView(radioButton);
                    }
                    radioButton.setText(item.getDescricao());
                    radioButton.setEnabled(false);
                }

                // Adição do layout de questão à lista em tela
                containerQuestoes.addView(linhaQuestao);
            }
        }
        return acertos;
    }

    public void goBackToMain(View view) {
        finish();
    }

}
