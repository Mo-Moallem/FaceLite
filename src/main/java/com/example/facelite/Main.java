package com.example.facelite;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
// The main class representing the FaceLife application
public class Main extends Application {
    Stage stage;
    //nodes
    // GUI components

    Button btAdd = new Button("Add");
    Button btDelete = new Button("Delete");
    Button btLookup = new Button("Lookup");
    Button btClear = new Button();
    Button btChangeStatus = new Button("Change Status");

    TextField tfChangeStatus = new TextField();
    Button btChangePicture = new Button("Change Picture");
    TextField tfChangePicture = new TextField();
    Button btAddFriend = new Button("Add Friend");
    Button btDeleteFriend = new Button("Delete Friend");
    Button btSend = new Button("send Massage");
    Button btDarkMode = new Button();
    Button btCommunity = new Button("Community");
    Button btBackToMain = new Button("<back");
    TextField tfSend = new TextField();
    TextField tfAddFriend = new TextField();
    TextField tfName = new TextField();
    Label userName = new Label();
    Label lFriends = new Label();
    Label lFriendsList = new Label();
    ScrollPane spFriendsList = new ScrollPane();
    ScrollPane spMassages = new ScrollPane();
    ScrollPane spPosts = new ScrollPane();
    Label lMassages = new Label();
    Label lName = new Label("Name");
    VBox vbFriends = new VBox();
    Label lStatus = new Label();
    Label lUpdates = new Label();
    BorderPane space = new BorderPane();
    BorderPane pane = new BorderPane();
    BorderPane communityPane = new BorderPane();
    Scene scene = new Scene(pane, 775, 400);
    Scene communityScene = new Scene(communityPane, 775, 400);
    // Icons and images
    static Image defaultPic = new Image("defaultPic.jpg");
    static Image darkDefaultPic = new Image("darkDefaultPic.png");
    Image sunIcon = new Image("sun.png");
    Image moonIcon = new Image("moon.png");
    Image icon = new Image("FaceLifeIcon1.png");
    Image broomIcon = new Image("broom.png");
    Image darkBroomIcon = new Image("darkBroom.png");
    ImageView ivBroomIcon = new ImageView(broomIcon);
    ImageView ivPic = new ImageView();
    ImageView ivDarkMode = new ImageView(moonIcon);

    boolean defIsDarkMode = false;
    // Lists and indices
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Post> posts = new ArrayList<>();
    Button[] buttons = {btDeleteFriend,btAddFriend,btChangePicture,btChangeStatus,btLookup,btDelete,btAdd,btDarkMode,btClear,btSend,btCommunity};
    Label[] labels = {lFriends, lFriendsList,lName,lStatus,lUpdates,lMassages};
    TextField[] textFields = {tfAddFriend,tfChangePicture,tfChangeStatus,tfName,tfSend};

    //// Constants
    int BT_WIDTH = 140;
    int BT_HIGH = 5;
    int currentProfileIndex;
    int HEAD_LINE1_FONT_SIZE = 15;
    int HEAD_LINE2_FONT_SIZE = 20;


    private User user;

    public static Image getDefaultPic() {
        return defaultPic;
    }


    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        //up border
        HBox upBorder = new HBox();
        //give picture constant size so it does not ruin the look
        ivDarkMode.setFitHeight(15);
        ivDarkMode.setFitWidth(15);
        ivBroomIcon.setFitHeight(15);
        ivBroomIcon.setFitWidth(15);
        //add the graphics
        btDarkMode.setGraphic(ivDarkMode);
        btClear.setGraphic(ivBroomIcon);

        /* combine, set spaces between buttons and padding so it hase some distace from its boreders*/
        upBorder.getChildren().addAll(btDarkMode,btClear,lName, tfName, btAdd, btDelete, btLookup,btCommunity);
        upBorder.setSpacing(20);
        upBorder.setPadding(new Insets(5, 0, 5, 0));

