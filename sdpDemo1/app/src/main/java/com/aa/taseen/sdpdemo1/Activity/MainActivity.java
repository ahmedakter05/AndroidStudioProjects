package com.aa.taseen.sdpdemo1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.aa.taseen.sdpdemo1.Helper.DatabaseHelper;
import com.aa.taseen.sdpdemo1.R;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;
    DatabaseHelper db;
    SliderLayout sliderShow;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = new DatabaseHelper(this);
        //boolean res = db.insertData();
        String namee = db.getStaffProfile(1);
        if(namee != null){Toast.makeText(this, "Result: "+ namee, Toast.LENGTH_SHORT).show();}

        loadSlider();

        loadText();
    }

    private void loadSlider() {

        sliderShow = (SliderLayout)findViewById(R.id.slider_main);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Decent Workplace, Happy Worker", "http://www.brac.net/program/wp-content/uploads/2018/08/progress-banner.jpg");
        url_maps.put("Happiness", "http://blog.brac.net/wp-content/uploads/2019/03/sheuly.jpg");
        url_maps.put("A classroom in the sky: Building upwards in the Rohingya camps", "http://blog.brac.net/wp-content/uploads/2018/11/classroom-in-the-sky-banner.jpg");
        //url_maps.put("Game of Thrones", "https://cdn.guidingtech.com/media/assets/WordPress-Import/2017/08/276305-thewallpaper.jpg");

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

            sliderShow.addSlider(textSliderView);
        }
    }

    private void loadText() {

        /*String uri = "@drawable/progress";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        ImageView imageView= (ImageView)findViewById(R.id.imageView2);
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res); */

        String textLong = "We offer high-quality training to people from all backgrounds, and link graduates with decent work opportunities.\n" +
                "\n" +
                "We work in both the formal and informal economy, identifying occupations in demand and skills in short supply, in partnership with market actors and employers.\n" +
                "\n" +
                "We offer technical and vocational training, enterprise and institution-based apprenticeships, entrepreneurship and enterprise development, especially for startups in the informal economy. Our projects aim to improve working conditions and strengthen market value chains.\n" +
                "\n" +
                "We promote and facilitate safe, regular and responsible migration for potential migrants at every step of their journey so that they can pursue safe migration routes and financing.\n" +
                "\n" +
                "We arrange trade-specific training, pre-decision and pre-departure orientation, and life skills training for migrant workers.\n" +
                "\n" +
                "Our reintegration service centres in 64 districts provide services to returnees to promote social and economic reintegration, including psychosocial counselling to those who have experienced trauma, and emergency support for returnees.";

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);

        String text2 = "<div>The Bangladesh Decent Work Country Programme (DWCP) was developed in close collaboration with ILO&rsquo;s tripartite constituents: the Government, Bangladesh Employers&rsquo; Federation and National Coordination Committee for Workers Education. This DWCP is results-oriented, focused and well-coordinated with national policy documents and global development initiatives such as the Sustainable Development Goals (SDGs).&nbsp;<br /><br />It is being implemented for the period 2017-20 focusing on four key priorities and outcomes which will contribute to helping reach the goals and &lsquo;core targets&rsquo; of the 7th Five Year Plan (FYP).&nbsp;<br /><br /><span style=\"color: blue;\">Priority 1:&nbsp;&nbsp;&nbsp;&nbsp;</span>Effective employment policies to enhance employability through skill development including for green growth<br /><span style=\"color: blue;\">Priority 2:&nbsp;&nbsp;&nbsp;&nbsp;</span>Promotion of a safe and clean working environment for all workers and in compliance with core international labour standards&nbsp;<br /><span style=\"color: blue;\">Priority 3:&nbsp;&nbsp;&nbsp;&nbsp;</span>Promotion of fundamental principles and rights at work through social dialogue and tripartism&nbsp;<br /><span style=\"color: blue;\">Priority 4:&nbsp;&nbsp;&nbsp;&nbsp;</span>Promotion of social protection for all workers and vulnerable groups including against climate change</div>\n" +
                "<h3>Outcomes 1 - Employment and skills</h3>\n" +
                "<div>1.1&nbsp;&nbsp;&nbsp; Bangladesh jobs strategy formulated for skills development and job creation with higher productivity and in green industries especially for young men and women.&nbsp;<br />1.2&nbsp;&nbsp;&nbsp; Accessibility of TVET system enhanced in alignment with the National Skills Development Policy (NSDP) especially for women, disadvantaged groups, people with disabilities and ethnic groups and in view of introducing skills in emerging technologies relating to climate-resilient green growth.</div>\n" +
                "<h3>Outcomes 2 - Compliance</h3>\n" +
                "<div>2.1&nbsp;&nbsp;&nbsp; International labour standards (ILS) especially the eight core conventions are promoted and constituents&rsquo; capacity enhanced for their better implementation.&nbsp;<br />2.2&nbsp;&nbsp;&nbsp; Implementation of policies, laws and programmes promoted to ensure occupational safety to improve working conditions and ensure a just transition to a climate resilient and green economy through the application of ILO guidelines for a just transition towards environmentally sustainable economies and societies for all. &nbsp;</div>\n" +
                "<h3>Outcomes 3 - Social dialogue</h3>\n" +
                "<div>3.1&nbsp;&nbsp;&nbsp; Employers&rsquo; and Workers&rsquo; organizations&rsquo; capacity developed to strengthen freedom of association, collective bargaining and sound industrial relations.&nbsp;<br />3.2&nbsp;&nbsp;&nbsp; Capacity of labour administration enhanced leading to good governance in the labour market.</div>\n" +
                "<h3>Outcomes 4 - Social protection</h3>\n" +
                "<div>4.1&nbsp;&nbsp;&nbsp; Employment injury social protection schemes for select sectors developed and implemented.<br />4.2&nbsp;&nbsp;&nbsp; Laws and policies for the protection of migrant workers, domestic workers, child labourers and indigenous workers developed and implemented.&nbsp;<br /><br />The implementation of the DWCP will be based on &lsquo;partnership&rsquo; among the tripartite constituents and the ILO Country Office. Current Technical Coordination (TC) projects of ILO, as well as some new ones which will be mobilized during the span of the DWCP, will play critical roles in achieving the outcomes of the DWCP.</div>";
        textView3.setText("A classroom in the sky: Building upwards in the Rohingya camps");
        textView2.setText(Html.fromHtml(text2));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, LoginStaffs.class);
            startActivity(i);
            //return true;
        } else if (id == R.id.action_register) {
            Intent i = new Intent(MainActivity.this, SignupStaffs.class);
            startActivity(i);
            //return true;
        } else if (id == R.id.action_signout) {
            mAuth.signOut();
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.action_register);
        MenuItem signout = menu.findItem(R.id.action_signout);
        MenuItem login = menu.findItem(R.id.action_settings);
        if(mAuth.getCurrentUser() != null)
        {
            register.setVisible(false);
            login.setVisible(false);
            signout.setVisible(true);
        }
        else
        {
            register.setVisible(true);
            login.setVisible(true);
            signout.setVisible(false);
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_overview) {
            Intent i = new Intent(MainActivity.this, overview.class);
            startActivity(i);
        } else if (id == R.id.nav_stories) {
            Intent i = new Intent(MainActivity.this, stories.class);
            startActivity(i);
        } else if (id == R.id.nav_organogram) {

        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(MainActivity.this, StaffProfiles.class);
            startActivity(i);
        } else if (id == R.id.nav_blood) {
            Intent i = new Intent(MainActivity.this, BloodFinder.class);
            startActivity(i);
        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    public String dataText(){
        String[] dwbd = new String[0];
        dwbd[0] = "Decent work in Bangladesh";
        dwbd[1] = "Decent work in Bangladesh";


        return String.valueOf(dwbd);

    }
}
