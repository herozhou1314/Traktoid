package com.florianmski.tracktoid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.util.Log;

import com.florianmski.tracktoid.trakt.TraktManager;
import com.florianmski.tracktoid.trakt.TraktManager.TraktListener;
import com.jakewharton.trakt.entities.TvShow;

public class TraktFragment extends Fragment implements TraktListener
{
	protected TraktManager tm = TraktManager.getInstance();
	protected FragmentListener listener;

	public TraktFragment() 
	{

	}

	public TraktFragment(FragmentListener listener)
	{
		super();
		this.listener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		tm.addObserver(this);
	}

	@Override
	public void onAttach(SupportActivity activity)
	{
		super.onAttach(activity);
//		Bundle b = activity.getIntent().getExtras();
//		if(b != null)
//			setArguments(b);
	}

	@Override
	public void onDestroy()
	{
		tm.removeObserver(this);
		super.onDestroy();
	}

	public void refreshFragment(Bundle bundle)
	{

	}

	protected void setTitle(String title)
	{
		getSupportActivity().getSupportActionBar().setTitle(title);
	}

	protected void setSubtitle(String subtitle)
	{
		getSupportActivity().getSupportActionBar().setSubtitle(subtitle);
	}

	public interface FragmentListener
	{
		public void onFragmentAction(Fragment f, Bundle bundle, int actionToPerformed);
	}

	@Override
	public void onAfterTraktRequest(boolean success) {}

	@Override
	public void onBeforeTraktRequest() {}

	@Override
	public void onErrorTraktRequest(Exception e, String message) 
	{
		//TODO
		//can't do this because onErrorTraktRequest is not executed in the ui thread
		//Utils.removeLoading();
	}

	@Override
	public void onShowRemoved(TvShow show) {}

	@Override
	public void onShowUpdated(TvShow show) {}
}
