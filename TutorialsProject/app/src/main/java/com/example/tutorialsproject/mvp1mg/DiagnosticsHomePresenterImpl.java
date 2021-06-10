package com.example.tutorialsproject.mvp1mg;

public class DiagnosticsHomePresenterImpl implements DiagnosticsHomePresenter {

    private DiagnosticsHomeView diagnosticsHomeView;

    DiagnosticsHomePresenterImpl(DiagnosticsHomeView diagnosticsHomeView) {
        this.diagnosticsHomeView = diagnosticsHomeView;
    }

    @Override
    public void onTestAddClick(String test) {
        addTest(test);
    }

    private void addTest(String test)
    {
        diagnosticsHomeView.addTestToCart(1);

    }

}
