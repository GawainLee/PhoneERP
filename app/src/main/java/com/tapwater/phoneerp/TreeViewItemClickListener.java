package com.tapwater.phoneerp;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by Tapwater on 17-1-1.
 */

public class TreeViewItemClickListener implements AdapterView.OnItemClickListener {
    /**
     * adapter
     */
    private TreeViewAdapter treeViewAdapter;

    public TreeViewItemClickListener(TreeViewAdapter treeViewAdapter) {
        this.treeViewAdapter = treeViewAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //点击的item代表的元素
        TreeViewElement element = (TreeViewElement) treeViewAdapter.getItem(position);
        //树中的元素
        ArrayList<TreeViewElement> elements = treeViewAdapter.getTreeViewElements();
        //元素的数据源
        ArrayList<TreeViewElement> elementsData = treeViewAdapter.getTreeViewElementsData();
        //点击没有子项的item直接返回
        if (!element.isHasChildren()) {
            System.out.println("Action");
            treeViewAdapter.runItemFragment(element.getElementFragment());
            return;
        }
        if (element.isExpanded()) {
            element.setExpanded(false);
            //删除节点内部对应子节点数据，包括子节点的子节点...
            ArrayList<TreeViewElement> elementsToDel = new ArrayList<TreeViewElement>();
            for (int i = position + 1; i < elements.size(); i++) {
                if (element.getLevel() >= elements.get(i).getLevel())
                {
                    break;
                }
                elementsToDel.add(elements.get(i));
            }
            elements.removeAll(elementsToDel);
            treeViewAdapter.notifyDataSetChanged();
        } else {
            element.setExpanded(true);
            //从数据源中提取子节点数据添加进树，注意这里只是添加了下一级子节点，为了简化逻辑
            int i = 1;//注意这里的计数器放在for外面才能保证计数有效
            for (TreeViewElement e : elementsData) {
                if (e.getParendId() == element.getId()) {
                    e.setExpanded(false);
                    elements.add(position + i, e);
                    i++;
                }
            }
            treeViewAdapter.notifyDataSetChanged();
        }
    }
}