package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class TextGraphicsConverterImpl implements TextGraphicsConverter {
    private double maxRatio;
    private int maxWidth;
    private int maxHeight;
    private TextColorSchemaImpl schema;

    public TextGraphicsConverterImpl(int maxHeight, int maxWidth, double maxRatio, TextColorSchemaImpl schema) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxRatio = maxRatio;
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        String picture = null;
        int newWidth = 0;
        int newHeight = 0;
        double ratio = 0;
        TextColorSchemaImpl schema = new TextColorSchemaImpl();
        BufferedImage img = ImageIO.read(new URL(url));
        double a = img.getHeight();
        double b = img.getWidth();
        if (a > b) {
            ratio = a / b;
            if (a > maxHeight) {
                newHeight = maxHeight;
                newWidth = (int) (newHeight / ratio);
            } else {
                newHeight = (int) a;
                newWidth = (int) b;
            }
        } else {
            ratio = b / a;
            if (b > maxWidth) {
                newWidth = maxWidth;
                newHeight = (int) (maxWidth / ratio);
            } else {
                newHeight = (int) a;
                newWidth = (int) b;
            }
        }
        if (ratio > this.maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }
        Image scaledImage = img.getScaledInstance((int) newWidth, (int) newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        ArrayList<Character> list = new ArrayList();
        StringBuilder builder = new StringBuilder();
        char c = 0;
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                c = schema.convert(color);
                list.add(c);
                builder.append(c).append(c);
            }
            builder.append(System.lineSeparator());
        }
        picture = builder.toString();
        return picture;
    }


    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = (TextColorSchemaImpl) schema;
    }
}
