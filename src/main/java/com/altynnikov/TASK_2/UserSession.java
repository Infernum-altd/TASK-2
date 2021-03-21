package com.altynnikov.TASK_2;


import com.altynnikov.TASK_2.views.HomeView;
import com.altynnikov.TASK_2.views.View;

public class UserSession {
    private View currentView;
    private View previousView;

    public UserSession() {
        this.currentView = new HomeView();
    }

    public UserSession(View currentView) {
        this.currentView = currentView;
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }

    public void interact(String answer) {
        this.currentView = this.currentView.interact(answer);
    }

    public boolean isViewChanged() {
        return currentView.getClass().equals(previousView.getClass());
    }
}
