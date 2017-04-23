package com.mdm.db;


import com.mdm.model.RawData;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Created by think360 on 15/03/17.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase"; // we will add the .db extension

    public static final int VERSION = 7;

    @Migration(version = 3, priority = 5, database = AppDatabase.class)
    public static class Migration1 extends AlterTableMigration<RawData> {

        public Migration1(Class<RawData> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {

            addColumn(SQLiteType.REAL, "openingBalanceMoney");
            addColumn(SQLiteType.REAL, "recievedBalanceMoney");
            addColumn(SQLiteType.REAL, "todayExpensesMoney");
            addColumn(SQLiteType.REAL, "finalTodayBalanceMoney");

            addColumn(SQLiteType.REAL, "openingBalanceWheat");
            addColumn(SQLiteType.REAL, "recievedBalanceWheat");
            addColumn(SQLiteType.REAL, "consumeTodayBalanceWheat");
            addColumn(SQLiteType.REAL, "finalTodayBalanceWheat");

            addColumn(SQLiteType.REAL, "openingBalanceRice");
            addColumn(SQLiteType.REAL, "recievedBalanceRice");
            addColumn(SQLiteType.REAL, "cousumeTodayRice");
            addColumn(SQLiteType.REAL, "finalBalanceRice");

        }
    }

    @Migration(version = 4, priority = 5, database = AppDatabase.class)
    public static class Migration4 extends AlterTableMigration<RawData> {

        public Migration4(Class<RawData> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {

            addColumn(SQLiteType.INTEGER, "day");
            addColumn(SQLiteType.INTEGER, "month");
            addColumn(SQLiteType.INTEGER, "year");

        }
    }

    @Migration(version = 7, priority = 5, database = AppDatabase.class)
    public static class Migration5 extends AlterTableMigration<RawData> {

        public Migration5(Class<RawData> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {

            addColumn(SQLiteType.INTEGER, "time");


        }
    }
}
