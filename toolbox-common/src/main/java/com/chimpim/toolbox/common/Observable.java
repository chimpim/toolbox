package com.chimpim.toolbox.common;


import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class Observable<T> {
    private boolean changed = false;
    private Vector<Observer<T>> obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(@NotNull Observer<T> observer) {
        if (!obs.contains(observer)) {
            obs.addElement(observer);
        }
    }

    public synchronized void deleteObserver(Observer observer) {
        obs.removeElement(observer);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(T t) {
        Object[] arrLocal;
        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            //noinspection unchecked
            ((Observer<T>) arrLocal[i]).onNext(this, t);
        }
    }

    public void instantlyNotifyObservers(T t) {
        setChanged();
        notifyObservers(t);
    }

    public void notifyErrorObservers(Throwable e) {
        Object[] arrLocal;
        synchronized (this) {
            arrLocal = obs.toArray();
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            Object o = arrLocal[i];
            if (o instanceof Observer) {
                ((Observer) o).onError(e);
            }
        }
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    public synchronized void setChanged() {
        changed = true;
    }

    public synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
