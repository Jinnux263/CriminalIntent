package com.example.criminalintent

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View


class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val v: View = LayoutInflater.from(activity)
            .inflate(R.layout.dialog_date, null)

        return AlertDialog.Builder(requireContext())
            .setView(v)
            .setTitle(R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok, null)
            .create()
    }
}