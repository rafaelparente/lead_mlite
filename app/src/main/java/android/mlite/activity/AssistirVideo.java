package android.mlite.activity;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.mlite.R;
import android.mlite.pojo.Aula;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class AssistirVideo extends Activity {

    private Aula aula;

    private VideoView videoView;

    private LinearLayout completionControlsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistir_video);
        videoView = findViewById(R.id.videoView);
        completionControlsLayout = findViewById(R.id.completionControlsLayout);

        Intent intent = getIntent();
        aula = intent.getParcelableExtra("videoName");
        executarVideo(aula.getVideo());
    }

    private void executarVideo(String videoName) {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                completionControlsLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void reiniciarVideo(View view) {
        completionControlsLayout.setVisibility(View.INVISIBLE);
        videoView.seekTo(0);
        videoView.start();
    }

    public void iniciarQuiz(View view) {

    }

}
