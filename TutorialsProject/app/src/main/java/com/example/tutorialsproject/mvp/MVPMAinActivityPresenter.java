package com.example.tutorialsproject.mvp;

public class MVPMAinActivityPresenter implements MVPMainActivityContract.Presenter {
    MVPMainActivityContract.View view;

    public MVPMAinActivityPresenter(MVPMainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String email, String password) {
        if(email.equals("pooja") && password.equals("1234")) {
           view.onSuccess("Successful");
        } else {
           view.onError("Wrong email or password");
        }
    }

}
