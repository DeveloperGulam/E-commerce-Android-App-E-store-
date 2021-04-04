package com.dattaprabodhinee.estore.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dattaprabodhinee.estore.Adapters.Products;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Fragments.CartFragment;
import com.dattaprabodhinee.estore.Fragments.CategoryFragment;
import com.dattaprabodhinee.estore.Fragments.ContactUsFragment;
import com.dattaprabodhinee.estore.Fragments.HomeFragment;
import com.dattaprabodhinee.estore.Fragments.LoginFragment;
import com.dattaprabodhinee.estore.Fragments.MyOrdersFragment;
import com.dattaprabodhinee.estore.Fragments.NewProductFragment;
import com.dattaprabodhinee.estore.Fragments.ProductFragment;
import com.dattaprabodhinee.estore.Fragments.YouFragment;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.Models.ResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.SharedPrefManager;
import com.dattaprabodhinee.estore.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    SharedPrefManager prefManager;
    EditText searchBox;
    Api api;

    ArrayList<ProductModel> list;
    Products adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = RetrofitIntance.getRetrofit().create(Api.class);

        searchBox = findViewById(R.id.search_box);
        prefManager = new SharedPrefManager(MainActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();

        //Open Navigation Drawer
        ImageView nav = findViewById(R.id.nav_menu_icon);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        //Search Box Data
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputValue = searchBox.getText().toString();
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Search Data
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                String inputValue = searchBox.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    Fragment fragment = new ProductFragment();
                    Bundle args = new Bundle();
                    args.putString("searchValue", inputValue);
                    fragment.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
                }
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    Fragment fragment = new ProductFragment();
                    Bundle args = new Bundle();
                    args.putString("searchValue", inputValue);
                    fragment.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
                }
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Fragment fragment = new ProductFragment();
                    Bundle args = new Bundle();
                    args.putString("searchValue", inputValue);
                    fragment.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
                }
                return handled;
            }
        });

        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home_menu_item:
                        fragment = new HomeFragment();
                        break;
                    case R.id.category_menu_item:
                        fragment = new CategoryFragment();
                        break;
                    case R.id.cart_menu_item:
                        fragment = new CartFragment();
                        break;
                    case R.id.you_menu_item:
                        fragment = new YouFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).commit();
                return true;
            }
        });

        View headerView = binding.navigationDrawer.getHeaderView(0);
        headerView.findViewById(R.id.go_profile_item).setOnClickListener(this);
        headerView.findViewById(R.id.login_item).setOnClickListener(this);
        headerView.findViewById(R.id.my_order_item).setOnClickListener(this);
        headerView.findViewById(R.id.product_item).setOnClickListener(this);
        headerView.findViewById(R.id.cart_item).setOnClickListener(this);
        headerView.findViewById(R.id.contact_item).setOnClickListener(this);
        headerView.findViewById(R.id.product_item).setOnClickListener(this);
        headerView.findViewById(R.id.help_item).setOnClickListener(this);
        headerView.findViewById(R.id.logout_menu_text).setOnClickListener(this);
        headerView.findViewById(R.id.setting_item).setOnClickListener(this);


        //logged user
        if (prefManager.isLoggedIn()) {
            TextView login_text = (TextView) headerView.findViewById(R.id.login_menu_text);
            TextView logout_text = (TextView) headerView.findViewById(R.id.logout_menu_text);
            RelativeLayout login_item1 = headerView.findViewById(R.id.login_item1);
            RelativeLayout login_item2 = headerView.findViewById(R.id.login_item2);
            RelativeLayout login_item3 =  headerView.findViewById(R.id.login_item3);
            RelativeLayout logout_item1 =  headerView.findViewById(R.id.logout_item1);
            View logout_view1 =  headerView.findViewById(R.id.logout_view1);
            View login_view1 =  headerView.findViewById(R.id.login_view1);
            View login_view2 =  headerView.findViewById(R.id.login_view2);
            View login_view3 =  headerView.findViewById(R.id.login_view3);

            login_text.setVisibility(View.INVISIBLE);
            logout_text.setVisibility(View.VISIBLE);
            login_item1.setVisibility(View.VISIBLE);
            login_item2.setVisibility(View.VISIBLE);
            login_item3.setVisibility(View.VISIBLE);
            login_view1.setVisibility(View.VISIBLE);
            login_view2.setVisibility(View.VISIBLE);
            login_view3.setVisibility(View.VISIBLE);
            logout_item1.setVisibility(View.GONE);
            logout_view1.setVisibility(View.GONE);

            ImageView profile_img = headerView.findViewById(R.id.logged_user_profile);
            Glide.with(MainActivity.this)
                    .load(prefManager.getUser().getCustomer_img())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_profile_picture)
                    .error(R.drawable.default_profile_picture)
                    .override(400, 400)
                    .centerCrop()
                    .into(profile_img);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new LoginFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
                break;
            case R.id.go_profile_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new YouFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
                break;
            case R.id.setting_item:
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.my_order_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new MyOrdersFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
                break;
            case R.id.product_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new NewProductFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
                break;
            case R.id.cart_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new CartFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
                break;
            case R.id.contact_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new ContactUsFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
                break;
            case R.id.help_item:
                Uri uri = Uri.parse("https://estore.dattaprabodhinee.com/assets/pdf/estore-guide.pdf"); // missing 'http://' will cause crashed
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent2);
                break;
            case R.id.logout_menu_text:
                logoutUser();
                break;
        }
        binding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    private void logoutUser() {
        prefManager.logout();
        Toast.makeText(MainActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}