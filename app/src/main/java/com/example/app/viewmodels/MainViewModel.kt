package com.example.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.models.Post
import com.example.app.models.PostComment
import com.example.app.other.Resource
import com.example.app.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bleker Cordova
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    private val _res = MutableLiveData<Resource<ArrayList<Post>>>()
    private val _resComments = MutableLiveData<Resource<ArrayList<PostComment>>>()

    val res : LiveData<Resource<ArrayList<Post>>>
        get() = _res

    val resComments : LiveData<Resource<ArrayList<PostComment>>>
        get() = _resComments

    init {
        getPosts()
    }

    private fun getPosts()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getPosts().let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
    fun getPostComments(id: Int)  = viewModelScope.launch {
        _resComments.postValue(Resource.loading(null))
        mainRepository.getPostComments(id).let {
            if (it.isSuccessful){
                _resComments.postValue(Resource.success(it.body()))
            }else{
                _resComments.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}