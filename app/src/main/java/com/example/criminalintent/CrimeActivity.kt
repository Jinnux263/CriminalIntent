package com.example.criminalintent

import android.content.Context
import androidx.fragment.app.Fragment
import android.content.Intent
import java.util.*


class CrimeActivity : SingleFragmentActivity() {

    // Companion object để cung cấp cho fragment hay activity khác phương thức tạo một intent
    // cung cấp extra cần thiết
    companion object {
        // key là private vì chỉ class ở trong activity cần để lấy value tương ứng
        private const val EXTRA_CRIME_ID = "com.example.criminalintent.crime_id"

        fun newIntent(packageContext: Context?, crimeId: UUID?): Intent {
            val intent = Intent(packageContext, CrimeActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }

    override fun createFragment(): Fragment {
        val crimeId = intent
            .getSerializableExtra(EXTRA_CRIME_ID) as UUID?
        return CrimeFragment.newInstance(crimeId)

    }
}