package jp.ac.it_college.std.s22004.databasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22004.databasesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var _helper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DB helper を生成
        _helper = DatabaseHelper(this)

        // RecyclerView の初期化
        initList(binding.lvCocktail)
    }

    override fun onDestroy() {
        _helper.close()      //　とても必要らしい
        super.onDestroy()
    }

    private fun initList(list: RecyclerView) {
        val data = resources.getStringArray(R.array.lv_cocktail_list)
        val adapter = CocktailAdapter(data.toList()) { pos, name ->
            binding.tvCocktailName.text = name
        }
        val manager = LinearLayoutManager(this)
        list.adapter = adapter
        list.layoutManager = manager
        list.addItemDecoration(
            DividerItemDecoration(this, manager.orientation)
        )
    }
}