package android.mlite.activity;

import android.app.Activity;

import android.content.Intent;
import android.mlite.R;
import android.mlite.pojo.Questao;
import android.mlite.pojo.Quiz;
import android.os.Bundle;

public class ResultadoQuiz extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_quiz);

        Intent intent = getIntent();
        Quiz quiz = intent.getParcelableExtra("quiz");
        Bundle respostas = intent.getBundleExtra("respostas");

        for (Questao questao : quiz.getQuestoes()) {
            String idQuestao = questao.getId().toString();
            String idResposta = respostas.getString(idQuestao);
        }
    }

}
