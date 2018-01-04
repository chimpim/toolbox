package com.chimpim.toolbox.common;


public abstract class Observer<T> {
    public abstract void onNext(Observable observable, T t);

    public void onError(Throwable e) {
    }
}
