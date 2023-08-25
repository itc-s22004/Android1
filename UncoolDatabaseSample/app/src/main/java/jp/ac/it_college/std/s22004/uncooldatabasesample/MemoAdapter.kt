package jp.ac.it_college.std.s22004.uncooldatabasesample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.it_college.std.s22004.uncooldatabasesample.databinding.RowBinding

class MemoAdapter(val data: List<Memo>) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        var id: Long = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].run {
            holder.id = id
            holder.binding.name.text = name
        }
    }

    override fun getItemCount(): Int = data.size

}