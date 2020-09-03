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

package org.nyjsl.network.util;

import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

import androidx.collection.ArrayMap;

public class RateLimiter<KEY> {
    private ArrayMap<KEY, Long> timestamps = new ArrayMap<>();
    private Long timeout;

    public RateLimiter(Integer timeout, TimeUnit timeUnit) {
        this.timeout = timeUnit.toMillis(timeout);
    }

    public synchronized Boolean shouldFetch(KEY key) {
        Long lastFetched = timestamps.get(key);
        Long now = now();
        if (lastFetched == null) {
            timestamps.put(key, now);
            return true;
        }
        if (now - lastFetched > timeout) {
            timestamps.put(key, now);
            return true;
        }
        return false;
    }

    private Long now() {
        return SystemClock.uptimeMillis();
    }

    public synchronized void reset(KEY key) {
        timestamps.remove(key);
    }
}
