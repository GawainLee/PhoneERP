package com.tapwater.phoneerp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Tapwater on 17-3-8.
 */

public class RunItemFragment {

    private Object itemFragment;
    private FragmentManager fragmentManager;

    public RunItemFragment() {
    }

    public RunItemFragment(Object itemFragment, FragmentManager fragmentManager) {
        this.itemFragment = itemFragment;
        this.fragmentManager = fragmentManager;
        runFragment();
    }

    public void runFragment()
    {
        if (itemFragment instanceof CheckFragment)
        {
            CheckFragment checkFragment = (CheckFragment) itemFragment;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayoutContent,checkFragment,checkFragment.getClass().getName());
            fragmentTransaction.addToBackStack(null);//create back key function
            fragmentTransaction.commitAllowingStateLoss();//
        }
    }
}
