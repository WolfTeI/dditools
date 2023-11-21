package ar.com.wolftei.ddi.data.Firebase

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val news: FirebaseFirestore
) {
    fun getNews() = news.collection("news").get()
}