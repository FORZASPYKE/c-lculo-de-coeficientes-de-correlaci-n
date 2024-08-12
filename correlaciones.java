import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class correlaciones {
    public static void main(String[] args){
         try{
            FileReader ArchivoLectura = new FileReader("startup-profit.csv");

            BufferedReader ArchivoEscritura = new BufferedReader(ArchivoLectura);
            String almacen = null;
            double sumaprofit = 0.0;
            double sumaspend = 0.0;
            double sumaadmin = 0.0;
            double sumamarketing = 0.0;
            int contador = 0;
            ArchivoEscritura.readLine();
            double[]profit = new double[50];
            double[]spend = new double[50];
            double[]admin = new double[50];
            double[]marketing = new double[50];
            double Correlacion = 0.0;
            
            

            while((almacen=ArchivoEscritura.readLine()) !=null){
            
            
               //encontrar las comas para delimitar los espacios
            int primeraComa = almacen.indexOf(",");
            int segundaComa = almacen.indexOf(",", primeraComa + 1);
            int terceracoma = almacen.indexOf(",", segundaComa+1);

            //sumar todos los valores de R&D Spend
            double valor = Double.parseDouble(almacen.substring(0,primeraComa));
            sumaspend = sumaspend + valor;
            spend[contador] = valor;
            //System.out.println(campos[0][contador]);
            //System.out.println("Suma Spend: " + sumaspend);

            //sumar todos los valores de Administracion
            valor = Double.parseDouble(almacen.substring(primeraComa+1, segundaComa));
            sumaadmin = sumaadmin + valor;
            admin[contador] = valor;
            //System.out.println(campos[1][contador]);
            //System.out.println("Suma Admin: " + sumaadmin);

            //sumar todos los valores de marketing
            valor = Double.parseDouble(almacen.substring(segundaComa +1, terceracoma));
            sumamarketing = sumamarketing + valor;
            marketing[contador] = valor;
            //System.out.println(campos[2][contador]);
            //System.out.println("Suma Marketing: " + sumamarketing);

            //sumar todos los valores de profit
            valor = Double.parseDouble(almacen.substring(almacen.lastIndexOf(",") + 1));
            sumaprofit = sumaprofit + valor;
            profit[contador] = valor;
            //System.out.println(campos[3][contador]);
            //System.out.println("suma profit: " + sumaprofit);

            contador++;
            
            }
            ArchivoEscritura.close();
            ArchivoLectura.close();

            Correlacion = calcorrelacion(profit, spend);
            System.out.println("Correlacion Profit y spend: "+ Correlacion);

            Correlacion = calcorrelacion(profit, marketing);
            System.out.println("Correlacion Profit y Marketing: "+ Correlacion);

            
         }catch (FileNotFoundException e){
            throw new RuntimeException(e);
         }catch (IOException e){
            throw new RuntimeException();
         }

    }


    public static double calcorrelacion(double[] arregloX, double[] arregloY) {
        double sumaX = 0.0;
        double sumaY = 0.0;
        double mediaX = 0.0;
        double mediaY = 0.0;
        double sumaresXY = 0.0;
        double sumaresY = 0.0;
        double covarianza = 0.0;
        int n = arregloX.length;
        double Correlacion = 0.0;
        double desviacionX = 0.0;
        double desviacionY = 0.0;
    
        // Sumar todos los datos
        for (int i = 0; i < n; i++) {  // Cambié '==' por '<'
            sumaX += arregloX[i];
            sumaY += arregloY[i];
        }
    
        // Calcular la media
        mediaX = sumaX / n;
        mediaY = sumaY / n;
    
        // Calcular la suma de las diferencias
        for (int i = 0; i < n; i++) { 
            sumaresXY += (arregloX[i] - mediaX)*(arregloY[i] - mediaY);
        }
    
        // Calcular la covarianza
        covarianza = (sumaresXY) / (n - 1);
        System.out.println("Covarianza: " + covarianza);
    
        // Calcular la desviación estándar
        desviacionX = caldesviacionestandar(arregloX);
        desviacionY = caldesviacionestandar(arregloY);
    
        // Calcular el coeficiente de correlación
        Correlacion = covarianza / (desviacionX * desviacionY);
    
        return Correlacion;
    }
    


    // calculo de desviacion estandar
    public static double caldesviacionestandar(double[] arreglo) {
        double suma = 0.0;
        double media = 0;
        double datosmenosmedia = 0.0;
        double DesviacionEstandar;
        int n = arreglo.length;
    
        // Sumar todos los datos
        for (int i = 0; i < n; i++) {
            suma += arreglo[i];
        }
    
        // Calcular la media
        media = suma / n;
    
        // Calcular la suma de los cuadrados de las diferencias
        for (int i = 0; i < n; i++) {
            datosmenosmedia += Math.pow((arreglo[i] - media), 2);
        }
    
        // Calcular la desviación estándar
        DesviacionEstandar = Math.sqrt(datosmenosmedia / (n - 1));
        System.out.println("Desviación estándar: " + DesviacionEstandar);
    
        return DesviacionEstandar;
    }
    
}
