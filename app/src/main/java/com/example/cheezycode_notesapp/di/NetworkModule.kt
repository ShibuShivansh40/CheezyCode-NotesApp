package com.example.cheezycode_notesapp.di

import com.example.cheezycode_notesapp.api.AuthInterceptor
import com.example.cheezycode_notesapp.api.NotesAPI
import com.example.cheezycode_notesapp.api.UserAPI
import com.example.cheezycode_notesapp.utils.Constants.BASE_URL
import com.example.cheezycode_notesapp.utils.Constants.JWT_TOKEN
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.apache.commons.lang3.StringEscapeUtils
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
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
    fun providesRetrofitBuilder(): Retrofit.Builder {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        //added this to dependency 'com.squareup.retrofit2:converter-scalars:2.3.0'
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
    }

//    @Singleton
//    @Provides
//    fun providesRetrofitBuilder(): Retrofit.Builder{
//        val gson = GsonBuilder().setLenient().create()
//        return Retrofit.Builder()
//
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//    }

    // Error -> com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
    //Applied this gson method,try something else

//    You know that all JSON objects are represented using enclosed curly braces “{}“, which means that it should always begin with open curly brace “{“, if not then gson will throw expected BEGIN_OBJECT
// Website used -> https://www.sneppets.com/java/error-expected-begin_object-but-was-string-at-line-1-column-1/

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder) :UserAPI{
        return retrofitBuilder.build().create(UserAPI::class.java)
    }
    //This function doesn't require the usage of Token for implementation purposes

//
////CHAT-GPT METHOD TO MANAGE JWT TOKENS
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val builder = OkHttpClient.Builder()
//        builder.addInterceptor { chain ->
//            val originalRequest = chain.request()
//            val request = originalRequest.newBuilder()
//                .header("Authorization", "Bearer $JWT_TOKEN")
//                .build()
//            chain.proceed(request)
//        }
//        return builder.build()
//    }



    //Here we will be creating a intercepting function for the Retrofit Object
//    This function will require a Token for Implementation
    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesNoteAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient) : NotesAPI{
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(NotesAPI::class.java)
    }
}