package com.tapwater.phoneerp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;


/**
 * Created by Tapwater on 17-1-21.
 */

public class UserGridViewFragment extends Fragment
{
    private DynamicGridView gridView;

    private ArrayList<UserGridViewItem> arrayListIcon = null;

    private boolean isShowDelete = false;

    private UserGridViewAdapter adapter;

    private boolean isRefresh = false;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        //create view
        View view = inflater.inflate(R.layout.user_index,container,false);
        //set grid view in
        gridView = (DynamicGridView) view.findViewById(R.id.dynamicgrid);
//        setHasOptionsMenu(true);
        //set user personal button
        if (arrayListIcon == null)
        {
            arrayListIcon = new ArrayList<UserGridViewItem>();
            arrayListIcon.add(new UserGridViewItem(R.drawable.image1,"1",new CheckFragment()));
            arrayListIcon.add(new UserGridViewItem(R.drawable.image2,"2",new CheckFragment()));
            arrayListIcon.add(new UserGridViewItem(R.drawable.image3,"3",new CheckFragment()));
            arrayListIcon.add(new UserGridViewItem(R.drawable.image4,"4",new CheckFragment()));
        }


//        arrayListIcon.add(new UserGridViewItem(R.drawable.image1,"1"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image2,"2"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image3,"3"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image4,"4"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image5,"5"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image6,"6"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image7,"7"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image8,"8"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image9,"9"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image10,"10"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image11,"11"));
//        arrayListIcon.add(new UserGridViewItem(R.drawable.image12,"12"));

        setAdapter();
//        //create user adapter, arrayList as data, 3 icon one line
//        adapter = new UserGridViewAdapter(getActivity(),arrayListIcon,3);
//        //********
//        //set adapter into gridView
//        gridView.setAdapter(adapter);
//        //set adapter into itself
//        adapter.setAdapter(adapter);


        setGridView();
//        //set icon click
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("User item + " + position);
//                new RunItemFragment(arrayListIcon.get(position).getItemFragment(),getFragmentManager());
//
//            }
//        });
//
//        //set icon long click
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                //长按触发拖曳交换位置操作。
//                if(!isShowDelete)
//                {
//                    //set gridView edit mode
//                    gridView.startEditMode(position);
//                    isShowDelete = true;
//                    //set adapter remove null
//                    //if adapter do not add/remove/replace then can not use notification to refresh
//                    adapter.remove(null);
//                    adapter.setShowDelete(isShowDelete);
//                }
//                return true;
//            }
//        });
//
//        //set gridView move drag change
//        gridView.setOnDragListener(new DynamicGridView.OnDragListener() {
//            @Override
//            public void onDragStarted(int position) {
//                //Toast.makeText(getApplication(), "drag started at position " + position,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDragPositionsChanged(int oldPosition, int newPosition) {
//                UserGridViewItem tempUserGridViewItem = arrayListIcon.get(oldPosition);
//                arrayListIcon.set(oldPosition,arrayListIcon.get(newPosition));
//                arrayListIcon.set(newPosition,tempUserGridViewItem);
//                Toast.makeText(getActivity(), String.format("from %d to %d", oldPosition, newPosition), Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }


    public void setAdapter()
    {
        //create user adapter, arrayList as data, 3 icon one line
        adapter = new UserGridViewAdapter(getActivity(),arrayListIcon,3);
        //********
        //set adapter into gridView
        gridView.setAdapter(adapter);
        //set adapter into itself
        adapter.setAdapter(adapter);
    }

    public void setGridView()
    {
        //set icon click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("User item + " + position);
                new RunItemFragment(arrayListIcon.get(position).getItemFragment(),getFragmentManager());

            }
        });

        //set icon long click
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //长按触发拖曳交换位置操作。
                if(!isShowDelete)
                {
                    //set gridView edit mode
                    gridView.startEditMode(position);
                    isShowDelete = true;
                    //set adapter remove null
                    //if adapter do not add/remove/replace then can not use notification to refresh
                    adapter.remove(null);
                    adapter.setShowDelete(isShowDelete);
                }
                return true;
            }
        });

        //set gridView move drag change
        gridView.setOnDragListener(new DynamicGridView.OnDragListener() {
            @Override
            public void onDragStarted(int position) {
                //Toast.makeText(getApplication(), "drag started at position " + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDragPositionsChanged(int oldPosition, int newPosition) {
                UserGridViewItem tempUserGridViewItem = arrayListIcon.get(oldPosition);
                arrayListIcon.set(oldPosition,arrayListIcon.get(newPosition));
                arrayListIcon.set(newPosition,tempUserGridViewItem);
                Toast.makeText(getActivity(), String.format("from %d to %d", oldPosition, newPosition), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean getShowDelete()
    {
        return isShowDelete;
    }

    //@@@@@@@@@@@
// add setHasOptionsMenu(true);
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.add(1,1,1,"黄色");
//        menu.add(1,2,5,"灰色");
//        menu.add(1,3,6,"蓝绿色");
//        menu.add(1,4,7,"黑色");
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case 1:
//                Toast.makeText(getActivity(),"one",Toast.LENGTH_LONG).show();
//                break;
//            case 2:
//                Toast.makeText(getActivity(),"two",Toast.LENGTH_LONG).show();
//                break;
//            case 3:
//                Toast.makeText(getActivity(),"three",Toast.LENGTH_LONG).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//@@@@@@@@@@@@@@@@@

    public void onBackPressed()
    {
        //此处将取消拖曳编辑模式，取消方式为按返回键。
        if (isShowDelete)
//            if (gridView.isEditMode())
            {
                gridView.stopEditMode();
                isShowDelete = false;
                adapter.remove(null);
                adapter.setShowDelete(isShowDelete);
            System.out.println(isShowDelete+" isShowDelete onBackPressed UserGridViewFragment");
        } else {
            Toast.makeText(getActivity(),"Error onBackPressed UserGridViewFragment",Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<UserGridViewItem> getArrayListIcon() {
        return arrayListIcon;
    }

    public void setArrayListIcon(ArrayList<UserGridViewItem> arrayListIcon) {
        this.arrayListIcon = arrayListIcon;
    }

    public void addArrayListIcon(UserGridViewItem tempUserGridViewItem)
    {
        this.arrayListIcon.add(tempUserGridViewItem);
        setAdapter();
//        //create user adapter, arrayList as data, 3 icon one line
//        adapter = new UserGridViewAdapter(getActivity(),arrayListIcon,3);
//        //********
//        //set adapter into gridView
//        gridView.setAdapter(adapter);
//        //set adapter into itself
//        adapter.setAdapter(adapter);
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
////        adapter.remove(null);
////        adapter.reFresh();
//        if(!hidden)
//        {
//            adapter.remove(null);
//            adapter.reFresh();
//            Toast.makeText(getActivity(),"onHiddenChanged",Toast.LENGTH_LONG).show();
//        }
//    }
}
