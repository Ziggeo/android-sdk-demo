package com.ziggeo.androidsdk.demo.ui.log

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.ziggeo.androidsdk.demo.R
import com.ziggeo.androidsdk.demo.model.data.feature.LogModel
import com.ziggeo.androidsdk.demo.presentation.log.LogPresenter
import com.ziggeo.androidsdk.demo.presentation.log.LogView
import com.ziggeo.androidsdk.demo.ui.global.BaseToolbarFragment
import kotlinx.android.synthetic.main.fragment_log.*


/**
 * Created by Alexander Bedulin on 25-Sep-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
class LogFragment : BaseToolbarFragment<LogView, LogPresenter>(), LogView {
    override val layoutRes = R.layout.fragment_log

    @InjectPresenter
    lateinit var presenter: LogPresenter

    @ProvidePresenter
    override fun providePresenter(): LogPresenter =
        scope.getInstance(LogPresenter::class.java)

    override fun getHeaderTextRes() = R.string.log_header

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_send_report.setOnClickListener {
            presenter.onBtnSendReportClicked()
        }
    }

    override fun showLogs(logModels: List<LogModel>) {
        tv_empty_list.visibility = View.INVISIBLE
        rv_logs.visibility = View.VISIBLE

        val adapter = LogAdapter(logModels.asReversed())
        rv_logs.layoutManager = LinearLayoutManager(context)
        rv_logs.adapter = adapter
        rv_logs.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun showNoLogsMessage() {
        tv_empty_list.visibility = View.VISIBLE
        rv_logs.visibility = View.INVISIBLE
    }
}