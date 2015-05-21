package com.wwm.petadopt;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class ClassificationFragment extends Fragment
{
	HorizontalListView hListViewDog;
	HorizontalListView hListViewCat;
	HorizontalListViewAdapter hListViewAdapter;
	private static android.app.FragmentManager fragmentManager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_classification, container, false);
		fragmentManager=getActivity().getFragmentManager();
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


		Button btn_more=(Button)view.findViewById(R.id.btn_more);
		//bt_second.setText(MainActivity.temp);
		btn_more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentTransaction ft = fragmentManager.beginTransaction();

				popAllFragmentsExceptTheBottomOne();
				ft = fragmentManager.beginTransaction();
				ft.hide(fragmentManager.findFragmentByTag("homepageFragment"));
				MoreFragment morefragment=new MoreFragment();
				ft.add(R.id.layout_fragment_space, morefragment, "morefragment");
				ft.addToBackStack("morefragment");
				ft.commit();
			}
		});

		return view;
	}

	public static void popAllFragmentsExceptTheBottomOne()
	{
		for (int i = 0, count = fragmentManager.getBackStackEntryCount() - 1; i < count; i++)
		{
			fragmentManager.popBackStack();
		}
	}
}
