package com.example.nytimesarticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.nytimesarticle.data.models.Content
import com.example.nytimesarticle.databinding.ActivityMainBinding
import com.example.nytimesarticle.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getArticles("1wq3cOOtCvVDe3vjeGNEsXhEgyHixSIP")

        binding.recyclerview.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event) {
                    is MainViewModel.NYArticleEvent.Success -> {
                        adapter.setNYArticleList(event.contents)
                        adapter.setOnClickListener(object: MainAdapter.OnItemClickListener{
                            override fun onItemClick(position: Int) {
                                val data:Content = event.contents.get(position)
                                val builder = AlertDialog.Builder(this@MainActivity,R.style.CustomAlertDialog)
                                    .create()
                                val view = layoutInflater.inflate(R.layout.item_details,null)
                                val title = view.findViewById<TextView>(R.id.title)
                                val byline = view.findViewById<TextView>(R.id.byline)
                                val description = view.findViewById<TextView>(R.id.description)
                                val links = view.findViewById<TextView>(R.id.links)

                                title.text = data.title
                                byline.text = "By " + data.byline + " on " + data.published_date
                                description.text = data.abstract
                                links.text = "Links: " + data.url

                                builder.setView(view)
                                builder.setCanceledOnTouchOutside(true)
                                builder.show()
                            }

                        })
                        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                    }
                    is MainViewModel.NYArticleEvent.Failure -> {
                        Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                    }
                    is MainViewModel.NYArticleEvent.Loading -> {
                        Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }


    }
}