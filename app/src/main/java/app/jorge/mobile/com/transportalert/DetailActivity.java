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
package app.jorge.mobile.com.transportalert;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.jorge.mobile.com.transportalert.factory.CardFactory;
import app.jorge.mobile.com.transportalert.factory.CardTube;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.iconTube);
        TextView textTubeName = (TextView) findViewById(R.id.tubeName);
        TextView textStatusView = (TextView)findViewById(R.id.tubeStatus);
        ImageView statusView = (ImageView) findViewById(R.id.iconStatusImage);

        String category=getIntent().getStringExtra(getString(R.string.activity_info_category));
        String description=getIntent().getStringExtra(getString(R.string.activity_info_description));
        String additional=getIntent().getStringExtra(getString(R.string.activity_info_additional));
        String name=getIntent().getStringExtra(getString(R.string.activity_info_icon));
        String status=getIntent().getStringExtra(getString(R.string.activity_info_status));


        textTubeName.setText(name);
        textStatusView.setText(status);
        imageView.setBackgroundResource(getIconLine(name));
        statusView.setBackgroundResource(getStatusIcon(status));


        TextView textCategoryView = (TextView) findViewById(R.id.category);
        textCategoryView.setText(category);

        TextView textDescriptionView = (TextView)findViewById(R.id.description);
        textDescriptionView.setText(description);

        TextView textAdditionalView = (TextView)findViewById(R.id.additional);
        textAdditionalView.setText(additional);

        if (status.equals(getString(R.string.status_good_service))){
            textCategoryView.setVisibility(View.GONE);
            textDescriptionView.setVisibility(View.GONE);
            textAdditionalView.setVisibility(View.GONE);
        }
        else{
            ImageView starView = (ImageView) findViewById(R.id.iconGoodService);
            starView.setVisibility(View.GONE);
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            imageView.setTransitionName(getString(R.string.activity_image_trans));
            textTubeName.setTransitionName(getString(R.string.activity_text_tube_name));
            textStatusView.setTransitionName(getString(R.string.activity_text_tube_status));

        }
    }

    private int getIconLine(String line) {
        //Toast.makeText( getContext(), line , Toast.LENGTH_SHORT).show();

        if (line.equals(getString(R.string.bakerloo_label))){
            return R.drawable.bakerloo_400x400;
        }
        else if (line.equals(getString(R.string.central_label))){
            return R.drawable.central_line_400x400;
        }
        else if (line.equals(getString(R.string.circle_label))){
            return R.drawable.circle_400x400;
        }
        else if (line.equals(getString(R.string.district_label))){
            return R.drawable.district_400x400;
        }
        else if (line.equals(getString(R.string.hammersmith_label))){
            return R.drawable.hc_400x400;
        }
        else if (line.equals(getString(R.string.jubilee_label))){
            return R.drawable.jubilee_400x400;
        }
        else if (line.equals(getString(R.string.metropolitan_label))){
            return R.drawable.metropolitan_400x400;
        }
        else if (line.equals(getString(R.string.northern_label))){
            return R.drawable.nothern_400x400;
        }
        else if (line.equals(getString(R.string.piccadilly_label))){
            return R.drawable.picadilly_400x400;
        }
        else if (line.equals(getString(R.string.victoria_label))){
            return R.drawable.victoria_400x400;
        }
        else if (line.equals(getString(R.string.waterloo_label))){
            return R.drawable.waterloo_400x400;
        }
        else if (line.equals(getString(R.string.london_overground_label))){
            return R.drawable.overground_wbg_400x400;
        }
        else if (line.equals(getString(R.string.tfl_rail_label))){
            return R.drawable.tflrail_wbg400x400;
        }
        else if (line.equals(getString(R.string.dlr_label))){
            return R.drawable.dlr_wbg400x400;
        }
        return -1;
    }

    private int getStatusIcon(String status){

        if (getString(R.string.status_good_service).equals(status)){
            return R.drawable.thumbs_up_128x128;
        }
        else if (getString(R.string.status_minor_delays).equals(status)){
            return R.drawable.hourglass_128x128;
        }
        else{
            return R.drawable.fire_128x128;
        }
    }
}
