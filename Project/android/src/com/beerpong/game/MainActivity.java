package com.beerpong.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.CheckBox;



import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;


import java.util.Stack;

/**
 * Created by Daniel on 31/05/2017.
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static MainActivity instance;
    private int score =0;

    LoginButton loginButton;
    MediaPlayer music;
    MediaPlayer sound;

    boolean soundAvailable;

    Stack<Integer> viewStack = new Stack<>();



    public void configMainLayout(){

        viewStack.push(R.layout.mainlayout);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.mainlayout);
        findViewById(R.id.startGame).setOnClickListener(this);
        findViewById(R.id.exitButton).setOnClickListener(this);
        findViewById(R.id.settingsButton).setOnClickListener(this);
        findViewById(R.id.helpButton).setOnClickListener(this);

        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);

        ShareLinkContent content =  new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
        shareButton.setShareContent(content);

    }

    public static MainActivity getInstance() {
        if(instance == null)
            instance = new MainActivity();
        return instance;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        configMainLayout();

        music = MediaPlayer.create(MainActivity.this, R.raw.whiplash);
        music.setLooping(true);
        music.start();

        sound = MediaPlayer.create(MainActivity.this, R.raw.button);
        soundAvailable = true;


            instance = this;



    }


    @Override
    public void onResume (){
        super.onResume();
        if(BeerPong.isExited()) {

            System.out.println("SCORE:" + score);


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.scorelayout);

            ShareLinkContent content =  new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();
            ShareButton shareButton = (ShareButton)findViewById(R.id.fb_share_button);
            shareButton.setShareContent(content);

            BeerPong.setExited(false);
        }
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.startGame:
                playSound();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                setContentView(R.layout.levelayout);
                findViewById(R.id.easyButton).setOnClickListener(this);
                findViewById(R.id.normalButton).setOnClickListener(this);
                findViewById(R.id.difficultButton).setOnClickListener(this);
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.easyButton:
                playSound();
                AndroidLauncher.setLevel(1);
                startActivity(new Intent(this, AndroidLauncher.class));
                setContentView(R.layout.scorelayout);
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.normalButton:
                playSound();
                AndroidLauncher.setLevel(2);
                startActivity(new Intent(this, AndroidLauncher.class));
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.difficultButton:
<<<<<<< HEAD

=======
>>>>>>> b1b5fafe4da57662167560183a506dd93570d87c
                playSound();
                AndroidLauncher.setLevel(3);
                startActivity(new Intent(this, AndroidLauncher.class));
                viewStack.push(R.layout.levelayout);
                break;
            case R.id.helpButton:
                playSound();
                setContentView(R.layout.helplayout);
                viewStack.push(R.layout.helplayout);
                break;
            case R.id.settingsButton:
                playSound();
                setContentView(R.layout.settingslayout);
                findViewById(R.id.checkBoxMusic).setOnClickListener(this);
                viewStack.push(R.layout.settingslayout);
                break;
            case R.id.checkBoxMusic:
                playSound();
                if(((CheckBox)v).isChecked()) music.start();
                else music.pause();
                break;
            case R.id.checkBoxSound:
               /* if(((CheckBox)v).isChecked()) soundAvailable = true;
                else soundAvailable = false;
                break;*/
                if(((CheckBox)v).isChecked()) sound.start();

                break;
            case R.id.exitButton:
                playSound();
                System.exit(0);
        }

    }


    public void playSound(){

        if(soundAvailable)
            sound.start();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if((viewStack.peek() == R.layout.levelayout) ||(viewStack.peek() == R.layout.settingslayout) || (viewStack.peek() == R.layout.helplayout) ) {
                viewStack.pop();
                configMainLayout();

            }
            else {
                viewStack.pop();
                setContentView(viewStack.peek());
            }
            return true;
        }

        return false;
       // return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void setScore(int score) {
        this.score = score;

    }




}
