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
package app.jorge.mobile.com.transportalert.model;

import app.jorge.mobile.com.transportalert.R;


public class WaterlooCardSelection implements CardSelection {



    @Override
    public int getIdKeyProperty() {
        return R.string.waterloo_label;
    }

    @Override
    public int getIdTxtLabel() {
        return R.id.txtWaterloo;
    }

    @Override
    public int getCheckedColourCard() {
        return R.string.waterloo_colour;
    }

    @Override
    public int getCkechedCoulourText() {
        return R.string.waterloo_colour_second;
    }

    @Override
    public int getUnCheckedColourCard() {
        return R.string.white;
    }

    @Override
    public int getUnCkechedCoulourText() {
        return R.string.black;
    }
}
