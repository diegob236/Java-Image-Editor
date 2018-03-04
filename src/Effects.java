import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Effects {


    // Posterize
    public static BufferedImage flipHorizontal(BufferedImage image){

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        for (int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){

                byte[] pixel = getRGBPixel(image, i, j);
/*
                if ((pixel[1] + pixel[2] + pixel[3]) < 384){
                    pixel[1] = (byte)0;
                    pixel[2] = (byte)0;
                    pixel[3] = (byte)0;
                } else {
                    pixel[1] = (byte)255;
                    pixel[2] = (byte)255;
                    pixel[3] = (byte)255;
                }
*/
                try {
                    output.write(pixel);
                } catch (IOException e) {
                }
            }
        }

        byte[] bufferedImage = output.toByteArray();

        return createImageFromBytes(bufferedImage);
    }


    // Posterize
    public static BufferedImage posterize(BufferedImage image){

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        for (int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){

                byte[] pixel = getRGBPixel(image, i, j);

                if ((pixel[1] + pixel[2] + pixel[3]) < 384){
                    pixel[1] = (byte)0;
                    pixel[2] = (byte)0;
                    pixel[3] = (byte)0;
                } else {
                    pixel[1] = (byte)255;
                    pixel[2] = (byte)255;
                    pixel[3] = (byte)255;
                }

                try {
                    output.write(pixel);
                } catch (IOException e) {
                }
            }
        }

        byte[] bufferedImage = output.toByteArray();

        return createImageFromBytes(bufferedImage);
    }


    public static byte[] getRGBPixel(BufferedImage image, int x, int y) {
        return ByteBuffer.allocate(4).putInt(image.getRGB(x,y)).array();
    }


    public static BufferedImage createImageFromBytes(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try{
            return ImageIO.read(bais);
        } catch (IOException e){
            return null;
        }
    }
}
