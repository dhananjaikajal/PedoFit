package com.dj.pedofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper {

	/** for database */
	static final String DataBaseName = "Database";

	/** for employee table */
	static final String FavouritesTable = "Records";
	public static final String ColId = "Id";
	public static final String date = "Date";
	public static final String steps = "Number_of_steps";

	public static final int DATABASE_VERSION = 1;

	private static final String EMPLOYEE_TABLE_CREATE = "Create table "
			+ FavouritesTable + "(" + ColId
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + date + " TEXT,"
			+ steps + " TEXT)";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public MyDBHelper(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DataBaseName, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(EMPLOYEE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + FavouritesTable);
			onCreate(db);
		}

	};

	public MyDBHelper open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	// For favourite Table
	public long insert(String name, String username) {
		ContentValues initialValues = new ContentValues();

		initialValues.put(date, name);
		initialValues.put(steps, username);

		return db.insert(FavouritesTable, null, initialValues);
	}

	public Cursor getEmpValues() {
		Cursor mCursor = db.query(FavouritesTable, null, null, null, null,
				null, null);
		return mCursor;
	}

	public boolean deleteEmpList(long rowId) {
		return db.delete(FavouritesTable, ColId + " = " + rowId, null) > 0;
	}

	public boolean updateEmplist(String name, String username, String password,
			Integer rowid) {

		ContentValues initialValues = new ContentValues();

		initialValues.put(date, name);
		initialValues.put(steps, username);

		try {
			int b = db.update(FavouritesTable, initialValues, ColId + " = "
					+ rowid, null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}