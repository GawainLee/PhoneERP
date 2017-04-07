package com.tapwater.phoneerp;

import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tapwater on 17-1-1.
 */

public class TreeViewAdapter extends BaseAdapter {
    /** 元素数据源 */
    private ArrayList<TreeViewElement> elementsData;
    /** 树中元素 */
    private ArrayList<TreeViewElement> elements;
    /** LayoutInflater */
    private LayoutInflater inflater;
    /** item的行首缩进基数 */
    private int indentionBase;
    /** fragment manager **/
    private FragmentManager fragmentManager;

    public TreeViewAdapter(ArrayList<TreeViewElement> elements, ArrayList<TreeViewElement> elementsData, LayoutInflater inflater,FragmentManager fragmentManager) {
        this.elements = elements;
        this.elementsData = elementsData;
        this.inflater = inflater;
        indentionBase = 50;
        this.fragmentManager = fragmentManager;
    }

    public ArrayList<TreeViewElement> getTreeViewElements() {
        return elements;
    }

    public ArrayList<TreeViewElement> getTreeViewElementsData() {
        return elementsData;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.treeview_item, null,false);
//            convertView = inflater.inflate(R.layout.treeview_item, null);
            holder.disclosureImg = (ImageView) convertView.findViewById(R.id.disclosureImg);
            holder.contentText = (TextView) convertView.findViewById(R.id.contentText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TreeViewElement element = elements.get(position);
        int level = element.getLevel();
        String textTabSpace = "";
        for (int i = 0; i < (level + 1); i++)
        {
            textTabSpace += " ";
        }
        holder.contentText.setText( textTabSpace + element.getContentText());
        if (element.isHasChildren() && !element.isExpanded())
        {
            holder.disclosureImg.setImageResource(R.drawable.tree_item_close);
            //这里要主动设置一下icon可见，因为convertView有可能是重用了"设置了不可见"的view，下同。
            holder.disclosureImg.setVisibility(View.VISIBLE);
        }
        else if (element.isHasChildren() && element.isExpanded())
        {
            holder.disclosureImg.setImageResource(R.drawable.tree_item_open);
            holder.disclosureImg.setVisibility(View.VISIBLE);
        }
        else if (!element.isHasChildren())
        {
            holder.disclosureImg.setImageResource(R.drawable.tree_item_close);
            holder.disclosureImg.setVisibility(View.INVISIBLE);
        }
// cover ImageView
//        holder.disclosureImg.setPadding(
//                indentionBase * (level + 1),
//                holder.disclosureImg.getPaddingTop(),
//                holder.disclosureImg.getPaddingRight(),
//                holder.disclosureImg.getPaddingBottom());
        return convertView;
    }

    /**
     * Holder
     *
     */
    static class ViewHolder{
        ImageView disclosureImg;
        TextView contentText;
    }

    public void runItemFragment(Object tempFragment)
    {
        new RunItemFragment(tempFragment,fragmentManager);
//        if (tempFragment instanceof CheckFragment)
//        {
//            CheckFragment checkFragment = (CheckFragment) tempFragment;
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.frameLayoutContent,checkFragment,checkFragment.getClass().getName());
//            fragmentTransaction.addToBackStack(null);//create back key function
//            fragmentTransaction.commitAllowingStateLoss();//
//        }
    }
}