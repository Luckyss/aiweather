package ru.androidpiraters.aiweather.presentation


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.androidpiraters.aiweather.data.model.weather.WeatherInfo
import ru.androidpirates.aiweather.presentation.recyclerview.DisplayableItem
import ru.androidpirates.aiweather.presentation.recyclerview.RecyclerViewAdapter
import ru.androidpirates.aiweather.presentation.recyclerview.ViewHolderBinder
import ru.androidpirates.aiweather.presentation.recyclerview.ViewHolderFactory
import ru.androidpirates.aiweather.weather.R
import javax.inject.Inject

class WeatherViewHolder private constructor(
        itemView: View,
        itemClickListener: RecyclerViewAdapter.ItemClickListener
) : RecyclerViewAdapter.ViewHolder(itemView, itemClickListener) {

    private fun bind(viewEntity: WeatherInfo) {

//        val geoText = viewEntity.geoCoords
//
//        itemView.textHeader.text = viewEntity.taskName
//        itemView.textPeriod.text = viewEntity.deliveryTimeInterval
//        itemView.textAddress.text = viewEntity.clientAddress
//        itemView.textOrdersQuantity.text = viewEntity.deliveryQuantity.toString()
//        itemView.textGeo.text = geoText.

    }

    class Factory @Inject constructor() : ViewHolderFactory {

        override fun createViewHolder(
                parent: ViewGroup,
                itemClickListener: RecyclerViewAdapter.ItemClickListener
        ): RecyclerViewAdapter.ViewHolder {
            return WeatherViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather, parent, false),
                    itemClickListener)
        }
    }

    class Binder @Inject constructor() : ViewHolderBinder {

        override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<*>) {
            val castedViewHolder = viewHolder as WeatherViewHolder
            val viewEntity = item.model as WeatherInfo
            castedViewHolder.bind(viewEntity)
        }
    }
}