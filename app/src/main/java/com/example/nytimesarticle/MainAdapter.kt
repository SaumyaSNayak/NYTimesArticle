package com.example.nytimesarticle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesarticle.data.models.Content
import com.example.nytimesarticle.databinding.AdapterArticlesBinding

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    private lateinit var contents: ArrayList<Content>
    fun setNYArticleList(contents: ArrayList<Content>) {
        this.contents = contents
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterArticlesBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val content = contents[position]
        holder.binding.title.text = content.title
        holder.binding.byline.text = content.byline
        holder.binding.publishedDate.text = content.published_date
    }
    override fun getItemCount(): Int {
        return contents.size
    }
}
class MainViewHolder(val binding: AdapterArticlesBinding) : RecyclerView.ViewHolder(binding.root) {
}