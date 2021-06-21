package com.example.tutorialsproject.mvp1mg;

public interface DiagnosticsHomePresenter {

    void onScreenDestroyed();

    void onScreenCreated();

    void onTestAddClick(String test);

    void fetchAndUpdateCart();

    void onViewStarted();

    void onViewStopped();

    void onViewTouched();

}
