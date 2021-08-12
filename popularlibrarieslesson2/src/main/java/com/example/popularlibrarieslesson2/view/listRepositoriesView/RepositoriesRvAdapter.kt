package com.example.popularlibrarieslesson2.view.listRepositoriesView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrarieslesson2.databinding.ItemRepoBinding


class RepositoriesRVAdapter(private val presenter: IRepositoriesPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolderRepo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderRepo(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolderRepo, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolderRepo(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {

        override var pos = -1

        override fun setRepoName(text: String) = with(vb) {
            nameRepo.text = text
        }
    }
}
