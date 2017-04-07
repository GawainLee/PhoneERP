package com.tapwater.phoneerp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Tapwater on 17-1-1.
 */

public class FunctionTreeViewFragment extends Fragment {

    /** 树中的元素集合 */
    private ArrayList<TreeViewElement> elements;
    /** 数据源元素集合 */
    private ArrayList<TreeViewElement> elementsData;

    private UserGridViewFragment userGridViewFragment;

    public void SetUserGridViewFragment(UserGridViewFragment userGridViewFragment)
    {
        this.userGridViewFragment = userGridViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_function_treeview,container,false);

//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        init();

        ListView treeview = (ListView) view.findViewById(R.id.treeview);
        TreeViewAdapter treeViewAdapter = new TreeViewAdapter(
                elements, elementsData, inflater,getFragmentManager());
        TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter);
        treeview.setAdapter(treeViewAdapter);
        treeview.setOnItemClickListener(treeViewItemClickListener);
        TreeViewItemLongClickListener treeViewItemLongClickListener = new TreeViewItemLongClickListener(treeViewAdapter,userGridViewFragment);
        treeview.setOnItemLongClickListener(treeViewItemLongClickListener);


        return view;
    }

    private void init() {
        elements = new ArrayList<TreeViewElement>();
        elementsData = new ArrayList<TreeViewElement>();

        //添加节点  -- 节点名称，节点level，节点id，父节点id，是否有子节点，是否展开

        TreeViewElement e1 = new TreeViewElement("山东省", TreeViewElement.TOP_LEVEL, 0, TreeViewElement.NO_PARENT, false, false,new CheckFragment());


//        //添加最外层节点
//        TreeViewElement e1 = new TreeViewElement("山东省", TreeViewElement.TOP_LEVEL, 0, TreeViewElement.NO_PARENT, true, true);
//
//        //添加第一层节点
//        TreeViewElement e2 = new TreeViewElement("青岛市", TreeViewElement.TOP_LEVEL + 1, 1, e1.getId(), true, false);
//        //添加第二层节点
//        TreeViewElement e3 = new TreeViewElement("市南区", TreeViewElement.TOP_LEVEL + 2, 2, e2.getId(), true, false);
//        //添加第三层节点
//        TreeViewElement e4 = new TreeViewElement("香港中路", TreeViewElement.TOP_LEVEL + 3, 3, e3.getId(), false, false);

        //添加第一层节点
//        TreeViewElement e5 = new TreeViewElement("烟台市", TreeViewElement.TOP_LEVEL + 1, 4, e1.getId(), true, false);
//        //添加第二层节点
//        TreeViewElement e6 = new TreeViewElement("芝罘区", TreeViewElement.TOP_LEVEL + 2, 5, e5.getId(), true, false);
//        //添加第三层节点
//        TreeViewElement e7 = new TreeViewElement("凤凰台街道", TreeViewElement.TOP_LEVEL + 3, 6, e6.getId(), false, false);

        //添加第一层节点
//        TreeViewElement e8 = new TreeViewElement("威海市", TreeViewElement.TOP_LEVEL + 1, 7, e1.getId(), false, false);

//        //添加最外层节点
//        TreeViewElement e9 = new TreeViewElement("广东省", TreeViewElement.TOP_LEVEL, 8, TreeViewElement.NO_PARENT, true, false);
//        //添加第一层节点
//        TreeViewElement e10 = new TreeViewElement("深圳市", TreeViewElement.TOP_LEVEL + 1, 9, e9.getId(), true, false);
//        //添加第二层节点
//        TreeViewElement e11 = new TreeViewElement("南山区", TreeViewElement.TOP_LEVEL + 2, 10, e10.getId(), true, false);
//        //添加第三层节点
//        TreeViewElement e12 = new TreeViewElement("深南大道", TreeViewElement.TOP_LEVEL + 3, 11, e11.getId(), true, false);
//        //添加第四层节点
//        TreeViewElement e13 = new TreeViewElement("10000号", TreeViewElement.TOP_LEVEL + 4, 12, e12.getId(), false, false);
//
//        //添加最外层节点
//        TreeViewElement e14 = new TreeViewElement("广东省", TreeViewElement.TOP_LEVEL, 13, TreeViewElement.NO_PARENT, true, false);
//        //添加第一层节点
//        TreeViewElement e15 = new TreeViewElement("深圳市", TreeViewElement.TOP_LEVEL + 1, 14, e14.getId(), true, false);
//        //添加第二层节点
//        TreeViewElement e16 = new TreeViewElement("罗湖区", TreeViewElement.TOP_LEVEL + 2, 15, e15.getId(), true, false);
//        //添加第三层节点
//        TreeViewElement e17 = new TreeViewElement("莲塘", TreeViewElement.TOP_LEVEL + 3, 16, e16.getId(), true, false);
//        //添加第四层节点
//        TreeViewElement e18 = new TreeViewElement("鹿茵翠地", TreeViewElement.TOP_LEVEL + 4, 17, e17.getId(), false, false);

        //添加初始树元素
        elements.add(e1);
//        elements.add(e2);
//        elements.add(e5);
//        elements.add(e8);
//        elements.add(e9);
//        elements.add(e14);

        //创建数据源
        elementsData.add(e1);
//        elementsData.add(e2);
//        elementsData.add(e3);
//        elementsData.add(e4);
//        elementsData.add(e5);
//        elementsData.add(e6);
//        elementsData.add(e7);
//        elementsData.add(e8);
//        elementsData.add(e9);
//        elementsData.add(e10);
//        elementsData.add(e11);
//        elementsData.add(e12);
//        elementsData.add(e13);
//        elementsData.add(e14);
//        elementsData.add(e15);
//        elementsData.add(e16);
//        elementsData.add(e17);
//        elementsData.add(e18);
    }

    public UserGridViewFragment getUserGridViewFragment() {
        return userGridViewFragment;
    }

    public void setUserGridViewFragment(UserGridViewFragment userGridViewFragment) {
        this.userGridViewFragment = userGridViewFragment;
    }
}
