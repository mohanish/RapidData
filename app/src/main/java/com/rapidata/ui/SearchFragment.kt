package com.rapidata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapidata.R
import com.rapidata.box.LoadingState
import com.rapidata.ui.bindingmodel.BindingInterface
import com.rapidata.ui.bindingmodel.SearchBindingModel
import com.rapidata.ui.apputils.GenericAppAdapter
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val searchViewModel by viewModel<SearchViewModel>()
    private lateinit var appAdapter: GenericAppAdapter<BindingInterface>
    private val searchValueList = mutableListOf<SearchBindingModel>()

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
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            adapter = appAdapter
        }
    }
}