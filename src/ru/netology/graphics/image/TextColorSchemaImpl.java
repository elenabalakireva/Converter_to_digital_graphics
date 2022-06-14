package ru.netology.graphics.image;

public class TextColorSchemaImpl implements TextColorSchema {
    @Override
    public char convert(int color) {
        char[] symbols = {'@', '%', '$', '#', '=', '/', '!', '-'};
        return symbols[(int) Math.floor(color / 256. * symbols.length)];
    }
}
