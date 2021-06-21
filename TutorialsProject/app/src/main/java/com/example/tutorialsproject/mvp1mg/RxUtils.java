package com.example.tutorialsproject.mvp1mg;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxUtils
{
    public static void dispose(Disposable... disposables)
    {
        for (Disposable disposable : disposables)
        {
            disposeDisposable(disposable);
        }
    }

    private static void disposeDisposable(Disposable disposable)
    {
        if (disposable != null)
        {
            disposable.dispose();
        }
    }

    public static void clearCompositeDisposables(CompositeDisposable disposables)
    {
        if (disposables != null)
        {
            if (!disposables.isDisposed())
            {
                disposables.clear();
            }
        }
    }
}
