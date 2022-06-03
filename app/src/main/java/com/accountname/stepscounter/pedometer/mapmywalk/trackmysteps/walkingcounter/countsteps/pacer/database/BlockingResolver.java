package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database;

import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DatabaseRepository.java */

public class BlockingResolver<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public T resolve(Maybe<T> maybe, T t) {
        return maybe.subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).blockingGet(t);
    }
}
