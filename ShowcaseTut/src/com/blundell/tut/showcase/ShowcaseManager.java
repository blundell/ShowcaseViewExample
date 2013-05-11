package com.blundell.tut.showcase;

import android.app.Activity;
import android.view.ViewGroup;

import com.blundell.tut.R;
import com.github.espiandev.showcaseview.ShowcaseView;

import static com.blundell.tut.showcase.ShowcaseViews.ItemViewProperties.ID_SPINNER;

// Encapsulate all the showcase behaviour so we can change it all in one place
public class ShowcaseManager {

    private final Activity activity;

    public ShowcaseManager(Activity activity) {
        this.activity = activity;
    }

    // Example of how to do consecutive showcases
    public void showcaseMainActivity() {
        ShowcaseViews views = new ShowcaseViews(activity, R.layout.view_showcase);
        views.addView(new ShowcaseViews.ItemViewProperties(ID_SPINNER, R.string.showcase_main_spinner_title, R.string.showcase_main_spinner_message, ShowcaseView.ITEM_TITLE_OR_SPINNER));
        views.addView(new ShowcaseViews.ViewProperties(R.id.activity_main_button_continue, R.string.showcase_main_continue_title, R.string.showcase_main_continue_message));
        views.show();
    }

    // Simpler example but fully customised
    public void showcaseSecondActivity() {
        ShowcaseView showcaseView = (ShowcaseView) activity.getLayoutInflater().inflate(R.layout.view_showcase, null);
        showcaseView.setShowcaseItem(ShowcaseView.ITEM_ACTION_HOME, android.R.id.home, activity);
        showcaseView.setText("Up Button", "Press this to send you 'up' one level");
        ((ViewGroup) activity.getWindow().getDecorView()).addView(showcaseView);
    }
}
