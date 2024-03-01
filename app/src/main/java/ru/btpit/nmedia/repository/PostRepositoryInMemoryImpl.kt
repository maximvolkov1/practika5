package ru.btpit.nmedia.repository

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.activity.formatCount
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Интересно знать!",
        content = "Спросите у любого, какое животное на нашей планете носит гордое звание царь зверей, и каждый из них ответит, что это лев. Именно эта большая кошка из рода пантер занимает столь высокий пост. Но почему именно лев – царь зверей? Лев – хищный представитель семейства Кошачьих, один из самых умных, быстрых и крупных животных. В этой статье мы расскажем почему лев – царь зверей.",
        published = "20 января в 12:12",
        likedByMe = false,
        likes = 101,
        sharedByMe = false,
        shares = 33,
        viewedByMe = false,
        viewes = 400 + 1
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like() {
        val newLikes = if (post.likedByMe) post.likes - 1 else post.likes + 1
        post = post.copy(likedByMe = !post.likedByMe, likes = newLikes)
        data.value = post
    }
    override fun share() {
        val newShares = post.shares + 1
        post = post.copy(sharedByMe = !post.sharedByMe, shares = newShares)
        data.value = post
    }
}