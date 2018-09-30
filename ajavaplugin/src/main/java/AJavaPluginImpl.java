import org.codeoverflow.chatoverflow.api.plugin.PluginImpl;
import org.codeoverflow.chatoverflow.api.plugin.PluginManager;

public class AJavaPluginImpl extends PluginImpl {

    public AJavaPluginImpl(PluginManager manager) {
        super(manager);
    }

    //Requirement<TwitchChatInput> twitchChat = require.input.twitchChat("twitchChat", "Twitch Chat", true);

    @Override
    public void setup() {
        loopInterval = 100;
        System.out.println("I'm a java plugin!");
    }

    @Override
    public void loop() {
        System.out.println("Loop!");
    }

    @Override
    public void shutdown() {

    }
}
