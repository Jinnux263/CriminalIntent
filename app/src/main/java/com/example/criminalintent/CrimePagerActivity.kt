package com.example.criminalintent

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import java.util.*


class CrimePagerActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var mCrimes: List<Crime>

    // companion object cho fragment hay activity khác tạo intent
    // có extra cần thiết
    companion object {
        private const val EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id"

        fun newIntent(packageContext: Context?, crimeId: UUID?): Intent {
            val intent = Intent(packageContext, CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        val crimeId = intent
            .getSerializableExtra(EXTRA_CRIME_ID) as UUID

        mViewPager = findViewById<View>(R.id.crime_view_pager) as ViewPager2
        mCrimes = CrimeLab.getCrimes()

        val fragmentManager: FragmentManager = supportFragmentManager

        // Thay vì tạo một nested Adapter class thì tạo một object luôn
        mViewPager.adapter = object : FragmentStateAdapter(fragmentManager, this.lifecycle) {

            override fun getItemCount(): Int {
                return mCrimes.size
            }

            override fun createFragment(position: Int): Fragment {
                val crime = mCrimes[position]
                return CrimeFragment.newInstance(crime.getId())
            }
        }

        for (i in mCrimes.indices) {
            if (mCrimes[i].getId() == crimeId) {
                mViewPager.currentItem = i
                break
            }
        }

    }
}