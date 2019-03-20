package com.aa.taseen.sdpdemo1;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class overview extends AppCompatActivity {

    SliderLayout sliderShow, mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Slider Code
        /*
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView
                .description("Game of Thrones")
                .image("https://cdn.guidingtech.com/media/assets/WordPress-Import/2017/08/276305-thewallpaper.jpg");

        sliderShow.addSlider(textSliderView);*/

        //Slider 2
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://getwallpapers.com/wallpaper/full/1/1/f/961893-mads-mikkelsen-hannibal-wallpaper-1920x1080-windows-10.jpg");
        url_maps.put("Big Bang Theory", "https://images8.alphacoders.com/431/thumb-1920-431311.jpg");
        url_maps.put("House of Cards", "https://cervifrank.files.wordpress.com/2013/09/house_of_cards_title_card-e1362242083908.jpg");
        url_maps.put("Game of Thrones", "https://cdn.guidingtech.com/media/assets/WordPress-Import/2017/08/276305-thewallpaper.jpg");

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop); //BaseSliderView.ScaleType.CenterCrop, BaseSliderView.ScaleType.CenterInside, BaseSliderView.ScaleType.Fit, BaseSliderView.ScaleType.FitCenterCrop

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

    }

    //
    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
