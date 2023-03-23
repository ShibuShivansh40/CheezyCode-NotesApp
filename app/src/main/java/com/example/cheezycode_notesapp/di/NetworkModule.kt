package com.example.cheezycode_notesapp.di

import com.example.cheezycode_notesapp.api.UserAPI
import com.example.cheezycode_notesapp.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.apache.commons.lang3.StringEscapeUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

//    private fun removeQuotesAndUnescape(uncleanJson: String): String? {
//        val noQuotes = uncleanJson.replace("^\"|\"$".toRegex(), "")
//        return StringEscapeUtils.unescapeJava(noQuotes)
//    }
    //Adding implementation 'org.apache.commons:commons-lang3:3.6' to the Dependencies
    //This approach failed. Unable to create the file

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {

//        val gson = GsonBuilder()
//            .setLenient()
//            .create()

        //added this to dependency 'com.squareup.retrofit2:converter-scalars:2.3.0'
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    // Error -> com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
    //Applied this gson method,try something else

//    You know that all JSON objects are represented using enclosed curly braces “{}“, which means that it should always begin with open curly brace “{“, if not then gson will throw expected BEGIN_OBJECT
// Website used -> https://www.sneppets.com/java/error-expected-begin_object-but-was-string-at-line-1-column-1/

    @Singleton
    @Provides
    fun providesUserAPI(retrofit: Retrofit) :UserAPI{
        return retrofit.create(UserAPI::class.java)
    }

}