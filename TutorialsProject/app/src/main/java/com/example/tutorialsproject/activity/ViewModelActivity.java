package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.viewmodel.PracticeViewModel;

public class ViewModelActivity extends AppCompatActivity {
    TextView numTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        numTV = findViewById(R.id.numTV);

        //PracticeViewModel viewModel = new PracticeViewModel();
        //numTV.setText(String.valueOf(viewModel.generateRandomNum()));

        PracticeViewModel viewModel = ViewModelProviders.of(this).get(PracticeViewModel.class);
        int randomNum = viewModel.generateRandomNum();
        numTV.setText(String.valueOf(randomNum));
        
    }
}
