package com.hoyaok2.bestbuddy;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface Retrofit_Service {
    //POST로 데이터를 보낼때는 @Field 사용 - @FormUrlEncoded와 함께 써야함
    //이미지 파일을 보낼때는 @Part 사용 - @Multipart와 함께 써야함
    //@FormUrlEncoded 와 @Multipart는 동시에 사용 불가
    //@Field처럼 php에서 $_POST로 받으려면 마치 GET방식의 @QueryMap처럼 @PartMap 사용
    @Multipart
    @POST("/BuddyPlay/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart,
                                  @Part MultipartBody.Part filePart);


    //서버에서 데이터를 json으로 파싱하여 가져오는 추상메소드
    @GET("/BuddyPlay/loadDB.php")
    Call<ArrayList<Play_item>> loadDataFromServer();

    //클릭 된것만 뺴옴
    @GET("/BuddyPlay/loadDBFavor.php")
    Call<ArrayList<Play_item>> loadDataFromPlay();



    //"좋아요" 클릭으로 데이터의 변경을 시키는 작업을 해주는 php를 실행시키기
    @PUT("/BuddyPlay/{fileName}")
    Call<Play_item> updateData(@Path("fileName") String fileName, @Body Play_item item);


    ///////////////////////////////////////////로그인 레트로 서비스

    @Multipart
    @POST("/BuddyMember/insertDB.php")
    Call<String> postDataToServermember(@PartMap Map<String, String> dataPart,
                                  @Part MultipartBody.Part filePart);


    //서버에서 데이터를 json으로 파싱하여 가져오는 추상메소드
    @GET("/BuddyMember/loadDB.php")
    Call<ArrayList<Member_Item>> postDataToServermember();


    ///////////로그인 확인
    @FormUrlEncoded
    @POST("/BuddyMember/loadLogin.php")
    Call<Member_Item> User(@Field("userid")String userid, @Field("userpw") String userpassword);



}
