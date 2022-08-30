package com.mirror.lotto_android.retrofit;

import com.mirror.lotto_android.classes.Lotto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILottoServiceAPI {
    /*
    Retrofit에서는 HTTP Request를 처리하기 위해 GET, POST, PUT, PATCH, DELETE와 같은 어노테이션을 지원한다.
    GET안의 파마미터는 Base Url 뒤에 오는 주소를 입력해준다. 그러면 Base Url + "파라미터 값" 으로 요청 url이 생성된다.
    url 주소에 query 값이 있는 경우 메서드 매개변수에 @Query("") 어노테이션과 값을 받을 변수를 받으면 된다.
     */
    @GET("common.do?method=getLottoNumber")
    Call<LottoDataResponse> getLotto(@Query("drwNo") String drwNo);
}
