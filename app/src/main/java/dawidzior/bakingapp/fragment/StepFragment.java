package dawidzior.bakingapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import dawidzior.bakingapp.R;
import dawidzior.bakingapp.model.Step;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class StepFragment extends Fragment {

    public static final String STEP_ARGUMENT = "STEP_ARGUMENT";
    public static final String STEPS_LIST = "STEPS_LIST";
    public static final String STEP_NUMBER = "STEP_NUMBER";

    private Step step;

    private SimpleExoPlayer simpleExoPlayer;

    private String videoUrl;

    private String description;

    @BindView(R.id.player_view)
    PlayerView playerView;

    @BindView(R.id.description_view)
    TextView descriptionView;

    public static StepFragment newInstance(Step step) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putParcelable(STEP_ARGUMENT, Parcels.wrap(step));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            step = Parcels.unwrap(getArguments().getParcelable(STEP_ARGUMENT));
        } else {
            step = Parcels.unwrap(savedInstanceState.getParcelable(STEP_ARGUMENT));
        }
        videoUrl = step.getVideoURL();
        description = step.getDescription();
        getActivity().setTitle(step.getShortDescription());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, root);


        if (videoUrl != null && !TextUtils.isEmpty(videoUrl)) {
            playerView.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE &&
                    !getResources().getBoolean(R.bool.isTablet)) {
                playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        } else {
            playerView.setVisibility(View.GONE);
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoUrl != null && !TextUtils.isEmpty(videoUrl)) {
            playerView.setVisibility(View.VISIBLE);
            if (simpleExoPlayer == null) {
                if (videoUrl != null && !TextUtils.isEmpty(videoUrl)) {
                    simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());

                    DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                            Util.getUserAgent(getContext(), getString(R.string.app_name)), null);
                    MediaSource mediaSource = new ExtractorMediaSource
                            .Factory(dataSourceFactory)
                            .createMediaSource(Uri.parse(videoUrl));
                    simpleExoPlayer.prepare(mediaSource);
                    simpleExoPlayer.setPlayWhenReady(true);

                    playerView.setPlayer(simpleExoPlayer);
                }
            }
        } else {
            playerView.setVisibility(View.GONE);
        }

        descriptionView.setText(description);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Fixes a little black blinking in the middle of the screen when closing activity.
        //https://github.com/google/ExoPlayer/issues/3699
        playerView.setVisibility(View.GONE);
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STEP_ARGUMENT, Parcels.wrap(step));
        super.onSaveInstanceState(outState);
    }
}
