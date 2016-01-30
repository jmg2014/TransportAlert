/*
 * Copyright 2016 Jorge Manrique
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.jorge.mobile.com.transportalert.service;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


public interface TaskService {
    @GET("Mode/tube,dlr,overground,tflrail/Status")
    Call<List<StatusLine>> login(@Query("app_id") String app_id,
                                 @Query("app_key") String app_key);
}