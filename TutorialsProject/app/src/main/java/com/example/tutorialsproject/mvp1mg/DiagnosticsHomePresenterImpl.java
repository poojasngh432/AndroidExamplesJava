package com.example.tutorialsproject.mvp1mg;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DiagnosticsHomePresenterImpl implements DiagnosticsHomePresenter {

    private DiagnosticsHomeView diagnosticsHomeView;
    private Disposable timerDisposable;
    private CompositeDisposable disposables = new CompositeDisposable();
    private DiagnosticsCartInteractor diagnosticCartInteractor;

    DiagnosticsHomePresenterImpl(DiagnosticsHomeView diagnosticsHomeView) {
        this.diagnosticsHomeView = diagnosticsHomeView;
        diagnosticCartInteractor = new DiagnosticsCartInteractorImpl();
    }

    @Override
    public void onScreenDestroyed() {
        diagnosticsHomeView = null;
        cancelTasks();
    }

    private void cancelTasks()
    {
        RxUtils.dispose(timerDisposable);
        RxUtils.dispose(disposables);
    }

    @Override
    public void onScreenCreated() {
        fetchAndUpdateCart();
    }

    @Override
    public void fetchAndUpdateCart()
    {
        diagnosticsHomeView.showProgress();
        disposables.add(diagnosticCartInteractor.getCart()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onCartFetched, this::onCartError));
    }

    private void onCartFetched(Test cart)
    {

        if (isViewAttached())
        {
            if (cart.getDescription()!= null || cart.getName() != null)
            {
                diagnosticCartInteractor.updateCartStore(cart);
            } else
            {
                diagnosticCartInteractor.clearCartStore();
            }
            fetchHomeData();
        }
    }

    private void fetchHomeData()
    {
        disposables.add(diagnosticCartInteractor.getCart()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onHomeDataFetched, this::onError));
    }

    private void onHomeDataFetched(Test diagnosticsHomeViewModel)
    {
        if (isViewAttached())
        {
            diagnosticsHomeView.hideProgress();
        }
    }

    private void onError(Throwable e)
    {
        if (isViewAttached())
        {
            diagnosticsHomeView.hideProgress();
            if (e instanceof NoNetworkException)
            {

            } else
            {

            }
        }
    }

    private void onCartError(Throwable e)
    {
        if (isViewAttached())
        {
            diagnosticsHomeView.hideProgress();
            if (e instanceof NoNetworkException)
            {
//                diagnosticsHomeView.showNoNetwork();
            } else if (e instanceof Exception)
            {
//                diagnosticsHomeView.showError(e);
            } else
            {
                //do nothing
            }
        }
    }

    private boolean isViewAttached()
    {
        return diagnosticsHomeView != null;
    }

    @Override
    public void onTestAddClick(String test) {
        addTest(test);
    }

    @Override
    public void onViewStarted() {

    }

    @Override
    public void onViewStopped() {

    }

    @Override
    public void onViewTouched() {

    }

    private void addTest(String test)
    {
        diagnosticsHomeView.addTestToCart(1);

    }

}
