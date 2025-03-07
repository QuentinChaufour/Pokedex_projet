import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Pokedex {
    
    private List<Pokemon> allPokemons;

    /**
     * Create the GUI for the Pokedex
     */
    public Pokedex(){
        
        this.allPokemons = this.createPokemons();
        System.out.println(this.allPokemons);

        JFrame frame = new JFrame("The Pokedex App V1.0");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("logo.png");
        frame.setIconImage(icon.getImage());

        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(2, 2));
        frame.add(panel, BorderLayout.CENTER);
        
        JButton button = new JButton("Click me!");
        panel.add(button);
        
        JLabel label = new JLabel("I'm a label!");
        panel.add(label);

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Get all images in the Card_Img directory
     * @return
     * the board of image Name in the Card_Img directory
     */
    public String[] getAllImages(){
        File directoryPath = new File(String.join(File.separator,"Card_Img"));
        String contents[] = directoryPath.list();
        return contents;
    }

    /**
     * Create a list of Pokemon from the images in the Card_Img directory
     * @return
     * the list of Pokemon
     */
    public List<Pokemon> createPokemons(){

        List<Pokemon> createdPokemon = new ArrayList<>(); 

        String[] images = this.getAllImages();
        for (String imageName : images) {

            //split the image name to get the pokemon caracteristics
            String[] imageNameSplit = imageName.split("_");
            String pokemonName = imageNameSplit[0];
            String rarity = imageNameSplit[1];
            String pokemonTypeofCard = imageNameSplit[2];
            String pokemonSet = imageNameSplit[3].substring(0, imageNameSplit[3].length() - 4);

            Pokemon pokemon = new Pokemon(imageName, pokemonName, rarity,pokemonTypeofCard,pokemonSet);
            createdPokemon.add(pokemon);
        }
        return createdPokemon;
    }
}
