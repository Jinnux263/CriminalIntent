package com.example.criminalintent

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import java.util.*


class DatePickerFragment : DialogFragment() {

    companion object {
        private const val ARG_DATE = "date"
        private lateinit var mDatePicker: DatePicker

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val date = requireArguments().getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val v: View = LayoutInflater.from(activity)
            .inflate(R.layout.dialog_date, null)

        mDatePicker = v.findViewById<View>(R.id.dialog_date_picker) as DatePicker
        mDatePicker.init(year, month, day, null)


        return AlertDialog.Builder(requireContext())
            .setView(v)
            .setTitle(R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok, null)
            .create()
    }
}