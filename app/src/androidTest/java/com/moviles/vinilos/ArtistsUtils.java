package com.moviles.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

public class ArtistsUtils {

    public static ViewInteraction verBtnArtists() {
        return onView(allOf(withId(R.id.artistsButton), withText(R.string.boton_menu_artists), isDisplayed()));
    }

    public static ViewInteraction verBtnBackDetail() {
        return onView(allOf(withId(R.id.backIcon), isDisplayed()));
    }

    public static void clickBtn(ViewInteraction btn) {
        btn.perform(click());
    }

    public static void validamosTituloVistaArtists() {
        onView(withId(R.id.artistsTitle)).check(matches(allOf(isDisplayed(), withText(R.string.boton_menu_artists))));
    }

    public static ViewInteraction verBtnMenu() {
        return onView(allOf(withId(R.id.menuIcon), isDisplayed()));
    }

    public static void validamosImagenHome() {
        onView(allOf(withId(R.id.logoImage), isDisplayed())).check(matches(isDisplayed()));
    }

    public static void verTituloArtist() {
        onView(allOf(withId(R.id.artistTitle), withParent(withParentIndex(0))));
    }

    public static void buscarArtistByName(String name) {
        onView(withId(R.id.searchInput)).perform(replaceText(name), closeSoftKeyboard());
    }

    public static void validarBuscarArtist(String name) {
        onView(withId(R.id.artistTitle)).check(matches(allOf(isDisplayed(), withText(name))));
    }

    public static ViewInteraction verBarraBusqueda() {
        return onView(allOf(withId(R.id.searchBar), isDisplayed()));
    }

    public static void validarBuscarArtistVacio() {
        //Verificar que no hay vistas de ítems de artistas
        try {
            onView(withId(R.id.artistTitle)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException expected) {}
    }

    public static ViewInteraction verBtnVolver() {
        return onView(allOf(withId(R.id.volverButton), withText(R.string.volver), isDisplayed()));
    }

    public static void seleccionarArtistNombre(String nombreArtist){
        onView(withId(R.id.artistsRv))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(nombreArtist)), click()));
    }

    public static void validarTituloArtist(String tituloArtist){
        onView(withId(R.id.artistDetailTitle)).check(matches(allOf(isDisplayed(), withText(tituloArtist))));
    }

    public static void validarSubtituloResumen(){
        onView(withId(R.id.ResumenTitle)).check(matches(allOf(isDisplayed(), withText(R.string.resumen))));
    }

    public static void seleccionarArtistPosicion(int position) {
        onView(withId(R.id.artistsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    public static void clickBotonVolver(){
        onView(withId(R.id.volverButton)).perform(click());
    }

    public static void clickBackIcon(){
        onView(withId(R.id.backIcon)).perform(click());
    }
}
