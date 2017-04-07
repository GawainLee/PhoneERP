package com.tapwater.phoneerp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Tapwater on 16-12-15.
 */

public class mainInterface extends AppCompatActivity //FragmentActivity
{

    Button buttonTools, buttonUser,buttonSetting, textViewSale,textViewPurchasing;
    LinearLayout linearLayoutBottom;
    FragmentManager fragmentManager;
    FrameLayout frameLayoutContent;
    CheckFragment checkFragment;
//    WareHouseFragment functionTreeViewFragment;

    SettingFragment settingFragment;

    FunctionTreeViewFragment functionTreeViewFragment;
    UserGridViewFragment userGridViewFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        //get intent from user input
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String password = bundle.getString("password");
        //check view create case
        if(savedInstanceState != null)
        {
            //// “内存重启”时调用
            //判断savedInstanceState不等以null就弹出所有Fragment 全部重新加载
            fragmentManager = getFragmentManager();
            fragmentManager.popBackStackImmediate(null,1);
        }
        else
        {
            //正常
            bindView();
        }
    }

    /**
     * create main view
     */
    private void bindView()
    {
        //get frame layout
        frameLayoutContent = (FrameLayout) findViewById(R.id.frameLayoutContent);
        fragmentManager = getFragmentManager();

        //get bottom linear layout
        linearLayoutBottom = (LinearLayout) findViewById(R.id.linearLayoutBottom);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);

        //User button
        buttonUser = new Button(this);
        buttonUser.setText("User");
        buttonUser.setGravity(Gravity.CENTER);
        buttonUser.setClickable(true);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get fragment manager
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                //hide all fragment
                hideAllFragment(fragmentTransaction);
                //set all button un-selected
                setSelected();
                //set user button selected
                buttonUser.setSelected(true);
                //check user fragment null or user fragment do not have view or userGrid
                if (userGridViewFragment == null || userGridViewFragment.getView() == null)
                {
                    //create user fragment
                    userGridViewFragment = new UserGridViewFragment();
                    //add user fragment in and set tap
                    fragmentTransaction.add(R.id.frameLayoutContent,userGridViewFragment,userGridViewFragment.getClass().getName());
                }
                else if (userGridViewFragment.isRefresh())
                {
//                    fragmentTransaction.add(R.id.frameLayoutContent,userGridViewFragment,userGridViewFragment.getClass().getName());
//                    fragmentTransaction.replace(R.id.frameLayoutContent,userGridViewFragment,userGridViewFragment.getClass().getName());
                    userGridViewFragment.setRefresh(false);
                    fragmentTransaction.show(userGridViewFragment);
                }
                //user fragment already create
                else
                {
                    fragmentTransaction.show(userGridViewFragment);

                }
                //set user fragment into back stack
                fragmentTransaction.addToBackStack(null);
                //set commit can loss
//                fragmentTransaction.commitAllowingStateLoss();
                fragmentTransaction.commit();
            }
        });
        //add user button into bottom line
        linearLayoutBottom.addView(buttonUser,params);


//        buttonCheck = new Button(this);
//        buttonCheck.setText("Check");
//        buttonCheck.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL);
//        buttonCheck.setClickable(true);
//        buttonCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                hideAllFragment(fragmentTransaction);
//                setSelected();
//                buttonCheck.setSelected(true);
//                if (checkFragment == null || checkFragment.getView() == null)
//                {
//                    checkFragment = new CheckFragment();
//                    fragmentTransaction.add(R.id.frameLayoutContent,checkFragment,checkFragment.getClass().getName());
//                    System.out.println("1");
//                }
//                else
//                {
//                    fragmentTransaction.show(checkFragment);
//                    System.out.println("11");
//                }
//                fragmentTransaction.addToBackStack(null);//create back key function
//                fragmentTransaction.commitAllowingStateLoss();//
//            }
//        });
//        linearLayoutBottom.addView(buttonCheck,params);


        //Tools button
        buttonTools = new Button(this);
        buttonTools.setText("Tools");
        buttonTools.setGravity(Gravity.CENTER);
        buttonTools.setClickable(true);
        buttonTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                hideAllFragment(fragmentTransaction);
                setSelected();
                buttonTools.setSelected(true);
                if (functionTreeViewFragment == null || functionTreeViewFragment.getView() == null)
                {
                    functionTreeViewFragment = new FunctionTreeViewFragment();
                    functionTreeViewFragment.setUserGridViewFragment(userGridViewFragment);
                    fragmentTransaction.add(R.id.frameLayoutContent,functionTreeViewFragment,functionTreeViewFragment.getClass().getName());
                    System.out.println("2");
                }
                else
                {
                    fragmentTransaction.show(functionTreeViewFragment);
                    System.out.println("22");
                }
                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commitAllowingStateLoss();
                fragmentTransaction.commit();
            }
        });
        linearLayoutBottom.addView(buttonTools,params);



