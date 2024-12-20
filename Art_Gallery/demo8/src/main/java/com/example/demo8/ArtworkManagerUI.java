package com.example.demo8;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArtworkManagerUI extends VBox {
    private List<Artwork> artworks;
    private VBox artworkList;
    private TextField searchField;
    private Button searchButton;
    private Button clearButton;

    public ArtworkManagerUI() {
        artworks = new ArrayList<>();
        artworks.add(new Artwork("Artwork 1", "A beautiful painting", "Wassily Kandinsky", "$1000", "Artwork_1.jpg", 15));
        artworks.add(new Artwork("Artwork 2", "A stunning sculpture", "Pablo Picasso", "$2000", "Artwork_2.jpg", 25));
        artworks.add(new Artwork("Artwork 3", "An abstract piece", "Wassily Kandinsky", "$1500", "Artwork_3.png", 22));
        artworks.add(new Artwork("Artwork 4", "A realistic Painting", "Gustave Courbet", "$3000", "Artwork_4.jpg", 63));
        artworks.add(new Artwork("Artwork 5", "Whistler's Mother", "James Mcneill Whistler", "$25", "Artwork_5.png", 56));
        artworks.add(new Artwork("Artwork 6", "Museo Del Prado in Madrid", "Diego Velazquez", "$99", "Artwork_6.png", 44));
        artworks.add(new Artwork("Artwork 7", "The Young Women of Avignon", "Pablo Picasso", "$950", "Artwork_7.png", 85));
        artworks.add(new Artwork("Artwork 8", "Reproduction Oil Painting", "John William Waterhouse", "$60", "Artwork_8.png", 80));
        artworks.add(new Artwork("Artwork 9", "Laughing Cavalier", "Frans Hals", "$300", "Artwork_9.png", 46));
        artworks.add(new Artwork("Artwork 10", "Van Gogh Self Portrait", "Vincent Van Gogh", "$700", "Artwork_10.png", 36));
        artworks.add(new Artwork("Artwork 11", "The Fifer", "Edouard Manet", "$600", "Artwork_11.png", 5));
        artworks.add(new Artwork("Artwork 12", "View of Toledo", "El Greco", "$2000", "Artwork_12.png", 41));
        artworks.add(new Artwork("Artwork 13", "Napoleon Crossing The Alps", "Jacques Louis David", "$7000", "Artwork_13.png", 75));
        artworks.add(new Artwork("Artwork 14", "Cafe Terrace At Night", "Vincent Van Gogh", "$400", "Artwork_14.png", 50));
        artworks.add(new Artwork("Artwork 15", "Barge Haulers On The Volga", "Ilya Repin", "$300", "Artwork_15.png", 23));
        artworks.add(new Artwork("Artwork 16", "Girl With The Pearl Earring", "Johannes Vermeer", "$200", "Artwork_16.png", 30));
        artworks.add(new Artwork("Artwork 17", "Irises", "Vincent Van Gogh", "$2000", "Artwork_17.png", 13));

        BorderPane root = new BorderPane();
        HBox topNav = new HBox(20);
        topNav.setPadding(new Insets(10));
        topNav.setStyle("-fx-background-color: Pink;");
        topNav.setAlignment(Pos.CENTER);
        Label greetingLabel = new Label("Welcome to Art Gallery");
        greetingLabel.setFont(Font.font("Algerian", 40));
        topNav.getChildren().addAll(greetingLabel);
        root.setTop(topNav);

        VBox header = new VBox(10);
        header.setPadding(new Insets(10));

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        searchField = new TextField();
        searchField.setPromptText("Search by title or artist...");
        searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchArtworks());

        clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearSearch());

        searchBox.getChildren().addAll(searchField, searchButton, clearButton);

        Button addNewArtworkButton = new Button("Add New Artwork +");
        header.getChildren().addAll(searchBox, addNewArtworkButton);

        artworkList = new VBox(10);
        artworkList.setPadding(new Insets(10));
        for (int i = 0; i < artworks.size(); i++) {
            artworkList.getChildren().add(createArtworkItem(artworks.get(i), i));
        }

        ScrollPane scrollPane = new ScrollPane(artworkList);
        scrollPane.setFitToWidth(true);

        VBox centerContent = new VBox(10);
        centerContent.getChildren().addAll(header, scrollPane);
        root.setCenter(centerContent);

        Label footerLabel = new Label("© All rights reserved");
        footerLabel.setPadding(new Insets(10));
        footerLabel.setAlignment(Pos.CENTER);
        footerLabel.setStyle("-fx-background-color: Pink;");
        root.setBottom(footerLabel);

        Scene scene = new Scene(root, 800, 600);
        Stage artworkStage = new Stage();
        artworkStage.setTitle("Artwork Manager");
        artworkStage.setScene(scene);
        artworkStage.show();

        addNewArtworkButton.setOnAction(e -> showAddArtworkForm());
    }

    private void showAddArtworkForm() {
        Stage addArtworkStage = new Stage();
        addArtworkStage.setTitle("Add New Artwork");

        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(20));
        formLayout.setStyle("-fx-background-color: Pink;");
        formLayout.setAlignment(Pos.CENTER);

        TextField titleField = new TextField();
        titleField.setPromptText("Artwork Title");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Artwork Description");

        TextField artistField = new TextField();
        artistField.setPromptText("Artist Name");

        TextField priceField = new TextField();
        priceField.setPromptText("Artwork Price");

        TextField imageUrlField = new TextField();
        imageUrlField.setPromptText("Image URL");

        TextField stockField = new TextField();
        stockField.setPromptText("Stock Available");

        Button addButton = new Button("Add Artwork");
        Button cancelButton = new Button("Cancel");

        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String artist = artistField.getText();
            String price = priceField.getText();
            String imageUrl = imageUrlField.getText();
            String stockAvailable = stockField.getText();

            if (title.isEmpty() || description.isEmpty() || artist.isEmpty() || price.isEmpty() || imageUrl.isEmpty() || stockAvailable.isEmpty()) {
                showAlert("Input Error", "Please fill in all fields.");
            } else {
                try {
                    int stock = Integer.parseInt(stockAvailable);

                    Artwork newArtwork = new Artwork(title, description, artist, price, imageUrl, stock);
                    artworks.add(newArtwork);
                    addArtworkToGallery(newArtwork);

                    addArtworkStage.close();
                    showAlert("Artwork Added", "Artwork '" + title + "' has been added successfully.");
                } catch (NumberFormatException ex) {
                    showAlert("Invalid Stock", "Please enter a valid number for stock.");
                }
            }
        });

        cancelButton.setOnAction(e -> addArtworkStage.close());

        formLayout.getChildren().addAll(titleField, descriptionField, artistField, priceField, imageUrlField, stockField, addButton, cancelButton);

        Scene scene = new Scene(formLayout, 400, 400);
        addArtworkStage.setScene(scene);
        addArtworkStage.show();
    }

    private void addArtworkToGallery(Artwork artwork) {
        artworkList.getChildren().add(createArtworkItem(artwork, artworks.indexOf(artwork)));
    }

    private void searchArtworks() {
        String query = searchField.getText().toLowerCase();
        List<Artwork> filteredArtworks = new ArrayList<>();

        for (Artwork artwork : artworks) {
            if (artwork.getTitle().toLowerCase().contains(query) || artwork.getArtist().toLowerCase().contains(query)) {
                filteredArtworks.add(artwork);
            }
        }

        refreshGallery(filteredArtworks);
    }

    private void clearSearch() {
        searchField.clear();
        refreshGallery(artworks);
    }

    private void refreshGallery(List<Artwork> artworksToShow) {
        artworkList.getChildren().clear();
        for (Artwork artwork : artworksToShow) {
            artworkList.getChildren().add(createArtworkItem(artwork, artworks.indexOf(artwork)));
        }
    }

    private HBox createArtworkItem(Artwork artwork, int index) {
        HBox artworkItem = new HBox(10);
        artworkItem.setPadding(new Insets(10));
        artworkItem.setStyle("-fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #f9f9f9;");

        ImageView artworkImage;
        try {
            artworkImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(artwork.getImageUrl())).toExternalForm()));
            artworkImage.setFitWidth(100);
            artworkImage.setFitHeight(100);
        } catch (NullPointerException e) {
            artworkImage = new ImageView(new Image(getClass().getResource("/path/to/placeholder.png").toExternalForm()));
            artworkImage.setFitWidth(100);
            artworkImage.setFitHeight(100);
        }

        VBox details = new VBox(5);
        Label titleLabel = new Label(artwork.getTitle());
        Label descriptionLabel = new Label(artwork.getDescription());
        Label artistLabel = new Label("Artist's Name: " + artwork.getArtist());
        Label priceLabel = new Label("Artwork Price: " + artwork.getPrice());
        Label stockLabel = new Label("Stock Available: " + artwork.getStockAvailable());

        details.getChildren().addAll(titleLabel, descriptionLabel, artistLabel, priceLabel, stockLabel);

        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        editButton.setOnAction(e -> editArtwork(index));

        deleteButton.setOnAction(e -> deleteArtwork(index));

        buttonBox.getChildren().addAll(editButton, deleteButton);

        artworkItem.getChildren().addAll(artworkImage, details, buttonBox);
        return artworkItem;
    }

    private void editArtwork(int index) {
        Artwork artworkToEdit = artworks.get(index);

        Stage editArtworkStage = new Stage();
        editArtworkStage.setTitle("Edit Artwork");

        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(20));
        formLayout.setStyle("-fx-background-color: Pink;");
        formLayout.setAlignment(Pos.CENTER);

        TextField titleField = new TextField(artworkToEdit.getTitle());
        TextField descriptionField = new TextField(artworkToEdit.getDescription());
        TextField artistField = new TextField(artworkToEdit.getArtist());
        TextField priceField = new TextField(artworkToEdit.getPrice());
        TextField imageUrlField = new TextField(artworkToEdit.getImageUrl());
        TextField stockField = new TextField(String.valueOf(artworkToEdit.getStockAvailable()));

        Button saveButton = new Button("Save Changes");
        Button cancelButton = new Button("Cancel");

        saveButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String artist = artistField.getText();
            String price = priceField.getText();
            String imageUrl = imageUrlField.getText();
            String stockAvailable = stockField.getText();

            if (title.isEmpty() || description.isEmpty() || artist.isEmpty() || price.isEmpty() || imageUrl.isEmpty() || stockAvailable.isEmpty()) {
                showAlert("Input Error", "Please fill in all fields.");
            } else {
                try {
                    int stock = Integer.parseInt(stockAvailable);

                    artworkToEdit.setTitle(title);
                    artworkToEdit.setDescription(description);
                    artworkToEdit.setArtist(artist);
                    artworkToEdit.setPrice(price);
                    artworkToEdit.setImageUrl(imageUrl);
                    artworkToEdit.setStockAvailable(stock);
                    editArtworkStage.close();
                    refreshGallery(artworks);
                    showAlert("Artwork Updated", "Artwork '" + title + "' has been updated successfully.");
                } catch (NumberFormatException ex) {
                    showAlert("Invalid Stock", "Please enter a valid number for stock.");
                }
            }
        });

        cancelButton.setOnAction(e -> editArtworkStage.close());

        formLayout.getChildren().addAll(titleField, descriptionField, artistField, priceField, imageUrlField, stockField, saveButton, cancelButton);

        Scene scene = new Scene(formLayout, 400, 400);
        editArtworkStage.setScene(scene);
        editArtworkStage.show();
    }

    private void deleteArtwork(int index) {
        Artwork artworkToDelete = artworks.get(index);

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText("Are you sure you want to delete this artwork?");
        confirmationAlert.setContentText("Title: " + artworkToDelete.getTitle());

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        confirmationAlert.getButtonTypes().setAll(yesButton, noButton);

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                artworks.remove(artworkToDelete);
                refreshGallery(artworks);
                showAlert("Artwork Deleted", "Artwork '" + artworkToDelete.getTitle() + "' has been deleted.");
            }
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}