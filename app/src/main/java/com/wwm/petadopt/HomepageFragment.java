package com.wwm.petadopt;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HomepageFragment extends Fragment
{


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_homepage, container, false);


		
		return view;
	}
}