//        //Setting button
//        buttonCheck = new Button(this);
//        buttonCheck.setText("Setting");
//        buttonCheck.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL);
//        buttonCheck.setClickable(true);
//        buttonCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                hideAllFragment(fragmentTransaction);
//                setSelected();
//                buttonCheck.setSelected(true);
//                if (checkFragment == null || checkFragment.getView() == null)
//                {
//                    checkFragment = new CheckFragment();
//                    fragmentTransaction.add(R.id.frameLayoutContent,checkFragment,checkFragment.getClass().getName());
//                }
//                else
//                {
//                    fragmentTransaction.show(checkFragment);
//                }
//                fragmentTransaction.addToBackStack(null);//create back key function
//                fragmentTransaction.commitAllowingStateLoss();//
//            }
//        });
//        linearLayoutBottom.addView(buttonCheck,params);

        buttonSetting = new Button(this);
        buttonSetting.setText("Setting");
        buttonSetting.setGravity(Gravity.CENTER);
        buttonSetting.setClickable(true);
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                hideAllFragment(fragmentTransaction);
                setSelected();
                buttonSetting.setSelected(true);
                if (settingFragment == null || settingFragment.getView() == null)
                {
                    settingFragment = new SettingFragment();
                    fragmentTransaction.add(R.id.frameLayoutContent, settingFragment,settingFragment.getClass().getName());
                }
                else
                {
                    fragmentTransaction.show(settingFragment);
                }
                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commitAllowingStateLoss();
                fragmentTransaction.commit();
            }
        });
        linearLayoutBottom.addView(buttonSetting,params);


//        buttonUser = new Button(this);
//        buttonUser.setText("User");
//        buttonUser.setGravity(Gravity.CENTER);
//        buttonUser.setClickable(true);
//        buttonUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                hideAllFragment(fragmentTransaction);
//                setSelected();
//                buttonUser.setSelected(true);
//                if (userGridViewFragment == null || userGridViewFragment.getView() == null )
//                {
//                    userGridViewFragment = new UserGridViewFragment();
//                    fragmentTransaction.add(R.id.frameLayoutContent,userGridViewFragment,userGridViewFragment.getClass().getName());
//                    System.out.println("3");
//                }
//                else
//                {
//                    fragmentTransaction.show(userGridViewFragment);
//                    System.out.println("33");
//                }
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commitAllowingStateLoss();
//            }
//        });
//        linearLayoutBottom.addView(buttonUser,params);

        // click user button first
        buttonUser.performClick();
    }

    /**
     * ser all button not selected
     */
    private void setSelected()
    {
        if (buttonSetting != null)
        {
            buttonSetting.setSelected(false);
        }
        if (buttonTools != null)
        {
            buttonTools.setSelected(false);
        }
        if (buttonUser != null)
        {
            buttonUser.setSelected(false);
        }


    }

    /**
     * hide all fragment
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction)
    {
        if (settingFragment != null)
        {
            fragmentTransaction.hide(settingFragment);
        }
        if (functionTreeViewFragment != null)
        {
            fragmentTransaction.hide(functionTreeViewFragment);
        }
        if (userGridViewFragment != null)
        {
            fragmentTransaction.hide(userGridViewFragment);
        }
    }

    /**
     * check user fragment edit or not
     * get fragment from back stack
     */
    @Override
    public void onBackPressed() {
        if(userGridViewFragment != null && userGridViewFragment.getShowDelete())
        {
            userGridViewFragment.onBackPressed();
            return;
        }

        if(getFragmentManager().getBackStackEntryCount() == 1)
        {
            finish();
        }
        else
        {
            getFragmentManager().popBackStackImmediate();
        }
    }

//    /**
//     * clean all fragment
//     * @param outState
//     */
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.remove(checkFragment)
//                .remove(functionTreeViewFragment)
//                .remove(userGridViewFragment);
//        fragmentTransaction.commitAllowingStateLoss();
//        super.onSaveInstanceState(outState);
//    }
}
