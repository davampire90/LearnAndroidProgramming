package com.example.myapplication


import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val calendarr = Calendar.getInstance()
        val cYear = calendarr.get(Calendar.YEAR)
        val cMonth = calendarr.get(Calendar.MONTH)
        val cDay = calendarr.get(Calendar.DAY_OF_MONTH)
        val hour = calendarr[Calendar.HOUR_OF_DAY]
        val minute = calendarr[Calendar.MINUTE]
        val person1 = Worker(1, "Jack", "", "", 0, "")

        binding.btnChekcIn.setOnClickListener {
            binding.textView.text = "BindingWork1"


            /* val pd = DatePickerDialog(
                 this,
                 DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                     //binding.textView.text = "" + mDay + "/" + mMonth + "/" + mYear
                     items.add("" + (mDay - 1) + "/" + mMonth + "/" + mYear)
                     items.add("" + mDay + "/" + mMonth + "/" + mYear)
                     items.add("" + (mDay + 1) + "/" + mMonth + "/" + mYear)

                 },
                 cYear,
                 cMonth,
                 cDay
             )

             pd.show()*/

            var mTimePicker = TimePickerDialog(
                this,
                { timePicker, selectedHour, selectedMinute ->
                    binding.textView.text = "$selectedHour:$selectedMinute"
                    // items.add("$selectedHour:$selectedMinute")

                    person1.startWork = "$selectedHour:$selectedMinute $cDay-$cMonth-$cYear"
                    println("StartWork:")
                    items.add(person1.startWork)
                    println(person1.startWork)

                },
                hour,
                minute,
                true
            ) // 24 hour = time

            mTimePicker.setTitle("Select Time")
            mTimePicker.show()

            //binding.textView.text = "BindingWork"
            
        }
        binding.btnChekcOut.setOnClickListener {
            var mTimePicker = TimePickerDialog(
                this,
                { timePicker, selectedHour, selectedMinute ->
                    binding.textView.text = "$selectedHour:$selectedMinute"
                    // items.add("$selectedHour:$selectedMinute")

                    person1.stopWork = "$selectedHour:$selectedMinute $cDay-$cMonth-$cYear"
                    println("Stop work: ${person1.stopWork}")
                    items.add(person1.stopWork)
                },
                hour,
                minute,
                true
            ) //Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        binding.btnExport.setOnClickListener {


            val sDate = person1.startWork
            val sDate2 = person1.stopWork

            try {
                val q = Regex(pattern = """\D+""").split(input = sDate)
                val qq = Regex(pattern = """\D+""").split(input = sDate2)

                //val e = w * 60 + ww
                val e3: Int = person1.ConvertMinutes(q[0].toInt(), q[1].toInt())
                println("hours+ minutes Int= $e3")

                println("CheckOutHour: ${qq[0]}")
                println("CheckOutMin: ${qq[1]}")

                //Working Minutes
                //val e = w * 60 + ww
                val e2: Int = person1.ConvertMinutes(qq[0].toInt(), qq[1].toInt())

                println("workMinutes Int= $e2")
                items.add("workMinutes Int= $e2")
                items.add(person1.stopWork)


                val l: Int = e2 - e3
                if (l < 0) {
                    binding.textView.text = "Invalid Input -$l"
                    items.add(l.toString())

                } else {
                    println("workMinutes Int= $l")
                    binding.textView.text = (l).toString()


                }


            } catch (e: Exception) {
                println("Catch")
            }

            zzz()

        }



    }
    private fun zzz() {
        val arrayAdapter: ArrayAdapter<*>
        val array = arrayOf(
            "Melbourne",
            "Vienna",
            "Vancouver",
            "Toronto",
            "Calgary",
            "Adelaide",
            "Perth",
            "Auckland",
            "Helsinki",
            "Hamburg",
            "Munich",
            "New York",
            "Sydney",
            "Paris",
            "Cape Town",
            "Barcelona",
            "London",
            "Bangkok"
        )
        // test
        /*
        items.add("aaaa")
        items.add("bbbb")
        items.add("ccccc")
*/
        //Convert ArrayList to Array
        val asd = items.toArray()

        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, asd
        )
        binding.listView.adapter = arrayAdapter


        // binding.spinner.adapter = aa
        // binding.listView.adapter = aa
    }

}








