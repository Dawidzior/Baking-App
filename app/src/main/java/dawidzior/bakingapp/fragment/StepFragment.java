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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import dawidzior.bakingapp.R;
import dawidzior.bakingapp.model.Step;
import lombok.Getter;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class StepFragment extends Fragment {

    public static final String STEP_ARGUMENT = "STEP_ARGUMENT";
    public static final String STEPS_LIST = "STEPS_LIST";
    public static final String STEP_NUMBER = "STEP_NUMBER";
    public static final String PLAYER_STATE = "PLAYER_STATE";
    public static final String PLAYER_POSITION = "PLAYER_POSITION";

    private SimpleExoPlayer simpleExoPlayer;

    private String videoUrl;

    private String description;

    private String thumbnailUrl;

    @Getter
    private boolean shouldAutoPlay = true;

    @Getter
    private long resumePosition;

    @BindView(R.id.player_view)
    PlayerView playerView;

    @BindView(R.id.thumbnail_image)
    ImageView thumbnailImage;

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
        Step step = Parcels.unwrap(getArguments().getParcelable(STEP_ARGUMENT));

        if (savedInstanceState != null) {
            shouldAutoPlay = savedInstanceState.getBoolean(PLAYER_STATE);
            resumePosition = savedInstanceState.getLong(PLAYER_POSITION, C.TIME_UNSET);
        }

        videoUrl = step.getVideoURL();
        thumbnailUrl = step.getThumbnailUrl();
        description = step.getDescription();

        //If not tablet, override toolbar title with step name.
        if (!getResources().getBoolean(R.bool.isTablet))
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
                    simpleExoPlayer.setPlayWhenReady(shouldAutoPlay);

                    playerView.setPlayer(simpleExoPlayer);

                    if (resumePosition != C.TIME_UNSET) {
                        simpleExoPlayer.seekTo(0, resumePosition);
                    }
                }
            }
        } else {
            playerView.setVisibility(View.GONE);
        }

        if (playerView.getVisibility() == View.GONE && thumbnailUrl != null && !TextUtils.isEmpty(thumbnailUrl))
            Picasso.with(getActivity()).load(thumbnailUrl).into(thumbnailImage);
        descriptionView.setText(description);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Fixes a little black blinking in the middle of the screen when closing activity.
        //https://github.com/google/ExoPlayer/issues/3699
        playerView.setVisibility(View.GONE);
        if (simpleExoPlayer != null) {
            shouldAutoPlay = simpleExoPlayer.getPlayWhenReady();
            updateResumePosition();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }


    private void updateResumePosition() {
        resumePosition = Math.max(0, simpleExoPlayer.getContentPosition());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(PLAYER_STATE, shouldAutoPlay);
        outState.putLong(PLAYER_POSITION, resumePosition);
        super.onSaveInstanceState(outState);
    }
}
