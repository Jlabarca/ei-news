package com.jlabarca.einews.rest;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import com.jlabarca.einews.pojo.GoogleNews;
/**
 * Interface para retrofit adaptado para añadir a la url los parametros start y query
 * @author Julio Labarca
 *
 */
public interface GoogleNewsService {

	@GET("/ajax/services/search/news?v=1.0&hl=es")
	void searchGoogleNews(@Query("start") int id,@Query("q") String query, Callback<GoogleNews> callback);
	}	