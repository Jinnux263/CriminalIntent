package com.example.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import java.text.DateFormat
import android.content.Intent





class CrimeListFragment : Fragment() {
    private var mCrimeRecyclerView: RecyclerView? = null
    private var mAdapter: CrimeAdapter? = null

    private class CrimeHolder(view : View) :
        RecyclerView.ViewHolder(view) {
        private var mTitleTextView: TextView = itemView.findViewById<View>(R.id.crime_title) as TextView
        private var mDateTextView: TextView = itemView.findViewById<View>(R.id.crime_date) as TextView
        private var mSolvedImageView: ImageView = itemView.findViewById(R.id.crime_solved) as ImageView
        private lateinit var mCrime: Crime

        init {
            itemView.setOnClickListener {
                val intent = CrimePagerActivity.newIntent(view.context, mCrime.getId())
                view.context.startActivity(intent)
            }
        }

        fun bind(crime: Crime) {
            mCrime = crime
            mTitleTextView.text = mCrime.getTitle()
            mDateTextView.text = DateFormat.getDateInstance().format(mCrime.getDate())
            mSolvedImageView.visibility = if (crime.isSolved()) View.VISIBLE else View.GONE
        }
    }

    private class CrimeAdapter(private val mCrimes: List<Crime>) :
        RecyclerView.Adapter<CrimeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = mCrimes[position]
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }

    }

    private fun updateUI() {
        val crimes = CrimeLab.getCrimes()
        if (mAdapter == null) {
            mAdapter = CrimeAdapter(crimes)
            mCrimeRecyclerView!!.adapter = mAdapter
        } else {
            mAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_crime_list, container, false)
        mCrimeRecyclerView = view
            .findViewById(R.id.crime_recycler_view)
        mCrimeRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        updateUI()
        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
