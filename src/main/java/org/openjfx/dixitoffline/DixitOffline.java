package org.openjfx.dixitoffline;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JavaFX App
 */
public class DixitOffline extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Dixit Offline");

        // Create gamers' list
        ArrayList<Gamer> mainGamer = new ArrayList<>();
        ArrayList<Gamer> otherGamers = new ArrayList<>();
        
        ArrayList<Integer> gamersCount = new ArrayList<>();
        gamersCount.add(0);
        ArrayList<Integer> currentGamerNum = new ArrayList<>();
        currentGamerNum.add(0);
                
        ArrayList<Integer> gamersNumsAfterRand = new ArrayList<>();
        ArrayList<Integer> gamersNumsAfterRandCurrIndex = new ArrayList<>();
        
        ArrayList<Integer> gamersNumsToVote = new ArrayList<>();
        ArrayList<Integer> gamersNumsToVoteCurrIndex = new ArrayList<>();
        gamersNumsToVoteCurrIndex.add(0);

        ArrayList<Integer> gamerImagesCount = new ArrayList<>();
//        gamerImagesCount.add(6);
        
        // Get images' files' list from directory
        String cardsDirPath = "./src/main/java/org/openjfx/dixitoffline/cardspackage";
        File cardsDir = new File(cardsDirPath);
        File[] fList = cardsDir.listFiles();
        
        ArrayList<String> cardsDirImagesNames = new ArrayList<>();
        ArrayList<String> cardsDirImagesNamesNotDeleted = new ArrayList<>();
        for (File file : fList) {
            cardsDirImagesNames.add(file.getName());
            cardsDirImagesNamesNotDeleted.add(file.getName());
        }
        
        // !!! Temporary !!!
        // Print current path
        File directory = new File("./");
        System.out.println("*** Absolute current path ***");
        System.out.println(directory.getAbsolutePath());
        
        // - !!! Temporary !!!
        
        // Layouts for scene1
        VBox vbox = new VBox();
        HBox hbox = new HBox();

        // Controls for layouts for scene1
        Label addGamersNamesLbl = new Label("Add gamers' names: ");
        Label enterNameLbl = new Label("Enter name: ");
        
        ListView gamersNamesList = new ListView();
        
        TextField enterNameTextField = new TextField();
        
        Button addGamerNameBtn = new Button("Add");
        Button nextBtn = new Button("Next");

        // Add all controls to layouts for scene1
        hbox.getChildren().addAll(enterNameLbl, enterNameTextField, addGamerNameBtn);
        vbox.getChildren().addAll(addGamersNamesLbl, gamersNamesList, hbox, nextBtn);

        // Create scene1
        Scene scene1 = new Scene(vbox, 640, 480);
        
        // Layouts for scene2
        VBox vbox2 = new VBox();
        
        // Controls for layouts for scene2
        Label areYouGamerLbl = new Label("Gamer " + "SomeGamer" + " have to choose an image. Are you gamer " + "SomeGamer" + " ?");
                                
        Button yesImGamerBtn = new Button("Yes");
        
        // Add all controls to layouts for scene2
        vbox2.getChildren().addAll(areYouGamerLbl, yesImGamerBtn);
        
        // Create scene2
        Scene scene2 = new Scene(vbox2, 640, 480);
        
        // Layouts for scene3
        VBox vbox3 = new VBox();
        HBox hbox31 = new HBox();
        
        // Controls for layouts for scene3
        Label gamerSelectImageAssocLbl = new Label("SomeGamer" + "selects image with association");
        
        Button scene3PrevImageBtn = new Button("<<");
        Button scene3NextImageBtn = new Button(">>");
        
        ImageView choosedImageWithAssoc = new ImageView();
        
        Label typeImageAssocLbl = new Label("Type image's association: ");
        
        TextField typeImageAssocFld = new TextField();
        
        Button scene3NextBtn = new Button("Next");
        
        // Add all controls to layouts for scene3
        hbox31.getChildren().addAll(scene3PrevImageBtn, scene3NextImageBtn);
        vbox3.getChildren().addAll(gamerSelectImageAssocLbl, hbox31, choosedImageWithAssoc, 
                typeImageAssocLbl, typeImageAssocFld, scene3NextBtn);
        
        // Create scene3
        Scene scene3 = new Scene(vbox3, 640, 480);
        
        // Layouts for scene4
        VBox vbox4 = new VBox();
        
        // Controls for layouts for scene4
        Label scene4Assoc = new Label("Assotiation: ");
        Label scene4AssocStrLbl = new Label("Some association");
        Label scene4GamerHaveToLbl = new Label("Some gamer" + 
                " have to choose an image to an association. Are you " + 
                "Some gamer" + "?");
        
        Button scene4YesBtn = new Button("Yes");
        
        // Add all controls to layouts to scene4
        vbox4.getChildren().addAll(scene4Assoc, scene4AssocStrLbl, scene4GamerHaveToLbl, scene4YesBtn);
        
        // Create scene4
        Scene scene4 = new Scene(vbox4, 640, 480);
        
        // Layouts for scene5
        VBox vbox5 = new VBox();
        HBox hbox51 = new HBox();
        
        // Controls for layouts for scene5
        Label scene5GamerAssocLbl = new Label("Some gamer" + 
                " selects image to an association: ");
        Label scene5AssocStrLbl = new Label("Some association");
        
        Button scene5PrevImageBtn = new Button("<<");
        Button scene5NextImageBtn = new Button(">>");
        
        ImageView imageChoosedToAssoc = new ImageView();
        
        Button scene5NextBtn = new Button("Next");
        
        // Add all controls to layouts to scene5
        hbox51.getChildren().addAll(scene5PrevImageBtn, scene5NextImageBtn);
        vbox5.getChildren().addAll(scene5GamerAssocLbl, scene5AssocStrLbl, hbox51, imageChoosedToAssoc, scene5NextBtn);
        
        // Create scene5
        Scene scene5 = new Scene(vbox5, 640, 480);
        
        // Layouts for scene6
        VBox vbox6 = new VBox();
        HBox hbox61 = new HBox();
        
        // Controls for layouts for scene6
        Label scene6AllGamersLbl = new Label("All gamers selected images to the association: ");
        Label scene6AssocLbl = new Label("Some association");
        
        Button scene6PrevImageBtn = new Button("<<");
        Button scene6NextImageBtn = new Button(">>");
        
        ImageView allChoosedImages = new ImageView();
        
        Button scene6NextBtn = new Button("Next");
        
        // Add all controls to layouts to scene6
        hbox61.getChildren().addAll(scene6PrevImageBtn, scene6NextImageBtn);
        vbox6.getChildren().addAll(scene6AllGamersLbl, scene6AssocLbl, hbox61, allChoosedImages, scene6NextBtn);
        
        // Create scene6
        Scene scene6 = new Scene(vbox6, 640, 480);
        
        // Layouts for scene7
        VBox vbox7 = new VBox();
        
        // Controls for layouts for scene7
        Label scene7GamerHaveToLbl = new Label("Some gamer" + " have to vote for some image to the association: ");
        Label scene7AssociationLbl = new Label("Some association");
        Label scene7AreYouGamer = new Label("Are you " + "Some gamer" + "?");
        
        Button scene7YesBtn = new Button("Yes");
        
        // Add all controls to layouts to scene7
        vbox7.getChildren().addAll(scene7GamerHaveToLbl, scene7AssociationLbl, scene7AreYouGamer, scene7YesBtn);
        
        // Create scene7
        Scene scene7 = new Scene(vbox7, 640, 480);
        
        // Layouts for scene8
        VBox vbox8 = new VBox();
        HBox hbox81 = new HBox();
        
        // Controls for layouts for scene8
        Label scene8GamerVotesLbl = new Label("Some gamer" + " votes for image to the association: ");
        Label scene8AssociationLbl = new Label("Some association");
        
        Button scene8PrevImageBtn = new Button("<<");
        Button scene8NextImageBtn = new Button(">>");
        
        ImageView scene8ImageToVote = new ImageView();
        
        Button scene8NextBtn = new Button("Next");
        
        // Add all controls to layouts to scene8
        hbox81.getChildren().addAll(scene8PrevImageBtn, scene8NextImageBtn);
        vbox8.getChildren().addAll(scene8GamerVotesLbl, scene8AssociationLbl, hbox81, scene8ImageToVote, scene8NextBtn);
        
        // Create scene8
        Scene scene8 = new Scene(vbox8, 640, 480);
        
        // Layouts for scene9
        VBox vbox9 = new VBox();
        HBox hbox91 = new HBox();
        VBox vbox911 = new VBox();
        VBox vbox9111 = new VBox();
        VBox vbox9112 = new VBox();
        
        // Controls for layouts for scene9
        Label scene9AllGamersVotedLbl = new Label("All gamers voted. ");
        Label scene9ImageOfGamerLbl = new Label("The image of " + "Some gamer" + " with association: ");
        Label scene9AssociationLbl = new Label("Some association");
        
        ImageView scene9ImageOfGamer = new ImageView();
        
        Label scene9VotedForItLbl = new Label("Voted for it: ");
        
        Label scene9SeparatorLbl = new Label("***");
        Label scene9GamerPointsLbl = new Label("Some gamer" + " +" + "n" + " points");
        
        Button scene9NextBtn = new Button("Next");
        
        // Add all controls to layouts to scene9
        vbox9112.getChildren().addAll(scene9SeparatorLbl, scene9GamerPointsLbl);
        vbox911.getChildren().addAll(scene9VotedForItLbl, vbox9111, vbox9112);
        hbox91.getChildren().addAll(scene9ImageOfGamer, vbox911);
        vbox9.getChildren().addAll(scene9AllGamersVotedLbl, scene9ImageOfGamerLbl, 
                scene9AssociationLbl, hbox91, scene9NextBtn);
        
        // Create scene9
        Scene scene9 = new Scene(vbox9, 640, 480);
        
        // Layouts for scene10
        VBox vbox10 = new VBox();
        HBox hbox10_1 = new HBox();
        VBox vbox10_1_1 = new VBox();
        VBox vbox10_1_1_1 = new VBox();
        VBox vbox10_1_1_2 = new VBox();
        
        // Controls for layouts for scene10
        Label scene10AllGamersVotedLbl = new Label("All gamers voted. ");
        Label scene10ImageOfGamerLbl = new Label("The image of " + "Some gamer" + " to the association: ");
        Label scene10AssociationLbl = new Label("Some association");
        
        ImageView scene10ImageOfGamer = new ImageView();
        
        Label scene10VotedForItLbl = new Label("Voted for it: ");
        
        Label scene10SeparatorLbl = new Label("***");
        Label scene10GamerPointsLbl = new Label("Some gamer" + " +" + "n" + " points");
        
        Button scene10NextBtn = new Button("Next");
        
        // Add all controls to layouts to scene10
        vbox10_1_1_2.getChildren().addAll(scene10SeparatorLbl, scene10GamerPointsLbl);
        vbox10_1_1.getChildren().addAll(scene10VotedForItLbl, vbox10_1_1_1, vbox10_1_1_2);
        hbox10_1.getChildren().addAll(scene10ImageOfGamer, vbox10_1_1);
        vbox10.getChildren().addAll(scene10AllGamersVotedLbl, scene10ImageOfGamerLbl, 
                scene10AssociationLbl, hbox10_1, scene10NextBtn);
        
        // Create scene10
        Scene scene10 = new Scene(vbox10, 640, 480);
        
        // Layouts for scene11
        VBox vbox11 = new VBox();
        VBox vbox11_1 = new VBox();
        
        // Controls for layouts for scene11
        Label scene11AllGamersVotedLbl = new Label("All gamers voted. ");
        Label scene11ScoreLbl = new Label("Score: ");
        
        Button scene11NextBtn = new Button("Next");
        
        // Add all controls to layouts to scene11
        vbox11.getChildren().addAll(scene11AllGamersVotedLbl, scene11ScoreLbl, vbox11_1, scene11NextBtn);
        
        // Create scene11
        Scene scene11 = new Scene(vbox11, 640, 480);
        
        // Set scene1
        primaryStage.setScene(scene1);

        primaryStage.show();
        
        // *** Comments added ***
        // Actions when 'Add gamer' button placed on scene1 
        // (scene where gamers are added) pressed
        addGamerNameBtn.setOnAction((final ActionEvent e) -> {
            // Get gamer's name from text field
            String gamerName;
            // If text from text field != null and text from text field isn't empty, 
            // get gamer's name from text field
            if (enterNameTextField.getText() != null && !enterNameTextField.getText().isEmpty()) {
                // Get gamer's name from text field
                gamerName = enterNameTextField.getText();
                // Clear text field
                enterNameTextField.clear();
                // Add gamer's name to ListView
                gamersNamesList.getItems().add(gamerName);
                
                // Add new gamer
                // If no added gamers, add main gamer ('storyteller')
                if (gamersCount.get(0) == 0) {
                    // Add main gamer ('storyteller')
                    mainGamer.add(new Gamer(gamerName, new ArrayList<>(), 0, "", new ArrayList<>(), 0, 0));
                    // Increase gamers' count by 1
                    int intGamersCount = gamersCount.get(0) + 1;
                    gamersCount.set(0, intGamersCount);
                } 
                // Else add other gamer
                else {
                    // Add other gamer
                    otherGamers.add(new Gamer(gamerName, new ArrayList<>(), 0, "", new ArrayList<>(), 0, 0));
                    // Increase gamers' count by 1
                    int intGamersCount = gamersCount.get(0) + 1;
                    gamersCount.set(0, intGamersCount);
                }
                
            }
        });
        
        // *** Comments added ***
        // Actions when 'Next' button placed on scene1 
        // (scene where gamers are added) pressed
        nextBtn.setOnAction((final ActionEvent e) -> {
            // Choose 6 or 7 random images for gamers
            int randImagesCount;
            
            // If 3 gamers added, add 7 random images' names for gamers
            if (gamersCount.get(0) == 3)
                randImagesCount = 7;
            // Else add 6 random images' names for gamers
            else
                randImagesCount = 6;
            gamerImagesCount.add(randImagesCount);
            
            // Choose random images for main gamer ('storyteller')
            ArrayList<String> gamerImages = new ArrayList<>();
            for (int i = 0; i < randImagesCount; i++) {
                // Get random index from range 0..size_of_arraylist_of_cards'_images'_names
                // to choose random card's image name
                int randIndex = ThreadLocalRandom.current().nextInt(0, cardsDirImagesNames.size());
                // Add randomly choosed card's image name to ArrayList gamerImages by random index
                gamerImages.add(cardsDirImagesNames.get(randIndex));
                // Remove randomly choosed card's image name from ArrayList cardsDirImagesNames by random index
                cardsDirImagesNames.remove(randIndex);
            }
            // Set list of randomly choosed images for main gamer ('storyteller')
            mainGamer.get(0).setGamerImages(gamerImages);
            
            // Choose random images for other gamers
            for (Gamer gamer : otherGamers) {
                gamerImages = new ArrayList<>();
                for (int i = 0; i < randImagesCount; i++) {
                    // Get random index from range 0..size_of_arraylist_of_cards'_images'_names
                    // to choose random card's image name
                    int randIndex = ThreadLocalRandom.current().nextInt(0, cardsDirImagesNames.size());
                    // Add randomly choosed card's image name to ArrayList gamerImages by random index
                    gamerImages.add(cardsDirImagesNames.get(randIndex));
                    // Remove randomly choosed card's image name from ArrayList cardsDirImagesNames by random index
                    cardsDirImagesNames.remove(randIndex);
                }
                // Set list of randomly choosed images for other gamer
                gamer.setGamerImages(gamerImages);
            }
            // - Choose 6 or 7 random images to gamers
            
            // Set text for label on scene2
            areYouGamerLbl.setText("Gamer " + mainGamer.get(0).getGamerName() + " have to choose an image. Are you gamer " + mainGamer.get(0).getGamerName() + "?");

            // Create scene2 (scene with question 'Are you gamer Gamer_name?')
            primaryStage.setScene(scene2);

            primaryStage.show();
        });
        
        // *** Comments added ***
        // Actions when 'Yes' button placed on scene2 
        // (scene with question 'Are you gamer Gamer_name?') pressed
        yesImGamerBtn.setOnAction((final ActionEvent e) -> {
            // Set text for label on scene3 (scene where main gamer ('storyteller') 
            // selects image and writes association for it)
            gamerSelectImageAssocLbl.setText(mainGamer.get(0).getGamerName() + " selects image with association");

            try {
                // Attemption to open first image from images randomly choosed for main gamer ('storyteller')
                FileInputStream inputstreamImgAssoc = new FileInputStream(cardsDirPath + "/"
                        + mainGamer.get(0).getGamerImages().get(mainGamer.get(0).getGamerCurrentImageNum()));
                Image imgAssoc = new Image(inputstreamImgAssoc);
                // Set opened image to scene3
                choosedImageWithAssoc.setImage(imgAssoc);
                choosedImageWithAssoc.setFitWidth(164);
                choosedImageWithAssoc.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Create scene3
            primaryStage.setScene(scene3);

            primaryStage.show();
        });
        
        // *** Comments added ***
        // Actions when '>>' (button to go to next image from images of main gamer ('storyteller')) 
        // placed on scene3 (scene where main gamer ('storyteller') selects image 
        // and writes association for it) pressed
        scene3NextImageBtn.setOnAction((final ActionEvent e) -> {
            // Get number of current image of main gamer ('storyteller')
            int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
            // If number of current image < 6 (if 7 images for gamer) 
            // or 5 (if 6 images for gamer), increase number of current image by 1
            if (gamerCurrentImageNum < gamerImagesCount.get(0) - 1)
                gamerCurrentImageNum++;
            // Else set number of current image to 0 (number of first image)
            else
                gamerCurrentImageNum = 0;
            // Set number of current image for main gamer ('storyteller')
            mainGamer.get(0).setGamerCurrentImageNum(gamerCurrentImageNum);
            
            try {
                // Attemption to open next image from images randomly choosed for main gamer ('storyteller')
                FileInputStream inputstreamImgAssoc = new FileInputStream(cardsDirPath + "/" +
                        mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                // Set opened image to scene3
                choosedImageWithAssoc.setImage(new Image(inputstreamImgAssoc));
                choosedImageWithAssoc.setFitWidth(164);
                choosedImageWithAssoc.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // *** Comments added ***
        // Actions when '<<' (button to go to previous image from images of main gamer ('storyteller')) 
        // placed on scene3 (scene where main gamer ('storyteller') selects image 
        // and writes association for it) pressed
        scene3PrevImageBtn.setOnAction((final ActionEvent e) -> {
            // Get number of current image of main gamer ('storyteller')
            int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
            // If number of current image > 0, decrease number of current image by 1
            if (gamerCurrentImageNum > 0)
                gamerCurrentImageNum--;
            // Else set number of current image to 6 (if 7 images for gamer) 
            // or 5 (if 6 images for gamer)
            else
                gamerCurrentImageNum = gamerImagesCount.get(0) - 1;
            // Set number of current image for main gamer ('storyteller')
            mainGamer.get(0).setGamerCurrentImageNum(gamerCurrentImageNum);
            
            try {
                // Attemption to open previous image from images randomly choosed for main gamer ('storyteller')
                FileInputStream inputstreamImgAssoc = new FileInputStream(cardsDirPath + "/" +
                        mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                // Set opened image to scene3
                choosedImageWithAssoc.setImage(new Image(inputstreamImgAssoc));
                choosedImageWithAssoc.setFitWidth(164);
                choosedImageWithAssoc.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // *** Comments added ***
        // Actions when 'Next' button placed on scene3 (scene where main gamer ('storyteller') selects image 
        // and writes association for it) pressed
        scene3NextBtn.setOnAction((final ActionEvent e) -> {
            // Set text with association from text field for main gamer ('storyteller')
            mainGamer.get(0).setImageAssociation(typeImageAssocFld.getText());

            // Clear text field
            typeImageAssocFld.clear();

            // Set text for labels on scene4
            scene4AssocStrLbl.setText(mainGamer.get(0).getImageAssociation());
            scene4GamerHaveToLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName()
                    + " have to choose an image to an association. Are you "
                    + otherGamers.get(currentGamerNum.get(0)).getGamerName() + "?");

            // Create scene4
            primaryStage.setScene(scene4);

            primaryStage.show();
        });
        
        // *** Comments added ***
        // Actions when 'Yes' button placed on scene4 (scene with question 
        // 'Are you Gamer_name?') pressed
        scene4YesBtn.setOnAction((final ActionEvent e) -> {
            // Set text for labels on scene5
            scene5GamerAssocLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName()
                    + " selects image to an association: ");
            scene5AssocStrLbl.setText(mainGamer.get(0).getImageAssociation());

            // Get number of current image of current other gamer
            int gamerCurrentImageNum = otherGamers.get(currentGamerNum.get(0)).getGamerCurrentImageNum();

            try {
                // Attemption to open first image from images randomly choosed for current other gamer
                FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                        + otherGamers.get(currentGamerNum.get(0)).getGamerImages().get(gamerCurrentImageNum));
                // Set opened image to scene5
                imageChoosedToAssoc.setImage(new Image(inputstreamImgToAssoc));
                imageChoosedToAssoc.setFitWidth(164);
                imageChoosedToAssoc.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Create scene5
            primaryStage.setScene(scene5);

            primaryStage.show();
        });
        
        scene5NextImageBtn.setOnAction((final ActionEvent e) -> {
            int gamerCurrentImageNum = otherGamers.get(currentGamerNum.get(0)).getGamerCurrentImageNum();
            if (gamerCurrentImageNum < 5)
                gamerCurrentImageNum++;
            else
                gamerCurrentImageNum = 0;
            
            otherGamers.get(currentGamerNum.get(0)).setGamerCurrentImageNum(gamerCurrentImageNum);
            
            try {
                FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                        + otherGamers.get(currentGamerNum.get(0)).getGamerImages().get(gamerCurrentImageNum));
                imageChoosedToAssoc.setImage(new Image(inputstreamImgToAssoc));
                imageChoosedToAssoc.setFitWidth(164);
                imageChoosedToAssoc.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        scene5PrevImageBtn.setOnAction((final ActionEvent e) -> {
            int gamerCurrentImageNum = otherGamers.get(currentGamerNum.get(0)).getGamerCurrentImageNum();
            if (gamerCurrentImageNum > 0)
                gamerCurrentImageNum--;
            else
                gamerCurrentImageNum = 5;
            
            otherGamers.get(currentGamerNum.get(0)).setGamerCurrentImageNum(gamerCurrentImageNum);
            
            try {
                FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                        + otherGamers.get(currentGamerNum.get(0)).getGamerImages().get(gamerCurrentImageNum));
                imageChoosedToAssoc.setImage(new Image(inputstreamImgToAssoc));
                imageChoosedToAssoc.setFitWidth(164);
                imageChoosedToAssoc.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        scene5NextBtn.setOnAction((final ActionEvent e) -> {
            int intCurrentGamerNum = currentGamerNum.get(0) + 1;
            currentGamerNum.set(0, intCurrentGamerNum);

            if (currentGamerNum.get(0) < otherGamers.size()) {
                scene4GamerHaveToLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName()
                        + " have to choose an image to an association. Are you "
                        + otherGamers.get(currentGamerNum.get(0)).getGamerName() + "?");

                // Create scene4
                primaryStage.setScene(scene4);

                primaryStage.show();
            } else {
                // randomize gamers' numbers' order
                // for example: 0, 1, 2 -> 1, 0, 2
                gamersNumsAfterRand.clear();
                for (int i = 0; i <= otherGamers.size(); i++) {
                    gamersNumsAfterRand.add(i);
                }

                Collections.shuffle(gamersNumsAfterRand, new Random());

                scene6AssocLbl.setText(mainGamer.get(0).getImageAssociation());

                gamersNumsAfterRandCurrIndex.clear();
                gamersNumsAfterRandCurrIndex.add(0);
                // If first element from arraylist gamersNumsAfterRand == 0
                if (gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) == 0) {
                    int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                    try {
                        FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                                + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                        allChoosedImages.setImage(new Image(inputstreamImgToAssoc));
                        allChoosedImages.setFitWidth(164);
                        allChoosedImages.setFitHeight(273);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    int gamerCurrentImageNum
                            = otherGamers.get(gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) - 1).getGamerCurrentImageNum();
                    try {
                        FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                                + otherGamers.get(gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) - 1).getGamerImages().get(gamerCurrentImageNum));
                        allChoosedImages.setImage(new Image(inputstreamImgToAssoc));
                        allChoosedImages.setFitWidth(164);
                        allChoosedImages.setFitHeight(273);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // Create scene6
                primaryStage.setScene(scene6);

                primaryStage.show();
            }
        });
        
        scene6NextImageBtn.setOnAction((final ActionEvent e) -> {
            if (gamersNumsAfterRandCurrIndex.get(0) < otherGamers.size()) {
                int intGamersNumsAfterRandCurrIndex = gamersNumsAfterRandCurrIndex.get(0) + 1;
                gamersNumsAfterRandCurrIndex.set(0, intGamersNumsAfterRandCurrIndex);
            } else {
                int intGamersNumsAfterRandCurrIndex = 0;
                gamersNumsAfterRandCurrIndex.set(0, intGamersNumsAfterRandCurrIndex);
            }
            
            if (gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) == 0) {
                int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                            + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                    allChoosedImages.setImage(new Image(inputstreamImgToAssoc));
                    allChoosedImages.setFitWidth(164);
                    allChoosedImages.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int gamerCurrentImageNum
                        = otherGamers.get(gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) - 1).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                            + otherGamers.get(gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) - 1).getGamerImages().get(gamerCurrentImageNum));
                    allChoosedImages.setImage(new Image(inputstreamImgToAssoc));
                    allChoosedImages.setFitWidth(164);
                    allChoosedImages.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        scene6PrevImageBtn.setOnAction((final ActionEvent e) -> {
            if (gamersNumsAfterRandCurrIndex.get(0) > 0) {
                int intGamersNumsAfterRandCurrIndex = gamersNumsAfterRandCurrIndex.get(0) - 1;
                gamersNumsAfterRandCurrIndex.set(0, intGamersNumsAfterRandCurrIndex);
            } else {
                int intGamersNumsAfterRandCurrIndex = otherGamers.size();
                gamersNumsAfterRandCurrIndex.set(0, intGamersNumsAfterRandCurrIndex);
            }
            
            if (gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) == 0) {
                int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                            + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                    allChoosedImages.setImage(new Image(inputstreamImgToAssoc));
                    allChoosedImages.setFitWidth(164);
                    allChoosedImages.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int gamerCurrentImageNum
                        = otherGamers.get(gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) - 1).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToAssoc = new FileInputStream(cardsDirPath + "/"
                            + otherGamers.get(gamersNumsAfterRand.get(gamersNumsAfterRandCurrIndex.get(0)) - 1).getGamerImages().get(gamerCurrentImageNum));
                    allChoosedImages.setImage(new Image(inputstreamImgToAssoc));
                    allChoosedImages.setFitWidth(164);
                    allChoosedImages.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        scene6NextBtn.setOnAction((final ActionEvent e) -> {
            // Set currentGamerNum to 0
            currentGamerNum.set(0, 0);

            scene7GamerHaveToLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName()
                    + " have to vote for some image to the association: ");
            scene7AssociationLbl.setText(mainGamer.get(0).getImageAssociation());
            scene7AreYouGamer.setText("Are you " + otherGamers.get(currentGamerNum.get(0)).getGamerName() + "?");

            primaryStage.setScene(scene7);

            primaryStage.show();
        });
        
        scene7YesBtn.setOnAction((final ActionEvent e) -> {
            scene8GamerVotesLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName() + " votes for image to the association: ");
            scene8AssociationLbl.setText(mainGamer.get(0).getImageAssociation());
            
            gamersNumsToVoteCurrIndex.set(0, 0);
            
            gamersNumsToVote.clear();
            
            for (Integer i : gamersNumsAfterRand)
                if (i != currentGamerNum.get(0) + 1)
                    gamersNumsToVote.add(i);
            
            if (gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) == 0) {
                int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToVote = new FileInputStream(cardsDirPath + "/"
                            + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                    scene8ImageToVote.setImage(new Image(inputstreamImgToVote));
                    scene8ImageToVote.setFitWidth(164);
                    scene8ImageToVote.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int gamerCurrentImageNum
                        = otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToVote = new FileInputStream(cardsDirPath + "/"
                            + otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getGamerImages().get(gamerCurrentImageNum));
                    scene8ImageToVote.setImage(new Image(inputstreamImgToVote));
                    scene8ImageToVote.setFitWidth(164);
                    scene8ImageToVote.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            primaryStage.setScene(scene8);
            
            primaryStage.show();
        });
        
        scene8NextImageBtn.setOnAction((final ActionEvent e) -> {
            if (gamersNumsToVoteCurrIndex.get(0) < otherGamers.size() - 1) {
                int intGamersNumsToVoteCurrIndex = gamersNumsToVoteCurrIndex.get(0) + 1;
                gamersNumsToVoteCurrIndex.set(0, intGamersNumsToVoteCurrIndex);
            } else {
                int intGamersNumsToVoteCurrIndex = 0;
                gamersNumsToVoteCurrIndex.set(0, intGamersNumsToVoteCurrIndex);
            }
            
            if (gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) == 0) {
                int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToVote = new FileInputStream(cardsDirPath + "/"
                            + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                    scene8ImageToVote.setImage(new Image(inputstreamImgToVote));
                    scene8ImageToVote.setFitWidth(164);
                    scene8ImageToVote.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int gamerCurrentImageNum
                        = otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToVote = new FileInputStream(cardsDirPath + "/"
                            + otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getGamerImages().get(gamerCurrentImageNum));
                    scene8ImageToVote.setImage(new Image(inputstreamImgToVote));
                    scene8ImageToVote.setFitWidth(164);
                    scene8ImageToVote.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        scene8PrevImageBtn.setOnAction((final ActionEvent e) -> {
            if (gamersNumsToVoteCurrIndex.get(0) > 0) {
                int intGamersNumsToVoteCurrIndex = gamersNumsToVoteCurrIndex.get(0) - 1;
                gamersNumsToVoteCurrIndex.set(0, intGamersNumsToVoteCurrIndex);
            } else {
                int intGamersNumsToVoteCurrIndex = otherGamers.size() - 1;
                gamersNumsToVoteCurrIndex.set(0, intGamersNumsToVoteCurrIndex);
            }
            
            if (gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) == 0) {
                int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToVote = new FileInputStream(cardsDirPath + "/"
                            + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                    scene8ImageToVote.setImage(new Image(inputstreamImgToVote));
                    scene8ImageToVote.setFitWidth(164);
                    scene8ImageToVote.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int gamerCurrentImageNum
                        = otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImgToVote = new FileInputStream(cardsDirPath + "/"
                            + otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getGamerImages().get(gamerCurrentImageNum));
                    scene8ImageToVote.setImage(new Image(inputstreamImgToVote));
                    scene8ImageToVote.setFitWidth(164);
                    scene8ImageToVote.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        scene8NextBtn.setOnAction((final ActionEvent e) -> {
            // Set gamers' name who voted to that gamer
            if (gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) == 0) {
                ArrayList<String> votedGamers = new ArrayList<>(mainGamer.get(0).getVotedGamers());
                votedGamers.add(otherGamers.get(currentGamerNum.get(0)).getGamerName());
                mainGamer.get(0).setVotedGamers(votedGamers);
            } else {
                ArrayList<String> votedGamers =
                        new ArrayList<>(otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).getVotedGamers());
                votedGamers.add(otherGamers.get(currentGamerNum.get(0)).getGamerName());
                otherGamers.get(gamersNumsToVote.get(gamersNumsToVoteCurrIndex.get(0)) - 1).setVotedGamers(votedGamers);
            }
            
            // If it's possible to go to next gamer, increase currentGamerNum and set scene7,
            // else set scene9
            if (currentGamerNum.get(0) < otherGamers.size() - 1) {
                // Set currentGamerNum to next value
                int intCurrentGamerNum = currentGamerNum.get(0) + 1;
                currentGamerNum.set(0, intCurrentGamerNum);
                
                scene7GamerHaveToLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName()
                        + " have to vote for some image to the association: ");
                scene7AssociationLbl.setText(mainGamer.get(0).getImageAssociation());
                scene7AreYouGamer.setText("Are you " + otherGamers.get(currentGamerNum.get(0)).getGamerName() + "?");
                
                primaryStage.setScene(scene7);
                
                primaryStage.show();
            } else {
                scene9ImageOfGamerLbl.setText("The image of " + mainGamer.get(0).getGamerName() + " with association: ");
                scene9AssociationLbl.setText(mainGamer.get(0).getImageAssociation());
                
                // Set main gamer's image on scene9
                int gamerCurrentImageNum = mainGamer.get(0).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImageOfGamer = new FileInputStream(cardsDirPath + "/"
                            + mainGamer.get(0).getGamerImages().get(gamerCurrentImageNum));
                    scene9ImageOfGamer.setImage(new Image(inputstreamImageOfGamer));
                    scene9ImageOfGamer.setFitWidth(164);
                    scene9ImageOfGamer.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Scoring for main gamer and other gamers who voted 
                // (or they all didn't vote) for main gamer
                int pointsForMainGamer, pointsForVotedGamers;
                // If all other gamers voted for main gamer's card
                if (mainGamer.get(0).getVotedGamers().size() == otherGamers.size()) {
                    pointsForMainGamer = 0;
                    pointsForVotedGamers = 2;
                } 
                // If nobody voted for main gamer's card
                else if(mainGamer.get(0).getVotedGamers().isEmpty()) {
                    pointsForMainGamer = 0;
                    pointsForVotedGamers = 2;
                } else {
                    pointsForMainGamer = pointsForVotedGamers = 3;
                }
                
                // Set points to main gamer
                mainGamer.get(0).setCurrentPoints(pointsForMainGamer);
                
                scene9GamerPointsLbl.setText(mainGamer.get(0).getGamerName() + " +" + pointsForMainGamer + " points");
                
                vbox9111.getChildren().clear();
                // Set points to other gamers
                for (String gamerName : mainGamer.get(0).getVotedGamers()) {
                    for (Gamer gamer : otherGamers) {
                        // If this gamer voted for main gamer
                        if (gamer.getGamerName().equals(gamerName)) {
                            gamer.setCurrentPoints(pointsForVotedGamers);
                            
                            Label gamerPointsLbl = new Label(gamerName + " +" + pointsForVotedGamers + " points");
                            vbox9111.getChildren().addAll(gamerPointsLbl);
                        }
                        // Else (this gamer didn't vote for main gamer)
                        else {
                            // If nobody voted for main gamer's card
                            if (mainGamer.get(0).getVotedGamers().isEmpty()) {
                                gamer.setCurrentPoints(pointsForVotedGamers);
                                
                                Label gamerPointsLbl = new Label(gamerName + " +" + pointsForVotedGamers + " points");
                                vbox9111.getChildren().addAll(gamerPointsLbl);
                            } 
                            // Else (somebody voted for main gamer's card but not this gamer)
                            else {
                                gamer.setCurrentPoints(0);
                            }
                        }
                    }
                }
                
                primaryStage.setScene(scene9);
                
                primaryStage.show();
            }
        });
        
        scene9NextBtn.setOnAction((final ActionEvent e) -> {
            // Set currentGamerNum to 0
            currentGamerNum.set(0, 0);
            
            scene10ImageOfGamerLbl.setText("The image of " + otherGamers.get(currentGamerNum.get(0)).getGamerName() + " to the association: ");
            scene10AssociationLbl.setText(mainGamer.get(0).getImageAssociation());
            
            // Set current gamer's image on scene10
            int gamerCurrentImageNum = otherGamers.get(currentGamerNum.get(0)).getGamerCurrentImageNum();
            try {
                FileInputStream inputstreamImageOfGamer = new FileInputStream(cardsDirPath + "/"
                        + otherGamers.get(currentGamerNum.get(0)).getGamerImages().get(gamerCurrentImageNum));
                scene10ImageOfGamer.setImage(new Image(inputstreamImageOfGamer));
                scene10ImageOfGamer.setFitWidth(164);
                scene10ImageOfGamer.setFitHeight(273);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Scoring for current (not main) gamer
            int pointsForCurrentGamer;
            int pointsForVotedGamers = 0;
            // If this gamer's current points > 0, then set pointsForCurrentGamer = count of gamers that voted for this gamer
            if (otherGamers.get(currentGamerNum.get(0)).getCurrentPoints() > 0)
                pointsForCurrentGamer = otherGamers.get(currentGamerNum.get(0)).getVotedGamers().size();
            // Else (this gamer's current points = 0, then set pointsForCurrentGamer = 0)
            else
                pointsForCurrentGamer = 0;
            
            // Set points to current gamer
            int currGamerCurrentPoints = otherGamers.get(currentGamerNum.get(0)).getCurrentPoints();
            otherGamers.get(currentGamerNum.get(0)).setCurrentPoints(currGamerCurrentPoints + pointsForCurrentGamer);
            
            scene10GamerPointsLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName() + " +"
                    + pointsForCurrentGamer + " points");
            
            vbox10_1_1_1.getChildren().clear();
            // Set points to other gamers
            for (String gamerName : otherGamers.get(currentGamerNum.get(0)).getVotedGamers()) {
                for (Gamer gamer : otherGamers) {
                    if (gamer.getGamerName().equals(gamerName)) {
                        Label gamerPointsLbl = new Label(gamerName + " +" + pointsForVotedGamers + " points");
                        vbox10_1_1_1.getChildren().addAll(gamerPointsLbl);
                    }
                }
            }
            
            primaryStage.setScene(scene10);
            
            primaryStage.show();
        });
        
        scene10NextBtn.setOnAction((final ActionEvent e) -> {
            // If it's possible to go to next gamer, increase currentGamerNum and set scene10,
            // else set scene11
            if (currentGamerNum.get(0) < otherGamers.size() - 1) {
                // Set currentGamerNum to next value
                int intCurrentGamerNum = currentGamerNum.get(0) + 1;
                currentGamerNum.set(0, intCurrentGamerNum);
                
                scene10ImageOfGamerLbl.setText("The image of " + otherGamers.get(currentGamerNum.get(0)).getGamerName() + " to the association: ");
                scene10AssociationLbl.setText(mainGamer.get(0).getImageAssociation());
                
                // Set current gamer's image on scene10
                int gamerCurrentImageNum = otherGamers.get(currentGamerNum.get(0)).getGamerCurrentImageNum();
                try {
                    FileInputStream inputstreamImageOfGamer = new FileInputStream(cardsDirPath + "/"
                            + otherGamers.get(currentGamerNum.get(0)).getGamerImages().get(gamerCurrentImageNum));
                    scene10ImageOfGamer.setImage(new Image(inputstreamImageOfGamer));
                    scene10ImageOfGamer.setFitWidth(164);
                    scene10ImageOfGamer.setFitHeight(273);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DixitOffline.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Scoring for current (not main) gamer
                int pointsForCurrentGamer;
                int pointsForVotedGamers = 0;
                // If this gamer's current points > 0, then set pointsForCurrentGamer = count of gamers that voted for this gamer
                if (otherGamers.get(currentGamerNum.get(0)).getCurrentPoints() > 0)
                    pointsForCurrentGamer = otherGamers.get(currentGamerNum.get(0)).getVotedGamers().size();
                // Else (this gamer's current points = 0, then set pointsForCurrentGamer = 0)
                else
                    pointsForCurrentGamer = 0;
                
                // Set points to current gamer
                int currGamerCurrentPoints = otherGamers.get(currentGamerNum.get(0)).getCurrentPoints();
                otherGamers.get(currentGamerNum.get(0)).setCurrentPoints(currGamerCurrentPoints + pointsForCurrentGamer);
                
                scene10GamerPointsLbl.setText(otherGamers.get(currentGamerNum.get(0)).getGamerName() + " +"
                        + pointsForCurrentGamer + " points");
                
                vbox10_1_1_1.getChildren().clear();
                // Set points to other gamers
                for (String gamerName : otherGamers.get(currentGamerNum.get(0)).getVotedGamers()) {
                    for (Gamer gamer : otherGamers) {
                        if (gamer.getGamerName().equals(gamerName)) {
                            Label gamerPointsLbl = new Label(gamerName + " +" + pointsForVotedGamers + " points");
                            vbox10_1_1_1.getChildren().addAll(gamerPointsLbl);
                        }
                    }
                }
                
                primaryStage.setScene(scene10);
                
                primaryStage.show();
            } else {
                // Set allPoints for each gamer:
                // for main gamer: 
                int mainGamerAllPoints = mainGamer.get(0).getAllPoints();
                int mainGamerCurrentPoints = mainGamer.get(0).getCurrentPoints();
                mainGamer.get(0).setAllPoints(mainGamerAllPoints + mainGamerCurrentPoints);
                // for other gamers: 
                otherGamers.forEach((gamer) -> {
                    int otherGamerAllPoints = gamer.getAllPoints();
                    int otherGamerCurrentPoints = gamer.getCurrentPoints();
                    gamer.setAllPoints(otherGamerAllPoints + otherGamerCurrentPoints);
                });
                
                // Clear votedGamers for each gamer:
                mainGamer.get(0).setVotedGamers(new ArrayList<>());
                otherGamers.forEach((gamer) -> {
                    gamer.setVotedGamers(new ArrayList<>());
                });
                
                // Delete current image for each gamer:
                // for main gamer: 
                ArrayList<String> mainGamerImages = mainGamer.get(0).getGamerImages();
                mainGamerImages.remove(mainGamer.get(0).getGamerCurrentImageNum());
                mainGamer.get(0).setGamerImages(mainGamerImages);
                // for other gamers: 
                otherGamers.forEach((gamer) -> {
                    ArrayList<String> otherGamerImages = gamer.getGamerImages();
                    otherGamerImages.remove(gamer.getGamerCurrentImageNum());
                    gamer.setGamerImages(otherGamerImages);
                });
                
                // Add new image for each gamer if cardsDirImagesNames.size() >= all gamers count:
                // else set gamerImagesCount--
                if (cardsDirImagesNames.size() >= otherGamers.size() + 1) {
                    // for main gamer: 
                    mainGamerImages = mainGamer.get(0).getGamerImages();
                    int imageRandIndex = ThreadLocalRandom.current().nextInt(0, cardsDirImagesNames.size());
                    mainGamerImages.add(cardsDirImagesNames.get(imageRandIndex));
                    cardsDirImagesNames.remove(imageRandIndex);
                    mainGamer.get(0).setGamerImages(mainGamerImages);
                    
                    // for other gamers: 
                    for (Gamer gamer : otherGamers) {
                        ArrayList<String> otherGamerImages = gamer.getGamerImages();
                        imageRandIndex = ThreadLocalRandom.current().nextInt(0, cardsDirImagesNames.size());
                        otherGamerImages.add(cardsDirImagesNames.get(imageRandIndex));
                        cardsDirImagesNames.remove(imageRandIndex);
                        gamer.setGamerImages(otherGamerImages);
                    }
                } else {
                    int intGamerImagesCount = gamerImagesCount.get(0) - 1;
                    gamerImagesCount.set(0, intGamerImagesCount);
                }
                
                vbox11_1.getChildren().clear();
                
                // Set gamers' labels to scene11
                // for main gamer: 
                Label mainGamerScoreLbl = new Label(mainGamer.get(0).getGamerName() + " *** "
                        + mainGamer.get(0).getAllPoints() + " points");
                vbox11_1.getChildren().addAll(mainGamerScoreLbl);
                // for other gamers: 
                for (Gamer gamer : otherGamers) {
                    Label otherGamerScoreLbl = new Label(gamer.getGamerName() + " *** "
                            + gamer.getAllPoints() + " points");
                    vbox11_1.getChildren().addAll(otherGamerScoreLbl);
                }
                
                // Change main gamer to first other gamer 
                // (current main gamer becomes last other gamer)
                Gamer temporaryGamer = mainGamer.get(0);
                mainGamer.set(0, otherGamers.get(0));
                otherGamers.remove(0);
                otherGamers.add(temporaryGamer);
                
                // Make gamers score list to find max value
                ArrayList<Integer> gamersScoreList = new ArrayList<>();
                gamersScoreList.add(mainGamer.get(0).getAllPoints());
                otherGamers.forEach((gamer) -> {
                    gamersScoreList.add(gamer.getAllPoints());
                });
                
                if (gamerImagesCount.get(0) == 0 || Collections.max(gamersScoreList) >= 30) {
                    scene11NextBtn.setText("New game");
                    
                    scene11NextBtn.setOnAction((final ActionEvent e1) -> {
                        mainGamer.clear();
                        otherGamers.clear();
                        gamersCount.set(0, 0);
                        currentGamerNum.set(0, 0);
                        gamerImagesCount.clear();
                        
                        cardsDirImagesNames.clear();
                        cardsDirImagesNamesNotDeleted.forEach((s) -> {
                            cardsDirImagesNames.add(s);
                        });
                        
                        gamersNamesList.getItems().clear();
                        
                        scene11NextBtn.setText("Next");
                        
                        primaryStage.setScene(scene1);
                        
                        primaryStage.show();
                    });
                } else {
                    scene11NextBtn.setOnAction((final ActionEvent e1) -> {
                        currentGamerNum.set(0, 0);
                        
                        String mainGamerName = mainGamer.get(0).getGamerName();
                        areYouGamerLbl.setText("Gamer " + mainGamerName + " have to choose an image. Are you gamer " + mainGamerName + " ?");
                        
                        primaryStage.setScene(scene2);
                        
                        primaryStage.show();
                    });
                }
                
                primaryStage.setScene(scene11);
                
                primaryStage.show();
            }
        });
    }
}


