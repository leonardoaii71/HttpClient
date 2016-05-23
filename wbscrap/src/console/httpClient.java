package console;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by Leonardo on 22/05/2016.
 */
public  class httpClient {

    public static  void main(String[] args){

        if(args.length >0)

        try {
            Document doc = Jsoup.connect(args[0]).get();
            String title = doc.title();

            Elements images = doc.getElementsByTag("img");
            Elements parrafos = doc.getElementsByTag("p");
            Elements forms = doc.getElementsByTag("form");
            List<FormElement> formularios = doc.getElementsByTag("form").forms();


            int imgs = images.size()-1;
            int Parrafos = parrafos.size()-1;
            int Forms = forms.size()-1;


            System.out.println("titulo: "+ title);
            //System.out.println(doc.toString());
            System.out.format("lineas: %s \n", LinesCount(doc.toString()));
            System.out.format("numero de imagenes: %d \n", imgs);
            System.out.format("numero de Parrafos: %d \n", Parrafos);
            System.out.format("numero de Formularios: %d \n", Forms);

            if(Forms >0){
                for (FormElement form : formularios) {
                    for(Element el : form.getElementsByTag("input")){

                        String name = el.attr("name");
                        String type = el.attr("type");
                        if (name.length() == 0) System.out.println("Nombre: none " + "type: "+type);
                        else System.out.println("Nombre: "+name+" type: "+type);


                    }
                }
            }
         else System.out.println("\n\nNo URL provided, try again..");






        }

        catch (IOException e){

            System.out.println("error while processing");
            e.printStackTrace();

        }
    }


    public static int LinesCount(String str)
    {
        if (str == null || str.length() == 0)
            return 0;
        int lineas = 1;
        int size = str.length();
        for( int cursor = 0; cursor < size; cursor++) {
            char c = str.charAt(cursor);
            if( c == '\r' ) {
                lineas++;
                if ( cursor+1 < size && str.charAt(cursor+1) == '\n' )
                    cursor++;
            } else if( c == '\n' ) {
                lineas++;
            }
        }
        return lineas;
    }



}