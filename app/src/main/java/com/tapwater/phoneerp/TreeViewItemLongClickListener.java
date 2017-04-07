package com.tapwater.phoneerp;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Tapwater on 17-1-1.
 */


public class TreeViewItemLongClickListener implements AdapterView.OnItemLongClickListener {

    private UserGridViewFragment userGridViewFragment;

    private TreeViewAdapter treeViewAdapter;

    public TreeViewItemLongClickListener(TreeViewAdapter treeViewAdapter,UserGridViewFragment userGridViewFragment) {
        this.userGridViewFragment = userGridViewFragment;
        this.treeViewAdapter = treeViewAdapter;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        TreeViewElement element = (TreeViewElement) treeViewAdapter.getItem(position);
        System.out.println("LongClick onItemLongClick");
        if (!element.isHasChildren()) {
            System.out.println("Action onItemLongClick");
//            treeViewAdapter.runItemFragment(element.getElementFragment());
            userGridViewFragment.addArrayListIcon(new UserGridViewItem(R.drawable.image1,element.getContentText(),element.getElementFragment()));
            System.out.println(userGridViewFragment.getArrayListIcon().size() + "******onItemLongClick");
            userGridViewFragment.setRefresh(true);
        }
        return true;
    }
}