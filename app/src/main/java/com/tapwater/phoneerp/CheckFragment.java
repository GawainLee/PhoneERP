package com.tapwater.phoneerp;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tapwater on 16-12-18.
 */

public class CheckFragment extends Fragment {

    private Button buttonAdd;
    private LogButton submitCheckButton;
    private Button buttonRemove;

    private AlertDialog alerDialog = null;
    private AlertDialog.Builder aBuilder = null;
    private boolean[] checkItems = null;

    private List<CheckLimitListItem> limitListItems = null;
    private CheckLimitListBaseAdapter checkLimitListBaseAdapter = null;
    private ListView listView = null;

    private Context mContext;

    @TargetApi(Build.VERSION_CODES.M)
    public CheckFragment() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check,container,false);

        mContext = getActivity();

        listView = (ListView) view.findViewById(R.id.listView_content);
        limitListItems = new LinkedList<CheckLimitListItem>();
        limitListItems.add(new CheckLimitListItem("Bearing","Bearing",false));
        limitListItems.add(new CheckLimitListItem("Warehouse","Warehouse",false));
        limitListItems.add(new CheckLimitListItem("Location","Location",false));
        checkLimitListBaseAdapter = new CheckLimitListBaseAdapter((LinkedList<CheckLimitListItem>) limitListItems,mContext);
        listView.setAdapter(checkLimitListBaseAdapter);


        buttonAdd = (Button) view.findViewById(R.id.buttonAdd);
        submitCheckButton = (LogButton) view.findViewById(R.id.buttonSubmitCheck);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                alerDialog = null;

                aBuilder = new AlertDialog.Builder(getActivity());
                final String[] fruits = new String[] {"Apple","Orange","Banana","Pear"};

                alerDialog = aBuilder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("Set List Title")
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,fruits[which],Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkLimitListBaseAdapter.addCheckLimit(new CheckLimitListItem("Company","Company",false));
                            }
                        }).create();
                alerDialog.show();
            }
        });
        buttonAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                alerDialog = null;
                aBuilder = new AlertDialog.Builder(mContext);
                final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
                checkItems = new boolean[]{false,false,false,false};
                alerDialog = aBuilder.setTitle("Menu")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(mContext,"Select "+menu[which],Toast.LENGTH_LONG).show();
                                checkItems[which] = true;
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String allSelected = "";
                                for (int i = 0; i < checkItems.length; i++)
                                {
                                    if (checkItems[i])
                                    {
                                        allSelected += " " + menu[i];
                                    }
                                }
                                Toast.makeText(mContext,allSelected,Toast.LENGTH_LONG).show();
                            }
                        }).create();
                alerDialog.show();
                return true;
            }
        });
        return view;
    }
}