        //lift border
        lStatus.setFont(Font.font("Arial", FontWeight.BOLD, HEAD_LINE1_FONT_SIZE));
        VBox vbChangeStatus = new VBox();
        vbChangeStatus.getChildren().addAll(tfChangeStatus, btChangeStatus);
        VBox vbChangePic = new VBox();
        vbChangePic.getChildren().addAll(tfChangePicture, btChangePicture);
        VBox vbAddFriend = new VBox();
        vbAddFriend.getChildren().addAll(tfAddFriend, btAddFriend,btDeleteFriend);
        VBox vbSend = new VBox();
        vbSend.getChildren().addAll(tfSend, btSend);
        //1.sub-panes
        VBox[] vbArray = {vbSend,vbChangeStatus, vbChangePic, vbAddFriend};

        for (VBox e : vbArray) {
            e.setSpacing(10);
            e.setAlignment(Pos.CENTER);
        }

        //2.up border's button
        Button[] hbArray = {btChangeStatus, btChangePicture, btAddFriend,btDeleteFriend,btSend};
        for (Button e : hbArray) {
            e.setPrefWidth(BT_WIDTH);
            e.setPrefHeight(BT_HIGH);
            e.setAlignment(Pos.CENTER);
        }

        //3.lift Border features
        VBox liftBorder = new VBox();
        liftBorder.getChildren().addAll(vbSend,vbChangeStatus, vbChangePic, vbAddFriend);
        liftBorder.setPadding(new Insets(0, 10, 0, 10));
        liftBorder.setSpacing(10);

        //Center pane
        space.setBackground(Background.fill(Color.WHITE));
        space.setPadding(new Insets(10));

        userName.setTextFill(Color.BLUE);
        userName.setFont(Font.font("Arial", FontWeight.BOLD, HEAD_LINE2_FONT_SIZE));
        //left Center
        VBox vbNameAndPic = new VBox();


        lMassages.setPrefWidth(300);
        lMassages.setMinHeight(34);
        spFriendsList.setFitToWidth(true);
        spMassages.setContent(lMassages);
        spMassages.setBackground(Background.fill(Color.valueOf("#FFFFFF")));
        lMassages.setStyle("-fx-border-color: #FFFFFF;-fx-background-color: #FFFFFF;");
        spMassages.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ivPic.setFitWidth(175);
        ivPic.setFitHeight(175);
        vbNameAndPic.getChildren().addAll(userName, ivPic, lStatus,spMassages);

        //Right Center
        vbFriends.setPadding(new Insets(20));
        vbFriends.setAlignment(Pos.TOP_LEFT);
        vbFriends.getChildren().add(lFriends);
        vbFriends.getChildren().add(spFriendsList);
        lFriends.setFont(Font.font("Arial", FontWeight.BOLD, HEAD_LINE1_FONT_SIZE));

        lFriendsList.setPrefWidth(250);
        lFriendsList.setMinHeight(34);
        spFriendsList.setFitToWidth(true);
        spFriendsList.setContent(lFriendsList);
        spFriendsList.setBackground(Background.fill(Color.valueOf("#FFFFFF")));
        lFriendsList.setStyle("-fx-border-color: #FFFFFF;-fx-background-color: #FFFFFF;");
        spFriendsList.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);



        //bottom Center
        BorderPane updatesPane = new BorderPane();

        space.setLeft(vbNameAndPic);
        space.setRight(vbFriends);
        space.setBottom(updatesPane);

        updatesPane.setBottom(lUpdates);
        BorderPane.setAlignment(lUpdates, Pos.BOTTOM_CENTER);

        //Super pane

        pane.setBackground(Background.fill(Color.valueOf("#EBEBEB")));
        pane.setTop(upBorder);
        pane.setLeft(liftBorder);
        pane.setCenter(space);
        upBorder.setAlignment(Pos.CENTER);
        liftBorder.setAlignment(Pos.CENTER);
        //gathering
