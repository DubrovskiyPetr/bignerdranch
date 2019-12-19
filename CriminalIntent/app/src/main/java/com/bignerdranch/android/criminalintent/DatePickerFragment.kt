package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onDateSelected(resultDate)
            }
        }

        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        val initYear = calendar[Calendar.YEAR]
        val initMonth = calendar[Calendar.MONTH]
        val initDay = calendar[Calendar.DAY_OF_MONTH]

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initYear,
            initMonth,
            initDay
        )
    }

    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            return DatePickerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_DATE, date)
                }
            }
        }
    }

}