package com.ttd.androidlearning;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wt
 * @time 2020/4/23
 */
public class MainActivity extends FragmentActivity {
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        PageMenuFragment fragment = new PageMenuFragment();
        fragment.setPageItems(PageItem.getMainItems());
        showFragment(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return true;
    }

    public void back() {
        if (fragments.size() > 1) {
            Fragment topFragment = fragments.get(fragments.size() - 1);
            Fragment showFragment = fragments.get(fragments.size() - 2);

            mFragmentManager.beginTransaction().show(showFragment).commit();
            mFragmentManager.beginTransaction().remove(fragments.get(fragments.size() - 1)).commit();

            fragments.remove(fragments.size() - 1);

        }
    }

    public void showFragment(Fragment fragment) {
        if (!fragments.contains(fragment)) {
            fragments.add(fragment);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.v_container, fragment)
                .show(fragment).commit();

    }
}
