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

        textStatusView.setText("Hey!");
        textTubeName.setText("Circle");
        //imageView.setBackgroundResource(R.drawable.circle_400x400);


        CardTube card= CardFactory.getCard(CardFactory.TUBE_LINE.CIRCLE);
        View child = getLayoutInflater().inflate(R.layout.activity_detail, null);



        String category=getIntent().getStringExtra(getString(R.string.activity_info_category));
        String description=getIntent().getStringExtra(getString(R.string.activity_info_description));
        String additional=getIntent().getStringExtra(getString(R.string.activity_info_additional));

        TextView textCategoryView = (TextView) findViewById(R.id.category);
        textCategoryView.setText(category);

        TextView textDescriptionView = (TextView)findViewById(R.id.description);
        textDescriptionView.setText(description);

        TextView textAdditionalView = (TextView)findViewById(R.id.additional);
        textAdditionalView.setText(additional);



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            imageView.setTransitionName(getString(R.string.activity_image_trans));
            textTubeName.setTransitionName(getString(R.string.activity_text_tube_name));
            textStatusView.setTransitionName(getString(R.string.activity_text_tube_status));

        }
    }
}
