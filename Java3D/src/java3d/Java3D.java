package java3d;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class Java3D implements Runnable {
    SimpleUniverse universo= new SimpleUniverse();
        /*En el objeto «universo» es donde se coloca todo.*/
        
        BranchGroup grupo = new BranchGroup();
        /*En el objeto «grupo» van las figuras que se generen*/
        
        ColorCube cubo = new ColorCube(0.5);
        /*En el objeto «cubo» se genera un cubo cuyo tamaño se establece en el 
        parámetro que aparece como 0.3.*/
        
        TransformGroup GT = new TransformGroup();
        /*Los objetos «GT» y «transformar» son los que nos van a permitir 
        modificar las propiedades del cubo que se ha generado.*/
        Transform3D transformar = new Transform3D();
        
        Thread hilo =new Thread(this);//Se declara el hilo
        double Y=0;
        public Java3D(){
            GT.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);/*se setea el grupo de transformación
            como un elemento modificable en tiempo de ejecucion*/
            hilo.start();//Se inicia el hilo
            GT.setTransform(transformar);
            GT.addChild(cubo);
            grupo.addChild(GT);
            universo.getViewingPlatform().setNominalViewingTransform();
            universo.addBranchGraph(grupo);
            /*Se ha agregado el cubo al grupo de transformación, que se agrega
            al grupo que se agrega al universo. Se debe invocar este método 
            desde el Main de nuestro proyecto.*/
        }
    public static void main(String[] args) {
        new Java3D();
    }

    @Override
    public void run() {
        Thread ct=Thread.currentThread();
        while(ct== hilo){
            try{
                Y = Y +0.08;//Variable global declarada como double
                
                transformar.rotY(Y);//Se rota en base al eje Y, X, Z
                GT.setTransform(transformar);//Se actualiza el gráfico
                
                Thread.sleep(100);//Se espera un tiempo antes de seguir la ejecución
            } catch(InterruptedException ex){
                java.util.logging.Logger.getLogger(Java3D.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            }
        }
    }
    
}
