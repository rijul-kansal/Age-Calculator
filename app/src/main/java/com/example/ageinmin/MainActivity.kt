/**
  XML->
  Java->
  Values->color
  Change App Name --res->string
  Change App colour
  Drawable ->gradient
  */
package com.example.ageinmin

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity :AppCompatActivity() {
    // creating objet
    var selecteddate :TextView?=null
    var dispinmin :TextView?=null
    var dispinhour :TextView?=null
    var dispinday :TextView?=null
    var dispinweek :TextView?=null
    var dispinmon :TextView?=null
    var dispinyear :TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)


        // connecting these object with xml widgets
        val btn=findViewById<Button>(R.id.btn1)
        selecteddate=findViewById<TextView>(R.id.SelectedDate)
        dispinmin=findViewById<TextView>(R.id.displayinmin)
        dispinhour=findViewById<TextView>(R.id.displayinhour)
        dispinday=findViewById<TextView>(R.id.displayinday)
        dispinweek=findViewById<TextView>(R.id.displayinweek)
        dispinmon=findViewById<TextView>(R.id.displayinmon)
        dispinyear=findViewById<TextView>(R.id.displayinyear)

        // functioning of button
        btn.setOnClickListener {
            ClickDatePicker()
        }
    }
    fun ClickDatePicker()
    {
        // current date and month and day
        val mycal=Calendar.getInstance()
        val year=mycal.get(Calendar.YEAR)
        val month=mycal.get(Calendar.MONTH)
        val day: Int =mycal.get(Calendar.DAY_OF_MONTH)

        var dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,Selectedyear,Selectedmonth,Selectedday->

                // setting selected date
                var sd="$Selectedday/${Selectedmonth+1}/$Selectedyear"
                selecteddate?.text =sd


                // give a sepecific format  and store in var thedate
                val sde=SimpleDateFormat("dd/MM/yyyy" , Locale.FRANCE)
                val thedate=sde.parse(sd)

                // .time fn will give time in minisec from date xx and convert into min
                val selecteddateinmin=(thedate.time/60000)
                val selecteddateinhour=(thedate.time/60000)/60
                val selecteddateinday=((thedate.time/60000)/60)/24
                val selecteddateinweek=(((thedate.time/60000)/60)/24)/7


                val currdate=sde.parse(sde.format(System.currentTimeMillis()))
                val currdateinmin=(currdate.time/60000)
                val currdateinhour=(currdate.time/60000)/60
                val currdateinday=((currdate.time/60000)/60)/24
                val currdateinweek=(((currdate.time/60000)/60)/24)/7


                val  diffinmin=currdateinmin-selecteddateinmin
                val  diffinhour=currdateinhour-selecteddateinhour
                val  diffinday=currdateinday-selecteddateinday
                val  diffinweek=currdateinweek-selecteddateinweek
                var diffinyear=0
                if(month-Selectedmonth>=0)
                    diffinyear=year-Selectedyear
                else
                    diffinyear=year-Selectedyear-1
                var diffinmon=0;
                if(day-Selectedday>=0)
                diffinmon=month-Selectedmonth+diffinyear*12
                else
                diffinmon=month-Selectedmonth+diffinyear*12-1


                dispinmin?.text=diffinmin.toString()
                dispinhour?.text=diffinhour.toString()
                dispinday?.text=diffinday.toString()
                dispinweek?.text=(diffinweek-1).toString()
                dispinmon?.text=(diffinmon).toString()
                dispinyear?.text=(diffinyear).toString()
            },
            year,
            month,
            day
        )
        // resistit user to enter value > then today date
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}


