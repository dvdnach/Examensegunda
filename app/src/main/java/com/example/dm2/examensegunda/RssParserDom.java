package com.example.dm2.examensegunda;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dm2 on 31/01/2017.
 */

public class RssParserDom {
    private URL rssUrl;

    public RssParserDom(String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pronostico> parse()
    {
        //Instanciamos la fabrica para DOM

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        ArrayList<Pronostico> pronosticos=new ArrayList<Pronostico>();

        //creamos un nuevo parser para DOM
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            //realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());



            //nos posicionamos en el nodo principal del arbol(<rss>)
            Element root = dom.getDocumentElement();

            //localizamos todos los elementos <item>
            NodeList items = root.getElementsByTagName("hora");

            //recorremos la lista de pronosticos
            for (int i = 0; i < 1; i++) {
                Pronostico pronostico = new Pronostico();

                //obtenemos el pronostico actual
                Node item = items.item(i);

                //Obtenemos la lista de datos del pronostico actual
                NodeList datospronostico = item.getChildNodes();

                //Procesamos cada dato del pronostico
                for (int j = 0; j < datospronostico.getLength(); j++) {
                    Node dato = datospronostico.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("hora_datos")) {
                        String texto = obtenerTexto(dato);
                        pronostico.setHora(texto);
                    } else {
                        if (etiqueta.equals("temperatura")) {
                            String texto = obtenerTexto(dato);
                            pronostico.setTemperatura(texto);
                        } else {
                            if (etiqueta.equals("texto")) {
                                String texto = obtenerTexto(dato);
                                pronostico.setEstado(texto);
                            }
                        }
                    }
                }
                pronosticos.add(pronostico);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return pronosticos;
    }

    public String obtenerTexto(Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();

        for(int k=0;k<fragmentos.getLength();k++)
        {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    public InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
