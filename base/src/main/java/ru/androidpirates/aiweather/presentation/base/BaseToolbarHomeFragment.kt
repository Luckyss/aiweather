package ru.androidpirates.aiweather.presentation.base

import android.os.Bundle
import android.view.MenuItem
import ru.androidpirates.aiweather.presentation.mvp.BaseContract

/**
 * Use this fragment if you need a fragment with Home button
 */
abstract class BaseToolbarHomeFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> :
        BaseInjectingFragment<V, P>(), BaseContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as BaseActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onHomePressed()
        }
        return super.onOptionsItemSelected(item)
    }

    protected open fun onHomePressed() {
        activity?.onBackPressed()
    }
}