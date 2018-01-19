package ru.mail.aslanisl.testresultant.api;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mail.aslanisl.testresultant.model.ValueResponse;

/**
 * Created by Ivan on 19.01.2018.
 */

public interface Endpoint {

    @GET("/stocks.json")
    Call<ValueResponse> getValues();
}
