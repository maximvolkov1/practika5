package ru.btpit.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.nmedia.R
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                likeCount.text = formatCount(post.likes)
                shareCount.text = formatCount(post.shares)
                viewsCount.text = formatCount(post.viewes)
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                binding.like.setOnClickListener {
                    viewModel.like()
                }
                    binding.share.setOnClickListener {
                        viewModel.share()
                    }
                }
            }
        }
    }
    fun formatCount(num: Int): String {
        return when {
            num >= 1_000_000 -> {
                val millionPart = num / 1_000_000
                val remainder = num % 1_000_000
                if (remainder == 0) {
                    "$millionPart"
                }
                    else { "$millionPart.${(remainder / 1_00000)}M"
                }
            }
            num   >= 999 -> {
                val numString =
                    if (num % 1_000 == 0) "${num / 1_000}K" else "${num / 1_000}.${(num % 1_000) / 100}K"
                numString
            }
            num >= 10000 -> "${num / 10000}K"
                else -> num.toString()
        }
    }
