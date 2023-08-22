package jp.ac.it_college.std.s22004.databasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22004.databasesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _helper: DatabaseHelper

    private var _cocktailId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DB helper を生成
        _helper = DatabaseHelper(this)

        // RecyclerView の初期化
        initList(binding.lvCocktail)

        // 保存ボタンのイベント設定
        binding.btnSave.setOnClickListener(::onSaveButton) // 多分これ使える
    }

    override fun onDestroy() {
        _helper.close()      //　とても必要らしい
        super.onDestroy()
    }

    private fun initList(list: RecyclerView) {
        val data = resources.getStringArray(R.array.lv_cocktail_list)
        val adapter = CocktailAdapter(data.toList()) { pos, name ->
            binding.tvCocktailName.text = name

            // 選択されたカクテルのID（番号）をMainActivityのプロパティで保持
            _cocktailId = pos.toLong()

            // データベース辛めもを取ってきてセットする
            binding.etNote.setText(loadCockTailMemos())    // -----------------------`
        }
        val manager = LinearLayoutManager(this)
        list.adapter = adapter
        list.layoutManager = manager
        list.addItemDecoration(
            DividerItemDecoration(this, manager.orientation)
        )
    }

    private fun onSaveButton(view: View) {
        //入力された感想を取得
        val note = binding.etNote.text.toString()
        // データベースヘルパーオブジェクトからデータベース接続オブジェクトを取得
        val db = _helper.writableDatabase
        // 既存のデータを削除してから、新たな感想を保存する方法
        // 削除用SQL
        val deleteSQL = """
            |DELETE FROM cocktail_memos
            |WHERE _id = ?
        """.trimMargin()
        db.compileStatement(deleteSQL).let { stmt ->
            // ブレースホルダ（?）に変数の値（ID）をバインド（割当）
            stmt.bindLong(1, _cocktailId)
            // 組み立てた DELETE SQL 分を実行
            stmt.executeUpdateDelete()
        }

        val insertSQL = """
            | INSERT INTO cocktail_memos (_id, name, note)
            | VALUES(?, ?, ?)
        """.trimMargin()

        db.compileStatement(insertSQL).let { stmt ->
            stmt.bindLong(1, _cocktailId)
            stmt.bindString(2, binding.tvCocktailName.text.toString())
            stmt.bindString(3, note)
            // 組み立てたINSERT SQL　文を実行
            stmt.executeInsert()
        }
        // View　とかの初期化？
        binding.etNote.setText("")
        binding.tvCocktailName.text = ""
        _cocktailId = 0
    }

    private fun loadCockTailMemos(): String {
        // 読み取り専用のデータベース接続オブジェクトを使用する
        val db = _helper.readableDatabase

        // 取得用SQL
        val select = """
            | SELECT * FROM cocktail_memos
            | WHERE _id = ?
        """.trimMargin()

        val cursor = db.rawQuery(select, arrayOf("$_cocktailId"))

        return cursor.use { // 勝手にcloseしてくれる　.use
            if (cursor.moveToNext()) {
                cursor.getString(cursor.getColumnIndexOrThrow("note"))  //

            } else {
                ""
            }
        }
    }
}