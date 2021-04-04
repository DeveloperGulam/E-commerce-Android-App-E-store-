package com.dattaprabodhinee.estore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dattaprabodhinee.estore.Adapters.IntroPagerAdapter;
import com.dattaprabodhinee.estore.Models.IntroModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.databinding.ActivityIntroBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;
    List<IntroModel> list;
    IntroPagerAdapter adapter;
    int position = 0;
    Animation buttonAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        buttonAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        list = new ArrayList<>();
        list.add(new IntroModel(R.drawable.fav, "Welcome to E-store", "Shop online spiritual products on Dattapeabodhinee e-store."));
        list.add(new IntroModel(R.drawable.fav, "Why choose E-store", "We're providing best spiritual products in e-store online and offline."));
        list.add(new IntroModel(R.drawable.fav, "Is it secure?", "Yes, It's fully secured for online spiritual products shopping."));
        adapter = new IntroPagerAdapter(this, list);
        binding.introViewPager.setAdapter(adapter);

        //setup tab indicator with viewpager slider
        binding.tabIndicator.setupWithViewPager(binding.introViewPager);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = binding.introViewPager.getCurrentItem();
                if (position < list.size()) {
                    position++;
                    binding.introViewPager.setCurrentItem(position);
                }
                if (position == list.size()-1) {
                    loadLastScreen();
                }
            }
        });

        binding.tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size()-1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                binding.nextBtn.setVisibility(View.VISIBLE);
                binding.tabIndicator.setVisibility(View.VISIBLE);
                binding.getStartBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size()-1) {
                    loadLastScreen();
                }
            }
        });

        //get start button
        binding.getStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    private void loadLastScreen() {
        binding.nextBtn.setVisibility(View.INVISIBLE);
        binding.tabIndicator.setVisibility(View.VISIBLE);
        binding.getStartBtn.setVisibility(View.VISIBLE);

        binding.getStartBtn.setAnimation(buttonAnim);
    }
}