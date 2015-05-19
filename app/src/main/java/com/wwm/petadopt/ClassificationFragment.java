package com.wwm.petadopt;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ClassificationFragment extends Fragment
{
	HorizontalListView hListViewDog;
	HorizontalListView hListViewCat;
	HorizontalListViewAdapter hListViewAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_classification, container, false);
		
		hListViewDog = (HorizontalListView) view.findViewById(R.id.horizontal_listview_dog);
		String[] dog_titles =
		{ "狗图片1", "狗图片2", "狗图片3" };
		final int[] dog_ids =
		{ R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher };
		hListViewAdapter = new HorizontalListViewAdapter(view.getContext(), dog_titles, dog_ids);
		hListViewDog.setAdapter(hListViewAdapter);
		hListViewDog.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				hListViewAdapter.setSelectIndex(position);
				hListViewAdapter.notifyDataSetChanged();
			}
		});
		
		hListViewCat = (HorizontalListView) view.findViewById(R.id.horizontal_listview_cat);
		String[] titles =
		{ "猫图片1", "猫图片2", "猫图片3" };
		final int[] ids =
		{ R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher };
		hListViewAdapter = new HorizontalListViewAdapter(view.getContext(), titles, ids);
		hListViewCat.setAdapter(hListViewAdapter);
		hListViewCat.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				hListViewAdapter.setSelectIndex(position);
				hListViewAdapter.notifyDataSetChanged();
			}
		});
		
		return view;
	}
}
