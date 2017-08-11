package com.ryan.rxretrofit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LayoutActivity extends AppCompatActivity {

    @BindView(R.id.top_area) LinearLayout topArea;
    @BindView(R.id.bottom_area) LinearLayout bottomArea;

    private  final static int max_row = 3;
    private  final static int max_column = 8;

    private  final static int bottom_column = 8;

    private final static String[][] colorArray = {
            { "#F7E8E8", "#BA2828"},
            { "#E7F5E6", "#13A00D"},
            { "#FAEDEA", "#CF532F"}
    };

    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        ButterKnife.bind(this);
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        setTopArea();
        setBottomArea();
    }

    private void setTopArea() {
        LayoutParams rowparams = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        rowparams.weight = 1;
        LayoutParams cellparams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        cellparams.weight = 1;
        cellparams.leftMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());

        topArea.setWeightSum(max_row);

        for (int i=0; i < max_row; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            for (int j=0; j < max_column; j++) {
                LinearLayout cell  = (LinearLayout) inflater.inflate(R.layout.layout_cell, null);
                cell.findViewById(R.id.cell_top).setBackgroundColor(Color.parseColor(colorArray[i][0]));
                cell.findViewById(R.id.cell_bottom).setBackgroundColor(Color.parseColor(colorArray[i][1]));
                cell.setOnClickListener(listener);
                row.addView(cell, cellparams);
            }
            topArea.addView(row, rowparams);
        }
    }


    private void setBottomArea() {
        LayoutParams cellparams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        cellparams.weight = 1;
        cellparams.leftMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        cellparams.rightMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        cellparams.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        cellparams.bottomMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());


        bottomArea.setWeightSum(bottom_column);
        for (int i=0; i < bottom_column; i++) {
            LinearLayout button  = (LinearLayout) inflater.inflate(R.layout.layout_button, null);
            ((TextView) button.findViewById(R.id.num)).setText(String.valueOf(i+1));

            bottomArea.addView(button, cellparams);
        }

    }

    View.OnClickListener listener = ((View v) -> System.out.println("click"));
}
