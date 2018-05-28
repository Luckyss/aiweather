package ru.androidpirates.aiweather.presentation.base

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager


class ViewPagerAdapter private constructor(
        val builder: ViewPagerAdapter.Builder
) : FragmentStatePagerAdapter(builder.fragmentManager) {

    companion object {
        @JvmStatic fun builder(context: Context, fragmentManager: FragmentManager): Builder {
            return Builder(context, fragmentManager)
        }
    }

    private val fragmentList: ArrayList<Fragment> = builder.fragmentList
    private val fragmentTitlesList: ArrayList<String> = builder.fragmentTitlesList
    private val fragmentIconsList: ArrayList<Int> = builder.fragmentIconsList

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = fragmentTitlesList[position]

    fun setupWith(viewPager: ViewPager, tabLayout: TabLayout, title: (String) -> Unit) {
        viewPager.adapter = this
        tabLayout.setupWithViewPager(viewPager)

        for (i in fragmentList.indices) {
            tabLayout.getTabAt(i)!!
                    .setText(fragmentTitlesList[i])
                    .setIcon(fragmentIconsList[i])
        }
        val listener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                title.invoke(fragmentTitlesList[position])
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }

        viewPager.addOnPageChangeListener(listener)
        listener.onPageSelected(0)
    }

    class Builder internal constructor(
            internal val context: Context,
            internal val fragmentManager: FragmentManager
    ) {
        internal val fragmentList: ArrayList<Fragment> = ArrayList()
        internal val fragmentTitlesList: ArrayList<String> = ArrayList()
        internal val fragmentIconsList: ArrayList<Int> = ArrayList()

        fun addFragment(fragment: Fragment, @StringRes title: Int, @DrawableRes icon: Int): Builder {
            fragmentList.add(fragment)
            fragmentTitlesList.add(context.getString(title))
            fragmentIconsList.add(icon)
            return this
        }

        fun addFragment(fragment: Fragment, title: String, @DrawableRes icon: Int): Builder {
            fragmentList.add(fragment)
            fragmentTitlesList.add(title)
            fragmentIconsList.add(icon)
            return this
        }

        fun build(): ViewPagerAdapter {
            return ViewPagerAdapter(this)
        }
    }
}