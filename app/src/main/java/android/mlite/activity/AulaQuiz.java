package android.mlite.activity;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class AulaQuiz extends Activity {

    // layout que armazena as questões
    private LinearLayout containerQuestoes;

    private Button sendAnswersButton;

    private int radioGroupUndefinedCount;

    private Bundle answersBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula_quiz);
        sendAnswersButton = findViewById(R.id.sendAnswersButton);

        Intent intent = getIntent();
        Aula aula = intent.getParcelableExtra("aula");

        TextView tv_titulo_quiz = findViewById(R.id.tv_titulo_quiz);
        tv_titulo_quiz.setText(getString(R.string.quiz_titulo, aula.getTitulo()));

        containerQuestoes = findViewById(R.id.ll_questoes);
        int quizIndex = new Random().nextInt(aula.getQuizzes().size());
        intent.putExtra("quizIndex", quizIndex);
        Quiz quiz = aula.getQuizzes().get(quizIndex);

        TextView tv_descricao_quiz = findViewById(R.id.tv_descricao_quiz);
        tv_descricao_quiz.setText(quiz.getTitulo());

        montarListaQuestoes(quiz.getQuestoes());
    }

    @Override
    public void onBackPressed() {
        goBackToVideo(null);
    }

    private void montarListaQuestoes(List<Questao> listaQuestoes) {
        // Verificando o preenchimento da lista de questoes
        if (listaQuestoes != null && listaQuestoes.size() > 0) {
            // Inflater - para alocação dinámica de componentes na UI
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Iteração na lista de questoes
            int index = 0;
            for (Questao questao : listaQuestoes) {
                // Instanciação do LinearLayout base do quiz
                LinearLayout linhaQuestao = (LinearLayout) inflater.inflate(
                        R.layout.item_quiz, containerQuestoes, false);
                linhaQuestao.setTag(questao.getId());
                if (index++ % 2 == 0) {
                    linhaQuestao.setBackgroundColor(Color.parseColor("#cccccc"));
                }

                TextView enunciado = linhaQuestao.findViewById(R.id.enunciado_questao);
                enunciado.setText(questao.getEnunciado());

                RadioGroup radioGroup = linhaQuestao.findViewById(R.id.radioGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        group.setOnCheckedChangeListener(null);
                        if (--radioGroupUndefinedCount == 0) {
                            sendAnswersButton.setEnabled(true);
                        }
                    }
                });
                for (Item item : questao.getItens()) {
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(item.getDescricao());
                    radioButton.setTag(item.getId());
                    radioGroup.addView(radioButton);
                    radioButton.setOnClickListener(new RadioButton.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            String idResposta = v.getTag().toString();
                            LinearLayout ll_questaoCorrecao = (LinearLayout) v.getParent().getParent();
                            String idQuestao = ll_questaoCorrecao.getTag().toString();
                            answersBundle.putString(idQuestao, idResposta);
                        }
                    });
                }

                // Adição do layout de questão à lista em tela
                containerQuestoes.addView(linhaQuestao);
            }
            radioGroupUndefinedCount = index;
        }
    }

    public void goBackToVideo(View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.titulo_dialog_retornar_video)
                .setMessage(R.string.msg_dialog_retornar_video)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        intent.setClass(getApplicationContext(), AssistirVideo.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.nao, null)
                .show();
    }

    public void sendAnswers(View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.titulo_dialog_enviar_respostas)
                .setMessage(R.string.msg_dialog_enviar_respostas)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        intent.setClass(getApplicationContext(), ResultadoQuiz.class);
                        intent.putExtra("respostas", answersBundle);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.nao, null)
                .show();
    }

}
