package jp.ac.it_college.std.s22004.databasesample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22004.databasesample.databinding.MenuRowBinding

class CocktailAdapter(
    private val data: List<String>,
    private val callback: (Int ,String) -> Unit
) : RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {
    class ViewHolder(internal val binding: MenuRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(MenuRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCocktailName.text = data[position]
        holder.binding.root.setOnClickListener {
            callback(position, data[position])
        }
    }
}