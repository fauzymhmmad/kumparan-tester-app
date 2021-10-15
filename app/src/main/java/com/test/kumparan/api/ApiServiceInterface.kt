package com.test.kumparan.api

import com.test.kumparan.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceInterface {
    @GET("posts?_limit=10")
    fun getPosts(): Observable<List<PostModel>>

    @GET("users/{id}")
    fun getUser(@Path("id") id : Int) : Observable<UserModel>

    @GET("posts/{id}")
    fun getPostDetail(@Path("id") id: Int): Observable<PostModel>

    @GET("posts/{id}/comments")
    fun getComment(@Path("id") id: Int): Observable<List<CommentModel>>

    @GET("users/{id}/albums")
    fun getUserAlbums(@Path("id") id: Int): Observable<List<AlbumModel>>

    @GET("albums/{id}/photos?_limit=10")
    fun getAlbumPhotos(@Path("id") id: Int): Observable<List<PhotoModel>>
}