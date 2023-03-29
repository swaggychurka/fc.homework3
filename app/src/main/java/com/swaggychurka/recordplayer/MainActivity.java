package com.swaggychurka.recordplayer;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private String nameAudio = "";
	private int npSongNum = 0;
    private MediaPlayer mediaPlayer;
    private Toast toast;

    private TextView textOut;

    private AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textOut = findViewById(R.id.textOut);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    }

    public void onSongClick(View view) throws IOException  {
        releaseMediaPlayer();
		switch(view.getId()) {
			case R.id.oneSong:
				nameAudio = "INSTASAMKA | Жара";
				mediaPlayer = MediaPlayer.create(this, R.raw.song_1);
				break;
			case R.id.twoSong:
				nameAudio = "Алла Пугачева | Звёздное небо";
				mediaPlayer = MediaPlayer.create(this, R.raw.song_2);
				break;
			case R.id.threeSong:
				nameAudio = "Moscow Never Sleeps";
				mediaPlayer = MediaPlayer.create(this, R.raw.song_3);
				break;
			case R.id.fourSong:
				nameAudio = "Pixies | Where Is My Mind?";
				mediaPlayer = MediaPlayer.create(this, R.raw.song_4);
				break;
			case R.id.fiveSong:
				nameAudio = "Vantage | 50//50";
				mediaPlayer = MediaPlayer.create(this, R.raw.song_5);
				break;
		}
        toast = Toast.makeText(this, nameAudio, Toast.LENGTH_SHORT);
        toast.show();
        mediaPlayer.start();
		textOut.setText(nameAudio + "\nиграет: " + mediaPlayer.isPlaying());
        if (mediaPlayer == null) return;
        mediaPlayer.setOnCompletionListener(this);
    }
	
	
	
    public void onClick(View view) {

        if (mediaPlayer == null) return;
		switch (view.getId()) {
            case R.id.btnResume:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.btnPause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); 
                }
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                break;
        }
        textOut.setText(nameAudio + "\nиграет: " + mediaPlayer.isPlaying());
    }



    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        toast = Toast.makeText(this, "On", Toast.LENGTH_SHORT); 
        toast.show(); 
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        toast = Toast.makeText(this, "Off", Toast.LENGTH_SHORT); 
        toast.show(); 
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


}
