package com.example.yun.yunstagram.data

import com.androidhuman.rxfirebase2.firestore.model.Value
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import io.reactivex.Completable
import io.reactivex.Single

interface DataSource {
    fun login(email: String, password: String): Single<FirebaseUser>

    fun signUp(email: String, password: String): Single<FirebaseUser>

    fun updateUser(user: User) : Completable

    fun getUser(uid: String) : Single<Value<DocumentSnapshot>>

    fun getCurrentUid() : String?

}