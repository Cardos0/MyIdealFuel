package br.com.vvbc.myidealfuel;

import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private TextInputEditText fuelIdealTextInputEditText;
    private TextView gasValueTextView;
    private TextView ethanolValueTextView;
    private double gasValue;
    private double ethanolValue;
    private ImageView fuelImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FindViews
        gasValueTextView = findViewById(R.id.gasValueTextView);
        ethanolValueTextView = findViewById(R.id.ethanolValueTextView);
        fuelIdealTextInputEditText = findViewById(R.id.fuelIdealTextInputEditText);
        fuelImageView = findViewById(R.id.fuelImageView);
        SeekBar gasSeekBar = findViewById(R.id.gasSeekBar);
        SeekBar ethanolSeekBar = findViewById(R.id.ethanolSeekBar);

        gasValue = gasSeekBar.getProgress();
        ethanolValue = ethanolSeekBar.getProgress();

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

        // Calc first time.
        calcFuelIdeal();
    }

    private void calcFuelIdeal(){
        String fuelIdealName;
        Double fuelDivision;
        String gasValueString = currencyFormat.format(gasValue);
        String ethanolValueString = currencyFormat.format(ethanolValue);
        int imageSrc;

        // Avoid Division by 0
        if (gasValue != 0){
            fuelDivision = ethanolValue / gasValue;
            if (fuelDivision >= 0.7){
                fuelIdealName = getString(R.string.gas);
                imageSrc = R.drawable.gas;
                gasValueString = "--> " + gasValueString;
                gasValueTextView.setTypeface(null, Typeface.BOLD);
                ethanolValueTextView.setTypeface(null, Typeface.NORMAL);
            }else{
                fuelIdealName = getString(R.string.ethanol);
                imageSrc = R.drawable.ethanol;
                ethanolValueString = "--> " + ethanolValueString;
                ethanolValueTextView.setTypeface(null, Typeface.BOLD);
                gasValueTextView.setTypeface(null, Typeface.NORMAL);

            }
        }else{
            fuelIdealName = getString(R.string.gas);
            imageSrc = R.drawable.gas;
            gasValueString = "--> " + gasValueString;
            gasValueTextView.setTypeface(null, Typeface.BOLD);
            ethanolValueTextView.setTypeface(null, Typeface.NORMAL);

        }

        // TextView of SeekBars
        gasValueTextView.setText(gasValueString);
        ethanolValueTextView.setText(ethanolValueString);

        fuelIdealTextInputEditText.setText(fuelIdealName);  // Set texts
        fuelImageView.setImageResource(imageSrc);           // Set image
    }
}
