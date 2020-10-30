package com.assignment.moveinsyncassignment.Interface;

import com.assignment.moveinsyncassignment.database.model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {
    String BASE_URL = "https://demo7846676.mockable.io/";

    @GET("productsData")
    Observable<List<Product>> getProducts();

}
