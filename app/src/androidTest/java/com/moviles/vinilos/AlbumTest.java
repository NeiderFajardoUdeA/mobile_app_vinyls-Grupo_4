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
        String titulo = "Mi angel";
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

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When presiono un album con título específico
        String titulo = "Poeta del pueblo";
        AlbumsUtils.seleccionarAlbumNombre(titulo);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then debo ver un título con el nombre del album
        AlbumsUtils.validarTituloAlbum(titulo);
    }

    @Test
    public void botonVolverListaAlbumes() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And doy clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(0);

        //And veo que aparece el resumen del album
        AlbumsUtils.validarSubtituloResumen();

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And el boton de volver esta
        ViewInteraction btnVolver = AlbumsUtils.verBtnVolver();

        //And doy clic en el boton volver
        AlbumsUtils.clickBtn(btnVolver);

        //And regreso a la lista de albumes
        AlbumsUtils.verTituloAlbum();

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And click en el segundo album de la lista
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

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(0);

        //And veo que aparece el resumen del album
        AlbumsUtils.validarSubtituloResumen();

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And busco que el boton este en la vista
        ViewInteraction backBtn = AlbumsUtils.verBtnBackDetail();

        //And doy clic en el ícono "Atras"
        AlbumsUtils.clickBtn(backBtn);

        //And regreso a la lista de albumes
        AlbumsUtils.verTituloAlbum();

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And click en el segundo album de la lista
        AlbumsUtils.seleccionarAlbumPosicion(1);

        //Then debo ver ahora el resumen del segundo album
        AlbumsUtils.validarSubtituloResumen();
    }

    @Test
    public void crearAlbumVistaAlbum() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //And entra al listado de albumes ve el titulo de la vista
        AlbumsUtils.validamosTituloVistaAlbumes();

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And valida que este el boton de crear album
        ViewInteraction createBtn = AlbumsUtils.verBtnCrear();

        //And da clic en el boton de crear
        AlbumsUtils.clickBtn(createBtn);

        //And llenamos el formulario
        String nombreAlbum = "Album 1";
        String coverAlbum = "https://cdn.venngage.com/template/thumbnail/small/79879260-0211-46bb-abcd-968fb4e2c0ea.webp";
        String releaseDate = "2022-12-18";
        String description = "Descripcion album 1";
        String genre = "metal";
        String recordLabel = "Etiqueta Album 1";

        //And llena el formulario
        AlbumsUtils.llenarFormulario(nombreAlbum, coverAlbum, releaseDate, description,
                genre, recordLabel);

        //And valida que este el boton de crear album en la vista album
        ViewInteraction submitBtn = AlbumsUtils.verBtnSubmit();

        //And da clic en el boton de submit
        AlbumsUtils.clickBtn(submitBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then valido que estoy en el listado de albumes
        AlbumsUtils.validamosTituloVistaAlbumes();
    }

    @Test
    public void crearAlbumValidarCreacion() {
        //Given que estoy en el home de la app
        ViewInteraction albumesBtn = AlbumsUtils.verBtnAlbums();

        //And da clic en el boton "Albumes"
        AlbumsUtils.clickBtn(albumesBtn);

        //And entra al listado de albumes ve el titulo de la vista
        AlbumsUtils.validamosTituloVistaAlbumes();

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And valida que este el boton de crear album
        ViewInteraction createBtn = AlbumsUtils.verBtnCrear();

        //And da clic en el boton de crear
        AlbumsUtils.clickBtn(createBtn);

        //And llenamos el formulario
        String nombreAlbum = "Album 2";
        String coverAlbum = "https://cdn.venngage.com/template/thumbnail/small/79879260-0211-46bb-abcd-968fb4e2c0ea.webp";
        String releaseDate = "2022-12-18";
        String description = "Descripcion album 2";
        String genre = "metal";
        String recordLabel = "Etiqueta Album 2";

        //And llena el formulario
        AlbumsUtils.llenarFormulario(nombreAlbum, coverAlbum, releaseDate, description,
                genre, recordLabel);

        //And valida que este el boton de crear album en la vista album
        ViewInteraction submitBtn = AlbumsUtils.verBtnSubmit();

        //And da clic en el boton de submit
        AlbumsUtils.clickBtn(submitBtn);

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

        //Then buscamos el album creado por un titulo especifico
        AlbumsUtils.buscarAlbumByTitulo(nombreAlbum);
    }

    @Test
    public void crearAlbumDuplicado() {}

    @Test
    public void crearAlbumVolverHome() {}

    @Test
    public void crearAlbumVerDetalle() {}
}