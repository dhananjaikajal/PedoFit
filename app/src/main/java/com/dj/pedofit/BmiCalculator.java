package com.dj.pedofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiCalculator extends Activity {
	private EditText txtheight;
	private EditText txtweight;
	private TextView txtresult;
	private Button btncalculate;
	private double bmi = 0;
	private double valueheight = 0;
	private double valueweight = 0;
	private String resulttext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmi);
		initControls();
	}

	private void initControls() {
		// TODO Auto-generated method stub
		txtheight = (EditText) findViewById(R.id.txtheight);
		txtweight = (EditText) findViewById(R.id.txtweight);
		txtresult = (TextView) findViewById(R.id.txtresult);
		btncalculate = (Button) findViewById(R.id.btncalculate);
		btncalculate.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calculate();
			}
		});

	}

	private void calculate() {
		valueheight = Double.parseDouble(txtheight.getText().toString());
		valueweight = Double.parseDouble(txtweight.getText().toString());
		Double valueheightmeters;

		valueheightmeters = valueheight / 100;
		bmi = (valueweight / (valueheightmeters * valueheightmeters));

		if (bmi >= 30) {
			resulttext = "Your BMI of " + Double.toString(bmi) + " is OBESE.";
			txtresult.setText(resulttext);
		} else if (bmi >= 25) {
			resulttext = "Your BMI of " + Double.toString(bmi)
					+ " is OVERWEIGHT.";
			txtresult.setText(resulttext);
		} else if (bmi >= 18.5) {
			resulttext = "Your BMI of " + Double.toString(bmi) + " is IDEAL.";
			txtresult.setText(resulttext);
		} else {
			resulttext = "Your BMI of " + Double.toString(bmi)
					+ " is UNDERWEIGHT.";
			txtresult.setText(resulttext);
		}
	}

	private void reset() {
		txtresult.setText("Your BMI is unknown.");
		txtheight.setText("0");
		txtweight.setText("0");
	}

}
