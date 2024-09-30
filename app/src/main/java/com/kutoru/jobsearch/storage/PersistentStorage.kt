package com.kutoru.jobsearch.storage

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class PersistentStorage @Inject constructor(context: Context) {
    private companion object {
        const val LAST_FETCHED_KEY = "LAST_FETCHED_KEY";
    }

    private val storage = context.getSharedPreferences("JobSearch", Context.MODE_PRIVATE)

    var lastFetched: Long?
        get() {
            return if (storage.contains(LAST_FETCHED_KEY)) {
                storage.getLong(LAST_FETCHED_KEY, 0)
            } else {
                null
            }
        }
        set(value) {
            storage.edit {
                if (value != null) {
                    putLong(LAST_FETCHED_KEY, value)
                } else {
                    remove(LAST_FETCHED_KEY)
                }
            }
        }

}