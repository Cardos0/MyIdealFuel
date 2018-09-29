package br.com.vvbc.myidealfuel;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText fuelIdealTextInputEditText;
    private double gasValue;
    private double ethanolValue;
    private ImageView fuelImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fuelIdealTextInputEditText = findViewById(R.id.fuelIdealTextInputEditText);
        fuelImageView = findViewById(R.id.fuelImageView);
        calcFuelIdeal();

        SeekBar gasSeekBar = findViewById(R.id.gasSeekBar);
        gasSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        gasValue = (double) progress;
                        calcFuelIdeal();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        SeekBar ethanolSeekBar = findViewById(R.id.ethanolSeekBar);
        ethanolSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        ethanolValue = (double) progress;
                        calcFuelIdeal();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    private void calcFuelIdeal(){
        String fuelIdealName;
        Double fuelDivision;
        int imageSrc;

        if (gasValue != 0){
            fuelDivision = ethanolValue / gasValue;
            if (fuelDivision >= 0.7){
                fuelIdealName = getString(R.string.gas);
                imageSrc = R.drawable.gas;
            }else{
                fuelIdealName = getString(R.string.ethanol);
                imageSrc = R.drawable.ethanol;
            }
        }else{
            fuelIdealName = getString(R.string.gas);
            imageSrc = R.drawable.gas;
        }
        fuelIdealTextInputEditText.setText(fuelIdealName);
        fuelImageView.setImageResource(imageSrc);
    }
}
