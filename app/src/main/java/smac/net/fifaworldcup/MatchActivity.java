package smac.net.fifaworldcup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
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

public class MatchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    TextView recentMatchs;
    TextView groups;
    TextView schedule;
    TextView about;
    TextView share;
    TextView rating;
    FragmentManager fragmentManager;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
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

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recentMatchs=findViewById(R.id.recentMatchsId);
        groups=findViewById(R.id.groupsId);
        schedule=findViewById(R.id.scheduleId);
        about =findViewById(R.id.aboutId);
        share =findViewById(R.id.shareId);
        rating =findViewById(R.id.ratingId);

        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.changeLayout,new BlankFragment()).commit();
        navigationView.setCheckedItem(R.id.recentMatchsId);

        recentMatchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.changeLayout,new BlankFragment()).commit();
                navigationView.setCheckedItem(R.id.recentMatchsId);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.changeLayout,new GroupFragment()).commit();
                navigationView.setCheckedItem(R.id.groupsId);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.changeLayout,new ScheduleFragment()).commit();
                navigationView.setCheckedItem(R.id.scheduleId);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.changeLayout,new AboutFragment()).commit();
                navigationView.setCheckedItem(R.id.aboutId);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"FIFA Schedule 2018, This is the initiative of SMAC Technology and from this app, you can see FIFA World Cup 2018 schedule. You will get a periodic reminder of each and every game. You can participate in the quiz, can predict the winner of the game and share it with your friends and family through the social platform  https://play.google.com/store/apps/details?id=smac.net.fifaworldcup");
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent,"Share via");
                startActivity(sendIntent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=smac.net.fifaworldcup"));
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!",
                        Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.match, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
