package com.onurseref.mackolik.ui.news.viewmodel

import androidx.lifecycle.MutableLiveData
import com.onurseref.mackolik.base.ui.BaseViewModel
import com.onurseref.mackolik.common.models.NewsResponse
import com.onurseref.mackolik.ui.news.domain.NewsUseCase
import com.onurseref.mackolik.ui.news.viewstate.NewsViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase) :
    BaseViewModel() {

    val newsResponse = MutableLiveData<NewsResponse?>()
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun getNews() {
        newsUseCase.getNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is NewsViewState.Loading -> {
                        isLoadingLiveData.value = true
                    }
                    is NewsViewState.Success -> {
                        isLoadingLiveData.value = false
                        newsResponse.value = it.newsResponse
                    }
                    else -> {
                        isLoadingLiveData.value = false
                        errorMessageLiveData.value = (it as NewsViewState.Error).throwable
                    }
                }
            }
            .also { disposable += it }
    }
}