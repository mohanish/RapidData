package com.rapidata.box.bindingmodel

import com.rapidata.R
import com.rapidata.box.apimodels.SearchData

class SearchBindingModel(val searchData: SearchData) : BindingInterface {

    override fun layoutRes(): Int {
        return R.layout.search_item_view
    }
}