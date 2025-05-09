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

public class AlbumsUtils {

    public static ViewInteraction verBtnAlbums() {
        return onView(allOf(withId(R.id.albumesButton), withText(R.string.boton_menu_albums), isDisplayed()));
    }

    public static ViewInteraction verBtnBackDetail() {
        return onView(allOf(withId(R.id.backIcon), isDisplayed()));
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

    public static void buscarAlbumByTitulo(String titulo) {
        onView(withId(R.id.searchInput)).perform(replaceText(titulo), closeSoftKeyboard());
    }

    public static void validarBuscarAlbum(String titulo) {
        onView(withId(R.id.albumTitle)).check(matches(allOf(isDisplayed(), withText(titulo))));
    }

    public static ViewInteraction verBarraBusqueda() {
        return onView(allOf(withId(R.id.searchBar), isDisplayed()));
    }

    public static void validarBuscarAlbumVacio() {
        //Verificar que no hay vistas de ítems de álbum
        try {
            onView(withId(R.id.albumTitle)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException expected) {}
    }


    public static void seleccionarAlbumNombre(String nombreAlbum){
        onView(withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(nombreAlbum)), click()));
    }

    public static void validarTituloAlbum(String tituloAlbum){
        onView(withId(R.id.albumDetailTitle)).check(matches(allOf(isDisplayed(), withText(tituloAlbum))));
    }

    public static void validarSubtituloResumen(){
        onView(withId(R.id.ResumenTitle)).check(matches(allOf(isDisplayed(), withText(R.string.resumen))));
    }

    public static void seleccionarAlbumPosicion(int position) {
        onView(withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    public static void clickBotonVolver(){
        onView(withId(R.id.volverButton)).perform(click());
    }

    public static void clickBackIcon(){
        onView(withId(R.id.backIcon)).perform(click());
    }
}
