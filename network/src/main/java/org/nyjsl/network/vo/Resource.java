/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.nyjsl.network.vo;

import androidx.annotation.Nullable;

public class Resource<T> {
    private Status status;
    private T data;
    private String message;

    private Resource(Status status,
                     @Nullable T data,
                     @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource(Status.SUCCESS, data, null);
    }

    public static  <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource(Status.LOADING, data,null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }

        Resource res = (Resource) obj;
        return res != null
                && status.equals(res.getStatus())
                && ((data == null && res.getData() == null) || (data != null && data.equals(res.getData())))
                && ((message == null && res.getMessage() == null) || (message != null && message.equals(res.getMessage())));
    }
}
