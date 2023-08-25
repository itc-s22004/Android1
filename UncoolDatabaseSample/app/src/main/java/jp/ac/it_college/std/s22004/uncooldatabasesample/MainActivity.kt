package jp.ac.it_college.std.s22004.uncooldatabasesample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22004.uncooldatabasesample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var helper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // dbヘルパー
        helper = DatabaseHelper(this)

        // リストの初期化
        initList()

        // ボタンのイベント設定
        binding.addButton.setOnClickListener(::onAdd)
    }

    private fun initList() {
        binding.dataList.apply {
            adapter = MemoAdapter(loadData())
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }
    }

    private fun onAdd(v: View) {
        startActivity(Intent(this, AddActivity::class.java))
    }

    private fun loadData(): List<Memo> {
        val selectSQL = """
            SELECT * FROM memo ORDER BY _id DESC
        """.trimIndent()
        return helper.readableDatabase.let { db ->
            db.rawQuery(selectSQL, null).use { c ->
                val list = mutableListOf<Memo>()
                while (c.moveToNext()) {
                    list.add(
                        Memo(
                            c.getLong(c.getColumnIndexOrThrow("_id")),
                            c.getString(c.getColumnIndexOrThrow("name")),
                            c.getString(c.getColumnIndexOrThrow("content")),
                        )
                    )
                }
                list
            }
        }
    }
}