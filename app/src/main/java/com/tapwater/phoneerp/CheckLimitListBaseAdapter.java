package com.tapwater.phoneerp;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Tapwater on 16-12-22.
 */

public class CheckLimitListBaseAdapter extends BaseAdapter {

    private LinkedList<CheckLimitListItem> limitListItems;
    private Context mContext;

    public CheckLimitListBaseAdapter(LinkedList<CheckLimitListItem> limitListItems, Context mContext) {
        this.limitListItems = limitListItems;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return limitListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return limitListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHost viewHost = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.check_list_item,parent,false);
            viewHost = new ViewHost();
            viewHost.textViewCheckLimit = (TextView) convertView.findViewById(R.id.checkListItemTextView);
            viewHost.editTextCheckLimit = (EditText) convertView.findViewById(R.id.checkListItemEditText);
            viewHost.buttonCheckLimit = (Button) convertView.findViewById(R.id.checkListItemButton);
            convertView.setTag(viewHost);
        }
        else
        {
            viewHost = (ViewHost) convertView.getTag();
        }

        final CheckLimitListItem listItem = limitListItems.get(position);
        if (viewHost.editTextCheckLimit.getTag() instanceof TextWatcher)
        {
            viewHost.editTextCheckLimit.removeTextChangedListener((TextWatcher)(viewHost.editTextCheckLimit.getTag()));
        }

        viewHost.editTextCheckLimit.setHint(position + ".");

        if (TextUtils.isEmpty(listItem.getCheckLimitEditString()))
        {
            viewHost.editTextCheckLimit.setText("");
        }
        else
        {
            viewHost.editTextCheckLimit.setText(listItem.getCheckLimitEditString());
        }
        if (listItem.isFocus())
        {
            if (!viewHost.editTextCheckLimit.isFocused())
            {
                viewHost.editTextCheckLimit.requestFocus();
            }
            CharSequence text = listItem.getCheckLimitEditString();
            viewHost.editTextCheckLimit.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());
        }
        else
        {
            if (viewHost.editTextCheckLimit.isFocused())
            {
                viewHost.editTextCheckLimit.clearFocus();
            }
        }
        final ViewHost finalViewHost = viewHost;
        final ViewHost finalViewHost1 = viewHost;
        viewHost.editTextCheckLimit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    final boolean focus = listItem.isFocus();
                    check(position);
                    if (!focus && !finalViewHost.editTextCheckLimit.isFocused())
                    {
                        finalViewHost1.editTextCheckLimit.requestFocus();
                        finalViewHost1.editTextCheckLimit.onWindowFocusChanged(true);
                    }
                }
                return false;
            }
        });
        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s))
                {
                    listItem.setCheckLimitEditString(null);
                }
                else
                {
                    listItem.setCheckLimitEditString(String.valueOf(s));
                }
            }
        };
        viewHost.editTextCheckLimit.addTextChangedListener(textWatcher);
        viewHost.editTextCheckLimit.setTag(textWatcher);


        viewHost.textViewCheckLimit.setText(listItem.getCheckLimitTextString());
        viewHost.buttonCheckLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCheckLimitPosition(position);
            }
        });

//        viewHost.imageViewHead.setBackgroundResource(listItems.get(position).getHead());
//        viewHost.editText.setText(listItems.get(position).getEditText());
//        viewHost.checkBox.setChecked(listItems.get(position).isCheckBox());

        return convertView;
    }


    private void check(int p)
    {
        for (CheckLimitListItem listItem : limitListItems)
        {
            listItem.setFocus(false);
        }
        limitListItems.get(p).setFocus(true);
    }

    public void addCheckLimit(CheckLimitListItem checkLimitListItem)
    {
        if (limitListItems == null)
        {
            limitListItems = new LinkedList<CheckLimitListItem>();
        }
        limitListItems.add(checkLimitListItem);
        notifyDataSetChanged();
    }


    public void removeCheckLimitPosition(int i)
    {
        if (limitListItems != null)
        {
            limitListItems.remove(i);
        }
        notifyDataSetChanged();
    }

    static class ViewHost
    {
        TextView textViewCheckLimit;
        EditText editTextCheckLimit;
        Button buttonCheckLimit;
    }


}
