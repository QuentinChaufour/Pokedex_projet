import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class Pokedex{
    
    private Map<String,List<Pokemon>> allPokemons;

    /**
     * Create the GUI for the Pokedex
     */
    public Pokedex(){
        
        this.allPokemons = this.createPokemons();
        
        //System.out.println(this.allPokemons);

        JFrame frame = new JFrame("The Pokedex App V1.0");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("logo.png");
        frame.setIconImage(icon.getImage());

        
        JPanel panel = new JPanel();
        panel.setSize(size);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(2, 3));
        frame.add(panel, BorderLayout.CENTER);
        
        //JButton button = new JButton("Click me!");
        //panel.add(button);
        
        JLabel label = new JLabel("The first pokemon IMAGE");
        panel.add(label,BorderLayout.CENTER);

        for (String pokemonNumber : this.allPokemons.keySet()) {

            Pokemon pokemon = this.allPokemons.get(pokemonNumber).get(0);

            try {                
                BufferedImage image = ImageIO.read(new File(String.join(File.separator,"Card_Img",pokemon.getCardSprite())));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                picLabel.setText(pokemon.getNamePoke());
                picLabel.setHorizontalTextPosition(JLabel.CENTER);
                picLabel.setVerticalTextPosition(JLabel.BOTTOM);
                panel.add(picLabel,0);
                picLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //frame.setVisible(false);
                        createPokemonFrame(frame,pokemonNumber);
                        //frame.setVisible(true);
                    }
                });
            }catch (IOException ex) {
                System.out.println(String.join(File.separator,"Card_Img",pokemon.getCardSprite()));
            }    
        }


        //frame.pack();
        frame.setVisible(true);

    }

    /**
     * Get all images in the Card_Img directory
     * @return
     * the board of image Name in the Card_Img directory
     */
    public String[] getAllImages(){
        File directoryPath = new File(String.join(File.pathSeparator,"Card_Img"));
        String contents[] = directoryPath.list();
        return contents;
    }

    /**
     * Create a list of Pokemon from the images in the Card_Img directory
     * @return
     * the list of Pokemon
     */
    public Map<String,List<Pokemon>> createPokemons(){

        Map<String,List<Pokemon>> allPokemonCards = new HashMap<>();

        String[] images = this.getAllImages();
        for (String imageName : images) {

            //split the image name to get the pokemon caracteristics
            String[] imageNameSplit = imageName.split("_");
            String numPoke = imageNameSplit[0];
            String pokemonName = imageNameSplit[1];
            String rarity = imageNameSplit[2];
            String pokemonTypeofCard = imageNameSplit[3];
            String pokemonSet = imageNameSplit[4].substring(0, imageNameSplit[4].length() - 4);

            Pokemon pokemon = new Pokemon(imageName, pokemonName, numPoke, rarity,pokemonTypeofCard,pokemonSet);

            if(allPokemonCards.containsKey(pokemon.getNumPoke())){
                allPokemonCards.get(pokemon.getNumPoke()).add(pokemon);
            }else{
                List<Pokemon> list = new ArrayList<>();
                list.add(pokemon);
                allPokemonCards.put(pokemon.getNumPoke(),list);
            }
        }
        return allPokemonCards;
    }

    public void createPokemonFrame(JFrame frame,String pokemonNumber){

        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();
        
        JPanel panel = new JPanel();
        panel.setSize(frame.getSize());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(2, 3));
        frame.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("The first pokemon IMAGE");
        frame.add(label,BorderLayout.CENTER);

        for (Pokemon pokemon : this.allPokemons.get(pokemonNumber)) {

            try {                
                BufferedImage image = ImageIO.read(new File(String.join(File.separator,"Card_Img",pokemon.getCardSprite())));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                picLabel.setText(pokemon.getNamePoke() + "\n" + pokemon.getRarity() + "\n" + pokemon.getCardType() + "\n" + pokemon.getSetFrom());
                picLabel.setHorizontalTextPosition(JLabel.CENTER);
                picLabel.setVerticalTextPosition(JLabel.BOTTOM);
                panel.add(picLabel,0);
                picLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //frame.setVisible(false);
                        createPokemonFrame(frame,pokemonNumber);
                        //frame.setVisible(true);
                    }
                });
            }catch (IOException ex) {
                System.out.println(String.join(File.separator,"Card_Img",pokemon.getCardSprite()));
            }    
        }

    }
}
