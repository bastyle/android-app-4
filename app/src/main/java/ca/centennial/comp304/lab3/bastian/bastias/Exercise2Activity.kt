package ca.centennial.comp304.lab3.bastian.bastias

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityExercise2Binding

class Exercise2Activity: AppCompatActivity() {
    private lateinit var binding: ActivityExercise2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityExercise2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.dateTextView.setLongDate(1698009851000)
        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            //Toast.makeText(this, "Clicked on $year", Toast.LENGTH_SHORT).show()
            binding.dateTextView.setLongDate(year,monthOfYear,dayOfMonth)
        }
    }


}