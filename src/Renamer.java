
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Renamer {
    
    public static void Rename(String Folderpath){

        Map<String,String> rarities = new HashMap<>();
        
        rarities.put("c","Common");
        rarities.put("u","Uncommon");
        rarities.put("r","Rare");
        rarities.put("h","Holo");

        String pokemonNumber = "";
        String pokemonName = "";
        String pokemonRarity = "";
        String pokemonSet = "";

        File Folder = new File(Folderpath);
        String[] files = Folder.list();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for(String file : files){
            File toRename = new File(Folderpath + File.separator + file);
            try {
                resizePokemonImage(toRename);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
            System.out.println("pokemon number :");
            pokemonNumber = r.readLine();

            System.out.println("pokemon name :");
            pokemonName = r.readLine();

            System.out.println("pokemon rarity :");
            pokemonRarity = r.readLine();

            System.out.println("pokemon set :");
            pokemonSet = r.readLine();
            
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            File newName = new File(Folderpath + File.separator + pokemonNumber + "_" + pokemonName + "_" + rarities.get(pokemonRarity) + "_" + pokemonSet + ".png");
            toRename.renameTo(newName);

        }
    }
    public static  void resizePokemonImage(File pokemonImage) throws IOException{
        //File inputImg = new File(String.join(File.separator,"Card_Img",pokemonImage);
        BufferedImage image = ImageIO.read(pokemonImage);
        BufferedImage resizedImage = resize(image,388, 278);
        ImageIO.write(resizedImage, "png", pokemonImage);
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}