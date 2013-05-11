package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.blundell.tut.showcase.ShowcaseManager;

public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        new ShowcaseManager(this).showcaseSecondActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
