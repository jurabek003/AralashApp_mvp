package uz.turgunboyevjurabek.valyutakursimvp.Prezenter

import android.os.Handler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.valyutakursimvp.Cantract.Cantrakt
import uz.turgunboyevjurabek.valyutakursimvp.madels.Valyuta_get
import uz.turgunboyevjurabek.valyutakursimvp.network.ApiClient

class Precenter(private val mainView :Cantrakt.View,private val madel:ApiClient):Cantrakt.Presenter {
    override fun apiSuccessOrFail() {
        mainView.showProgress()
        Handler().postDelayed({
            madel.getApiServis().getValyuta().enqueue(object :Callback<ArrayList<Valyuta_get>>{
                override fun onResponse(
                    call: Call<ArrayList<Valyuta_get>>,
                    response: Response<ArrayList<Valyuta_get>>,
                ) {
                    mainView.hideProgress()
                    mainView.successfulResponse(response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<Valyuta_get>>, t: Throwable) {
                    mainView.errorResponse(t.message.toString())
                }
            })
        },1000)

    }

}