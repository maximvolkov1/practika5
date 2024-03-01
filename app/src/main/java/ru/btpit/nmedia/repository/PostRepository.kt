package ru.btpit.nmedia.repository

import androidx.lifecycle.LiveData
import ru.btpit.nmedia.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}