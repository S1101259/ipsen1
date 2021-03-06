package commonFunctions;

import Controllers.ClientManager;
import Views.SettingsScene;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Label;

/**
 * Deze class extend de standaard Label class uit javafx om spraakondersteuning en geluidseffecten toe te voegen
 */
public class SmartLabel extends Label {

	// Stem type van speech (Kevin)
	private static final String VOICENAME="kevin16";

	//tts libary voice settings
	private Voice voice;
	private VoiceManager vm = VoiceManager.getInstance();


	// Overload constructs
	public SmartLabel(String text) {
		super(text);
		voice = vm.getVoice(VOICENAME);

		if(voice != null){
			voice.allocate();
		}

		// Onhover talk text
		this.setOnMouseEntered(e -> {
			if(ClientManager.debug == true)
				System.out.println("HOVER : " + this.getText());

			talk(this.getText());
		});

		initialize();
	}

	public SmartLabel() {
		voice = vm.getVoice(VOICENAME);

		if(voice != null){
			voice.allocate();
		}

		// Onhover talk text
		this.setOnMouseEntered(e -> {
			if(ClientManager.debug == true)
				System.out.println("HOVER : " + this.getText());

			talk(this.getText());
		});
		initialize();
	}

	/**
	 * Lees label text voor (speech)
	 * @param text
	 * Geef de tekst mee in de vorm van een String die uitgesproken zal gaan worden
	 */
	public void talk(String text){
		if(SettingsScene.optieSpreken) {
			try {
				voice.speak(text);
			} catch (Exception e) {
				System.out.println("FOUT: " + e);
			}
		}
	}

	// Ignore, dit is allemaal van label
	private void initialize() {
		getStyleClass().setAll("label");
		setAccessibleRole(AccessibleRole.TEXT);
		// Labels are not focus traversable, unlike most other UI Controls.
		// focusTraversable is styleable through css. Calling setFocusTraversable
		// makes it look to css like the user set the value and css will not
		// override. Initializing focusTraversable by calling set on the
		// CssMetaData ensures that css will be able to override the value.
		((StyleableProperty<Boolean>)(WritableValue<Boolean>)focusTraversableProperty()).applyStyle(null, Boolean.FALSE);
	}

}
