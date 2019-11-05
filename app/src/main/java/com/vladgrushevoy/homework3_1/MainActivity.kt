package com.vladgrushevoy.homework3_1

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE


class MainActivity : AppCompatActivity(), OnItemListener {
    private var ssd = DataHelper().getSsd()
    private val dataAdapter = DataAdapter(ssd, this)
    private var currSsd: SSD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
        initCpuObject()
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

    private fun initCpuObject() {
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

    companion object {
        const val SSD_KEY = "position"
    }
}
