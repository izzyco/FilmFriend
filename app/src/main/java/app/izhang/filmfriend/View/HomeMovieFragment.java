package app.izhang.filmfriend.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.izhang.filmfriend.Model.Movie;
import app.izhang.filmfriend.Presenter.HomePresenter;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Util.NetworkUtil;
import app.izhang.filmfriend.View.Adapter.HomeMovieViewAdapter;
import app.izhang.filmfriend.View.Adapter.MyGroupRecyclerViewAdapter;
import app.izhang.filmfriend.View.Base.BaseDataView;
import app.izhang.filmfriend.View.dummy.DummyContent;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeMovieFragment extends Fragment implements BaseDataView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private HomeMovieViewAdapter mMovieViewAdapter;

    @BindView(R.id.movies_rv) RecyclerView mMovieRV;

    public HomeMovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeMovieFragment newInstance(String param1, String param2) {
        HomeMovieFragment fragment = new HomeMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_movie, container, false);
        ButterKnife.bind(this, view);

        // Set the adapter
        Context context = view.getContext();
        mMovieRV.setLayoutManager(new LinearLayoutManager(context));
        mMovieViewAdapter = new HomeMovieViewAdapter(getContext(), null);
        mMovieRV.setAdapter(mMovieViewAdapter);

        getData(1);

        return view;
    }


    @Override
    public void showLoadingState(boolean visible) {

    }

    @Override
    public void getData(int pageNum) {
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getMovieData(pageNum);
    }

    @Override
    public void getDataSuccess(ArrayList movies) {
        mMovieViewAdapter.addData(movies);
        mMovieViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataFailure() {
        Toast.makeText(getContext(),
                "Experiencing network errors. Please confirm you have a valid internet connection and try again.",
                Toast.LENGTH_SHORT)
                .show();
    }

}
