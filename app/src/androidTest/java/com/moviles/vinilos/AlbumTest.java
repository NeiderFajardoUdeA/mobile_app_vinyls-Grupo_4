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

    @Test
    public void buscarAlbumVacio() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And vemos la barra de busqueda
        ViewInteraction barraBusqueda = AlbumsUtils.verBarraBusqueda();

        //And le damos click al clic a la barra de busqueda
        AlbumsUtils.clickBtn(barraBusqueda);

        //When buscamos un album por un titulo no existente
        String titulo = "xxxxxxxxxxx";
        AlbumsUtils.buscarAlbumByTitulo(titulo);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then veo no se ve la card creada
        AlbumsUtils.validarBuscarAlbumVacio();
    }

    @Test
    public void buscarAlbum() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And vemos la barra de busqueda
        ViewInteraction barraBusqueda = AlbumsUtils.verBarraBusqueda();

        //And le damos click al clic a la barra de busqueda
        AlbumsUtils.clickBtn(barraBusqueda);

        //When buscamos un album por un titulo especifico
        String titulo = "Poeta del pueblo";
        AlbumsUtils.buscarAlbumByTitulo(titulo);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then veo su card en pantalla con el titulo buscado
        AlbumsUtils.validarBuscarAlbum(titulo);
    }

    @Test
    public void verDetalleAlbum() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And doy clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When presiono un album con título específico
        String titulo = "Poeta del pueblo";
        AlbumsUtils.seleccionarAlbumNombre(titulo);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then debo ver un título con el nombre del album
        AlbumsUtils.validarTituloAlbum(titulo);

        //And debo ver un subtitulo de Resumen del álbum
        AlbumsUtils.validarSubtituloResumen();

    }

    @Test
    public void botonVolverListaAlbumes() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And doy clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(0);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And veo que aparece el resumen del album
        AlbumsUtils.validarSubtituloResumen();

        //And doy clic en el boton volver
        AlbumsUtils.clickBotonVolver();

        //Then debo regresar a la lista de albumes
        AlbumsUtils.verTituloAlbum();

        //Then puedo ahora hacer click en el segundo album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(1);

        //Then debo ver ahora el resumen del segundo album
        AlbumsUtils.validarSubtituloResumen();
    }

    @Test
    public void backIconDetalleAlbum() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And doy clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(0);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And veo que aparece el resumen del album
        AlbumsUtils.validarSubtituloResumen();

        //And doy clic en el ícono "Atras"
        AlbumsUtils.clickBackIcon();

        //Then debo regresar a la lista de albumes
        AlbumsUtils.verTituloAlbum();

        //Then puedo ahora hacer click en el segundo album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(1);

        //Then debo ver ahora el resumen del segundo album
        AlbumsUtils.validarSubtituloResumen();
    }
}