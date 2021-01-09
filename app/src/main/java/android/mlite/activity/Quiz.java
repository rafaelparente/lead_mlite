package android.mlite.activity;

import android.app.Activity;

import android.content.Intent;
import android.mlite.R;
import android.mlite.pojo.Aula;
import android.os.Bundle;
import android.widget.TextView;

public class Quiz extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        Aula aula = intent.getParcelableExtra("aula");

        TextView tv_titulo_quiz = findViewById(R.id.tv_titulo_quiz);
        tv_titulo_quiz.setText(getString(R.string.quiz_titulo, aula.getTitulo()));

        TextView tv_descricao_quiz = findViewById(R.id.tv_descricao_quiz);
        tv_descricao_quiz.setText(aula.getDescricao());
    }
}