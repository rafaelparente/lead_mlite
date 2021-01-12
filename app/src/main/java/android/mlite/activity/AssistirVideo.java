package android.mlite.activity;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.mlite.R;
import android.mlite.component.CustomVideoView;
import android.mlite.pojo.Aula;
import android.mlite.util.Util;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;

public class AssistirVideo extends Activity {

    private CustomVideoView videoView;

    private LinearLayout completionControlsLayout;

    private int currentPosition = 0;

    private int playingStatus = 0;

    private boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistir_video);
        videoView = findViewById(R.id.videoView);
        completionControlsLayout = findViewById(R.id.completionControlsLayout);

        Intent intent = getIntent();
        Aula aula = intent.getParcelableExtra("aula");
        configureVideo(aula.getVideo());

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(Util.POSICAO_ATUAL_VIDEO)) {
            currentPosition = savedInstanceState.getInt(Util.POSICAO_ATUAL_VIDEO);
            playingStatus = savedInstanceState.getInt(Util.STATUS_VIDEO);
            // removendo a flag que indica dados salvos
            savedInstanceState.remove(Util.POSICAO_ATUAL_VIDEO);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playingStatus == 2) {
            isFinished = true;
            completionControlsLayout.setVisibility(View.VISIBLE);
            return;
        }
        videoView.seekTo(currentPosition);
        if (playingStatus == 1) return;
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playingStatus = (videoView.isPlaying() ? 0 : 1) + (isFinished? 1: 0);
        currentPosition = videoView.getCurrentPosition();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Salvando o estado do vídeo (true = executando, false = pausado)
        savedInstanceState.putInt(Util.STATUS_VIDEO, playingStatus);
        // Salvando a posição atual do vídeo em milissegundos
        savedInstanceState.putInt(Util.POSICAO_ATUAL_VIDEO, currentPosition);

        super.onSaveInstanceState(savedInstanceState);
    }

    private void configureVideo(String videoName) {
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName);
        videoView.setVideoURI(uri);

        videoView.setPlayPauseListener(new CustomVideoView.PlayPauseListener() {
            @Override
            public void onPlay() {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }

            @Override
            public void onPause() {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                isFinished = true;
                completionControlsLayout.setVisibility(View.VISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
    }

    public void reiniciarVideo(View view) {
        isFinished = false;
        completionControlsLayout.setVisibility(View.INVISIBLE);
        videoView.seekTo(0);
        videoView.requestFocus();
        videoView.start();
    }

    public void iniciarQuiz(View view) {
        Intent intent = getIntent();
        intent.setClass(getApplicationContext(), AulaQuiz.class);
        startActivity(intent);
        finish();
    }

}
