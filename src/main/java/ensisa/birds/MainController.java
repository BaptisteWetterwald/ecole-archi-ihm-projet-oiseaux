package ensisa.birds;

import ensisa.birds.model.Bird;
import ensisa.birds.model.BirdRepository;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainController {
    private BirdRepository repository;

    @FXML
    private Label commonNameLabel;
    @FXML
    private Label latinNameLabel;
    @FXML
    private Label familyLabel;
    @FXML
    private Label genusLabel;
    @FXML
    private Label specieLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView birdImageView;
    @FXML
    private ListView<Bird> birdListView;
    @FXML
    private VBox birdView;
    private final ObjectProperty<Bird> currentBird;

    public MainController() {
        repository = new BirdRepository();
        repository.Load();
        currentBird = new SimpleObjectProperty<>(repository.birds.get(0));
    }
    public void initialize() {
        birdListView.setCellFactory(new BirdCellFactory());
        birdListView.setItems(repository.birds);
        currentBirdProperty().bind(birdListView.getSelectionModel().selectedItemProperty());
        currentBirdProperty().addListener((observable, oldBird, newBird) -> {
            // Pour une liaison unidirectionnelle, il n'est pas nécessaire de supprimer
            // l'ancienne liaison avant d'en créer une nouvelle
            if (newBird != null)
                bind(newBird);
        });
        birdView.visibleProperty().bind(Bindings.createBooleanBinding(() -> getCurrentBird() != null, currentBirdProperty()) );
    }

    public Bird getCurrentBird() {
        return currentBird.get();
    }

    public ObjectProperty<Bird> currentBirdProperty() {
        return currentBird;
    }

    public void setCurrentBird(Bird currentBird) {
        this.currentBird.set(currentBird);
    }

    public void bind(Bird bird) {
        commonNameLabel.textProperty().bind(bird.commonNameProperty());
        latinNameLabel.textProperty().bind(bird.latinNameProperty());
        familyLabel.textProperty().bind(bird.familyProperty());
        genusLabel.textProperty().bind(bird.genusProperty());
        specieLabel.textProperty().bind(bird.specieProperty());
        descriptionLabel.textProperty().bind(bird.descriptionProperty());
        birdImageView.imageProperty().bind(bird.imageProperty());
    }



}