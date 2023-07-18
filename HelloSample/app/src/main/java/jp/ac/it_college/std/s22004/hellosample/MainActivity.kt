package jp.ac.it_college.std.s22004.hellosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import jp.ac.it_college.std.s22004.hellosample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
    ViewBinding 機能を使って画面を作る方法。
    activity_main.xml ファイルに対して　ActivityMainBinding　クラスが
    自動生成されます。（レイアウトファイル名 + Binding）
     */
    private lateinit var binding: ActivityMainBinding  // なんか追加 A

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding クラスのインスタンスを生成してもらう
        binding = ActivityMainBinding.inflate(layoutInflater)  // なんか追加　A
        //Binding クラスが管理しているビューを使う
        //setContentView(R.layout.activity_main) -> もともと
        setContentView(binding.root) //root　-> linearlayout　　　A

        //ぼたんを取ってくる
//        val btClick = findViewById<Button>(R.id.btClick)

        //リスナクラスのインスタンスを作る
        val listener = HelloListener()

        // Binding 経由うで牡丹にリスナを設定する
        //btClick.setClickListener(listener)
        binding.btClick.setOnClickListener(listener)
    }

    private inner class HelloListener : View.OnClickListener {
        override fun onClick(v: View?) {

            // 名前が入力されるであろう　EditText をとってくる
//            val input = findViewById<EditText>(R.id.etName)
//            val input = binding.etName

            // メッセージを出力する先の　TextView を取ってくる
//            val output = findViewById<TextView>(R.id.tvOutput)
//            val output = binding.tvOutput

            //input(EditText)　から入力内容を取り出してString型へ
//            val inputStr = input.text.toString()
            val inputStr = binding.etName.text.toString()

//            output.text = "${inputStr}さん、こんにちは！"
            binding.tvOutput.text = "${inputStr}さん、こんにちは"
        }

    }
}


/*
build.gradle　に設定を追加
Binding クラスを inflate
setContentView(binding.root)
ビューを参照するときは　binding　から
 */