package com.example.administrator.testz;



        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.os.Bundle;

        import com.google.android.material.appbar.AppBarLayout;
        import com.google.android.material.tabs.TabLayout;
        import com.suke.widget.SwitchButton;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.widget.NestedScrollView;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentPagerAdapter;
        import androidx.viewpager.widget.ViewPager;

public class AllRtkActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    NestedScrollView mNestedScrollView;
    AppBarLayout mAppBarLayout;
    ViewPager mViewPager;
    SwitchButton mSwitchButton;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allrtk);
        viewPager = findViewById(R.id.myViewPager);

        tabLayout = findViewById(R.id.myTabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_history_24dp));
        tabLayout.setSelectedTabIndicatorColor(Color.RED);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contact_selected_24dp);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(Color.parseColor("#FFF52844"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#4B0912"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private class TabPagerAdapter extends FragmentPagerAdapter {

        private int tabCount;

        public TabPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new MobileFragment();
                case 1:
                    return new NetRtkFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }
}
