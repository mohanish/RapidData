package com.rapidata.ui.apputils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rapidata.BR
import com.rapidata.ui.bindingmodel.BindingInterface

class GenericAppAdapter<T : BindingInterface>(private var items: List<T>) :
    ListAdapter<T, GenericViewHolder<*>>(TaskDiffCallback()) {

    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = items[position].layoutRes()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        val bind = DataBindingUtil.bind<ViewDataBinding>(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
        return GenericViewHolder(bind)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        val model = items[position]
        holder.getBinding()?.setVariable(BR.model, model)
        holder.getBinding()?.executePendingBindings()
    }

    fun removeAt(position: Int) {
        getListAsArrayList().removeAt(position)
        notifyItemRemoved(position)
    }

    private fun getListAsArrayList(): ArrayList<T> = items as ArrayList<T>
}

class GenericViewHolder<out V : ViewDataBinding?>(private val viewDataBinding: V) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(viewDataBinding?.root!!) {
    fun getBinding(): V {
        return viewDataBinding
    }
}

class TaskDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}