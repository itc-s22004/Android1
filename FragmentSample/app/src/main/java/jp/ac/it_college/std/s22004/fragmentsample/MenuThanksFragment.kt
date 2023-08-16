package jp.ac.it_college.std.s22004.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import jp.ac.it_college.std.s22004.fragmentsample.databinding.FragmentMenuThanksBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
internal const val ARG_NAME = "menuName"
internal const val ARG_PRICE = "menuPrice"

internal const val REQUEST_BACK_MENU = "backMenu"

class MenuThanksFragment : Fragment() {
    //Bindingクラスのインスタンスを入れておくプロパティ(Nullable)
    private var _binding: FragmentMenuThanksBinding? = null

    //Bindingをnon-null 型としてシンプルに使うため工夫
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuThanksBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // arguments プロパティ経由腕外部からパラメータを受け取る
        val menuName = arguments?.getString(ARG_NAME) ?: ""
        val menuPrice = arguments?.getInt(ARG_PRICE) ?: 0

        binding.tvMenuName.text = menuName
//        binding.tvMenuPrice.text = "%,d".format(menuPrice)
        binding.tvMenuPrice.text = "%,d".format(menuPrice)

        //ボタンのイベントリスナを設定
        binding.btThxBack.setOnClickListener(::onBackButtonClick)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBackButtonClick(v: View) {
        parentFragmentManager.setFragmentResult(REQUEST_BACK_MENU, bundleOf())
    }
}