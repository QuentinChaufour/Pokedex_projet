import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Pokedex{
    
    private final Map<String,List<Pokemon>> allPokemons;

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

        this.paintHomeIU(frame);


        
        
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

        File directoryPath = new File(String.join(File.pathSeparator,"Card_Img"));
        String contents[] = directoryPath.list();

        boolean existingJsonFile = false;

        for (String content : contents) {
            if(content.equals("cardsStored.json")){
                existingJsonFile = true;
                break;
            }
        }

        if(existingJsonFile){
            return this.loadPokemonFromJson();
        }

        Map<String,List<Pokemon>> allPokemonCards = new HashMap<>();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().create();
        Gson gson = builder.create();

        File file = new File("cards.json");
        int count = 0;

        try{
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("[\n");
            String[] images = this.getAllImages();
            for (String imageName : images) {

                count++;
                //split the image name to get the pokemon caracteristics
                String[] imageNameSplit = imageName.split("_");
                String numPoke = imageNameSplit[0];
                String pokemonName = imageNameSplit[1];
                String rarity = imageNameSplit[2];
                String pokemonTypeofCard = imageNameSplit[3];
                String pokemonSet = imageNameSplit[4];

                Pokemon pokemon = new Pokemon(imageName, pokemonName, numPoke, rarity,pokemonTypeofCard,pokemonSet);

                fileWriter.write(gson.toJson(pokemon));
                if(count < images.length){
                    fileWriter.write(",\n");
                }

                if(allPokemonCards.containsKey(pokemon.getNumPoke())){
                    allPokemonCards.get(pokemon.getNumPoke()).add(pokemon);
                }else{
                    List<Pokemon> list = new ArrayList<>();
                    list.add(pokemon);
                    allPokemonCards.put(pokemon.getNumPoke(),list);
                }
            }
            fileWriter.write("\n]");
            fileWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        return allPokemonCards;
    }

    public void paintHomeIU(JFrame frame){

        
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        frame.setTitle("The Pokedex App V1.0");

        JPanel panel = new JPanel();
        panel.setSize(frame.getSize());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(2, 3));
        frame.add(panel, BorderLayout.CENTER);
        
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


        frame.repaint();
        frame.setVisible(true);

    }


    public void createPokemonFrame(JFrame frame,String pokemonNumber){

        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        frame.setTitle("All existng cards for" + allPokemons.get(pokemonNumber).get(0).getNamePoke());
        
        JPanel panelCard = new JPanel();
        panelCard.setSize(frame.getSize());
        panelCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panelCard.setLayout(new GridLayout(2, 3));
        frame.add(panelCard, BorderLayout.CENTER);

        JLabel label = new JLabel("The first pokemon IMAGE");
        frame.add(label,BorderLayout.CENTER);

        for (Pokemon pokemon : this.allPokemons.get(pokemonNumber)) {

            try {                
                BufferedImage image = ImageIO.read(new File(String.join(File.separator,"Card_Img",pokemon.getCardSprite())));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                picLabel.setText(pokemon.getNamePoke() + "\n" + pokemon.getRarity() + "\n" + pokemon.getCardType() + "\n" + pokemon.getSetFrom());
                picLabel.setHorizontalTextPosition(JLabel.CENTER);
                picLabel.setVerticalTextPosition(JLabel.BOTTOM);
                panelCard.add(picLabel,0);
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
        JButton backButton = new JButton("Back");
        backButton.setSize(100, 50);
        //backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(backButton,BorderLayout.EAST,1);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               paintHomeIU(frame);
            }
        });

        
        frame.repaint();

        frame.setVisible(true);
    }

    public Map<String,List<Pokemon>> loadPokemonFromJson(){
        return null;
    }

    public void writePokemonToJson(Pokemon pokemon){
        
    }
}
