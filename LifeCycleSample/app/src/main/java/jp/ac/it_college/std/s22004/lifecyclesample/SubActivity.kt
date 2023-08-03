package jp.ac.it_college.std.s22004.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import jp.ac.it_college.std.s22004.lifecyclesample.databinding.ActivityMainBinding
import jp.ac.it_college.std.s22004.lifecyclesample.databinding.ActivitySubAcitivyBinding

class SubActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubAcitivyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycleSample", "Main onCreate() called.")
        super.onCreate(savedInstanceState)
        binding = ActivitySubAcitivyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btPrev.setOnClickListener(::onPrevClick)
    }

    private fun onPrevClick(view: View) {
        finish()
    }

    override fun onStart() {
        Log.i("LifeCycleSample", "Main onStart() called.")
        super.onStart()
    }

    override fun onResume() {
        Log.i("LifeCycleSample", "Main onResume() called.")
        super.onResume()
    }

    override fun onRestart() {
        Log.i("LifeCycleSample", "Main onRestart() called.")
        super.onRestart()
    }

    override fun onPause() {
        Log.i("LifeCycleSample", "Main onPause() called.")
        super.onPause()
    }

    override fun onStop() {
        Log.i("LifeCycleSample", "Main onStop() called.")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("LifeCycleSample", "Main onDestroy() called.")
        super.onDestroy()
    }
}