package com.rapidata.ui.bindingmodel

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.rapidata.R
import com.rapidata.box.apimodels.SearchValue

class SearchBindingModel(private val searchValue: SearchValue) : BindingInterface {

    override fun layoutRes(): Int {
        return R.layout.search_item_view
    }

    fun thumbnail(): String? = searchValue.thumbnail
    fun original(): String? = searchValue.url
    fun titleText(): String? = searchValue.title
    fun nameText(): String? = searchValue.name
    fun openFullImage(view: View, originalUrl: String) {

    }
}