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
package app.jorge.mobile.com.transportalert.factory;


public class CardTube {

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
    }

    public String getColour() {
        return colour;
    }

    private String name;

    private int icon;

    private String status;

    private String colour;


    public CardTube(String name,int icon,String status,String colour){

        this.name=name;
        this.icon=icon;
        this.status=status;
        this.colour=colour;


    }


}
