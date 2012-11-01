/*
 * Copyright (C) 2012 Cyril Mottier (http://www.cyrilmottier.com)
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
package com.cyrilmottier.polaris.internal;

import java.lang.reflect.Field;

import com.cyrilmottier.polaris.BuildConfig;

/**
 * @author Cyril Mottier
 */
@SuppressWarnings("all")
public class Config {

    // /////////////////////////////////////////////////////////////
    //
    // Logs
    //
    // /////////////////////////////////////////////////////////////

    private static final int LOG_LEVEL_INFO = 3;
    private static final int LOG_LEVEL_WARNING = 2;
    private static final int LOG_LEVEL_ERROR = 1;
    private static final int LOG_LEVEL_NONE = 0;

    /**
     * Set this flag to LOG_LEVEL_NONE when releasing your application in order
     * to remove all logs.
     */
    private static final int LOG_LEVEL = LOG_LEVEL_NONE;

    /**
     * Indicates whether info logs are enabled. This should be true only when
     * developing/debugging an application/the library
     */
    public static final boolean INFO_LOGS_ENABLED = (LOG_LEVEL == LOG_LEVEL_INFO);

    /**
     * Indicates whether warning logs are enabled
     */
    public static final boolean WARNING_LOGS_ENABLED = INFO_LOGS_ENABLED || (LOG_LEVEL == LOG_LEVEL_WARNING);

    /**
     * Indicates whether error logs are enabled. Error logs are usually always
     * enabled, even in production releases.
     */
    public static final boolean ERROR_LOGS_ENABLED = WARNING_LOGS_ENABLED || (LOG_LEVEL == LOG_LEVEL_ERROR);

}
