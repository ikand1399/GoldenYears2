package com.gold.goldenyears;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Profile extends Fragment {

    private View mainView;
    public Button button;
    public TextView textViewName;
    public TextView textViewAge;
    public TextView textViewIncome;
    public TextView textViewExpenses;
    public TextView textViewPeriods;
    public TextView textViewInterest;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String user_name = "";
    public static final String user_age = "";
    public static final String user_income = "";
    public static final String user_expenses = "";
    public static final String user_periods = "";
    public static final String user_interest = "";
    public String user_name_temp = "";
    public String user_age_temp = "";
    public String user_income_temp = "";
    public String user_expenses_temp = "";
    public String user_periods_temp = "";
    public String user_interest_temp = "";

    public Profile() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_profile, container, false);
        button = mainView.findViewById(R.id.applyButton);
        textViewName = mainView.findViewById(R.id.profile_name_view);
        textViewAge = mainView.findViewById(R.id.profile_age_view);
        textViewIncome = mainView.findViewById(R.id.income_view);
        textViewExpenses = mainView.findViewById(R.id.expenses_view);
        textViewPeriods = mainView.findViewById(R.id.periods_view);
        textViewInterest = mainView.findViewById(R.id.interest_view);
        button.setOnClickListener(btnListener);
        loadData();
        upDateViews();
        return mainView;
    }



    private View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View btnView) {
            EditText textName = mainView.findViewById(R.id.text_input_user_name);
            EditText textAge = mainView.findViewById(R.id.text_input_user_age);
            EditText textIncome = mainView.findViewById(R.id.text_input_yearly_income);
            EditText textExpenses = mainView.findViewById(R.id.text_input_yearly_expenses);
            EditText textPeriods = mainView.findViewById(R.id.text_input_periods);
            EditText textInterest = mainView.findViewById(R.id.text_input_yearly_interest);
            user_name_temp = textName.getText().toString();
            user_age_temp = textAge.getText().toString();
            user_income_temp = textIncome.getText().toString();
            user_expenses_temp = textExpenses.getText().toString();
            user_periods_temp = textPeriods.getText().toString();
            user_interest_temp = textInterest.getText().toString();

            saveData();
        }
    };

    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name", user_name_temp);
        editor.putString("user_age", user_age_temp);
        editor.putString("user_income", user_income_temp);
        editor.putString("user_expenses", user_expenses_temp);
        editor.putString("user_periods", user_periods_temp);
        editor.putString("user_interest", user_interest_temp);
        editor.apply();
        loadData();
        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        user_name_temp = sharedPreferences.getString("user_name", "   ");
        user_age_temp = sharedPreferences.getString("user_age", "18");
        user_income_temp = sharedPreferences.getString("user_income", "0");
        user_expenses_temp = sharedPreferences.getString("user_expenses", "0");
        user_periods_temp = sharedPreferences.getString("user_periods", " ");
        user_interest_temp = sharedPreferences.getString("user_interest", "0");
        upDateViews();
    }
    public void upDateViews(){
        textViewName.setText(user_name_temp);
        textViewAge.setText(user_age_temp);
        textViewIncome.setText(user_income_temp);
        textViewExpenses.setText(user_expenses_temp);
        textViewPeriods.setText(user_periods_temp);
        textViewInterest.setText(user_interest_temp);
    }


}
