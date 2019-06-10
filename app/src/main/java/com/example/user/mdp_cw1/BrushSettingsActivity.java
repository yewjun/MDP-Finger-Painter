package com.example.user.mdp_cw1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class BrushSettingsActivity extends AppCompatActivity {

    private SeekBar brushSeekBar;
    private String brushWidth;
    private String brushShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);

        // bundle to get the data pass from MainActivity
        Bundle bundle = getIntent().getExtras();

        // if data pass from MainActivity is null then return 0
        if(bundle == null){
            return;
        }

        // get the brush width and shape from MainActivity
        brushWidth = bundle.getString("brushWidth");
        brushShape = bundle.getString("brushShape");

        //  show the old brush settings
        final TextView currentWidth = (TextView)findViewById(R.id.widthText);
        currentWidth.setText("Current width:" + brushWidth + "px ");

        // set the seek bar to the data from MainActivity
        // limit seek bar value from 0 - 100
        brushSeekBar = (SeekBar)findViewById(R.id.MySeekBar);
        brushSeekBar.setMax(100);
        brushSeekBar.setProgress(Integer.parseInt(brushWidth));

        // set the radio button with the data from MainActivity
        switch (brushShape){
            case "Round":
                RadioButton roundRadioButton = findViewById(R.id.radio_round);
                roundRadioButton.setChecked(true);
                break;
            case "Square":
                RadioButton squareRadioButton = findViewById(R.id.radio_square);
                squareRadioButton.setChecked(true);
                break;
        }

        seekBarView();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked? Boolean will take true or false
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked and check it
        switch(view.getId()) {
            case R.id.radio_round:
                if (checked)
                    brushShape = "Round";
                break;
            case R.id.radio_square:
                if (checked)
                    brushShape = "Square";
                break;
        }

    }

    //   seekBar settings
    public void seekBarView() {
        brushSeekBar = (SeekBar)findViewById(R.id.MySeekBar);
        final TextView currentWidth = (TextView)findViewById(R.id.widthText);

        // seekBar onChangeListener
        brushSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // change the text value
                currentWidth.setText("Current width:" + progress + "px ");

                //set current width to a variable pass back to MainActivity
                brushWidth = String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do something when triggered
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do something when stop touching
            }
        });
    }

    public void returnClicked(View v){
        // do when return button is triggered
        finish();
    }

    @Override
    public void finish(){
        // store the brush width and shape then pass back to MainActivity
        Intent intent = new Intent();
        intent.putExtra("brushWidth", Integer.parseInt(brushWidth));
        intent.putExtra("brushShape", brushShape);
        setResult(MainActivity.RESULT_OK, intent);

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
