package jp.ac.it_college.std.s22004.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22004.intentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvMenu.apply {
            adapter = MenuListAdapter(menuList) {name, price ->
                val intent2MenuThanks = Intent(  //Intent ---> 画面遷移させるやつ
                    this@MainActivity,   // (this, 遷移場所)
                    MenuThanksActivity::class.java
                )
                intent2MenuThanks.putExtra("menuName", name)
                intent2MenuThanks.putExtra("menuPrice", price)

                startActivity(intent2MenuThanks)  //画面を出すための
            }
            LinearLayoutManager(this@MainActivity).let {
                layoutManager = it
                addItemDecoration(DividerItemDecoration(this@MainActivity, it.orientation))
            }
        }
    }
}