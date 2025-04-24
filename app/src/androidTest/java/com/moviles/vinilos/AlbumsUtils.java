package com.moviles.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.ViewInteraction;

public class AlbumsUtils {

    public static ViewInteraction verBtnAlbums() {
        return onView(allOf(withId(R.id.albumesButton), withText(R.string.boton_menu_albums), isDisplayed()));
    }

    public static void clickBtn(ViewInteraction btn) {
        btn.perform(click());
    }

    public static void validamosTituloVistaAlbumes() {
        onView(withId(R.id.albumsTitle)).check(matches(allOf(isDisplayed(), withText(R.string.boton_menu_albums))));
    }

    public static ViewInteraction verBtnMenu() {
        return onView(allOf(withId(R.id.menuIcon), isDisplayed()));
    }

    public static void validamosImagenHome() {
        onView(allOf(withId(R.id.logoImage), isDisplayed())).check(matches(isDisplayed()));
    }

    public static void verTituloAlbum() {
        onView(allOf(withId(R.id.albumTitle), withParent(withParentIndex(0))));
    }
}
