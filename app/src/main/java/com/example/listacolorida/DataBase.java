package com.example.listacolorida;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MeuDatabase";
        private static final String TABLE_NAME = "Tabela";
        private static final String COL_TEXTO = "Texto";
        private static final String COL_COR = "Cor";

        private static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_TEXTO + " TEXT, " +
                        COL_COR + " INTEGER" +
                        ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public DataBase(Context context) {
            super(context, DATABASE_NAME, null, 2);
        }

        public long inserirDado(String texto, int cor) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_TEXTO, texto);
            contentValues.put(COL_COR, cor);
            return db.insert(TABLE_NAME, null, contentValues);
        }

        public void excluirDadoPorId(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String whereClause = "_id=?";
            String[] whereArgs = {String.valueOf(id)};
            db.delete(TABLE_NAME, whereClause, whereArgs);
            db.close();
        }
    public Cursor obterTodosDados() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT _id, " + COL_TEXTO + ", " + COL_COR + " FROM " + TABLE_NAME, null);
    }
}
