package com.example.materialdesignapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesignapp.BuildConfig
import com.example.materialdesignapp.Repo.PictureOfTheDayDTO
import com.example.materialdesignapp.Repo.PictureOfTheDayRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(


    private val liveData: MutableLiveData<PictureOfTheDayAppState> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {
    fun getData(): LiveData<PictureOfTheDayAppState> {
        return liveData
    }

    fun sendRequest(day: String) {
        liveData.postValue(PictureOfTheDayAppState.Loading(null))



        pictureOfTheDayRetrofitImpl.getRetrofitImpl()
            .getPictureOfTheDay(day, BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<PictureOfTheDayDTO> {
                    override fun onResponse(
                        call: Call<PictureOfTheDayDTO>,
                        response: Response<PictureOfTheDayDTO>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            response.body()?.let {
                                liveData.postValue(PictureOfTheDayAppState.Success(it))
                            }

                        } else {

                            liveData.postValue(
                                PictureOfTheDayAppState.Error(response.message() + "( ${response.code()} )")
                            )
                        }

                    }

                    override fun onFailure(call: Call<PictureOfTheDayDTO>, t: Throwable) {

                    }
                }
            )
    }
}