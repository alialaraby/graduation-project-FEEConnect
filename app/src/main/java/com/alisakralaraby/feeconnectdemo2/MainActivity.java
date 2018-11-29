package com.alisakralaraby.feeconnectdemo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.nav_fragments.Frag_CatchTrain;
import com.alisakralaraby.feeconnectdemo2.nav_fragments.Frag_OfficialAccount;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * created by ali sakr alaraby
     */
    //declaration part for views
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    BroadcastReceiver broadcastReceiver;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    //string to hold the username
    String mainUserName;

    //
    public static final String ANONYMOUS = "anonymous";

    //request code for signing in
    public static final int RC_SIGN_IN = 1;
    private boolean exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //collapsing toolbar setup
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle("FEE Connect");

        //checking the connection
        checkNetwork();

        //firebase setup
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        mainUserName = ANONYMOUS;

        /**/
        //firebase authentication functionality (signup and login)
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //get the logged user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                    //case that a user is logged in
                }else {

                    //case a user isnot logged in,a signup/login page powered by firebase UI
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                                    ))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

        //creating and assigning the navigation drawer to the xml one
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //displaying the trains tab by default in the main activity fragment
        displayFragment(R.id.nav_catch_train);
    }

    //overriding the onback
    @Override
    public void onBackPressed() {
        //case the navigation drawer is open one back press closes it
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            //case the navigation drawer is closed and one back pressed, this way stpos
            //the activity from going back to the home page
            if (exit){
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }else {

                //when 2 backs are pressed in 3 seconds time the app exits
                Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_LONG).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        exit = false;
                    }
                }, 3 * 1000);
            }
        }
    }

    //case the signin is successful, enter the app just fine
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){

            if (resultCode == RESULT_OK){
                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED){
                //if some thing went wrong in logging in, the app exits
                finish();
            }
        }
    }
    //remove the authentication when the app pauses
    @Override
    protected void onPause() {
        super.onPause();
        /**/
        if (authStateListener != null){

            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
    //confirm authentication when the app is resumed
    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    //this is the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //when the optins menu item is clicked, opens the visitors activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_visitors) {
            startActivity(new Intent(getApplicationContext(), VisitorsActivity.class));
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displayFragment(id);
        if (id == R.id.nav_logout){

            AuthUI.getInstance().signOut(this);
        }else if (id == R.id.nav_announcements){

            startActivity(new Intent(getApplicationContext(), AnnouncementsActivity.class));
        }else if (id == R.id.nav_add_note){

            startActivity(new Intent(getApplicationContext(), AddNoteActivity.class));
        }else if (id == R.id.aboutUs){

            Intent intent = new Intent(getApplicationContext(), AboutUsContactUsActivity.class);
            intent.putExtra("communicateKey","about_us");
            startActivity(intent);
        }
        /*
        *this section is not functioning well, it`s also optional and not important
        else if (id == R.id.nav_regulations){

            Intent intent = new Intent(getApplicationContext(), AboutUsContactUsActivity.class);
            intent.putExtra("communicateKey","regulations");
            startActivity(intent);
        }
        else if (id == R.id.nav_FEE_SCC){

            Intent intent = new Intent(getApplicationContext(), AboutUsContactUsActivity.class);
            intent.putExtra("communicateKey","FEE_SCC");
            startActivity(intent);
        }
        else if (id == R.id.nav_ELibrary){

            Intent intent = new Intent(getApplicationContext(), AboutUsContactUsActivity.class);
            intent.putExtra("communicateKey","E-Library");
            startActivity(intent);
        }
        */
        else if (id == R.id.contactUs){

            Intent intent = new Intent(getApplicationContext(), AboutUsContactUsActivity.class);
            intent.putExtra("communicateKey","contact_us");
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //choosing the fragments or activities to display in the main activity from the navigation drawer items
    public void displayFragment(int id){
        Fragment fragment = null;
        switch (id){

            case R.id.nav_Tables :
                startActivity(new Intent(MainActivity.this, TablesActivity.class));
                break;
            case R.id.nav_events :
                startActivity(new Intent(MainActivity.this, EventsActivity.class));
                break;
            case R.id.nav_house :
                startActivity(new Intent(MainActivity.this, FindAnAppartmentActivity.class));
                break;
            case R.id.nav_official_account :
                fragment = new Frag_OfficialAccount();
                break;
            case R.id.nav_catch_train :
                fragment = new Frag_CatchTrain();
                break;
            case R.id.nav_paper_work :
                startActivity(new Intent(MainActivity.this, PaperWorkActivity.class));
                break;
        }

        if (fragment != null){

            //setting up the fragments and inflating its xml
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.base_frame_layout, fragment);
            ft.commit();

        }

    }

    //method for checking mobile network
    private void checkNetwork() {

        if (broadcastReceiver == null) {

            broadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {

                    Bundle extras = intent.getExtras();

                    NetworkInfo info = (NetworkInfo) extras
                            .getParcelable("networkInfo");

                    NetworkInfo.State state = info.getState();

                    if (state == NetworkInfo.State.CONNECTED) {


                    } else {

                        Toast.makeText(getApplicationContext(), "check mobile network", Toast.LENGTH_LONG).show();

                    }

                }
            };

            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
