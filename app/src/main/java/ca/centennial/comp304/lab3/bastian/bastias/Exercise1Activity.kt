package ca.centennial.comp304.lab3.bastian.bastias

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.bastias.databinding.ActivityExercise1Binding


class Exercise1Activity:AppCompatActivity() {
    private lateinit var binding: ActivityExercise1Binding

    private lateinit var reusableImageView: ImageView
    private lateinit var textView: TextView


    private var startx = 10
    private var starty = 10
    private var endx = 300
    private var endy = 300

    //
    private lateinit var canvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private lateinit var bitmap: Bitmap

    companion object {
        val LINE_LARGE = 20
    }
    //
    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        style = Paint.Style.STROKE // default: FILL
        strokeWidth = 10f // default: Hairline-width (really thin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercise1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //creating a bitmap as content view for the image
        bitmap = Bitmap.createBitmap(
            windowManager
                .defaultDisplay.width, windowManager
                .defaultDisplay.height, Bitmap.Config.ARGB_8888
        )
        //tell canvas about the content view
        canvas = Canvas(bitmap)
        //set the background for your drawings
        //canvas.drawColor(Color.GRAY) //background
        //setup the image view
        reusableImageView = findViewById<View>(R.id.ImageViewForDrawing) as ImageView
        //tell image view for the bitmap format/content
        reusableImageView.setImageBitmap(bitmap)
        reusableImageView.setVisibility(View.VISIBLE)
        textView = findViewById<View>(R.id.textView1) as TextView
        textView!!.text = "Y = ".plus(endy.toString())
        //textView!!.text = resources.getString(R.string.help)
        //spinner behaviour
        val thicknessArray = resources.getIntArray(R.array.thickness_values)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, thicknessArray.map { it.toString() })
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedValue = thicknessArray[position]
                // Handle the selected integer value
                Toast.makeText(this@Exercise1Activity, "Selected value: $selectedValue", Toast.LENGTH_SHORT).show()
                paint.strokeWidth= selectedValue.toFloat()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {          }
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                val selectedValue = selectedRadioButton.text.toString()
                when(selectedValue){
                    "Cyan"->{
                        paint.color=Color.CYAN
                    }
                    "Red"->{
                        paint.color=Color.RED
                    }
                    "Yellow"->{
                        paint.color=Color.YELLOW
                    }
                }
            }
        }

        binding.btnDown.setOnClickListener {
            endy = endy + LINE_LARGE
            drawLine(canvas)
        }
        binding.btnUp.setOnClickListener {
            reusableImageView.setFocusable(true)
            reusableImageView.requestFocus()
            endy = endy - LINE_LARGE
            drawLine(canvas)
            //moveRect(canvas);
            reusableImageView!!.invalidate()
        }
        binding.btnLeft.setOnClickListener {
            reusableImageView.setFocusable(true)
            reusableImageView.requestFocus()
            endx = endx - LINE_LARGE
            drawLine(canvas)
            //moveRect(canvas);
            reusableImageView!!.invalidate()
        }

        binding.btnRight.setOnClickListener {
            reusableImageView.setFocusable(true)
            reusableImageView.requestFocus()
            endx = endx + LINE_LARGE
            drawLine(canvas)
            //moveRect(canvas);
            reusableImageView.invalidate()
        }


    } // end of onCreate

    fun clearCanvas(v: View?) {
        canvas?.drawColor(Color.WHITE)
        canvas = Canvas(bitmap)
        startx = 10
        starty = 10
        endx = 300
        endy = 300
        textView!!.text = "Y = ".plus(endy.toString())//resources.getString(R.string.help)
    }

    fun startDrawing(v: View?) {
        canvas.drawPoint(15f, 15f, paint!!)
    }

    fun drawLine(canvas: Canvas) {
        textView.text = "Y = ".plus(endy.toString())
        //canvas.drawLine(100,100,300,300,paint);
        canvas.drawLine(startx.toFloat(), starty.toFloat(), endx.toFloat(), endy.toFloat(), paint!!)
        startx = endx
        starty = endy
    }

    override fun onBackPressed() {
        val intent = Intent(this@Exercise1Activity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}