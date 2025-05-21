package com.moviles.vinilos;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TrackTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addTrackToAlbum() {
        // Given
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And click on the button "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);
        //And async wait for data load
        AlbumsUtils.waitFor(5000);
        AlbumsUtils.seleccionarAlbumNombre(AlbumsUtils.ALBUM_NAME);
        AlbumsUtils.waitFor(5000);
        // click to add track button
        AlbumsUtils.clickToViewTrackButton();
        // Click to Add Track
        AlbumsUtils.clickToAddTrackButton();
        // And add track name
        var trackName = Commonsutils.GetAlpahNumericString(10);
        AlbumsUtils.addTrackName(trackName);
        // And add track duration
        AlbumsUtils.addTrackDuration("3:45");
        // And click to save
        AlbumsUtils.clickToSaveTrackButton();
        // Then validate track added
        var isTrackAdded = AlbumsUtils.isTrackAdded(trackName);
        assertTrue("Track not added", isTrackAdded);
    }

    @Test
    public void cancelAddTrackToAlbum() {
        // Given
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And click on the button "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);
        //And async wait for data load
        AlbumsUtils.waitFor(5000);
        AlbumsUtils.seleccionarAlbumNombre(AlbumsUtils.ALBUM_NAME);
        AlbumsUtils.waitFor(5000);
        // click to add track button
        AlbumsUtils.clickToViewTrackButton();
        // Click to Add Track
        AlbumsUtils.clickToAddTrackButton();
        // And add track name
        var trackName = Commonsutils.GetAlpahNumericString(10);
        AlbumsUtils.addTrackName(trackName);
        // And add track duration
        AlbumsUtils.addTrackDuration("3:45");
        // And click to cancel
        AlbumsUtils.clickToBackButtonTrack();
        // Then validate track not added
        var isTrackAdded = AlbumsUtils.isTrackAdded(trackName);
        assertFalse("Track not added", isTrackAdded);
    }

    @Test
    public void validateAddTrackDurationField() {
        // Given
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And click on the button "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);
        //And async wait for data load
        AlbumsUtils.waitFor(5000);
        AlbumsUtils.seleccionarAlbumNombre(AlbumsUtils.ALBUM_NAME);
        AlbumsUtils.waitFor(5000);
        // click to add track button
        AlbumsUtils.clickToViewTrackButton();
        // Click to Add Track
        AlbumsUtils.clickToAddTrackButton();
        // And add track name
        var trackName = Commonsutils.GetAlpahNumericString(10);
        // And add track duration
        AlbumsUtils.addTrackDuration("3:45");
        // And click to save
        AlbumsUtils.clickToSaveTrackButton();
        // Then validate track view
        var isInCreateView = AlbumsUtils.isInView(R.id.formContainer);
        assertTrue("Failed to validate track view", isInCreateView);
    }

    @Test
    public void validateAddTrackNameField() {
        // Given
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And click on the button "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);
        //And async wait for data load
        AlbumsUtils.waitFor(5000);
        AlbumsUtils.seleccionarAlbumNombre(AlbumsUtils.ALBUM_NAME);
        AlbumsUtils.waitFor(5000);
        // click to add track button
        AlbumsUtils.clickToViewTrackButton();
        // Click to Add Track
        AlbumsUtils.clickToAddTrackButton();
        // And add track name
        var trackName = Commonsutils.GetAlpahNumericString(10);
        // And add track duration
        AlbumsUtils.addTrackName(trackName);
        // And click to save
        AlbumsUtils.clickToSaveTrackButton();
        // Then validate track view
        var isInCreateView = AlbumsUtils.isInView(R.id.formContainer);
        assertTrue("Failed to validate track view", isInCreateView);
    }
}
