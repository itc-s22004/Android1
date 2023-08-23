package jp.ac.it_college.std.s22004.uncooldatabasesample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "memo_database.db"
private const val DB_VERSION = 1

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DB_NAME, null, DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // テーブルを作る
        val createTable = """
            CREATE TABLE memo (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                content TEXT
            );
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}