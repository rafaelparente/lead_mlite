package android.mlite.activity;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.mlite.R;
import android.mlite.component.CustomVideoView;
import android.mlite.pojo.Aula;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;

public class AssistirVideo extends Activity {

    private CustomVideoView videoView;

    private LinearLayout completionControlsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistir_video);
        videoView = findViewById(R.id.videoView);
        completionControlsLayout = findViewById(R.id.completionControlsLayout);
        configureVideo();

        Intent intent = getIntent();
        Aula aula = intent.getParcelableExtra("aula");
        executarVideo(aula.getVideo());
    }

    @Override
    protected void onStart() {
        super.onStart();
        reiniciarVideo(null);
    }

    private void configureVideo() {
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

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
                completionControlsLayout.setVisibility(View.VISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
    }

    private void executarVideo(String videoName) {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void reiniciarVideo(View view) {
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
