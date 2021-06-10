package com.example.tutorialsproject.mvp;

public interface MVPMainActivityContract {

    interface View {
        void onSuccess(String message);
        void onError(String message);
    }

    interface Presenter {
        void doLogin(String email, String password);
    }

}
