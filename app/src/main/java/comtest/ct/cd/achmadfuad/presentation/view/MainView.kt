package comtest.ct.cd.achmadfuad.presentation.view

import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import comtest.ct.cd.achmadfuad.presentation.adapter.ItemAdapter
import comtest.ct.cd.achmadfuad.presentation.framework.view.LifecycleView
import comtest.ct.cd.achmadfuad.presentation.widget.LoadingView

interface MainView : LifecycleView {
    var itemAdapter: ItemAdapter
    var itemLayoutManager: LinearLayoutManager
    var retryListener: LoadingView.OnRetryListener
    var bTextWatcher: TextWatcher
}