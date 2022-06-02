package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db;

import android.annotation.SuppressLint;
import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;

import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.RxRoom;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public final class StepDatabaseAccessor_Impl implements StepDatabaseAccessor {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Step> __insertionAdapterOfStep;
    private final SharedSQLiteStatement __preparedStmtOfUpdateOffset;
    private final SharedSQLiteStatement __preparedStmtOfUpdateSteps;
    private volatile StepDatabaseAccessor _stepDatabaseAccessor;
    @SuppressLint("RestrictedApi")
    public StepDatabaseAccessor_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfStep = new EntityInsertionAdapter<Step>(roomDatabase) { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `step` (`id`,`steps`,`offset`,`date`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Step step) {
                if (step.id == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, step.id);
                }
                supportSQLiteStatement.bindDouble(2, step.steps);
                supportSQLiteStatement.bindDouble(3, step.offset);
                Long dateToTimestamp = DatabaseConverter.dateToTimestamp(step.date);
                if (dateToTimestamp == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, dateToTimestamp.longValue());
                }
            }
        };
        this.__preparedStmtOfUpdateOffset = new SharedSQLiteStatement(roomDatabase) { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE step SET `offset`=? WHERE id=?";
            }
        };
        this.__preparedStmtOfUpdateSteps = new SharedSQLiteStatement(roomDatabase) { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE step SET `steps`=? WHERE id=?";
            }
        };
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void insertOrUpdateStep(Step step) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
//            this.__insertionAdapterOfStep.insert((EntityInsertionAdapter<Step>) step);
            this.__insertionAdapterOfStep.insert(step);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void updateOffset(String str, float f) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateOffset.acquire();
        acquire.bindDouble(1, f);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateOffset.release(acquire);
        }
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void updateSteps(String str, float f) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateSteps.acquire();
        acquire.bindDouble(1, f);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateSteps.release(acquire);
        }
    }



    @SuppressLint("RestrictedApi")
    @Override
    public Maybe<List<Step>> getAllSteps() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM step", 0);
        return Maybe.fromCallable(new Callable<List<Step>>() { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.4
            @Override // java.util.concurrent.Callable
            public List<Step> call() throws Exception {
                Cursor query = DBUtil.query(StepDatabaseAccessor_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "steps");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "offset");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new Step(query.getString(columnIndexOrThrow), query.getFloat(columnIndexOrThrow2), query.getFloat(columnIndexOrThrow3), DatabaseConverter.fromTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)))));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public Maybe<List<Step>> getStepsForLastDays(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM step order by date desc limit ?", 1);
        acquire.bindLong(1, i);
        return Maybe.fromCallable(new Callable<List<Step>>() { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.5
            @Override // java.util.concurrent.Callable
            public List<Step> call() throws Exception {
                Cursor query = DBUtil.query(StepDatabaseAccessor_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "steps");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "offset");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(new Step(query.getString(columnIndexOrThrow), query.getFloat(columnIndexOrThrow2), query.getFloat(columnIndexOrThrow3), DatabaseConverter.fromTimestamp(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)))));
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public Maybe<Step> getStepById(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM step where id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return Maybe.fromCallable(new Callable<Step>() { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Step call() throws Exception {
                Step step = null;
                Long l = null;
                Cursor query = DBUtil.query(StepDatabaseAccessor_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "steps");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "offset");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date");
                    if (query.moveToFirst()) {
                        String string = query.getString(columnIndexOrThrow);
                        float f = query.getFloat(columnIndexOrThrow2);
                        float f2 = query.getFloat(columnIndexOrThrow3);
                        if (!query.isNull(columnIndexOrThrow4)) {
                            l = Long.valueOf(query.getLong(columnIndexOrThrow4));
                        }
                        step = new Step(string, f, f2, DatabaseConverter.fromTimestamp(l));
                    }
                    return step;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public Flowable<Step> getStepByIdStream(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM step where id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"step"}, new Callable<Step>() { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Step call() throws Exception {
                Step step = null;
                Long l = null;
                Cursor query = DBUtil.query(StepDatabaseAccessor_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "steps");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "offset");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "date");
                    if (query.moveToFirst()) {
                        String string = query.getString(columnIndexOrThrow);
                        float f = query.getFloat(columnIndexOrThrow2);
                        float f2 = query.getFloat(columnIndexOrThrow3);
                        if (!query.isNull(columnIndexOrThrow4)) {
                            l = Long.valueOf(query.getLong(columnIndexOrThrow4));
                        }
                        step = new Step(string, f, f2, DatabaseConverter.fromTimestamp(l));
                    }
                    return step;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public Maybe<Float> getStepsById(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT steps FROM step where id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return Maybe.fromCallable(new Callable<Float>() { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Float call() throws Exception {
                Float f = null;
                Cursor query = DBUtil.query(StepDatabaseAccessor_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        f = Float.valueOf(query.getFloat(0));
                    }
                    return f;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
    @SuppressLint("RestrictedApi")
    @Override
    public Maybe<Float> getOffsetById(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `offset` FROM step where id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return Maybe.fromCallable(new Callable<Float>() { // from class: com.kewitschka.stepcounter.database.StepDatabaseAccessor_Impl.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Float call() throws Exception {
                Float f = null;
                Cursor query = DBUtil.query(StepDatabaseAccessor_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst() && !query.isNull(0)) {
                        f = Float.valueOf(query.getFloat(0));
                    }
                    return f;
                } finally {
                    query.close();
                }
            }

            protected void finalize() {
                acquire.release();
            }
        });
    }
}
