package ca.centennial.comp304.lab3.bastian.bastias

import android.R
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
        val data = arrayOf("Exercise 1", "Exercise 2", "Exercise 3")

        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, data)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "Clicked on $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}