import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.IO;
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
import javax.swing.JScrollPane;
import java.awt.*;
import java.util.Collections;



public class Pokedex{
    
    private final Map<Integer,List<Pokemon>> allPokemons;

    private static final Map<String, String> POKEMON_SETS = new HashMap<>();
    
    static {
        POKEMON_SETS.put("BS", "Base Set");
        POKEMON_SETS.put("JU", "Jungle");
        POKEMON_SETS.put("FO", "Fossil");
        POKEMON_SETS.put("TR", "Team Rocket");
        POKEMON_SETS.put("G1", "Gym Heroes");
        POKEMON_SETS.put("G2", "Gym Challenge");
        POKEMON_SETS.put("N1", "Neo Genesis");
        POKEMON_SETS.put("N2", "Neo Discovery");
        POKEMON_SETS.put("N3", "Neo Revelation");
        POKEMON_SETS.put("N4", "Neo Destiny");
        POKEMON_SETS.put("LC", "Legendary Collection");
        POKEMON_SETS.put("EXRS", "EX Ruby & Sapphire");
        POKEMON_SETS.put("EX", "Expedition");
        POKEMON_SETS.put("AQ", "Aquapolis");
        POKEMON_SETS.put("SK", "Skyridge");
        POKEMON_SETS.put("RS", "EX Ruby & Sapphire");
        POKEMON_SETS.put("SS", "EX Sandstorm");
        POKEMON_SETS.put("DR", "EX Dragon");
        POKEMON_SETS.put("MA", "EX Team Magma vs Team Aqua");
        POKEMON_SETS.put("HL", "EX Hidden Legends");
        POKEMON_SETS.put("RG", "EX FireRed & LeafGreen");
        POKEMON_SETS.put("TRR", "EX Team Rocket Returns");
        POKEMON_SETS.put("DX", "EX Deoxys");
        POKEMON_SETS.put("EM", "EX Emerald");
        POKEMON_SETS.put("UF", "EX Unseen Forces");
        POKEMON_SETS.put("DS", "EX Delta Species");
        POKEMON_SETS.put("HP", "EX Holon Phantoms");
        POKEMON_SETS.put("CG", "EX Crystal Guardians");
        POKEMON_SETS.put("DF", "EX Dragon Frontiers");
        POKEMON_SETS.put("PK", "EX Power Keepers");
        POKEMON_SETS.put("DP", "Diamond & Pearl");
        POKEMON_SETS.put("MT", "Diamond & Pearl - Mysterious Treasures");
        POKEMON_SETS.put("SW", "Diamond & Pearl - Secret Wonders");
        POKEMON_SETS.put("GE", "Diamond & Pearl - Great Encounters");
        POKEMON_SETS.put("MD", "Diamond & Pearl - Majestic Dawn");
        POKEMON_SETS.put("LA", "Diamond & Pearl - Legends Awakened");
        POKEMON_SETS.put("SF", "Diamond & Pearl - Stormfront");
        POKEMON_SETS.put("PL", "Platinum");
        POKEMON_SETS.put("RR", "Platinum - Rising Rivals");
        POKEMON_SETS.put("SV", "Platinum - Supreme Victors");
        POKEMON_SETS.put("AR", "Platinum - Arceus");
        POKEMON_SETS.put("HS", "HeartGold & SoulSilver");
        POKEMON_SETS.put("UL", "HeartGold & SoulSilver - Unleashed");
        POKEMON_SETS.put("UD", "HeartGold & SoulSilver - Undaunted");
        POKEMON_SETS.put("TF", "HeartGold & SoulSilver - Triumphant");
        POKEMON_SETS.put("CL", "Call of Legends");
        POKEMON_SETS.put("BWL", "Black & White");
        POKEMON_SETS.put("EPO", "Black & White - Emerging Powers");
        POKEMON_SETS.put("NVI", "Black & White - Noble Victories");
        POKEMON_SETS.put("NXD", "Black & White - Next Destinies");
        POKEMON_SETS.put("DEX", "Black & White - Dark Explorers");
        POKEMON_SETS.put("DRX", "Black & White - Dragons Exalted");
        POKEMON_SETS.put("DRX", "Black & White - Dragons Exalted");
        POKEMON_SETS.put("DRV", "Black & White - Dragon Vault");
        POKEMON_SETS.put("BCR", "Black & White - Boundaries Crossed");
        POKEMON_SETS.put("PLS", "Black & White - Plasma Storm");
        POKEMON_SETS.put("PLF", "Black & White - Plasma Freeze");
        POKEMON_SETS.put("PLB", "Black & White - Plasma Blast");
        POKEMON_SETS.put("LTR", "Black & White - Legendary Treasures");
        POKEMON_SETS.put("KSS", "Kalos Starter Set");
        POKEMON_SETS.put("XY", "XY");
        POKEMON_SETS.put("FLF", "XY - Flashfire");
        POKEMON_SETS.put("FFI", "XY - Furious Fists");
        POKEMON_SETS.put("PHF", "XY - Phantom Forces");
        POKEMON_SETS.put("PRC", "XY - Primal Clash");
        POKEMON_SETS.put("DCR", "XY - Double Crisis");
        POKEMON_SETS.put("ROS", "XY - Roaring Skies");
        POKEMON_SETS.put("AOR", "XY - Ancient Origins");
        POKEMON_SETS.put("BKT", "XY - BREAKthrough");
        POKEMON_SETS.put("BKP", "XY - BREAKpoint");
        POKEMON_SETS.put("GEN", "Generations");
        POKEMON_SETS.put("FCO", "Fates Collide");
        POKEMON_SETS.put("STS", "Steam Siege");
        POKEMON_SETS.put("EVO", "Evolutions");
        POKEMON_SETS.put("SUM", "Sun & Moon");
        POKEMON_SETS.put("GRI", "Sun & Moon - Guardians Rising");
        POKEMON_SETS.put("BUS", "Sun & Moon - Burning Shadows");
        POKEMON_SETS.put("SLG", "Sun & Moon - Shining Legends");
        POKEMON_SETS.put("CIN", "Sun & Moon - Crimson Invasion");
        POKEMON_SETS.put("UPR", "Sun & Moon - Ultra Prism");
        POKEMON_SETS.put("FLI", "Sun & Moon - Forbidden Light");
        POKEMON_SETS.put("CES", "Sun & Moon - Celestial Storm");
        POKEMON_SETS.put("DRM", "Sun & Moon - Dragon Majesty");
        POKEMON_SETS.put("LOT", "Sun & Moon - Lost Thunder");
        POKEMON_SETS.put("TEU", "Sun & Moon - Team Up");
        POKEMON_SETS.put("DET", "Sun & Moon - Detective Pikachu");
        POKEMON_SETS.put("UNB", "Sun & Moon - Unbroken Bonds");
        POKEMON_SETS.put("UNB", "Sun & Moon - Unbroken Bonds");
        POKEMON_SETS.put("HIF", "Sun & Moon - Hidden Fates");
        POKEMON_SETS.put("CEC", "Sun & Moon - Cosmic Eclipse");
        POKEMON_SETS.put("SSH", "Sword & Shield");
        POKEMON_SETS.put("RCL", "Sword & Shield - Rebel Clash");
        POKEMON_SETS.put("DAA", "Sword & Shield - Darkness Ablaze");
        POKEMON_SETS.put("CPA", "Sword & Shield - Champion's Path");
        POKEMON_SETS.put("VIV", "Sword & Shield - Vivid Voltage");
        POKEMON_SETS.put("SHF", "Sword & Shield - Shining Fates");
        POKEMON_SETS.put("BST", "Sword & Shield - Battle Styles");
        POKEMON_SETS.put("CRE", "Sword & Shield - Chilling Reign");
        POKEMON_SETS.put("EVS", "Sword & Shield - Evolving Skies");
        POKEMON_SETS.put("CEL", "Sword & Shield - Celebrations");
        POKEMON_SETS.put("FST", "Sword & Shield - Fusion Strike");
        POKEMON_SETS.put("BRS", "Sword & Shield - Brilliant Stars");
        POKEMON_SETS.put("ASR", "Sword & Shield - Astral Radiance");
        POKEMON_SETS.put("FRL", "Sword & Shield - Forbidden Light");
    }

