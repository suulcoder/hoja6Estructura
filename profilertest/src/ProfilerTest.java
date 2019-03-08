import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

public class ProfilerTest {

   public static void main(String[] args) {
       String map_string = "HashMap";
       Coleccion coleccion = new Coleccion(map_string);//Creamos una coleccion inicial para evita el NullPointerException
       ArrayList<String> allCarts	 = new ArrayList<String>();//Nos sera util para el selection box
       ArrayList<String> types = new ArrayList<String>();

        Map<String, Carta> new_local = coleccion.getLocal();
        coleccion = new Coleccion(map_string);
        coleccion.setLocal(new_local);
        String cadena;
        ArrayList<String> operation = new ArrayList<String>();
        int operator = 0;
        int operand = 0;
        try {//si no es un operando
            Stream<String> lines = Files.lines(
                    Paths.get("C:\\Users\\efpro\\miscontactos\\profilertest\\src\\cards_desc.txt"),//Leemos el archivo
                    StandardCharsets.UTF_8
            );
            lines.forEach(s ->{
                operation.add(s);
            });
        }catch (
                IOException exception){
            System.out.println("Error3");
        }
       for (String s : operation) {
           int index = s.indexOf('|') + 1;//Creamos dos substrings
           String tipo = s.substring(index);
           String nombre = s.substring(0, index - 1);
           Carta carta = new Carta(nombre, tipo);
           String id = coleccion.generateId(carta);
           coleccion.add(id, carta, true);
       }

       System.out.println(coleccion.mostrar(true,true));
       System.out.println(coleccion.mostrar(false,true));
}
}
