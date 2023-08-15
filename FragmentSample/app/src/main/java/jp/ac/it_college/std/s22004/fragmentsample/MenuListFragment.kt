package jp.ac.it_college.std.s22004.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s22004.fragmentsample.databinding.FragmentMenuListBinding

// Fragment Result API を通じてActivity へデータを渡すためのイベント
internal const val REQUEST_SELECTED_MENU = "selectedMenu"
internal const val RESULT_NAME = "menuName"
internal const val RESULT_PRICE = "menuPrice"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuListFragment : Fragment() {
    private var _binding: FragmentMenuListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //表示に関するなにか
        binding.menuList.apply {
            adapter = MenuAdapter(teishokuList) {
                parentFragmentManager.setFragmentResult(REQUEST_SELECTED_MENU, bundleOf(
                    RESULT_NAME to it.name,
                    RESULT_PRICE to it.price
                ))
            }
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }
    }

    override fun onDestroyView() {  //　メモリ開けるやつ
        super.onDestroyView()
        _binding = null
    }


}