package top.psf.zhihuclient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.psf.zhihuclient.ui.InformationFragment;
import top.psf.zhihuclient.uitls.Tools;

import top.psf.zhihuclient.R;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.vp_container)
    ViewPager mainViewpager;

    @BindView(R.id.bb_tabs)
    BottomBar bottomBar;

    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.statusBarColor(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        bottomBar.setOnTabSelectListener(this);
    }

    private void initViewPager(){
        fragments = new ArrayList<>();
        InformationFragment fragment1 = new InformationFragment();
        fragments.add(fragment1);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                position = position % fragments.size();
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        ViewCompat.setOnApplyWindowInsetsListener(mainViewpager, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                WindowInsetsCompat applied = ViewCompat.onApplyWindowInsets(v, insets);
                Log.e("xxxxxxx", " " + applied.getStableInsetBottom() + " " + applied.getStableInsetTop());
                return insets;
            }
        });
        mainViewpager.setAdapter(adapter);
        mainViewpager.setCurrentItem(0);
        ViewCompat.requestApplyInsets(mainViewpager);
    }

    @Override
    public void onTabSelected(int tabId) {
        mainViewpager.setCurrentItem(tabId);
        ViewCompat.requestApplyInsets(mainViewpager);
    }
}
