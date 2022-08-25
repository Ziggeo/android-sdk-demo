package com.ziggeo.androidsdk.demo.ui.settings.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.ziggeo.androidsdk.demo.R
import kotlinx.android.synthetic.main.item_settings_switch.view.*

class SettingsSwitchItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    fun setName(name: String){
        tv_setting_name.text = name
    }

    fun setValue(value: Boolean){
        sc_setting_value.isChecked = value
    }

    fun setOnChangeListener(onChangeListener: CompoundButton.OnCheckedChangeListener){
        sc_setting_value.setOnCheckedChangeListener(onChangeListener)
    }

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_settings_switch, this, true)
    }

}


