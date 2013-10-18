/*
 * Copyright (C) 2013 The LiquidSmooth Project
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

package com.android.internal.util.liquid;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.DisplayInfo;
import android.view.WindowManager;

public class ScreenTypeUtils {

    // Screen types
    private static final int DEVICE_PHONE = 0;
    private static final int DEVICE_HYBRID = 1;
    private static final int DEVICE_TABLET = 2;

    // Screen type reference
    private static int mDeviceType = -1;

    // Determines screen type
    private static int getScreenType(Context con) {
        if (mDeviceType == -1) {
            WindowManager wm = (WindowManager)con.getSystemService(Context.WINDOW_SERVICE);
            DisplayInfo outDisplayInfo = new DisplayInfo();
            wm.getDefaultDisplay().getDisplayInfo(outDisplayInfo);
            int shortSize = Math.min(outDisplayInfo.logicalHeight, outDisplayInfo.logicalWidth);
            int shortSizeDp = shortSize * DisplayMetrics.DENSITY_DEFAULT / outDisplayInfo.logicalDensityDpi;
            if (shortSizeDp < 600) {
                // 0-599dp: "phone" UI
                mDeviceType =  DEVICE_PHONE;
            } else if (shortSizeDp < 720) {
                // 600-719dp: "phone" UI
                mDeviceType = DEVICE_HYBRID;
            } else {
                // 720dp: "tablet" UI
                mDeviceType = DEVICE_TABLET;
            }
        }
        return mDeviceType;
    }

    // Screen type is phone
    public static boolean isPhone(Context con) {
        return getScreenType(con) == DEVICE_PHONE;
    }

    // Screen type is hybrid
    public static boolean isHybrid(Context con) {
        return getScreenType(con) == DEVICE_HYBRID;
    }

    // Screen type is tablet
    public static boolean isTablet(Context con) {
        return getScreenType(con) == DEVICE_TABLET;
    }
}
