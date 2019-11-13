package com.vladgrushevoy.homework3_1

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity(), OnItemListener {
    private var ssd = DataHelper().getSsd()
    private val dataAdapter = DataAdapter(ssd, this)
    private var currSsd: SSD? = null
    private lateinit var client: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
        initSsdObject()
        if (savedInstanceState != null) {
            if (savedInstanceState.getSerializable(SSD_KEY) != null) {
                currSsd = savedInstanceState.getSerializable(SSD_KEY) as SSD
                switchFragment(currSsd!!)
            }
        }

    }

    private fun initFragments() {
        val listFragment = initListFragmentAdapter()
        when {
            ORIENTATION_PORTRAIT == resources.configuration.orientation -> {
                clearBackStack()
                supportFragmentManager.beginTransaction()
                    .add(R.id.list_container, listFragment)
                    .commit()
            }
            ORIENTATION_LANDSCAPE == resources.configuration.orientation -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.info_container, InfoFragment())
                    .add(R.id.list_container, listFragment)
                    .commit()
            }
        }
    }

    private fun initSsdObject() {
        supportFragmentManager.addOnBackStackChangedListener {
            if (ORIENTATION_PORTRAIT == resources.configuration.orientation
                && supportFragmentManager.backStackEntryCount == 0
            ) {
                currSsd = null
            }
        }
    }

    private fun switchFragment(cpu: SSD) {
        when {
            ORIENTATION_LANDSCAPE == resources.configuration.orientation && isItemSelected() -> {
                clearBackStack()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.info_container, InfoFragment.newInstance(cpu))
                    .commit()
            }
            ORIENTATION_PORTRAIT == resources.configuration.orientation -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.list_container, InfoFragment.newInstance(cpu))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onClickItem(position: Int) {
        currSsd = ssd[position]
        switchFragment(currSsd!!)
    }

    private fun isItemSelected() = currSsd != null

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
    }

    private fun initListFragmentAdapter() = ListFragment().apply {
        adapter = dataAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (currSsd != null) {
            outState.putSerializable(SSD_KEY, currSsd)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requestPermission()
        if (item.itemId == R.id.location_btn) {
            if (supportFragmentManager.backStackEntryCount == 0) {
                showCoordinates()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showCoordinates() {
        var locationFragment: LocationFragment
        client = LocationServices.getFusedLocationProviderClient(this)
        if (isPermitted()) {
            client.lastLocation.addOnCompleteListener { task ->
                run {
                    if (task.isSuccessful && task.result != null) {
                        task.result.apply {
                            locationNotification(longitude, latitude)
                            locationFragment =
                                LocationFragment.newInstance(DataCoordinate(longitude, latitude))
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.list_container, locationFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }
            }
        }
    }

    private fun isPermitted() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE
        )
    }

    private fun locationNotification(longitude: Double, latitude: Double) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Location notification",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableVibration(true)
                notificationManager.createNotificationChannel(this)
            }
        }

        NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID).apply {
            this.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Location")
                .setContentText("Longitude: $longitude; Latitude: $latitude")
            notificationManager.notify(1, this.build())
        }
    }

    companion object {
        const val SSD_KEY = "position"
        const val REQUEST_CODE = 100
        const val COORDINATE_KEY = "coordinate"
        const val NOTIFICATION_CHANNEL_ID = "notification_1"
    }
}
