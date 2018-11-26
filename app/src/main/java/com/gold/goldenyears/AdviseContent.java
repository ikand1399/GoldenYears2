package com.gold.goldenyears;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import android.widget.RadioButton;


public class AdviseContent extends Fragment {

    private View mainView;
    private EditText txtIncome;
    private EditText txtExpend;
    private EditText txtIdeal;
    private EditText txtPeriods;
    private EditText txtYears;
    private double interest;

    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View btnView) {
            double ideal = Double.parseDouble(txtIdeal.getText().toString());
            int periods = Integer.parseInt(txtPeriods.getText().toString());
            double years = Double.parseDouble(txtYears.getText().toString());

            double amount = calculation(interest, ideal, periods, years);
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            formatter.format(amount);
            TextView estimate = mainView.findViewById(R.id.estimate_text);
            estimate.setText(formatter.format(amount) + " at " + interest + "% interest");

        }
    };

    private View.OnClickListener radioListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();
            switch(view.getId()) {
                case R.id.radio_low:
                    if (checked) {
                        interest = 0.025;
                    }
                    break;
                case R.id.radio_medium:
                    if (checked) {
                        interest = 0.04;
                    }
                    break;
                case R.id.radio_high:
                    if (checked) {
                        interest = 0.065;
                    }
                    break;
                default:
                    interest = 0.025;
            }
        }
    };



    public AdviseContent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_advise_content, container, false);
        Button button = mainView.findViewById(R.id.estimate_button);
        button.setOnClickListener(btnListener);
        txtIncome = mainView.findViewById(R.id.txt_income);
        txtExpend = mainView.findViewById(R.id.txt_expend);
        txtIdeal = mainView.findViewById(R.id.txt_ideal);
        txtPeriods = mainView.findViewById(R.id.txt_paymentperiods);
        txtYears = mainView.findViewById(R.id.txt_years);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        txtPeriods.setText(sharedPreferences.getString("user_periods", ""));
        RadioButton lowButton = (RadioButton) mainView.findViewById(R.id.radio_low);
        RadioButton mediumButton = (RadioButton) mainView.findViewById(R.id.radio_medium);
        RadioButton highButton = (RadioButton) mainView.findViewById(R.id.radio_high);
        lowButton.setOnClickListener(radioListener);
        mediumButton.setOnClickListener(radioListener);
        highButton.setOnClickListener(radioListener);

        return mainView;
    }

    public double calculation(double interest, double ideal, int periods, double years) {
        double totalPeriods = periods*years;
        return ideal/((Math.pow((1+interest/periods), totalPeriods)-1)*(periods/interest));
    }


}
