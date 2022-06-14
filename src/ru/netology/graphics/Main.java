package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchemaImpl;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverterImpl;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextColorSchemaImpl schema = new TextColorSchemaImpl();
        TextGraphicsConverter converter = new TextGraphicsConverterImpl(300, 300, 4, schema);

        GServer server = new GServer(converter);
        server.start();

//        If you want to test this program in terminal

//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
    }
}
