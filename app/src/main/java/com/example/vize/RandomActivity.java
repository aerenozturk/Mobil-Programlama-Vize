package com.example.vize;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    int minvalue, maxvalue, adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        EditText adetv=findViewById(R.id.adet_input);
        EditText minv=findViewById(R.id.min_input);
        EditText maxv=findViewById(R.id.max_input);

        LinearLayout linearLayout=findViewById(R.id.linear_layout);

        try{
            maxv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        adet = Integer.parseInt(adetv.getText().toString());
                        minvalue = Integer.parseInt(minv.getText().toString());
                        maxvalue = Integer.parseInt(maxv.getText().toString());

                        linearLayout.removeAllViews();

                        for (int i = 0; i < adet; i++) {
                            AddProgressBar(RandomActivity.this,linearLayout);
                        }
                        return true;
                    }
                    return false;
                }
            });
        }catch (Exception e){}
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void AddProgressBar(Context context, LinearLayout linearLayout) {
        int min,max, bulucu;
        do{
            min=minvalue+new Random().nextInt(maxvalue-minvalue);
            max=min+new Random().nextInt(maxvalue-min+1);
            bulucu=min+new Random().nextInt(max-min+1);
        }while (min==max || min==bulucu || bulucu==max);
        String yuzde1=Double.toString(((double) (bulucu-min)/(max-min))*100);
        String part[] =yuzde1.split("\\.");
        String yuzde=part[0];

        ConstraintLayout constraintLayout= new ConstraintLayout(context);
        constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        TextView txtYuzde=new TextView(context);
        txtYuzde.setId(View.generateViewId());
        String str = bulucu+" %" +yuzde;
        txtYuzde.setText(str);

        ConstraintLayout.LayoutParams paramsYuzde = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        paramsYuzde.topToTop= ConstraintSet.PARENT_ID;
        paramsYuzde.startToStart=ConstraintSet.PARENT_ID;
        paramsYuzde.setMargins(dpToPx(180),dpToPx(30),0,0);
        txtYuzde.setLayoutParams(paramsYuzde);
        constraintLayout.addView(txtYuzde);


        TextView txtMin = new TextView(context);
        txtMin.setId(View.generateViewId());
        txtMin.setText(String.valueOf(min));
        ConstraintLayout.LayoutParams paramsmin = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        paramsmin.topToTop=ConstraintSet.PARENT_ID;
        paramsmin.startToStart=ConstraintSet.PARENT_ID;
        paramsmin.setMargins(dpToPx(100),dpToPx(40),0,0);
        txtMin.setLayoutParams(paramsmin);
        constraintLayout.addView(txtMin);

        TextView txtMax = new TextView(context);
        txtMax.setId(View.generateViewId());
        txtMax.setText(String.valueOf(max));
        ConstraintLayout.LayoutParams paramsmax = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        paramsmax.topToTop=ConstraintSet.PARENT_ID;
        paramsmax.endToEnd=ConstraintSet.PARENT_ID;
        paramsmax.setMargins(0,dpToPx(40),dpToPx(100),0);
        txtMax.setLayoutParams(paramsmax);
        constraintLayout.addView(txtMax);

        ProgressBar progressBar=new ProgressBar(context,null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setId(View.generateViewId());
        ConstraintLayout.LayoutParams paramsPb= new ConstraintLayout.LayoutParams(
                dpToPx(150),
                dpToPx(20)
        );
        paramsPb.topToTop=ConstraintSet.PARENT_ID;
        paramsPb.startToStart=txtMin.getId();
        paramsPb.endToEnd=txtMax.getId();
        paramsPb.setMargins(dpToPx(30),dpToPx(40),dpToPx(30),dpToPx(100));
        progressBar.setLayoutParams(paramsPb);
        progressBar.setMin(0);
        progressBar.setProgress(Integer.parseInt(yuzde));
        progressBar.setMax(100);
        constraintLayout.addView(progressBar);

        linearLayout.addView(constraintLayout);
    }
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp*density);
    }
}