package comtest.ct.cd.achmadfuad.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comtest.ct.cd.achmadfuad.BaseApplication
import comtest.ct.cd.achmadfuad.R
import comtest.ct.cd.achmadfuad.databinding.ActivityMainBinding
import comtest.ct.cd.achmadfuad.domain.entities.ResultState
import comtest.ct.cd.achmadfuad.domain.entities.SearchUsersResult
import comtest.ct.cd.achmadfuad.domain.entities.UserItemResult
import comtest.ct.cd.achmadfuad.presentation.adapter.ItemAdapter
import comtest.ct.cd.achmadfuad.presentation.framework.base.BaseDaggerActivity
import comtest.ct.cd.achmadfuad.presentation.framework.observe
import comtest.ct.cd.achmadfuad.presentation.framework.owner.ViewDataBindingOwner
import comtest.ct.cd.achmadfuad.presentation.framework.owner.ViewModelOwner
import comtest.ct.cd.achmadfuad.presentation.framework.showToastErrorMessage
import comtest.ct.cd.achmadfuad.presentation.view.MainView
import comtest.ct.cd.achmadfuad.presentation.viewmodel.MainViewModel
import comtest.ct.cd.achmadfuad.presentation.widget.LoadingView
import javax.inject.Inject

class MainActivity : BaseDaggerActivity(),
    MainView,
    ViewModelOwner<MainViewModel>,
    ViewDataBindingOwner<ActivityMainBinding> {

    override lateinit var binding: ActivityMainBinding
    @Inject
    override lateinit var viewModel: MainViewModel

    override lateinit var itemAdapter: ItemAdapter

    private lateinit var itemUsers:MutableList<UserItemResult>

    private var isMore = true

    override fun injectComponent() {
        (application as BaseApplication)
            .appComponent.mainComponent().create().inject(this)
    }

    override var itemLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbar.let {
            setSupportActionBar(it)
            supportActionBar?.title = getString(R.string.app_name)
        }

        itemUsers = mutableListOf()
        itemAdapter = ItemAdapter(itemUsers)

        observe(viewModel.stateResultUser,::manageStateResponseMyTruck)

        binding.loadingView.showEmpty(
            getString(R.string.title_initial_state),
            getString(R.string.subtitle_initial_state),
            false
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let {
            when (it) {
                R.id.descending_menu ->
                    viewModel.bSortType.value = "desc"

                R.id.ascending_menu ->
                    viewModel.bSortType.value = "asc"

            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main
    }

    override var retryListener = object : LoadingView.OnRetryListener {
        override fun onRetry() {
            viewModel.fetchUsers(viewModel.bTextSearch.value!!, viewModel.bSortType.value!!, viewModel.currentPage)
        }
    }

    val handler = Handler()

    override var bTextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            handler.removeCallbacksAndMessages(null)
        }

        override fun afterTextChanged(s: Editable?) {
            if (!s.isNullOrEmpty()) {
                handler.postDelayed({
                    itemUsers.clear()
                    itemAdapter.notifyDataSetChanged()
                    viewModel.currentPage = 1
                    viewModel.fetchUsers(
                        viewModel.bTextSearch.value!!,
                        viewModel.bSortType.value!!,
                        viewModel.currentPage
                    )
                }, 500)

            }
        }
    }

    private fun manageStateResponseMyTruck(state: ResultState){
        when(state){
            is ResultState.Success<*> ->{
                val result = state.data as SearchUsersResult
                itemUsers.addAll(result.data!!)
                itemAdapter.notifyDataSetChanged()
                isMore = itemUsers.size < result.count


                binding.rvUserList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            val visibleItemCount = (recyclerView.layoutManager as LinearLayoutManager).childCount
                            val totalItemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
                            val pastVisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                            if (isMore) {
                                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                    isMore = false
                                    viewModel.currentPage = (viewModel.currentPage + 1)
                                    viewModel.fetchUsers(viewModel.bTextSearch.value!!, viewModel.bSortType.value!!, viewModel.currentPage)

                                }
                            }
                        }
                    }
                })

            }
            is ResultState.Loading ->{
                binding.loadingView.showLoading()
                viewModel.showLoading()
            }
            is ResultState.NotFound ->{
                viewModel.showLoading()
                binding.loadingView.showEmpty(
                    getString(R.string.title_empty_state),
                    getString(R.string.subtitle_empty_state),
                    false
                )
            }

            is ResultState.HideLoading->{
                viewModel.hideLoading()
            }
            else->{
                showToastErrorMessage(state, this)
            }

        }
    }
}

