package com.ziggeo.androidsdk.demo.ui.sdks

import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.feature.FeatureModel
import com.ziggeo.androidsdk.demo.model.data.feature.SdkModel
import com.ziggeo.androidsdk.demo.presentation.availablesdks.AvailableSDKsPresenter
import com.ziggeo.androidsdk.demo.presentation.availablesdks.AvailableSDKsView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_available_sdks.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class AvailableSDKsFragment : BaseToolbarFragment<AvailableSDKsView, AvailableSDKsPresenter>(),
    AvailableSDKsView {

    override val layoutRes = R.layout.fragment_available_sdks

    @InjectPresenter
    lateinit var presenter: AvailableSDKsPresenter

    @ProvidePresenter
    override fun providePresenter(): AvailableSDKsPresenter =
        scope.getInstance(AvailableSDKsPresenter::class.java)

    override fun getHeaderTextRes() = R.string.sdks_header

    override fun showSdks(clientsList: List<FeatureModel>) {
        val adapter = AvailableSDKsAdapter(clientsList)
        adapter.onItemClickListener = object : AvailableSDKsAdapter.ItemClickListener {
            override fun onItemClick(model: SdkModel) {
                presenter.onClientItemClicked(model)
            }
        }
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    TYPE_CATEGORY -> 2
                    TYPE_ITEM -> 1
                    else -> 1
                }
            }
        }
        rv_sdks.layoutManager = layoutManager
        rv_sdks.adapter = adapter
    }

}