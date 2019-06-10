package com.example.user.mdp_cw1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// third-party open source library from GitHub is imported
// Credit to AmbilWarna library
// Source from "https://github.com/yukuku/ambilwarna"
// implementation 'com.github.yukuku:ambilwarna:2.0.1' have been added to dependencies
import yuku.ambilwarna.AmbilWarnaDialog;

public class ColourSelectActivity extends AppCompatActivity {

    private Button btn;
    private int DefaultColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        // set default colour to primary colour
        DefaultColour = ContextCompat.getColor(this, R.color.colorPrimary);

        // set the current colour button to default colour
        btn = findViewById(R.id.button);
        btn.setBackgroundColor(DefaultColour);
    }

    public void colourButtonClicked(View v){
        // when onClick button triggered
        OpenColorPicker(false);
    }

    public void returnClicked(View v){
        finish();
    }

    private void OpenColorPicker (boolean AlphaSupport){
        // AmbilWarna third-party library have been imported
        // initialColor is the initially-selected color to be shown in the rectangle on the left of the arrow.
        // for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware of the initial 0xff which is the alpha.
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, DefaultColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // color is the colour selected by the user.
                // DefaultColour will take the selected color pass back to MainActivity
                DefaultColour = color;

                // set the current colour button to the colour selected by user to let user know which colour is selected
                btn.setBackgroundColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // If onCancel is triggered, nothing will happen
            }
        });
        dialog.show();
    }


    @Override
    public void finish(){
        // store the selected colour and pass back to MainActivity
        Intent intent = new Intent();
        intent.putExtra("colourCode",DefaultColour);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
}
