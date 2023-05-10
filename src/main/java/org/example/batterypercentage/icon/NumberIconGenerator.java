package org.example.batterypercentage.icon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class NumberIconGenerator {
    public static BufferedImage generateNumberIcon(int number) {

        // 設定圖片的大小和背景色
        int width = 16;
        int height = 16;
        int fontSize = 15;
        Color backgroundColor = Color.WHITE;

        if (number > 99){
            fontSize = 11;
        }

        // 建立一個空白的圖片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 設定字型和顏色
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        Color fontColor = Color.WHITE;
        g2d.setFont(font);
        g2d.setColor(fontColor);

        // 繪製數字
        String numberStr = String.valueOf(number);
        int x = (width - g2d.getFontMetrics().stringWidth(numberStr)) / 2;
        int y = (height - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
        g2d.drawString(numberStr, x, y);

        // 釋放資源並回傳圖片
        g2d.dispose();
        return image;
    }
}
