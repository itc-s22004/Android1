package jp.ac.it_college.std.s22004.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22004.menusample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val menuList = mutableListOf<jp.ac.it_college.std.s22004.menusample.Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)

        initList(binding.lvMenu)
    }
    private fun initList(view: RecyclerView) {
        menuList.addAll(teishokuList)
        view.adapter = MenuListAdapter(menuList, ::order) // ::---  メンバ参照
        val layoutManager = LinearLayoutManager(this)
        view.layoutManager = layoutManager
        view.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        registerForContextMenu(view)
    }

    private fun order(name: String, price: Int) {
        startActivity(Intent(this, MenuThanksActivity::class.java).apply {
            putExtra("menuName", name)
            putExtra("menuPrice", price)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when (item.itemId) {
            R.id.menuListOptionTeishoku -> resetMenu(teishokuList)

            R.id.menuListOptionCurry -> resetMenu(curryList)

            else -> super.onOptionsItemSelected(item)

        }
        return result
    }

    private fun resetMenu(list: List<jp.ac.it_college.std.s22004.menusample.Menu>): Boolean {   // ココ重要
        menuList.clear()
        menuList.addAll(list)
        binding.lvMenu.adapter?.notifyDataSetChanged()
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.menu_context_menu_list,menu)

        menu?.setHeaderTitle(R.string.menu_list_options_header)
    }
}