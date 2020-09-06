package com.rapidata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rapidata.R
import com.rapidata.box.LoadingState
import com.rapidata.ui.apputils.GenericAppAdapter
import com.rapidata.ui.bindingmodel.BindingInterface
import com.rapidata.ui.bindingmodel.SearchBindingModel
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val searchViewModel by viewModel<SearchViewModel>()
    private lateinit var appAdapter: GenericAppAdapter<BindingInterface>
    private val searchValueList = mutableListOf<SearchBindingModel>()
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var lastVisibleItemPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        searchViewModel.searchResultData.observe(viewLifecycleOwner, Observer {
            it.forEach { searchResult ->
                val searchBindingModel = SearchBindingModel(searchResult)
                searchValueList.add(searchBindingModel)
                appAdapter.notifyDataSetChanged()
            }
        })

        searchViewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> Toast.makeText(context, it.msg, Toast.LENGTH_LONG)
                    .show()
                LoadingState.Status.RUNNING -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG)
                    .show()
                LoadingState.Status.SUCCESS -> {
                    appAdapter.notifyDataSetChanged()
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun initView() {
        rvSearch.apply {
            appAdapter = GenericAppAdapter<BindingInterface>(searchValueList)
            linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayoutManager
            hasFixedSize()
            adapter = appAdapter
            setRecyclerViewScrollListener()
        }

        btnSearch.setOnClickListener {
            searchViewModel.fetchSearchData(0, edit_query.text.toString())
        }
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(rvSearch, newState)
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    searchViewModel.fetchSearchData(1, edit_query.text.toString())
                }
            }
        }
        rvSearch.addOnScrollListener(scrollListener)
    }
}