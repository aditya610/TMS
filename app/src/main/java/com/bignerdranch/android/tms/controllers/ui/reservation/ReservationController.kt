package com.bignerdranch.android.tms.controllers.ui.reservation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.tablereservation.TableController
import com.bignerdranch.android.tms.databinding.ActivityReservationFormBinding
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "ReservationController"

@AndroidEntryPoint
class ReservationController : AppCompatActivity() {

    private val viewModel: ReservationViewModel by viewModels()
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityReservationFormBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_reservation_form)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.intialize()
        binding.addReservation.setOnClickListener({
            if (binding.reservationMobileNo.text.toString().isEmpty()) {
                binding.reservationMobileNo.error = getString(R.string.required)
            } else if (binding.reservationName.text.toString().isEmpty()) {
                binding.reservationName.error = getString(R.string.required)
            } else if (binding.reservationGroup.text.toString().isEmpty()) {
                binding.reservationGroup.error = getString(R.string.required)
            } else {
                viewModel.save()
                dataStore = createDataStore(name = getString(R.string.dataStoreName))
                val dataStoreKey = preferencesKey<String>(getString(R.string.dataStoreKey))
                lifecycleScope.launch {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        launch {
                            dataStore.edit { settings ->
                                settings[dataStoreKey] = binding.reservationMobileNo.text.toString()
                            }
                        }
                    }
                }
                val intent = Intent(this, TableController::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        viewModel.intialize()
        super.onResume()
    }

}

//reservationMobileNo.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Log.d(TAG,"")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (!s.isNullOrBlank()) {
//                    //userDetailViewMode.setUserMobileNo(s.toString().toLong())
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                Log.d(TAG,"")
//            }
//
//        })
