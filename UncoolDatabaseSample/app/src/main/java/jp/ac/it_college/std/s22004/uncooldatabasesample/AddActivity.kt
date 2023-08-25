package jp.ac.it_college.std.s22004.uncooldatabasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jp.ac.it_college.std.s22004.uncooldatabasesample.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var helper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener(::onSave)

        helper = DatabaseHelper(this)
    }

    private fun onSave(v: View): Unit {
        // データの保存処理をする。
        helper.writableDatabase.let { db ->
            val insertSQL = """
                INSERT INTO memo (name, content)
                VALUES (?, ?);
            """.trimIndent()
            db.compileStatement(insertSQL).use { stmt ->
                stmt.bindString(1, binding.name.text.toString())
                stmt.bindString(2, binding.content.text.toString())
                stmt.executeInsert()
            }
        }
        // 保存が終わったら元の画面(一覧画面)に戻る。
        finish()
    }
}