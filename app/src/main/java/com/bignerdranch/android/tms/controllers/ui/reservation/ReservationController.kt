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
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.controllers.ui.dashboard.DashboardController
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
    private lateinit var binding: ActivityReservationFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left)

        val extra = intent.getStringExtra(SeedData.reservationKey)

        if (extra != null) {
            viewModel.loadIntialData(extra.toLong())
        }
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_reservation_form)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.waitReservation.setOnClickListener({
            addReservations(SeedData.DashBoardActivity)
        })
        binding.addReservation.setOnClickListener({
            addReservations(SeedData.TableActivity)
        })
    }

    fun addReservations(activity: String) {
        if (binding.reservationMobileNo.text.toString()
                .isEmpty() || binding.reservationMobileNo.length() != 10
        ) {
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
            if (activity == SeedData.TableActivity) {
                val intent = Intent(this, TableController::class.java)
                startActivity(intent)
                this.overridePendingTransition(
                    R.transition.slide_in_right,
                    R.transition.slide_out_left
                )
            } else {
                val intent = Intent(this, DashboardController::class.java)
                startActivity(intent)
                this.overridePendingTransition(
                    R.transition.slide_in_left,
                    R.transition.slide_out_right
                )
            }
        }
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
