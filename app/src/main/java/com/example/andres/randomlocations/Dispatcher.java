package com.example.andres.randomlocations;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static final String TAG = Dispatcher.class.getSimpleName( );

    private String[] options;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;

    List<JSONObject> positions = new ArrayList<>( );

    public static Context context;

    public static Context getAppContext() {
        return Dispatcher.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dispatcher);

        //////////PROBABLY WILL BE DELETED///////
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        /////////////////////////////

        Dispatcher.context = getApplicationContext( );

        options = new String[] { "Home", "Solicitar", "Cotizar", "Perfil"};
        drawerLayout = (DrawerLayout)findViewById(R.id.mainContainer);
        listView = (ListView)findViewById(R.id.leftMenu);
        listView.setAdapter( new ArrayAdapter<String>( getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, options));

        FragmentManager fragmentManager = getSupportFragmentManager( );
        fragmentManager.beginTransaction().replace(R.id.frameContainer, new Map( ) ).commit( );
        getSupportActionBar().setTitle(options[2]);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                FragmentManager fragmentManager = getSupportFragmentManager( );

//                if(position == 0){
//                    fragment = new Fragmento1();
//                    fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
//                    getSupportActionBar().setTitle(options[position]);
//                }
//                if(position == 1){
//                    fragment = new Fragmento2();
//                    fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
//                    getSupportActionBar().setTitle(options[position]);
//                }
//                if(position == 2){
//                    fragment = new Fragmento4();
//                    fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
//                    getSupportActionBar().setTitle(options[position]);
//                }
                listView.setItemChecked(position, true);
                drawerLayout.closeDrawer(listView);
            }
        });

        //ACTION BAR

        drawerToggle = new android.support.v4.app.ActionBarDrawerToggle(this, drawerLayout, R.drawable.cubes, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ActivityCompat.invalidateOptionsMenu(Dispatcher.this);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(Dispatcher.this);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(drawerToggle.onOptionsItemSelected(item)){
            return  true;
        }

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
