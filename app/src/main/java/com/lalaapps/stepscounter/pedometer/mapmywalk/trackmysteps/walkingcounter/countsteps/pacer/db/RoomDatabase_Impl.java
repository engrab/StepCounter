package com.lalaapps.stepscounter.pedometer.mapmywalk.trackmysteps.walkingcounter.countsteps.pacer.db;

import android.annotation.SuppressLint;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


import java.util.HashMap;
import java.util.HashSet;


public final class RoomDatabase_Impl extends RoomDatabase {
    private volatile StepDatabaseAccessor _stepDatabaseAccessor;

    @SuppressLint("RestrictedApi")
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) { // from class: com.kewitschka.stepcounter.database.RoomDatabase_Impl.1
            @Override
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `step` (`id` TEXT NOT NULL, `steps` REAL NOT NULL DEFAULT 0.0, `offset` REAL NOT NULL DEFAULT 0.0, `date` INTEGER DEFAULT 0.0, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ed1f94e5e1bd1790d95dfaf883620681')");
            }

            @Override
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `step`");
                if (RoomDatabase_Impl.this.mCallbacks != null) {
                    int size = RoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) RoomDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (RoomDatabase_Impl.this.mCallbacks != null) {
                    int size = RoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) RoomDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                RoomDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                RoomDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (RoomDatabase_Impl.this.mCallbacks != null) {
                    int size = RoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((Callback) RoomDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(4);
                hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                hashMap.put("steps", new TableInfo.Column("steps", "REAL", true, 0, "0.0", 1));
                hashMap.put("offset", new TableInfo.Column("offset", "REAL", true, 0, "0.0", 1));
                hashMap.put("date", new TableInfo.Column("date", "INTEGER", false, 0, "0.0", 1));
                TableInfo tableInfo = new TableInfo("step", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "step");
                if (tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(true, null);
                }
                return new RoomOpenHelper.ValidationResult(false, "step(com.lalaapps.stepcounter.database.Step).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
        }, "ed1f94e5e1bd1790d95dfaf883620681", "d5cbc2117657d10ba3cdc8eaa5b56ddd")).build());
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "step");
    }

    @SuppressLint("RestrictedApi")
    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `step`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override
    public StepDatabaseAccessor accessor() {
        StepDatabaseAccessor stepDatabaseAccessor;
        if (this._stepDatabaseAccessor != null) {
            return this._stepDatabaseAccessor;
        }
        synchronized (this) {
            if (this._stepDatabaseAccessor == null) {
                this._stepDatabaseAccessor = new StepDatabaseAccessor_Impl(this);
            }
            stepDatabaseAccessor = this._stepDatabaseAccessor;
        }
        return stepDatabaseAccessor;
    }
}
