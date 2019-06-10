package com.example.user.mdp_cw1;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    // request code for BrushSettingsActivity.java
    static int BRUSH_REQUEST_CODE = 1;
    // request code for ColourSelectActivity.java
    static int COLOUR_REQUEST_CODE = 2;

    FingerPainterView fpv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a object to use FingerPainterView functions
        fpv = findViewById(R.id.fingerpainterview);

        getMyIntent();
    }

    private void getMyIntent() {
        // get the image path load from gallery and load at FingerPainterView canvas
        Intent intent = getIntent();
        fpv.load(intent.getData());
    }

    public void clearMyCanvas(View view) {
        // clear FingerPainterView canvas
        fpv.clearCanvas();
    }

    public void myBrushSetting (View v){

        String[] brushDetails = new String[2];
        Paint.Cap brushShape = fpv.getBrush();

        // store current brush width and brush shape in a string array
        brushDetails[0] = String.valueOf(fpv.getBrushWidth());

        if(brushShape == Paint.Cap.ROUND){
            brushDetails[1] = "Round";
        }else{
            brushDetails[1] = "Square";
        }

        // bring stored current brush width and brush shape to BrushSettingsActivity to use
        Intent intent = new Intent(this, BrushSettingsActivity.class);
        intent.putExtra("brushWidth", brushDetails[0]);
        intent.putExtra("brushShape", brushDetails[1]);
        startActivityForResult(intent, BRUSH_REQUEST_CODE);

    }

    public void myColourSelect (View view){
        // send message to ColourSelectActivity
        Intent intent = new Intent(this, ColourSelectActivity.class);
        startActivityForResult(intent, COLOUR_REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {

        // get result send back from BrushSettingsActivity
        if (reqCode == BRUSH_REQUEST_CODE) {
            if (resultCode == RESULT_OK){

                // get data from BrushSettingsActivity
                String selectedBrushShape = data.getExtras().getString("brushShape");
                int selectedBrushWidth = data.getIntExtra("brushWidth", 0);

                // set brush width and brush shape to FingerPainterView
                fpv.setBrushWidth(selectedBrushWidth);

                switch(selectedBrushShape){
                    case "Round":
                        fpv.setBrush(Paint.Cap.ROUND);
                        break;

                    case "Square":
                        fpv.setBrush(Paint.Cap.SQUARE);
                        break;

                    default:
                        break;
                }
            }

            // get result send back from ColourSelectActivity
        }else if(reqCode == COLOUR_REQUEST_CODE) {
            if(resultCode == RESULT_OK){

                // get data from ColourSelectActivity
                int selectedColourCode = data.getIntExtra("colourCode", 0);

                // set brush colour to FingerPainterView
                fpv.setColour(selectedColourCode);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state){
        // save current state
        super.onSaveInstanceState(state);

        // store brush width, colour and shape in state
        state.putInt("BrushWidth", fpv.getBrushWidth());
        state.putInt("BrushColour", fpv.getColour());
        
        if(fpv.getBrush().equals(Paint.Cap.SQUARE))
            state.putString("BrushShape", "Square");
        else
            state.putString("BrushShape", "Round");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState){
        // load saved state
        super.onRestoreInstanceState(savedState);

        // load brush width, colour and shape from state
        fpv.setBrushWidth(savedState.getInt("BrushWidth"));
        fpv.setColour(savedState.getInt("BrushColour"));

        if(savedState.getString("BrushShape").equals("SQUARE"))
            fpv.setBrush(Paint.Cap.SQUARE);
        else
            fpv.setBrush(Paint.Cap.ROUND);
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
