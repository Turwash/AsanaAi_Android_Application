package com.example.android.getfit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.getfit.data.Dao

class SeePastCountsViewModelFactory(private val dataSource: Dao,
                                    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SeePastCountsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SeePastCountsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
