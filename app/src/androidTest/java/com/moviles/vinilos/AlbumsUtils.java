package com.moviles.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;


public class AlbumsUtils {

    public static String ALBUM_NAME = "Poeta del pueblo";
    public static ViewInteraction verBtnAlbums() {
        return onView(allOf(withId(R.id.albumesButton), withText(R.string.boton_menu_albums), isDisplayed()));
    }

    public static ViewInteraction verBtnVolver() {
        return onView(allOf(withId(R.id.volverButton), withText(R.string.volver), isDisplayed()));
    }

    public static ViewInteraction verBtnBackDetail() {
        return onView(allOf(withId(R.id.backIcon), isDisplayed()));
    }

    public static void clickBtn(@NonNull ViewInteraction btn) {
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

    public static ViewInteraction verBtnCrear() {
        return onView(allOf(withId(R.id.addIcon), isDisplayed()));
    }

    public static ViewInteraction verBtnSubmit() {
        return onView(allOf(withId(R.id.btnSubmit), isDisplayed()));
    }

    public static void llenarFormulario(String nombreAlbum, String coverAlbum, String releaseDate,
                                        String description, String genre, String recordLabel) {

        //Llenamos el formulario
        onView(withId(R.id.nameAlbum))
                .perform(typeText(nombreAlbum), closeSoftKeyboard());

        onView(withId(R.id.coverAlbum))
                .perform(typeText(coverAlbum), closeSoftKeyboard());

        onView(withId(R.id.ReleaseDate))
                .perform(typeText(releaseDate), closeSoftKeyboard());

        onView(withId(R.id.Description))
                .perform(typeText(description), closeSoftKeyboard());

        onView(withId(R.id.Genre))
                .perform(typeText(genre), closeSoftKeyboard());

        onView(withId(R.id.RecordLabel))
                .perform(typeText(recordLabel), closeSoftKeyboard());      
    }
    
    public static void clickToViewTrackButton() {
        onView(withId(R.id.verTracks)).perform(click());
    }

    public static void clickToAddTrackButton() {
        onView(withId(R.id.agregarTrack)).perform(scrollTo(), click());
    }

    public static void addTrackName(String trackName) {
        onView(withId(R.id.nameTrack)).perform(replaceText(trackName), closeSoftKeyboard());
    }

    public static void addTrackDuration(String trackDuration) {
        onView(withId(R.id.trackDuration)).perform(replaceText(trackDuration), closeSoftKeyboard());
    }

    public static void clickToSaveTrackButton() {
        onView(withId(R.id.agregarTrack)).perform(click());
    }

    public static void clickToBackButtonTrack() {
        onView(withId(R.id.btnCancelar)).perform(click());
    }

    public static boolean isTrackAdded(String trackName) {
        try {
            waitFor(2000);
            scrollToBottom(R.id.tracksRV);
            onView(allOf(withId(R.id.trackName), withText(trackName))).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void scrollToBottom(int recyclerViewId) {
        onView(withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition(getRecyclerViewItemCount(recyclerViewId) - 1));
    }

    private static int getRecyclerViewItemCount(int recyclerViewId) {
        final int[] itemCount = {0};
        onView(withId(recyclerViewId)).check((view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            itemCount[0] = recyclerView.getAdapter().getItemCount();
        });
        return itemCount[0];
    }

    public static boolean isInView(@NonNull int viewId) {
        try {
            onView(withId(viewId)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public static void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
