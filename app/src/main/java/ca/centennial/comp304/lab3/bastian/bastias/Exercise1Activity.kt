package ca.centennial.comp304.lab3.bastian.bastias

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityExercise1Binding
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityMainBinding

class Exercise1Activity:AppCompatActivity() {
    private lateinit var binding: ActivityExercise1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercise1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}