//      add100Persons();
//      addFriendsToPerson(users.get(0), 50);

        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("FaceLife");
        stage.show();
        //communityPane

        //postBar
        TextField tfPost = new TextField("write something...");
        Button btPost = new Button("post");
        Button picButton = new Button("pic");
        HBox postBarHBox = new HBox();
        HBox.setHgrow(tfPost, javafx.scene.layout.Priority.ALWAYS); // This makes the text field grow
        postBarHBox.getChildren().addAll(tfPost, btPost,picButton);
        // Optional: Set spacing between the elements
        postBarHBox.setSpacing(5);
        postBarHBox.setPadding(new Insets(5));
        communityPane.setBottom(postBarHBox);
        communityPane.setTop(btBackToMain);

        //postToggle
        communityPane.setCenter(spPosts);//make it grow (to do)

        // Event handler for the "Add" button
        EventHandler<ActionEvent> handleAddAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Check if the name field is empty
                if (tfName.getText().isEmpty()) {
                    lUpdates.setText("A profile must have a name");
                } else {
                    // Check if the entered name is available
                    int existingProfileIndex = findTfIndex(tfName);
                    if (existingProfileIndex == -1) {
                        // Create a new Person object and add it to the users list
                        user = new User(tfName.getText());
                        users.add(user);

                        // Set UI elements based on the new profile
                        userName.setText(user.getName());
                        lStatus.setText(user.getStatus());
                        lUpdates.setText("New profile created");
                        lFriends.setText("Friends:");
                        lFriendsList.setText(user.getFriendsList());
                        ivPic.setImage(defaultPic);

                        // Update UI based on dark mode
                        updateUIBasedOnDarkMode(user.isDarkMode());

                        System.out.println(users);
                    } else {
                        // Inform the user that the entered name is not available
                        lUpdates.setText("The name \"" + tfName.getText() + "\" is not available");
                    }
                }
            }
        };
        // Set the onAction for btAdd using an anonymous inner class
        btAdd.setOnAction(handleAddAction);
        // Add an event listener for the Enter key press on tfName using an anonymous inner class
        tfName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleAddAction.handle(new ActionEvent());
                }
            }
        });

        // Event handler for the "Change Status" button
        EventHandler<ActionEvent> handleSetStatusAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Find the index of the current profile
                currentProfileIndex = findCurrentProfileIndex();

                // Check if a profile is selected
                if (currentProfileIndex == -1) {
                    lUpdates.setText("Please select a profile to change status");
                } else {
                    // Update the status of the current profile
                    users.get(currentProfileIndex).setStatus(tfChangeStatus.getText());
                    lStatus.setText(tfChangeStatus.getText());
                    lUpdates.setText("Status Updated to: " + tfChangeStatus.getText());
                }
            }
        };

        btChangeStatus.setOnAction(handleSetStatusAction);
        // Add an event listener for the Enter key press on tfChangeStatus using an anonymous inner class
        tfChangeStatus.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleSetStatusAction.handle(new ActionEvent());
                }
            }
        });

        // Event handler for the "Change Picture" button
        EventHandler<ActionEvent> handleSetPictureAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the index of the current profile
                currentProfileIndex = findCurrentProfileIndex();

                // Get the entered image path from the text field
                String imagePath = tfChangePicture.getText();

                // Check if a profile is selected
                if (currentProfileIndex == -1) {
                    lUpdates.setText("Please select a profile to change picture");
                } else if (!imagePath.isEmpty()) {
                    try {
                        // Attempt to create a new Image object from the entered path
                        Image newImage = new Image(imagePath);

                        // Set the new image to the profile picture view
                        ivPic.setImage(newImage);

                        // Update the profile's profile picture
                        users.get(currentProfileIndex).setProfilePic(newImage);

                        lUpdates.setText("Picture updated");
                    } catch (Exception e) {
                        // Inform the user if there's an error loading the image
                        lUpdates.setText("Error while trying to retrieve photo: " + e.getMessage());
                    }
                } else {
                    // Inform the user that no image has been entered
                    lUpdates.setText("No image has been entered, please enter an image");
                }
            }
        };

        btChangePicture.setOnAction(handleSetPictureAction);
        // Add an event listener for the Enter key press on tfChangePicture using an anonymous inner class
        tfChangePicture.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleSetPictureAction.handle(new ActionEvent());
                }
            }
        });

        // Event handler for the "Lookup" button
        btLookup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the index of the entered profile name in the users list
                int i = findTfIndex(tfName);

                // Check if the profile with the entered name exists
                if (i == -1) {
                    lUpdates.setText("A profile with the name \"" + tfName.getText() + "\" does not exist");
                } else {
                    // Retrieve the Person object for the found index
                    user = users.get(i);
                    updateUIto(user);

                    // Update UI elements with information from the retrieved profile

                    // Display massages associated with the retrieved profile
                    lMassages.setText(user.getMassage());
                }
            }
        });

        // Event handler for the "Delete" button
        btDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the index of the entered profile name in the users list
                int indexToDelete = findTfIndex(tfName);

                // Check if the profile with the entered name exists
                if (indexToDelete == -1) {
                    lUpdates.setText("No profile with the name " + tfName.getText() + " found");
                } else {
                    // Retrieve the profile to delete from the users list
                    User profileToDelete = users.get(indexToDelete);

                    // Retrieve the current profile
                    User currentProfile = users.get(findCurrentProfileIndex());

                    // Remove the profile to delete from all friend lists
                    for (User user : users) {
                        user.getFriends().remove(profileToDelete);
                    }

                    // Check if the profile to delete is the current profile
                    if (indexToDelete == findCurrentProfileIndex()) {
                        // Clear UI elements related to the current profile
                        userName.setText("");
                        lStatus.setText("");
                        lFriends.setText("");
                        lFriendsList.setText("");
                        ivPic.setImage(null);
                        updateUIBasedOnDarkMode(false);
                        lMassages.setText("");
                    }

                    // Update the friends list of the current profile
                    lFriendsList.setText(currentProfile.getFriendsList());

                    // Remove the profile to delete from the users list
                    users.remove(indexToDelete);

                    // Inform the user that the profile has been deleted
                    lUpdates.setText("Profile " + tfName.getText() + " deleted");
                }
            }
        });

        // Event handler for the "Add Friend" button

        EventHandler<ActionEvent> handleAddFriendAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the index of the current profile
                currentProfileIndex = findCurrentProfileIndex();
                // Find the index of the entered friend's profile name in the users list
                int friendIndex = findTfIndex(tfAddFriend);
                // Check if a profile is currently selected
                if(currentProfileIndex==-1){
                    lUpdates.setText("Please select a profile to add a friend");
                }
                else if(friendIndex==-1){
                    // Check if the entered friend's profile name exists
                    String friendName = tfAddFriend.getText();
                    lUpdates.setText("A profile with the name \"" + friendName + "\" dose not exist");
                } else if(currentProfileIndex == friendIndex) {
                    // Check if the user is trying to add themselves as a friend
                    lUpdates.setText("You cannot add yourself as a friend");
                } else {
                    // Retrieve the friend's profile from the users list
                    User friendProfile = users.get(friendIndex);
                    // Retrieve the current profile
                    User currentProfile = users.get(currentProfileIndex);
                    // Check if the friend is already in the current profile's friends list
                    if (currentProfile.getFriends().contains(friendProfile)) {
                        lUpdates.setText(friendProfile.getName() + " is already a friend");
                    } else {
                        // Add the friend to both profiles' friends lists
                        friendProfile.getFriends().add(currentProfile);
                        currentProfile.getFriends().add(friendProfile);
                        // Update the friends list in the UI
                        lFriendsList.setText(currentProfile.getFriendsList());
                        // Inform the user that the friend has been added
                        lUpdates.setText(friendProfile.getName() + " is added as a friend");
                    }
                }
            }
        };
        btAddFriend.setOnAction(handleAddFriendAction);
        // Add an event listener for the Enter key press on tfAddFriend using an anonymous inner class
        tfAddFriend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleAddFriendAction.handle(new ActionEvent());
                }
            }
        });

        // Event handler for the "Delete Friend" button
        btDeleteFriend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the index of the current profile
                currentProfileIndex = findCurrentProfileIndex();

                // Find the index of the entered friend's profile name in the users list
                int friendIndex = findTfIndex(tfAddFriend);

                // Check if a profile is currently selected
                if (currentProfileIndex == -1) {
                    lUpdates.setText("Please select a profile to delete a friend");
                } else if (friendIndex == -1) {
                    // Check if the entered friend's profile name exists
                    String friendName = tfAddFriend.getText();
                    lUpdates.setText("A profile with the name \"" + friendName + "\" does not exist");
                } else {
                    // Retrieve the friend's profile from the users list
                    User friendProfile = users.get(friendIndex);

                    // Retrieve the current profile
                    User currentProfile = users.get(currentProfileIndex);

                    // Check if the friend is not in the current profile's friends list
                    if (!currentProfile.getFriends().contains(friendProfile)) {
                        lUpdates.setText(friendProfile.getName() + " is not a friend");
                    } else {
                        // Remove the friend from both profiles' friends lists
                        friendProfile.getFriends().remove(currentProfile);
                        currentProfile.getFriends().remove(friendProfile);

                        // Update the friends list in the UI
                        lFriendsList.setText(currentProfile.getFriendsList());

                        // Inform the user that the friend has been deleted
                        lUpdates.setText(friendProfile.getName() + " has been deleted from your friends list");
                    }
                }
            }
        });


        // Event handler for the "Dark Mode" button
        btDarkMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the index of the current profile
                int index = findCurrentProfileIndex();

                // Check if a profile is currently selected
                if (index == -1) {
                    // If no profile is selected, update the UI based on the default dark mode state
                    updateUIBasedOnDarkMode(!defIsDarkMode);
                } else {
                    // If a profile is selected, toggle the dark mode for that profile
                    User currentProfile = users.get(index);
                    if ((!currentProfile.isDarkMode()) && (currentProfile.getProfilePic().equals(defaultPic)))
                        currentProfile.setProfilePic(darkDefaultPic);
                    else if ((currentProfile.isDarkMode()) && (currentProfile.getProfilePic().equals(darkDefaultPic))) {
                        currentProfile.setProfilePic(defaultPic);
                    }
                    ivPic.setImage(currentProfile.getProfilePic());
                    currentProfile.setDarkMode(!currentProfile.isDarkMode());

                    // Update the UI based on the current profile's dark mode state
                    updateUIBasedOnDarkMode(currentProfile.isDarkMode());
                }

                // Toggle the default dark mode state for future profiles
                defIsDarkMode = !defIsDarkMode;
            }
        });

        // Event handler for the "Clear" button
        btClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Clear text fields
                clearTextFields();

                // Display a message in the updates label
                lUpdates.setText("Text fields cleared");

                // Clear massages and update the profile's massage
                lMassages.setText("");
                users.get(findCurrentProfileIndex()).setMassage("");
            }
        });

        // Event handler for the "Send" button
        EventHandler<ActionEvent> handleSendMassageAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Find the current profile index
                currentProfileIndex = findCurrentProfileIndex();
                // Find the index of the profile to send the massage to
                int profileToSendToIndex = findTfIndex(tfName);
                // Check conditions for sending a massage
                if(profileToSendToIndex==-1){
                    // Display an error message if the profile does not exist
                    lUpdates.setText("A profile with the name \"" + tfName.getText() + "\" dose not exist");
                }else if(currentProfileIndex==-1){
                    // Display an error message if no profile is selected
                    lUpdates.setText("Please select a profile to send a massage");
                }else if(currentProfileIndex==profileToSendToIndex){
                    // Display an error message if trying to send a massage to oneself
                    lUpdates.setText("you not send a massage to yourself");
                }else {
                    // Get the profiles involved in sending the massage
                    User profileToSentTo = users.get(profileToSendToIndex);
                    User currentProfile = users.get(currentProfileIndex);
                    // Check if the receiver is in the sender's friend list
                    if(currentProfile.getFriends().contains(profileToSentTo)){
                        // Update the massages and display a success message
                        lUpdates.setText("massage sent");
                        profileToSentTo.updateMassage(tfSend.getText(),currentProfile);
                        currentProfile.updateMassage(tfSend.getText(),"You");
                        lMassages.setText(currentProfile.getMassage());
                    }else {
                        // Display an error message if the receiver is not in the friend list
                        lUpdates.setText(tfName.getText() + " is not in your friend list, you can't send massages");
                    }
                }
            }
        };
        btSend.setOnAction(handleSendMassageAction);
        // Add an event listener for the Enter key press on tfsend using an anonymous inner class
        tfSend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handleSendMassageAction.handle(new ActionEvent());
                }
            }
        });
        btCommunity.setOnAction(new ChangeSceneHandler(communityScene,btCommunity));
        EventHandler<ActionEvent> handlePostAction = new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                post(users.get(findCurrentProfileIndex()).getName() + ":",tfPost.getText());
            }
        };
        btPost.setOnAction(handlePostAction);
        tfPost.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    handlePostAction.handle(new ActionEvent());
                }
            }
        });
        btBackToMain.setOnAction(new ChangeSceneHandler(scene,btBackToMain));
        class handleBackAction implements  EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        }
    }
