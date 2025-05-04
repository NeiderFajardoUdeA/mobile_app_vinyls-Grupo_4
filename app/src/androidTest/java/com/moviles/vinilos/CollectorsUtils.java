package com.moviles.vinilos;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

public class CollectorsUtils {

    public static ViewInteraction verBtnCollectors() {
        return onView(allOf(withId(R.id.collectorButton), withText(R.string.boton_menu_collectors), isDisplayed()));
    }

    public static ViewInteraction verBtnBackDetail() {
        return onView(allOf(withId(R.id.backIcon), isDisplayed()));
    }

    public static void clickBtn(ViewInteraction btn) {
        btn.perform(click());
    }

    public static void validamosTituloVistaColeccionistas() {
        onView(withId(R.id.collectorRv)).check(matches(isDisplayed()));
    }

    public static ViewInteraction verBtnMenu() {
        return onView(allOf(withId(R.id.menuIcon), isDisplayed()));
    }

    public static void validamosImagenHome() {
        onView(allOf(withId(R.id.logoImage), isDisplayed())).check(matches(isDisplayed()));
    }

    public static void validarListaColeccionista() {
        onView(withId(R.id.collectorRv)).check(matches(hasDescendant(withId(R.id.collectorTitle))));
    }

    public static void buscarCollectorByName(String nombre) {
        onView(withId(R.id.searchInput)).perform(replaceText(nombre), closeSoftKeyboard());
    }

    public static void validarBuscarCollector(String nombre) {
        onView(withId(R.id.collectorTitle)).check(matches(allOf(isDisplayed(), withText(nombre))));
    }

    public static ViewInteraction verBarraBusqueda() {
        return onView(allOf(withId(R.id.searchBar), isDisplayed()));
    }

    public static void validarBuscarCollectorVacio() {
        //Verificar que no hay vistas de ítems de álbum
        try {
            onView(withId(R.id.collectorTitle)).check(matches(isDisplayed()));
        } catch (NoMatchingViewException expected) {}
    }


    public static void seleccionarColeccionistaNombre(String nombre){
        onView(withId(R.id.collectorRv))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(nombre)), click()));
    }

    public static void validarNombreColeccionista(String nombre){
        onView(withId(R.id.collectorDetailTitle)).check(matches(allOf(isDisplayed(), withText(nombre))));
    }

    public static void validarSubtituloResumen(){
        onView(withId(R.id.ResumenTitle)).check(matches(allOf(isDisplayed(), withText(R.string.resumen))));
    }

    public static void seleccionarColeccionistaPosicion(int position) {
        onView(withId(R.id.collectorRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    public static void clickBotonVolver(){
        onView(withId(R.id.volverButton)).perform(click());
    }

    public static void clickBackIcon(){
        onView(withId(R.id.backIcon)).perform(click());
    }
}
