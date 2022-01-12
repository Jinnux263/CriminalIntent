package com.example.criminalintent

import java.util.*

class Crime {
    private var mId: UUID = UUID.randomUUID()
    private lateinit var mTitle: String
    private var mDate: Date = Date()
    private var mSolved = false

    fun getId(): UUID {
        return mId
    }

    fun getTitle(): String {
        return mTitle
    }

    fun setTitle(title: String) {
        mTitle = title
    }

    fun getDate(): Date {
        return mDate
    }

    fun setDate(date: Date) {
        mDate = date
    }

    fun isSolved(): Boolean {
        return mSolved
    }

    fun setSolved(solved: Boolean) {
        mSolved = solved
    }
}