//push
    class ChangeSceneHandler implements EventHandler<ActionEvent>{
        Scene scene;
        Button button;
        ChangeSceneHandler(Scene scene,Button button){
            this.scene = scene;
            this.button = button;
        }
        @Override
        public void handle(ActionEvent e){
            if(e.getSource()==button){
                stage.setScene(scene);
                update(scene);
            }

        }
    }
    // Method to find the index of the currently displayed profile in the users list
    private int findCurrentProfileIndex() {
        // Iterate through the users list
        for (int i = 0; i < users.size(); i++) {
            // Check if the name of the currently displayed profile matches the name of the person at index i
            if (userName.getText().equals(users.get(i).getName())) {
                // Return the index if a match is found
                return i;
            }
        }
        // Return -1 if no match is found
        return -1;
    }
    // Method to find the index of a person with a name matching the text of a given TextField
    private int findTfIndex(TextField textField) {
        // Iterate through the users list
        for (int i = 0; i < users.size(); i++) {
            // Check if the text of the TextField matches the name of the person at index i
            if (textField.getText().equals(users.get(i).getName())) {
                // Return the index if a match is found
                return i;
            }
        }
        // Return -1 if no match is found
        return -1;
    }

    // Method to update UI based on dark mode
    private void updateUIBasedOnDarkMode(boolean isDarkMode) {

        // Check if dark mode is enabled
        if (isDarkMode) {
            // Update UI for dark mode

            // Set background colors and styles for dark mode
            pane.setBackground(Background.fill(Color.valueOf("#15202B")));
            space.setBackground(Background.fill(Color.valueOf("#273340")));
            spFriendsList.setBackground(Background.fill(Color.valueOf("#273340")));
            spFriendsList.lookup(".scroll-bar:vertical").setStyle("-fx-base: #273340;");
            lFriendsList.setStyle("-fx-border-color: #273340;-fx-background-color: #273340;");
            spMassages.setBackground(Background.fill(Color.valueOf("#273340")));
            spMassages.lookup(".scroll-bar:horizontal").setStyle("-fx-base: #273340;");
            lMassages.setStyle("-fx-border-color: #273340;-fx-background-color: #273340;");

            // Set images for dark mode
            ivDarkMode.setImage(sunIcon);
            ivBroomIcon.setImage(darkBroomIcon);

            // Set text and foreground colors for labels, text fields, and buttons
            for (Label label : labels) {
                label.setTextFill(Color.WHITE);
            }
            for (TextField textField : textFields) {
                textField.setStyle("-fx-control-inner-background: #273340; -fx-text-fill: white;");
            }
            for (Button button : buttons) {
                button.setStyle("-fx-base: #273340; -fx-text-fill: white;");
            }
            userName.setTextFill(Color.valueOf("#1D9BF0"));
        } else {
            // Update UI for light mode

            // Set background colors and styles for light mode
            pane.setBackground(Background.fill(Color.valueOf("#EBEBEB")));
            space.setBackground(Background.fill(Color.valueOf("#FFFFFF")));
            spFriendsList.setBackground(Background.fill(Color.valueOf("#FFFFFF")));
            spFriendsList.lookup(".scroll-bar:vertical").setStyle("-fx-base: #FFFFFF;");
            lFriendsList.setStyle("-fx-border-color: #FFFFFF;-fx-background-color: #FFFFFF;");
            spMassages.setBackground(Background.fill(Color.valueOf("#FFFFFF")));
            spMassages.lookup(".scroll-bar:horizontal").setStyle("-fx-base: #FFFFFF;");
            lMassages.setStyle("-fx-border-color: #FFFFFF;-fx-background-color: #FFFFFF;");

            // Set images for light mode
            ivDarkMode.setImage(moonIcon);
            ivBroomIcon.setImage(broomIcon);

            // Set text and foreground colors for labels, text fields, and buttons
            for (Label label : labels) {
                label.setTextFill(Color.BLACK);
            }
            for (TextField textField : textFields) {
                textField.setStyle("-fx-control-inner-background: #FFFFFF;");
            }
            for (Button button : buttons) {
                button.setStyle("-fx-base: #EBEBEB;");
            }
            userName.setTextFill(Color.BLUE);
        }

        // Add any additional modifications for UI elements based on dark mode
    }
    //goes over every textField clear it all
    private void clearTextFields() {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
    private void addFriendsToPerson(User selectedUser, int numberOfFriendsToAdd) {
        if (users.size() >= numberOfFriendsToAdd) {
            for (int i = 0; i < numberOfFriendsToAdd; i++) {
                selectedUser.getFriends().add(users.get(i));
            }
        }
    }
    private void add100Persons() {
        for (int i = 1; i <= 100; i++) {
            users.add(new User(Integer.toString(i)));
        }
    }
    private void update(Scene scene){
        if (scene == communityScene) updateCommunity();
        else new Exception("scene does not exist");


    }
    private void updateUIto(User user){
        userName.setText(user.getName());
        lStatus.setText(user.getStatus());
        ivPic.setImage(user.getProfilePic());
        lFriendsList.setText(user.getFriendsList());
        lFriends.setText("Friends:");
        lUpdates.setText("Displaying " + tfName.getText());

        // Update UI based on dark mode setting of the retrieved profile
        updateUIBasedOnDarkMode(user.isDarkMode());
    }
    private void updateCommunity() {
        VBox vPosts = new VBox();
        for(Post post: posts){
            vPosts.getChildren().add(post.getPane());
        }
        spPosts.setContent(vPosts);
    }
    private void post(String userName,String text,Image image){
        Post post=new Post(userName, text, image);
        posts.add(post);
        updateCommunity();
    }
    private void post(String userName,String text){
        Post post=new Post(userName, text);
        posts.add(post);
        updateCommunity();
        System.out.println(posts);
    }



    public static void main(String[] args) {
        launch(args);
    }

}