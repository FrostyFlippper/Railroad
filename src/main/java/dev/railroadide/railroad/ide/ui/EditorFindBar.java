package dev.railroadide.railroad.ide.ui;

import dev.railroadide.railroad.ui.RRButton;
import dev.railroadide.railroad.ui.RRHBox;
import dev.railroadide.railroad.ui.RRTextField;
import javafx.scene.layout.Region;
import lombok.Getter;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;

public class EditorFindBar extends RRHBox {
    @Getter
    private final RRTextField searchField;
    @Getter
    private final RRButton closeButton;

    public EditorFindBar(){
        this.searchField = new RRTextField("railroad.ide.find_bar.search_field.placeholder");
        this.closeButton = new RRButton("", FontAwesomeSolid.TIMES);
        this.getChildren().addAll(searchField, closeButton);
        this.setMaxWidth(Region.USE_PREF_SIZE);
        this.setMaxHeight(Region.USE_PREF_SIZE);
    }
}
