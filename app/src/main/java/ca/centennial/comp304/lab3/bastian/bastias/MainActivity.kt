package ca.centennial.comp304.lab3.bastian.bastias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val data = arrayOf("Exercise 1", "Exercise 2", "Exercise 3")
        val exercisesArray = resources.getStringArray(R.array.exercises)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exercisesArray)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Clicked on $selectedItem", Toast.LENGTH_SHORT).show()
            when (selectedItem) {
                "Exercise 1" -> {
                    val intent = Intent(this, Exercise1Activity::class.java)
                    startActivity(intent)
                }
                "Exercise 2" -> {
                    val intent = Intent(this, Exercise2Activity::class.java)
                    startActivity(intent)
                }
                /*"Exercise 3" -> {
                    val intent = Intent(this, Exercise3Activity::class.java)
                    startActivity(intent)
                }
                // Add more cases for additional exercises
                else -> {
                    // Handle any other case or provide a default action
                */
            }
        }
    }
}