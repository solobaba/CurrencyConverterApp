package com.solobaba.currencyconverter.utils

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solobaba.currencyconverter.domain.response.CountryList
import java.io.IOException
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)
object Extensions {
    fun getFlagImageBitMap(base64String: String): ImageBitmap {
        var encodedString = base64String
        //remove the image description part of the string
        if(encodedString.contains(",")) {
            encodedString = encodedString.split(",")[1]
        }
        val decodedByteArray = Base64.getDecoder()
            .decode(encodedString.toByteArray(charset("UTF-8")))
        val bitMap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
        return bitMap.asImageBitmap()
    }

    fun loadMapOfCurrencySymbolToFlag(assets: AssetManager): MutableMap<String, String> {
        //create an empty map
        val mapOfCurrencySymbolToFlag = mutableMapOf<String, String>()
        //load countries from asset, store their currency code and flag as a key-value pair in the
        //map
        loadCountriesFromAsset(assets)?.forEach {
            mapOfCurrencySymbolToFlag[it.currency.code] = it.flag
        }
        return mapOfCurrencySymbolToFlag
    }

    //Read json file from asset and return list of countries with data of their flag and currency
    //symbol
    private fun loadCountriesFromAsset(assets: AssetManager): List<CountryList>? {

        val listOfCountries: List<CountryList>?

        try {
            //use InputStream to open the file and stream the data into it.
            val stream = assets.open("countries.json")
            //create a variable to store the size of the file.
            val size = stream.available()
            //create a buffer of the size of the file.
            val buffer = ByteArray(size)
            //read the inputStream file into the buffer.
            stream.read(buffer)
            //close the inputStream file.
            stream.close()
            //convert the buffer file to the format in which you need your data.
            val stringJson = String(buffer, charset("UTF-8"))
            val gson = Gson()
            val customListType = object : TypeToken<List<CountryList>>() {}.type //custom list type
            listOfCountries = gson.fromJson(stringJson, customListType)

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return listOfCountries
    }
}