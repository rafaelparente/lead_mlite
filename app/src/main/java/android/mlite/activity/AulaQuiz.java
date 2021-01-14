package android.mlite.activity;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.mlite.R;
import android.mlite.db.MLiteDatabase;
import android.mlite.pojo.Aula;
import android.mlite.pojo.Item;
import android.mlite.pojo.Questao;
import android.mlite.pojo.Quiz;
import android.os.AsyncTask;
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

    private Aula aula;

    private int quizIndex;

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
        aula = intent.getParcelableExtra("aula");

        TextView tv_titulo_quiz = findViewById(R.id.tv_titulo_quiz);
        tv_titulo_quiz.setText(getString(R.string.quiz_titulo, aula.getTitulo()));

        containerQuestoes = findViewById(R.id.ll_questoes);
        new CarregarQuizzesTask().execute();
    }

    @Override
    public void onBackPressed() {
        goBackToVideo(null);
    }

    private void montarQuiz(List<Quiz> listaQuiz) {
        quizIndex = new Random().nextInt(listaQuiz.size());
        Quiz quiz = listaQuiz.get(quizIndex);

        TextView tv_descricao_quiz = findViewById(R.id.tv_descricao_quiz);
        tv_descricao_quiz.setText(quiz.getTitulo());

        new CarregarQuestoesTask().execute(quiz);
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

                final RadioGroup radioGroup = linhaQuestao.findViewById(R.id.radioGroup);
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
                
                CarregarItensTask carregarItensTask = new CarregarItensTask(new TaskListener<Item>() {
                    @Override
                    public void onFinished(List<Item> itens) {
                        for (Item item : itens) {
                            RadioButton radioButton = new RadioButton(getApplicationContext());
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
                    }
                });
                carregarItensTask.execute(questao);

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
                        Intent intent = new Intent(getApplicationContext(), AssistirVideo.class);
                        intent.putExtra("aula", aula);
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
                        Intent intent = new Intent(getApplicationContext(), ResultadoQuiz.class);
                        intent.putExtra("aula", aula);
                        intent.putExtra("quizIndex", quizIndex);
                        intent.putExtra("respostas", answersBundle);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.nao, null)
                .show();
    }

    private class CarregarQuizzesTask extends AsyncTask<Void, Void, List<Quiz>> {

        @Override
        protected List<Quiz> doInBackground(Void... param) {
            List<Quiz> quizzes = aula.getQuizzes();
            if (quizzes == null) {
                quizzes = MLiteDatabase.carregarQuizzes(aula.getId());
                aula.setQuizzes(quizzes);
            }
            return quizzes;
        }

        @Override
        protected void onPostExecute(List<Quiz> quizzes) {
            super.onPostExecute(quizzes);
            montarQuiz(quizzes);
        }

    }

    private class CarregarQuestoesTask extends AsyncTask<Quiz, Void, List<Questao>> {

        @Override
        protected List<Questao> doInBackground(Quiz... param) {
            Quiz quiz = (Quiz) param[0];
            List<Questao> questoes = quiz.getQuestoes();
            if (questoes == null) {
                questoes = MLiteDatabase.carregarQuestoes(quiz.getId());
                quiz.setQuestoes(questoes);
            }
            return questoes;
        }

        @Override
        protected void onPostExecute(List<Questao> questoes) {
            super.onPostExecute(questoes);
            montarListaQuestoes(questoes);
        }

    }

    public interface TaskListener<T> {
        void onFinished(List<T> resultList);
    }

    private class CarregarItensTask extends AsyncTask<Questao, Void, List<Item>> {

        // This is the reference to the associated listener
        private final TaskListener<Item> taskListener;

        public CarregarItensTask(TaskListener<Item> listener) {
            // The listener reference is passed in through the constructor
            this.taskListener = listener;
        }

        @Override
        protected List<Item> doInBackground(Questao... param) {
            Questao questao = (Questao) param[0];
            List<Item> itens = questao.getItens();
            if (itens == null) {
                itens = MLiteDatabase.carregarItems(questao.getId());
                questao.setItens(itens);
            }
            return itens;
        }

        @Override
        protected void onPostExecute(List<Item> itens) {
            super.onPostExecute(itens);

            // In onPostExecute we check if the listener is valid
            if (this.taskListener != null) {
                // And if it is we call the callback function on it.
                this.taskListener.onFinished(itens);
            }
        }

    }

}
