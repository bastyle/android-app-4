package ca.centennial.comp304.lab3.bastian.bastias

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityExercise2Binding
import java.text.SimpleDateFormat
import java.util.*

class Exercise2Activity: AppCompatActivity() {
    private lateinit var binding: ActivityExercise2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercise2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            //Toast.makeText(this, "Clicked on $year", Toast.LENGTH_SHORT).show()
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)
            val longDate = calendar.timeInMillis
            binding.dateStringView.text = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(Date(longDate))
            binding.dateTextView.date=longDate
        }
    }


}