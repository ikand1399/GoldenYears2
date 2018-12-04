package com.gold.goldenyears;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;

public class HomeContent extends Fragment {

    private View mainView;

    public HomeContent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_home_content, container, false);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        TextView dateTextView = mainView.findViewById(R.id.date_display);
        dateTextView.setText(currentDateTimeString);
        return mainView;
    }

}
