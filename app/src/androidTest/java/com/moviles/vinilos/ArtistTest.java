package com.moviles.vinilos;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ArtistTest {
    //Inicializamos la MainActivity antes de cada test
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void verTituloVistaArtists() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //When da clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //Then entra al listado de Artists ve el titulo de la vista
        ArtistsUtils.validamosTituloVistaArtists();
    }

    @Test
    public void verTituloVistaArtistsYVolverHome() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //And da clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //When da clic en el boton de menu
        ViewInteraction menuBtn = ArtistsUtils.verBtnMenu();

        //And da clic en el boton "Menu"
        ArtistsUtils.clickBtn(menuBtn);

        //Then deberia verse el la imagen del home
        ArtistsUtils.validamosImagenHome();
    }

    @Test
    public void verListaArtists() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //When da clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then se ve la lista de Artists en la vista
        ArtistsUtils.verTituloArtist();
    }

    @Test
    public void buscarArtistVacio() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //And da clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And vemos la barra de busqueda
        ViewInteraction barraBusqueda = ArtistsUtils.verBarraBusqueda();

        //And le damos click al clic a la barra de busqueda
        ArtistsUtils.clickBtn(barraBusqueda);

        //When buscamos un Artist por un nombre no existente
        String nombre = "xxxxxxxxxxx";
        ArtistsUtils.buscarArtistByName(nombre);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then veo no se ve la card creada
        ArtistsUtils.validarBuscarArtistVacio();
    }

    @Test
    public void buscarArtist() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //And da clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And vemos la barra de busqueda
        ViewInteraction barraBusqueda = ArtistsUtils.verBarraBusqueda();

        //And le damos click al clic a la barra de busqueda
        ArtistsUtils.clickBtn(barraBusqueda);

        //When buscamos un Artist por un nombre especifico
        String name = "Rubén Blades Bellido de Luna";
        ArtistsUtils.buscarArtistByName(name);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then veo su card en pantalla con el nombre buscado
        ArtistsUtils.validarBuscarArtist(name);
    }

    @Test
    public void verDetalleArtist() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //And doy clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When presiono un Artist con nombre específico
        String name = "Rubén Blades Bellido de Luna";
        ArtistsUtils.seleccionarArtistNombre(name);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then debo ver un título con el nombre del Artist
        ArtistsUtils.validarTituloArtist(name);
    }

    @Test
    public void botonVolverListaArtists() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //And doy clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer Artist de la lista
        ArtistsUtils.seleccionarArtistPosicion(0);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And veo que aparece el resumen del Artist
        ArtistsUtils.validarSubtituloResumen();

        //And doy clic en el boton volver
        ArtistsUtils.clickBotonVolver();

        //And regreso a la lista de Artists
        ArtistsUtils.verTituloArtist();

        //And click en el segundo Artist de la lista
        ArtistsUtils.seleccionarArtistPosicion(0);

        //Then debo ver ahora el resumen del segundo Artist
        ArtistsUtils.validarSubtituloResumen();
    }

    @Test
    public void backIconDetalleArtist() {
        //Given que estoy en el home de la app
        ViewInteraction artistsBtn = ArtistsUtils.verBtnArtists();

        //And doy clic en el boton "Artists"
        ArtistsUtils.clickBtn(artistsBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer Artist de la lista
        ArtistsUtils.seleccionarArtistPosicion(0);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And veo que aparece el resumen del Artist
        ArtistsUtils.validarSubtituloResumen();

        //And busco que el boton este en la vista
        ViewInteraction backBtn = ArtistsUtils.verBtnBackDetail();

        //And doy clic en el ícono "Atras"
        ArtistsUtils.clickBtn(backBtn);

        //And regreso a la lista de Artists
        ArtistsUtils.verTituloArtist();

        //And click en el segundo Artist de la lista
        ArtistsUtils.seleccionarArtistPosicion(0);

        //Then debo ver ahora el resumen del segundo Artist
        ArtistsUtils.validarSubtituloResumen();
    }
}