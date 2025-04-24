package com.moviles.vinilos;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AlbumTest {
    //Inicializamos la MainActivity antes de cada test
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void verTituloVistaAlbumes() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //When da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Then entra al listado de albumes ve el titulo de la vista
        AlbumsUtils.validamosTituloVistaAlbumes();
    }

    @Test
    public void verTituloVistaAlbumesYVolverHome() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //When da clic en el boton de menu
        ViewInteraction menuBtn = AlbumsUtils.verBtnMenu();

        //And da clic en el boton "Menu"
        AlbumsUtils.clickBtn(menuBtn);

        //Then deberia verse el la imagen del home
        AlbumsUtils.validamosImagenHome();
    }

    @Test
    public void verListaAlbumes() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //When da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then se ve la lista de albumes en la vista
        AlbumsUtils.verTituloAlbum();
    }
}
