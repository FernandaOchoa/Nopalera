package com.fernanda.nopalera;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private String drawerTitle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);

        if (navigationView != null){
            setupDrawerContent(navigationView);
        }
        drawerTitle = getResources().getString(R.string.titulo_inicio);
        if (savedInstanceState == null){
            //Select Item
            selectItem(drawerTitle);
        }
    }
    private void agregarToolbar(){
        //Add my toolbar for compat
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null){
            //Set drawer toggle icon

            ab.setHomeAsUpIndicator(R.drawable.ic_drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setupDrawerContent (NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //Item selected check
                        menuItem.setChecked(true);
                        String title = menuItem.getTitle().toString();
                        selectItem(title);
                        return true;
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void selectItem(String title) {
        // Enviar título como arguemento del fragmento
        Bundle args = new Bundle();
        args.putString(ViewFragment.Seccion_Titulo, title);

        Fragment fragment = ViewFragment.newInstance(title);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.contenedor_principal, fragment)
                .commit();

        drawerLayout.closeDrawers(); // Cerrar drawer

        setTitle(title); // Setear título actual

    }

}
