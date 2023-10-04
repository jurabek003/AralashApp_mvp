package uz.turgunboyevjurabek.valyutakursimvp.Cantract

import retrofit2.Call
import retrofit2.http.GET
import uz.turgunboyevjurabek.valyutakursimvp.madels.Valyuta_get

interface Cantrakt {

    interface View{
        fun showProgress()
        fun hideProgress()
        fun successfulResponse(list: ArrayList<Valyuta_get>)
        fun errorResponse(tht:String)

    }

    interface Model{
        interface apiServis{
            @GET("json/")
            fun getValyuta():Call<ArrayList<Valyuta_get>>
        }
    }

    interface Presenter{
        fun apiSuccessOrFail()
    }
}