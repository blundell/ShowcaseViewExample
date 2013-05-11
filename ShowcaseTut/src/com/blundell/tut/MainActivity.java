package com.blundell.tut;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.blundell.tut.showcase.ShowcaseManager;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addSpinnerNavigation();

        new ShowcaseManager(this).showcaseMainActivity();
    }

    private void addSpinnerNavigation() {
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(new ArrayAdapter<String>(this, 0), null);
    }

    public void onContinueClick(View button) {
        Intent intent = new Intent(button.getContext(), SecondActivity.class);
        startActivity(intent);
    }
}