    // To complete the list of sets
    // https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_Trading_Card_Game_expansions

    /**
     * Create the GUI for the Pokedex
     */
    public Pokedex(){
        
        this.allPokemons = this.createPokemons();

        JFrame frame = new JFrame("The Pokedex App V1.0");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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
    public Map<Integer,List<Pokemon>> createPokemons(){

        File jsonFile = new File(String.join(File.pathSeparator,"."));
        String jsonContents[] = jsonFile.list();

        boolean existingJsonFile = false;

        for (String content : jsonContents) {
            if(content.equals("cardsStored.json")){
                existingJsonFile = true;
                System.out.println("Json file already exist");
                break;
            }
        }

        File ImgFiles = new File("Card_Img");
        String imgContents[] = ImgFiles.list(); 
        if(existingJsonFile && jsonContents.length == imgContents.length){
            return this.loadPokemonFromJson();
        }
        else if (existingJsonFile) {
            File jsonFileToDelete = new File("cardsStored.json");
            jsonFileToDelete.delete();
            jsonFileToDelete = null;
        }

        Map<Integer,List<Pokemon>> allPokemonCards = new HashMap<>();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().create();
        Gson gson = builder.create();

        File file = new File("cardsStored.json");
        int count = 0;

        try{
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write("[\n");
                String[] images = this.getAllImages();
                for (String imageName : images) {

                    File inputImg = new File(String.join(File.separator,"Card_Img",imageName));
                    BufferedImage imgCard = ImageIO.read(inputImg);

                    if(imgCard.getWidth() != 388 && imgCard.getHeight() != 278){
                        imgCard = null;
                        this.resizePokemonImage(inputImg);
                    }
                    count++;
                    //split the image name to get the pokemon caracteristics
                    String[] imageNameSplit = imageName.split("_");
                    Integer numPoke = Integer.valueOf(imageNameSplit[0]);
                    String pokemonName = imageNameSplit[1];
                    String rarity = imageNameSplit[2];
                    String pokemonSet = imageNameSplit[3];
                    
                    Pokemon pokemon = new Pokemon(imageName, pokemonName, numPoke, rarity,pokemonSet);
                    
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
            }
            }catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        return allPokemonCards;
    }

    public void paintHomeIU(JFrame frame){

        
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        frame.setTitle("The Pokedex App V1.0");

        JPanel panelTop = new JPanel();
        panelTop.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        frame.add(panelTop, BorderLayout.NORTH);

        JLabel homeLabel = new JLabel("Welcome to the Pokedex App V1.0 , the app that allows you to see all the existing Pokemon cards");
        homeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        panelTop.add(homeLabel);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(Math.abs(this.allPokemons.size()/4), 4,5,50));

        // Faire que les pokémons soient représenté dans l'ordre du num pokédex
        List<Integer> sortedKeys = new ArrayList<>(this.allPokemons.keySet());
        Collections.sort(sortedKeys);

        for (Integer pokemonNumber : sortedKeys){

            Pokemon pokemon = this.allPokemons.get(pokemonNumber).get(0);

            try {                
                BufferedImage image = ImageIO.read(new File(String.join(File.separator,"Card_Img",pokemon.getCardSprite())));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                picLabel.setText(pokemon.getNamePoke());
                picLabel.setHorizontalTextPosition(JLabel.CENTER);
                picLabel.setVerticalTextPosition(JLabel.BOTTOM);
                picLabel.setIconTextGap(15);
                panel.add(picLabel);
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


        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); //speed up the scroll

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.repaint();
        frame.setVisible(true);

    }

    public void createPokemonFrame(JFrame frame,Integer pokemonNumber){

        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        frame.setTitle("All existing cards for " + allPokemons.get(pokemonNumber).get(0).getNamePoke());
        
        JPanel panelCard = new JPanel();
        int lignes = Math.abs(this.allPokemons.get(pokemonNumber).size()/4) + 1;
        panelCard.setLayout(new GridLayout(lignes,1,5,80));
        JPanel panelCardDown = new JPanel();

        panelCard.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        for (Pokemon pokemon : this.allPokemons.get(pokemonNumber)) {
            try {                
                BufferedImage image = ImageIO.read(new File(String.join(File.separator,"Card_Img",pokemon.getCardSprite())));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                picLabel.setText(pokemon.getNamePoke() + "\n" + pokemon.getRarity() + "\n" + "\n" + pokemon.getSetFrom());
                picLabel.setHorizontalTextPosition(JLabel.CENTER);
                picLabel.setVerticalTextPosition(JLabel.BOTTOM);
                picLabel.setIconTextGap(10);
                panelCard.add(picLabel,BorderLayout.CENTER,0);
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
        //backButton.setBorder(BorderFactory.createLineBorder(Color.red,3));
        //backButton.setBounds(50, 50, 200, 100);
        backButton.setVerticalAlignment(JButton.BOTTOM);
        backButton.setHorizontalAlignment(JButton.RIGHT);
        //backButton.setBounds(frame.getWidth()-200,frame.getHeight() -200, 100, 100);
        
        panelCardDown.add(backButton,BorderLayout.CENTER);
        backButton.addActionListener(e -> paintHomeIU(frame)); // afficher la page d'accueil

        
        frame.repaint();
        frame.revalidate();

        JScrollPane scrollPane = new JScrollPane(panelCard);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); //speed up the scroll

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelCardDown, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public Map<Integer,List<Pokemon>> loadPokemonFromJson(){

        Map<Integer,List<Pokemon>> allJsonPokemons = new HashMap<>();

        File jsonFile = new File("cardsStored.json");
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Gson gson = new Gson();
            Pokemon[] pokemons = gson.fromJson(fileReader, Pokemon[].class);

            for(Pokemon pokemon : pokemons){
                if(allJsonPokemons.containsKey(pokemon.getNumPoke())){
                    allJsonPokemons.get(pokemon.getNumPoke()).add(pokemon);
                }else{
                    List<Pokemon> list = new ArrayList<>();
                    list.add(pokemon);
                    allJsonPokemons.put(pokemon.getNumPoke(),list);
                }
            }

        } catch (IOException e) {
        }

        System.out.println("loading Json");
        return allJsonPokemons;
    }

    // https://memorynotfound.com/java-resize-image-fixed-width-height-example/

    public void resizePokemonImage(File pokemonImage) throws IOException{
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