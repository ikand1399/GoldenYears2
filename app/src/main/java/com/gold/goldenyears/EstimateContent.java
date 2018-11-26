package com.gold.goldenyears;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class EstimateContent extends Fragment {

    private View mainView;
    private EditText txtIncome;
    private EditText txtExpend;
    private EditText txtInterest;
    private EditText txtPeriods;
    private EditText txtYears;


    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View btnView) {
            double income = Double.parseDouble(txtIncome.getText().toString());
            double expend = Double.parseDouble(txtExpend.getText().toString());
            double interest = Double.parseDouble(txtInterest.getText().toString());
            int periods = Integer.parseInt(txtPeriods.getText().toString());
            double years = Double.parseDouble(txtYears.getText().toString());

            double amount = calculation(income, expend, interest, periods, years);
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            formatter.format(amount);
            TextView estimate = mainView.findViewById(R.id.estimate_text);
            estimate.setText(formatter.format(amount));
        }
    };

    public EstimateContent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_estimate_content, container, false);
        Button button = mainView.findViewById(R.id.estimate_button);
        button.setOnClickListener(btnListener);
        txtIncome = mainView.findViewById(R.id.txt_income);
        txtExpend = mainView.findViewById(R.id.txt_expend);
        txtInterest = mainView.findViewById(R.id.txt_interest);
        txtPeriods = mainView.findViewById(R.id.txt_paymentperiods);
        txtYears = mainView.findViewById(R.id.txt_years);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        txtIncome.setText(sharedPreferences.getString("user_income", ""));
        txtExpend.setText(sharedPreferences.getString("user_expenses", ""));
        txtPeriods.setText(sharedPreferences.getString("user_periods", ""));
        txtInterest.setText(sharedPreferences.getString("user_interest", ""));

        return mainView;
    }

    public double calculation(double income, double expend, double interest, int periods, double years) {
        double paymentAmount = (income - expend)/periods;
        double totalPeriods = periods*years;
        return paymentAmount*(Math.pow((1+interest/periods), totalPeriods)-1)*(periods/interest);
    }

}
