package com.team15.customnavigationbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private final static int ID_HOME = 1;
    private final static int ID_STORE = 2;
    private final static int ID_GIFT = 3;
    private final static int ID_CART = 4;
    private final static int ID_ACCOUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        toolbar.setTitle("Shop");
        loadFragment(new StoreFragment());

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_STORE, R.drawable.ic_baseline_store_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_GIFT, R.drawable.ic_baseline_card_giftcard_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CART, R.drawable.ic_baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_baseline_account_circle_24));

        bottomNavigation.setCount(ID_CART, "115");

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(MainActivity.this, "showing item : " + item.getId(), Toast.LENGTH_SHORT).show();

                Fragment fragment;
                switch (item.getId()) {
                    case ID_HOME:
                        toolbar.setTitle("Home");
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case ID_STORE:
                        toolbar.setTitle("Shop");
                        fragment = new StoreFragment();
                        loadFragment(fragment);
                        break;
                    case ID_GIFT:
                        toolbar.setTitle("My Gifts");
                        fragment = new GiftsFragment();
                        loadFragment(fragment);
                        break;
                    case ID_CART:
                        toolbar.setTitle("Cart");
                        fragment = new CartFragment();
                        loadFragment(fragment);
                        break;
                    case ID_ACCOUNT:
                        toolbar.setTitle("Profile");
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    default:
                        break;
                }

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.show(ID_HOME, true);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}