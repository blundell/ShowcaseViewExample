package com.blundell.tut.showcase;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.github.espiandev.showcaseview.ShowcaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows you to have multiple showcases on one screen, you can then page through
 * each one as you press the 'ok' button
 */
public class ShowcaseViews {

    private final List<ShowcaseView> views = new ArrayList<ShowcaseView>();
    private final Activity activity;
    private final int showcaseTemplateId;

    private interface OnShowcaseAcknowledged {
        void onShowCaseAcknowledged(ShowcaseView oldView);
    }

    /**
     * @param activity               The activity containing the views you wish to showcase
     * @param showcaseTemplateLayout Must be the layout of a ShowcaseView - use this to style your showcase
     */
    public ShowcaseViews(Activity activity, int showcaseTemplateLayout) {
        this.activity = activity;
        this.showcaseTemplateId = showcaseTemplateLayout;
    }

    public void addView(ItemViewProperties properties) {
        ShowcaseView viewTemplate = newInstanceOfShowcaseView();
        viewTemplate.setShowcaseItem(properties.itemType, properties.id, activity);
        viewTemplate.setText(properties.titleResId, properties.messageResId);
        setChainClickListener(viewTemplate);
        views.add(viewTemplate);
    }

    public void addView(ViewProperties properties) {
        ShowcaseView viewTemplate = newInstanceOfShowcaseView();
        View v = activity.findViewById(properties.id);
        viewTemplate.setShowcaseView(v);
        viewTemplate.setText(properties.titleResId, properties.messageResId);
        setChainClickListener(viewTemplate);
        views.add(viewTemplate);
    }

    private ShowcaseView newInstanceOfShowcaseView() {
        return (ShowcaseView) activity.getLayoutInflater().inflate(showcaseTemplateId, null);
    }

    private void setChainClickListener(final ShowcaseView viewTemplate) {
        viewTemplate.overrideButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acknowledgedListener.onShowCaseAcknowledged(viewTemplate);
            }
        });
    }

    private OnShowcaseAcknowledged acknowledgedListener = new OnShowcaseAcknowledged() {
        @Override
        public void onShowCaseAcknowledged(ShowcaseView oldView) {
            oldView.hide();
            show();
        }
    };

    /**
     * Showcases will be shown in the order they where added, continuing when the button is pressed
     */
    public void show() {
        if (views.isEmpty()) {
            return;
        }
        final ShowcaseView view = views.get(0);
        ((ViewGroup) activity.getWindow().getDecorView()).addView(view);
        views.remove(0);
    }

    /**
     * Used for views on the ActionBar
     */
    public static class ItemViewProperties extends ViewProperties {
        public static final int ID_SPINNER = 0;
        public static final int ID_TITLE = 0;
        protected final int itemType;

        public ItemViewProperties(int id, int titleResId, int messageResId, int itemType) {
            super(id, titleResId, messageResId);
            this.itemType = itemType;
        }
    }

    /**
     * Used for all views except those on the ActionBar
     */
    public static class ViewProperties {
        protected final int titleResId;
        protected final int messageResId;
        protected final int id;

        public ViewProperties(int id, int titleResId, int messageResId) {
            this.id = id;
            this.titleResId = titleResId;
            this.messageResId = messageResId;
        }
    }

}
