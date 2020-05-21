package com.ttd.androidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Objects;

/**
 * @author wt
 * @time 2020/5/15
 */
public class PageMenuFragment extends Fragment {
    private List<PageItem> pageItems;
    private RecyclerView rvMenu;
    private PageMenuAdapter adapter;
    private MainActivity activity;

    public void setPageItems(List<PageItem> pageItems) {
        this.pageItems = pageItems;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_menu, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = (MainActivity) getActivity();

        rvMenu = findViewById(R.id.rv_menu);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PageMenuAdapter(pageItems);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            PageItem item = adapter.getItem(position);
            if(item.next != null){
                PageMenuFragment fragment = new PageMenuFragment();
                fragment.setPageItems(item.next);
                activity.showFragment(fragment);
            }else {
                startActivity(new Intent(getContext(), item.mClass));
            }
        });
        rvMenu.setAdapter(adapter);

    }

    private class PageMenuAdapter extends BaseQuickAdapter<PageItem, BaseViewHolder> {

        public PageMenuAdapter(@Nullable List<PageItem> data) {
            super(R.layout.adapter_page_menu, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PageItem item) {
            helper.setText(R.id.tv_title, item.title);
        }
    }

    protected <T extends View> T findViewById(int id) {
        try {
            View view = Objects.requireNonNull(getView()).findViewById(id);
            if (view == null) {
                view = Objects.requireNonNull(getActivity()).findViewById(id);
            }
            return (T) view;
        } catch (Exception ex) {
            return null;
        }
    }
}
