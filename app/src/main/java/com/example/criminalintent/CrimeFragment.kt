package com.example.criminalintent

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import androidx.fragment.app.FragmentManager
import java.util.*

class CrimeFragment : Fragment() {
    private var mCrime: Crime? = null
    private var mTitleField: EditText? = null
    private var mDateButton: Button? = null
    private var mSolvedCheckBox: CheckBox? = null

    // Companion object để cung cấp cho activity host phương thức tạo một fragment
    // gắn bundle có crimeId
    companion object {
        private const val ARG_CRIME_ID = "crime_id"
        private const val DIALOG_DATE = "DialogDate"

        fun newInstance(crimeId: UUID?): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeId)
            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val crimeId = requireArguments().getSerializable(ARG_CRIME_ID) as UUID
        mCrime = CrimeLab.getCrime(crimeId)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_crime, container, false)
        mTitleField = v.findViewById<View>(R.id.crime_title) as EditText
        mTitleField!!.setText(mCrime!!.getTitle())
        mTitleField!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
                // This space intentionally left blank
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                mCrime!!.setTitle(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                // This one too
            }
        })

        mDateButton = v.findViewById<View>(R.id.crime_date) as Button
        mDateButton?.text = mCrime!!.getDate().toString()
        mDateButton!!.setOnClickListener {
            val manager: FragmentManager = parentFragmentManager
            val dialog = DatePickerFragment()
            dialog.show(manager, DIALOG_DATE)
        }

        mSolvedCheckBox = v.findViewById<View>(R.id.crime_solved) as CheckBox
        mSolvedCheckBox!!.isChecked = mCrime!!.isSolved()
        mSolvedCheckBox!!.setOnCheckedChangeListener { _, isChecked ->
            mCrime!!.setSolved(
                isChecked
            )
        }

        return v
    }


}