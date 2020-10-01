package com.dongldh.carrot.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import com.dongldh.carrot.R
import com.dongldh.carrot.util.App
import com.dongldh.carrot.util.NO_REGION_DATA
import com.dongldh.carrot.viewmodel.SetMyRegionViewModel
import kotlinx.android.synthetic.main.activity_set_my_region.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val REGION_EMPTY = 0
const val REGION_SELECTED = 1
const val REGION_NOT_SELECTED = 2

class SetMyRegionActivity : AppCompatActivity() {
    val setMyRegionViewModel: SetMyRegionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_my_region)

        setMyRegionViewModel.regionList.observe(this) { regions ->
            setTextRegionList(regions)
        }

        setMyRegionViewModel.regionSelectedString.observe(this) {
            setCardStyle()
        }

        action_back.setOnClickListener { finish() }
    }

    private fun getRegionListSize(): Int {
        setMyRegionViewModel.regionList.value?.let {
            return it.size
        } ?: return NO_REGION_DATA.toInt()
    }

    private fun setCardStyle() {
        when(setMyRegionViewModel.getSelectedPosition()) {
            0 -> {
                when(getRegionListSize()) {
                    1 -> setAllRegionCardStyle(REGION_SELECTED, REGION_EMPTY)
                    2 -> setAllRegionCardStyle(REGION_SELECTED, REGION_NOT_SELECTED)
                    else -> throw Exception()
                }
            }
            1 -> setAllRegionCardStyle(REGION_NOT_SELECTED, REGION_SELECTED)
            else -> throw Exception()
        }
    }

    private fun setAllRegionCardStyle(typeFirst: Int, typeSecond: Int) {
        setEachRegionCardStyle(layout_first_region, text_first_region, action_delete_first_region, typeFirst)
        setEachRegionCardStyle(layout_second_region, text_second_region, action_delete_second_region, typeSecond)
    }

    private fun setEachRegionCardStyle(layout: ConstraintLayout, text: TextView, delImage: ImageView, type: Int) {
        setBackground(layout, type)
        setTextVisibilityAndColor(text, type)
        setImageVisibilityAndTint(delImage, type)

        if(type == REGION_EMPTY) image_add.visibility = View.VISIBLE else image_add.visibility = View.GONE
    }

    private fun setBackground(view: ConstraintLayout, type: Int) {
        when(type) {
            REGION_EMPTY -> { view.setBackgroundResource(R.drawable.decorate_border_colorseparatorlight_solid_colordeepsurface_round) }
            REGION_SELECTED -> { view.setBackgroundResource(R.drawable.decorate_border_colorprimary_solid_colorprimary_round) }
            REGION_NOT_SELECTED -> { view.setBackgroundResource(R.drawable.decorate_border_colorseparatorlight_solid_colorsurface_round) }
        }
    }

    private fun setTextRegionList(regions: List<Pair<Long, String>>) {
        when(regions.size) {
            1 -> { text_first_region.text = regions[0].second }
            2 -> {
                text_first_region.text = regions[0].second
                text_second_region.text = regions[1].second
            }
            else -> throw Exception()
        }
    }

    private fun setTextVisibilityAndColor(view: TextView, type: Int) {
        when(type) {
            REGION_EMPTY -> { view.visibility = View.GONE }
            REGION_SELECTED -> {
                view.visibility = View.VISIBLE
                view.setTextColor(ContextCompat.getColor(App.applicationContext(), R.color.colorWhite))}
            REGION_NOT_SELECTED -> {
                View.VISIBLE
                view.setTextColor(ContextCompat.getColor(App.applicationContext(), R.color.colorDefaultText))
            }
        }
    }

    private fun setImageVisibilityAndTint(view: ImageView, type: Int) {
        when(type) {
            REGION_EMPTY -> { view.visibility = View.GONE }
            REGION_SELECTED -> {
                view.visibility = View.VISIBLE
                view.clearColorFilter()
            }
            REGION_NOT_SELECTED -> {
                view.visibility = View.VISIBLE
                view.setColorFilter(R.color.colorGray, PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
}

