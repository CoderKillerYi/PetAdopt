package com.wwm.petadopt;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity
{
	private static android.app.FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		initView();
		addRadioGroupListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		// add som comment
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed()
	{
		if(fragmentManager.findFragmentByTag("homepageFragment") != null && fragmentManager.findFragmentByTag("homepageFragment").isVisible())
		{
			MainActivity.this.finish();
		}
		else
		{
			super.onBackPressed();
		}
	}

	public void initView()
	{
		HomepageFragment homepageFragment = new HomepageFragment();
		FragmentTransaction fragmentTraction = fragmentManager.beginTransaction();
		fragmentTraction.add(R.id.layout_fragment_space, homepageFragment, "homepageFragment");
		fragmentTraction.addToBackStack("homepageFragment");
		fragmentTraction.commit();
	}

	public void addRadioGroupListener()
	{
		RadioGroup navigatorRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_navigator);
		navigatorRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			FragmentTransaction ft = null;

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				switch (checkedId)
				{
					case R.id.radiobutton_homepage:
						if (fragmentManager.findFragmentByTag("homepageFragment") != null && fragmentManager.findFragmentByTag("homepageFragment").isVisible())
						{
							return;
						}
						popAllFragmentsExceptTheBottomOne();
						break;
					case R.id.radiobutton_classification:
						popAllFragmentsExceptTheBottomOne();
						ft = fragmentManager.beginTransaction();
						ft.hide(fragmentManager.findFragmentByTag("homepageFragment"));
						ClassificationFragment classificationFragment = new ClassificationFragment();
						ft.add(R.id.layout_fragment_space, classificationFragment, "classificationFragment");
						ft.addToBackStack("classificationFragment");
						ft.commit();
						break;
					case R.id.radiobutton_collection:
						popAllFragmentsExceptTheBottomOne();
						ft = fragmentManager.beginTransaction();
						ft.hide(fragmentManager.findFragmentByTag("homepageFragment"));
						CollectionFragment collectionFragment = new CollectionFragment();
						ft.add(R.id.layout_fragment_space, collectionFragment, "collectionFragment");
						ft.addToBackStack("collectionFragment");
						ft.commit();
						break;
					case R.id.radiobutton_account:
						popAllFragmentsExceptTheBottomOne();
						ft = fragmentManager.beginTransaction();
						ft.hide(fragmentManager.findFragmentByTag("homepageFragment"));
						AccountFragment accountFragment = new AccountFragment();
						ft.add(R.id.layout_fragment_space, accountFragment, "accountFragment");
						ft.addToBackStack("accountFragment");
						ft.commit();
						break;
					default:
						break;
				}

			}
		});
	}

	public static void popAllFragmentsExceptTheBottomOne()
	{
		for (int i = 0, count = fragmentManager.getBackStackEntryCount() - 1; i < count; i++)
		{
			fragmentManager.popBackStack();
		}
	}
}
