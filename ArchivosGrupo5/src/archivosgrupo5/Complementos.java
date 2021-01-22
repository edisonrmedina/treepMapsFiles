package archivosgrupo5;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Complementos 
{
    public static void dibujadorDeTreeMap(TreeMap arbolDeDirectorios, HBox contenedor)
    {
        contenedor.getChildren().clear();
        contenedor.setAlignment(Pos.CENTER);
        
        VBox caja = new VBox();
        double anchCaja =contenedor.getMaxWidth();
        double altCaja =contenedor.getMaxHeight();
        caja.setMaxSize(anchCaja, altCaja);
        contenedor.getChildren().add(caja);
            
        if(!arbolDeDirectorios.tieneHijos() || arbolDeDirectorios.getRaiz().getHijos().size()==1)
        {
            ponerCuadro(caja);
        }
      
        if(arbolDeDirectorios.getRaiz().getHijos().size()>1)
        {
            double tamaño = arbolDeDirectorios.getRaiz().getTamañoFichero();
                
            arbolDeDirectorios.getRaiz().getHijos().forEach((arbolito) -> 
            {
                VBox caja1 = new VBox();
                double anchCaja1 =contenedor.getMaxWidth()*(arbolito.getRaiz().getTamañoFichero()/tamaño);
                double altCaja1 =contenedor.getMaxHeight();
                caja1.setMaxSize(anchCaja1, altCaja1);
                contenedor.getChildren().add(caja1);
                    
                if(!arbolito.tieneHijos() || arbolito.getRaiz().getHijos().size()==1)
                {
                    ponerCuadro(caja1);
                }

                if (arbolito.getRaiz().getHijos().size()>1) 
                {
                    double tamaño2 = arbolito.getRaiz().getTamañoFichero();

                    arbolito.getRaiz().getHijos().forEach((arb) -> 
                    {
                        HBox caja2 = new HBox();
                        double anchCaja2 = caja1.getMaxWidth();
                        double altCaja2 =caja1.getMaxHeight()*(arb.getRaiz().getTamañoFichero()/tamaño2);
                        caja2.setMaxSize(anchCaja2, altCaja2);
                        caja1.getChildren().add(caja2);

                        dibujadorDeTreeMap(arb, caja2);
                    });
                }
            });
        }    
    }
    
    private static void ponerCuadro(Pane contenedor)
    {
        Rectangle rect = new Rectangle();
        rect.setWidth(contenedor.getMaxWidth());
        rect.setHeight(contenedor.getMaxHeight());
        colorear(rect);
        contenedor.getChildren().add(rect);
    }
    
    private static void colorear(Rectangle pintar)
    {
       int rojo = (int)Math.floor((Math.random()*190)+60);
       int verde = (int)Math.floor((Math.random()*190)+60);
       int azul = (int)Math.floor((Math.random()*190)+60);
    
       Color color = Color.rgb(rojo, verde, azul);
       pintar.setFill(color);
    }
}
