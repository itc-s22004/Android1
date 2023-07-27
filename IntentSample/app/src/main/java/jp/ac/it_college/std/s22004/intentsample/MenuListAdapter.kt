package jp.ac.it_college.std.s22004.intentsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22004.intentsample.databinding.ActivityMainBinding
import jp.ac.it_college.std.s22004.intentsample.databinding.MenuRowBinding

//import jp.ac.it_college.std.s22004.intentsample.databinding.MenuRowBinding

class MenuListAdapter(private val data: List<Menu>)
    : RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {

    class ViewHolder(val binding: MenuRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            MenuRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            tvName.text = item.name
            tvPrice.text = item.price.toString()
        }
    }



}