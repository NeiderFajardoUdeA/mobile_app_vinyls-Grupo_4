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
public class CollectorsTest {
    //Inicializamos la MainActivity antes de cada test
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void verTituloVistaColeccionistas() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //When doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //Then entra al listado de coleccionistas y ve el titulo de la vista
        CollectorsUtils.validamosTituloVistaColeccionistas();
    }

    @Test
    public void verTituloVistaColeccionistasYVolverHome() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //When doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //And doy clic en el boton de menu
        ViewInteraction menuBtn = CollectorsUtils.verBtnMenu();

        //And doy clic en el boton "Menu"
        CollectorsUtils.clickBtn(menuBtn);

        //Then deberia verse el la imagen del home
        CollectorsUtils.validamosImagenHome();
    }

    @Test
    public void verListaColeccionistas() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //When doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then debo ver una lista con al menos un coleccionista
        CollectorsUtils.validarListaColeccionista();
    }

    @Test
    public void buscarColeccionistaVacio() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //And doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And vemos la barra de busqueda
        ViewInteraction barraBusqueda = CollectorsUtils.verBarraBusqueda();

        //And le damos click al clic a la barra de busqueda
        CollectorsUtils.clickBtn(barraBusqueda);

        //When buscamos un coleccionista por un nombre inexistente
        String titulo = "xxxxxxxxxxx";
        CollectorsUtils.buscarCollectorByName(titulo);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then veo no se ve la card creada
        CollectorsUtils.validarBuscarCollectorVacio();
    }

    @Test
    public void buscarColeccionista() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //And doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And vemos la barra de busqueda
        ViewInteraction barraBusqueda = CollectorsUtils.verBarraBusqueda();

        //And le damos click al clic a la barra de busqueda
        CollectorsUtils.clickBtn(barraBusqueda);

        //When buscamos un coleccionista por un nombre especifico
        String nombre = "Manolo Bellon";
        CollectorsUtils.buscarCollectorByName(nombre);

        //Espera asincrona para la carga de datos
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then veo su card en pantalla con el titulo buscado
        CollectorsUtils.validarBuscarCollector(nombre);
    }

    @Test
    public void verDetalleColeccionista() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //And doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When presiono un coleccionista con nombre específico
        String nombre = "Manolo Bellon";
        CollectorsUtils.seleccionarColeccionistaNombre(nombre);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Then debo ver un título con el nombre del coleccionista
        CollectorsUtils.validarNombreColeccionista(nombre);
    }

    @Test
    public void botonVolverListaCollecionistas() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //And doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer coleccionista de la lista
        CollectorsUtils.seleccionarColeccionistaPosicion(0);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And veo que aparece el resumen del coleccionista
        CollectorsUtils.validarSubtituloResumen();

        //And doy clic en el boton volver
        CollectorsUtils.clickBotonVolver();

        //And regreso a la lista de coleccionistas
        CollectorsUtils.validarListaColeccionista();

        //And click en el segundo coleccionista de la lista
        CollectorsUtils.seleccionarColeccionistaPosicion(1);

        //Then debo ver ahora el resumen del segundo coleccionista
        CollectorsUtils.validarSubtituloResumen();
    }

    @Test
    public void backIconDetalleCollector() {
        //Given que estoy en el home de la app
        ViewInteraction collectorsBtn = CollectorsUtils.verBtnCollectors();

        //And doy clic en el boton "Coleccionistas"
        CollectorsUtils.clickBtn(collectorsBtn);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //When selecciono el primer coleccionista de la lista
        CollectorsUtils.seleccionarColeccionistaPosicion(0);

        //And espera asincrona para la carga de datos
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //And veo que aparece el resumen del coleccionista
        CollectorsUtils.validarSubtituloResumen();

        //And doy clic en el icono volver
        CollectorsUtils.clickBackIcon();

        //And regreso a la lista de coleccionistas
        CollectorsUtils.validarListaColeccionista();

        //And click en el segundo coleccionista de la lista
        CollectorsUtils.seleccionarColeccionistaPosicion(1);

        //Then debo ver ahora el resumen del segundo coleccionista
        CollectorsUtils.validarSubtituloResumen();
    }
}