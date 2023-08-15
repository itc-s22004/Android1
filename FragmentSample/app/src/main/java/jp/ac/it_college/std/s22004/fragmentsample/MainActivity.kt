package jp.ac.it_college.std.s22004.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s22004.fragmentsample.databinding.ActivityMainBinding
import jp.ac.it_college.std.s22004.fragmentsample.databinding.FragmentMenuListBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}