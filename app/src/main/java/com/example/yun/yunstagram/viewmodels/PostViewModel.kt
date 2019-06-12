package com.example.yun.yunstagram.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yun.yunstagram.data.DataRepository
import com.example.yun.yunstagram.data.Post
import com.example.yun.yunstagram.data.State
import com.example.yun.yunstagram.utilities.getLocalDateTime
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: DataRepository) : BaseViewModel() {

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post>
        get() = _post

    private val _updateResult = MutableLiveData<State>()
    val updateResult: LiveData<State>
        get() = _updateResult

    fun updatePost(post: Post) {
        disposables += repository.updatePost(post)
            .compose(loadingCompletableTransformer())
            .subscribe({
                _updateResult.value = State(isSuccess = true)
            }) {
                _updateResult.value = State(errorMessages = it.message)
            }
    }

    fun makePost(messages: String): Post {
        val uid = repository.getCurrentUid()
        val time = getLocalDateTime()
        val id = uid + "_" + time
        return Post(
            id = id,
            created_time = time,
            author = uid,
            message = messages
        )
    }

}
