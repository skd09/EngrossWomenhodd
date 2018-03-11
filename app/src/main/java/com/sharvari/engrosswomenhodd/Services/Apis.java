package com.sharvari.engrosswomenhodd.Services;

import com.sharvari.engrosswomenhodd.Requests.AcceptTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.ChangePassword;
import com.sharvari.engrosswomenhodd.Requests.FollowRequest;
import com.sharvari.engrosswomenhodd.Requests.GetTaskHelpRequest;
import com.sharvari.engrosswomenhodd.Requests.GetTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.InsertAddressRequest;
import com.sharvari.engrosswomenhodd.Requests.InsertTaskHelpRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateAddressRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateMyTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateUserDetailsRequest;
import com.sharvari.engrosswomenhodd.Requests.UpdateUserRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadBusinessRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadFeedbackRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadNewsRequest;
import com.sharvari.engrosswomenhodd.Requests.UploadUserTaskRequest;
import com.sharvari.engrosswomenhodd.Requests.UserLoginRequest;
import com.sharvari.engrosswomenhodd.Requests.UserRegisterRequest;
import com.sharvari.engrosswomenhodd.Response.Category.GetCategoryResponse;
import com.sharvari.engrosswomenhodd.Response.Feedback.GetFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.GetAddress.GetAddressResponse;
import com.sharvari.engrosswomenhodd.Response.GetBusiness.GetBusinessResponse;
import com.sharvari.engrosswomenhodd.Response.GetProfileDetailsResponse;
import com.sharvari.engrosswomenhodd.Response.GetTaskResponse.GetTaskresponse;
import com.sharvari.engrosswomenhodd.Response.MyTask.GetMyTaskResponse;
import com.sharvari.engrosswomenhodd.Response.News.News;
import com.sharvari.engrosswomenhodd.Response.TaskRequest.TaskRequestResponse;
import com.sharvari.engrosswomenhodd.Response.UploadFeedbackResponse;
import com.sharvari.engrosswomenhodd.Response.UserDetails.GetUserDetails;
import com.sharvari.engrosswomenhodd.Response.UserLoginResponse;
import com.sharvari.engrosswomenhodd.Response.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public interface Apis {

    @POST("UserRegister")
    Call<UserRegisterResponse> registerUser(@Body UserRegisterRequest registerRequest);

    @POST("UserLogin")
    Call<UserLoginResponse> userLogin(@Body UserLoginRequest request);

    @POST("GetTask")
    Call<GetTaskresponse> getTask(@Body GetTaskRequest request);

    @POST("UploadFeedback")
    Call<UploadFeedbackResponse> uploadFeedback(@Body UploadFeedbackRequest request);

    @POST("GetFeedback")
    Call<GetFeedbackResponse> getFeedback();

    @POST("UploadNews")
    Call<UploadFeedbackResponse> uploadNews(@Body UploadNewsRequest request);

    @POST("GetNews")
    Call<News> getNews();

    @POST("UpdateUser")
    Call<UploadFeedbackResponse> updateUserRequest(@Body UpdateUserRequest request);

    @POST("GetUserDetails")
    Call<GetUserDetails> getUserDetails(@Body GetTaskRequest request);

    @POST("GetCategory")
    Call<GetCategoryResponse> getCategory();

    @POST("GetUserAddress")
    Call<GetAddressResponse> getAddresses(@Body GetTaskRequest request);

    @POST("UploadTaskRequest")
    Call<UploadFeedbackResponse> uploadTaskRequest(@Body UploadUserTaskRequest request);

    @POST("InsertTaskHelpRequest")
    Call<UploadFeedbackResponse> insertTaskHelpRequest(@Body InsertTaskHelpRequest request);

    @POST("GetTaskRequest")
    Call<TaskRequestResponse> getTaskRequest(@Body GetTaskHelpRequest request);

    @POST("GetMyTask")
    Call<GetMyTaskResponse> getMyTask(@Body GetTaskRequest request);

    @POST("UpdateUser")
    Call<UploadFeedbackResponse> updateUserDetails(@Body UpdateUserDetailsRequest request);

    @POST("ChangePassword")
    Call<UploadFeedbackResponse> changePassword(@Body ChangePassword request);

    @POST("UpdateAddress")
    Call<UploadFeedbackResponse> updateAddress(@Body UpdateAddressRequest request);

    @POST("InsertAddress")
    Call<UploadFeedbackResponse> insertAddress(@Body InsertAddressRequest request);

    @POST("AcceptTaskRequest")
    Call<UploadFeedbackResponse> acceptTaskRequest(@Body AcceptTaskRequest request);

    @POST("UpdateMyTaskRequest")
    Call<UploadFeedbackResponse> updateMyTaskRequest(@Body UpdateMyTaskRequest request);

    @POST("GetProfileDetails")
    Call<GetProfileDetailsResponse> getProfileDetails(@Body GetTaskRequest request);

    @POST("FollowRequest")
    Call<UploadFeedbackResponse> followRequest(@Body FollowRequest request);

    @POST("UploadBusiness")
    Call<UploadFeedbackResponse> uploadBusiness(@Body UploadBusinessRequest request);

    @POST("GetBusiness")
    Call<GetBusinessResponse> getBusiness();
}
