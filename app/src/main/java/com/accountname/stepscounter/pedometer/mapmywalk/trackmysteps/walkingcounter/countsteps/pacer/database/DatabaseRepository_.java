package com.accountname.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.database;

import android.content.Context;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.OnViewChangedNotifier;


public final class DatabaseRepository_ extends DatabaseRepository {
    private static DatabaseRepository_ instance_;
    private Context context_;
    private Object rootFragment_;

    private DatabaseRepository_(Context context) {
        this.context_ = context;
    }

    private DatabaseRepository_(Context context, Object obj) {
        this.context_ = context;
        this.rootFragment_ = obj;
    }

    public static DatabaseRepository_ getInstance_(Context context) {
        if (instance_ == null) {
            OnViewChangedNotifier replaceNotifier = OnViewChangedNotifier.replaceNotifier(null);
            DatabaseRepository_ databaseRepository_ = new DatabaseRepository_(context.getApplicationContext());
            instance_ = databaseRepository_;
            databaseRepository_.init_();
            OnViewChangedNotifier.replaceNotifier(replaceNotifier);
        }
        return instance_;
    }

    private void init_() {
        this.context = this.context_;
        init();
    }

    @Override
    public void insertOrUpdateStep(final Step step) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0L, "") { // from class: com.kewitschka.stepcounter.database.DatabaseRepository_.1
            @Override // org.androidannotations.api.BackgroundExecutor.Task
            public void execute() {
                try {
                    DatabaseRepository_.super.insertOrUpdateStep(step);
                } catch (Throwable th) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
                }
            }
        });
    }

    @Override
    public void updateOffset(final float f) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0L, "") { // from class: com.kewitschka.stepcounter.database.DatabaseRepository_.2
            @Override // org.androidannotations.api.BackgroundExecutor.Task
            public void execute() {
                try {
                    DatabaseRepository_.super.updateOffset(f);
                } catch (Throwable th) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
                }
            }
        });
    }

    @Override
    public void updateSteps(final float f) {
        BackgroundExecutor.execute(new BackgroundExecutor.Task("", 0L, "") { // from class: com.kewitschka.stepcounter.database.DatabaseRepository_.3
            @Override // org.androidannotations.api.BackgroundExecutor.Task
            public void execute() {
                try {
                    DatabaseRepository_.super.updateSteps(f);
                } catch (Throwable th) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
                }
            }
        });
    }
}
